package Library_management_system.Library_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Library_management_system.Library_management_system.model.BorrowingTransaction;
import Library_management_system.Library_management_system.service.BorrowingTransactionService;

@RestController
@RequestMapping("/api/borrowing")
public class BorrowingTransactionController {
    
    @Autowired
    private BorrowingTransactionService borrowingTransactionService;
    
    // Get all transactions
    @GetMapping
    public List<BorrowingTransaction> getAllTransactions() {
        return borrowingTransactionService.getAllTransactions();
    }
    
    // Get transaction by ID
    @GetMapping("/{id}")
    public BorrowingTransaction getTransactionById(@PathVariable Integer id) {
        return borrowingTransactionService.getTransactionById(id);
    }
    
    // Get transactions by member
    @GetMapping("/member/{memberId}")
    public List<BorrowingTransaction> getTransactionsByMember(@PathVariable Integer memberId) {
        return borrowingTransactionService.getTransactionsByMember(memberId);
    }
    
    // Get transactions by book
    @GetMapping("/book/{bookId}")
    public List<BorrowingTransaction> getTransactionsByBook(@PathVariable Integer bookId) {
        return borrowingTransactionService.getTransactionsByBook(bookId);
    }
    
    // Get transactions by status
    @GetMapping("/status/{status}")
    public List<BorrowingTransaction> getTransactionsByStatus(@PathVariable String status) {
        BorrowingTransaction.TransactionStatus transactionStatus = 
            BorrowingTransaction.TransactionStatus.valueOf(status.toUpperCase());
        return borrowingTransactionService.getTransactionsByStatus(transactionStatus);
    }
    
    // Create transaction
    @PostMapping
    public BorrowingTransaction createTransaction(@RequestBody BorrowingTransaction transaction) {
        return borrowingTransactionService.saveTransaction(transaction);
    }
    
    // Update transaction
    @PutMapping("/{id}")
    public BorrowingTransaction updateTransaction(@PathVariable Integer id, @RequestBody BorrowingTransaction transactionDetails) {
        return borrowingTransactionService.updateTransaction(id, transactionDetails);
    }
    
    // Delete transaction
    @DeleteMapping("/{id}")
    public boolean deleteTransaction(@PathVariable Integer id) {
        return borrowingTransactionService.deleteTransaction(id);
    }
}