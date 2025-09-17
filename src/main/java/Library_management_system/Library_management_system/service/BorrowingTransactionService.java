package Library_management_system.Library_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Library_management_system.Library_management_system.model.BorrowingTransaction;
import Library_management_system.Library_management_system.repository.BorrowingTransactionRepository;

@Service
public class BorrowingTransactionService {
    
    @Autowired
    private BorrowingTransactionRepository borrowingTransactionRepository;
    
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
        return borrowingTransactionRepository.save(transaction);
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
        
        return borrowingTransactionRepository.save(transaction);
    }
    
    // Delete transaction
    public boolean deleteTransaction(Integer id) {
        BorrowingTransaction transaction = getTransactionById(id);
        if (transaction == null) {
            return false;
        }
        
        borrowingTransactionRepository.delete(transaction);
        return true;
    }
}