package Library_management_system.Library_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Library_management_system.Library_management_system.model.BorrowingTransaction;
import Library_management_system.Library_management_system.model.User;
import Library_management_system.Library_management_system.repository.BorrowingTransactionRepository;

@Service
public class BorrowingTransactionService {
    
    @Autowired
    private BorrowingTransactionRepository borrowingTransactionRepository;
    
    @Autowired
    private UserActivityLogService userActivityLogService;
    
    // Method to get current user from Security Context
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }
    
    // Get all transactions
    public List<BorrowingTransaction> getAllTransactions() {
        return borrowingTransactionRepository.findAll();
    }
    
    // Get transaction by ID
    public BorrowingTransaction getTransactionById(Integer id) {
        return borrowingTransactionRepository.findById(id).orElse(null);
    }
    
    // Get transactions by member
    public List<BorrowingTransaction> getTransactionsByMember(Integer memberId) {
        return borrowingTransactionRepository.findByMemberId(memberId);
    }
    
    // Get transactions by book
    public List<BorrowingTransaction> getTransactionsByBook(Integer bookId) {
        return borrowingTransactionRepository.findByBookId(bookId);
    }
    
    // Get transactions by status
    public List<BorrowingTransaction> getTransactionsByStatus(BorrowingTransaction.TransactionStatus status) {
        return borrowingTransactionRepository.findByStatus(status);
    }
    
    // Save transaction
    public BorrowingTransaction saveTransaction(BorrowingTransaction transaction) {
        BorrowingTransaction savedTransaction = borrowingTransactionRepository.save(transaction);
        
        // Log activity with current user
        User currentUser = getCurrentUser();
        String bookTitle = transaction.getBook() != null ? transaction.getBook().getTitle() : "Unknown Book";
        String memberName = transaction.getMember() != null ? 
            transaction.getMember().getFirstName() + " " + transaction.getMember().getLastName() : "Unknown Member";
        userActivityLogService.logActivity(currentUser, "Book borrowed: " + bookTitle + " by " + memberName);
        
        return savedTransaction;
    }
    
    // Update transaction
    public BorrowingTransaction updateTransaction(Integer id, BorrowingTransaction transactionDetails) {
        BorrowingTransaction transaction = getTransactionById(id);
        if (transaction == null) {
            return null;
        }
        
        transaction.setBook(transactionDetails.getBook());
        transaction.setMember(transactionDetails.getMember());
        transaction.setBorrowedBy(transactionDetails.getBorrowedBy());
        transaction.setBorrowDate(transactionDetails.getBorrowDate());
        transaction.setDueDate(transactionDetails.getDueDate());
        transaction.setReturnDate(transactionDetails.getReturnDate());
        transaction.setStatus(transactionDetails.getStatus());
        
        BorrowingTransaction updatedTransaction = borrowingTransactionRepository.save(transaction);
        
        // Log activity with current user
        User currentUser = getCurrentUser();
        String bookTitle = transaction.getBook() != null ? transaction.getBook().getTitle() : "Unknown Book";
        userActivityLogService.logActivity(currentUser, "Updated borrowing transaction for book: " + bookTitle);
        
        return updatedTransaction;
    }
    
    // Delete transaction
    public boolean deleteTransaction(Integer id) {
        BorrowingTransaction transaction = getTransactionById(id);
        if (transaction == null) {
            return false;
        }
        
        String bookTitle = transaction.getBook() != null ? transaction.getBook().getTitle() : "Unknown Book";
        borrowingTransactionRepository.delete(transaction);
        
        // Log activity with current user
        User currentUser = getCurrentUser();
        userActivityLogService.logActivity(currentUser, "Deleted borrowing transaction for book: " + bookTitle);
        
        return true;
    }
}