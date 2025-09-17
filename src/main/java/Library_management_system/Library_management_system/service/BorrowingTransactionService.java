package Library_management_system.Library_management_system.service;

import Library_management_system.Library_management_system.model.Book;
import Library_management_system.Library_management_system.model.BorrowingTransaction;
import Library_management_system.Library_management_system.model.Member;
import Library_management_system.Library_management_system.model.User;
import Library_management_system.Library_management_system.repository.BorrowingTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowingTransactionService {
    
    @Autowired
    private BorrowingTransactionRepository borrowingTransactionRepository;
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private MemberService memberService;
    
    public List<BorrowingTransaction> getAllTransactions() {
        return borrowingTransactionRepository.findAll();
    }
    
    public List<BorrowingTransaction> getTransactionsByMember(Integer memberId) {
        return borrowingTransactionRepository.findByMemberId(memberId);
    }
    
    public List<BorrowingTransaction> getTransactionsByBook(Integer bookId) {
        return borrowingTransactionRepository.findByBookId(bookId);
    }
    
    public List<BorrowingTransaction> getTransactionsByStatus(BorrowingTransaction.TransactionStatus status) {
        return borrowingTransactionRepository.findByStatus(status);
    }
    
    public BorrowingTransaction getTransactionById(Integer id) {
        Optional<BorrowingTransaction> transaction = borrowingTransactionRepository.findById(id);
        if (transaction.isPresent()) {
            return transaction.get();
        }
        return null;
    }
    
    public BorrowingTransaction borrowBook(Integer bookId, Integer memberId, Integer borrowedById, int daysToReturn) {
        Book book = bookService.getBookById(bookId);
        if (book == null) {
            return null; // Book not found
        }
        
        if (book.getAvailableCopies() <= 0) {
            return null; // Book not available
        }
        
        Member member = memberService.getMemberById(memberId);
        if (member == null) {
            return null; // Member not found
        }
        
        // Check if member already has this book borrowed
        List<BorrowingTransaction> activeTransactions = borrowingTransactionRepository
                .findByMemberIdAndStatus(memberId, BorrowingTransaction.TransactionStatus.BORROWED);
        
        for (BorrowingTransaction transaction : activeTransactions) {
            if (transaction.getBook().getId().equals(bookId)) {
                return null; // Member already has this book
            }
        }
        
        // Create borrowing transaction
        BorrowingTransaction transaction = new BorrowingTransaction();
        transaction.setBook(book);
        transaction.setMember(member);
        
        User user = new User();
        user.setId(borrowedById);
        transaction.setBorrowedBy(user);
        
        transaction.setBorrowDate(LocalDateTime.now());
        transaction.setDueDate(LocalDateTime.now().plusDays(daysToReturn));
        transaction.setStatus(BorrowingTransaction.TransactionStatus.BORROWED);
        
        // Update book available copies
        bookService.updateAvailableCopies(bookId, -1);
        
        return borrowingTransactionRepository.save(transaction);
    }
    
    public BorrowingTransaction returnBook(Integer transactionId) {
        BorrowingTransaction transaction = borrowingTransactionRepository.findById(transactionId).orElse(null);
        if (transaction == null) {
            return null;
        }
        
        if (transaction.getStatus() != BorrowingTransaction.TransactionStatus.BORROWED) {
            return null; // Book is not currently borrowed
        }
        
        // Update transaction
        transaction.setReturnDate(LocalDateTime.now());
        transaction.setStatus(BorrowingTransaction.TransactionStatus.RETURNED);
        
        // Update book available copies
        bookService.updateAvailableCopies(transaction.getBook().getId(), 1);
        
        return borrowingTransactionRepository.save(transaction);
    }
    
    public BorrowingTransaction markAsLost(Integer transactionId) {
        BorrowingTransaction transaction = borrowingTransactionRepository.findById(transactionId).orElse(null);
        if (transaction == null) {
            return null;
        }
        
        if (transaction.getStatus() != BorrowingTransaction.TransactionStatus.BORROWED) {
            return null; // Book is not currently borrowed
        }
        
        // Update transaction
        transaction.setStatus(BorrowingTransaction.TransactionStatus.LOST);
        transaction.setReturnDate(LocalDateTime.now());
        
        return borrowingTransactionRepository.save(transaction);
    }
}
