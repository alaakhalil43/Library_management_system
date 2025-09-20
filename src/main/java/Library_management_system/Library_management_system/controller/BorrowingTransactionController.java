package Library_management_system.Library_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    
    // قراءة فقط - STAFF+ يمكنهم القراءة
    @GetMapping
    @PreAuthorize("hasAnyRole('STAFF', 'LIBRARIAN', 'ADMINISTRATOR')")
    public List<BorrowingTransaction> getAllTransactions() {
        return borrowingTransactionService.getAllTransactions();
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('STAFF', 'LIBRARIAN', 'ADMINISTRATOR')")
    public BorrowingTransaction getTransactionById(@PathVariable Integer id) {
        return borrowingTransactionService.getTransactionById(id);
    }
    
    @GetMapping("/member/{memberId}")
    @PreAuthorize("hasAnyRole('STAFF', 'LIBRARIAN', 'ADMINISTRATOR')")
    public List<BorrowingTransaction> getTransactionsByMember(@PathVariable Integer memberId) {
        return borrowingTransactionService.getTransactionsByMember(memberId);
    }
    
    @GetMapping("/book/{bookId}")
    @PreAuthorize("hasAnyRole('STAFF', 'LIBRARIAN', 'ADMINISTRATOR')")
    public List<BorrowingTransaction> getTransactionsByBook(@PathVariable Integer bookId) {
        return borrowingTransactionService.getTransactionsByBook(bookId);
    }
    
    @GetMapping("/status/{status}")
    @PreAuthorize("hasAnyRole('STAFF', 'LIBRARIAN', 'ADMINISTRATOR')")
    public List<BorrowingTransaction> getTransactionsByStatus(@PathVariable String status) {
        BorrowingTransaction.TransactionStatus transactionStatus = 
            BorrowingTransaction.TransactionStatus.valueOf(status.toUpperCase());
        return borrowingTransactionService.getTransactionsByStatus(transactionStatus);
    }
    
    // إدارة المعاملات - LIBRARIAN+ فقط
    @PostMapping
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMINISTRATOR')")
    public BorrowingTransaction createTransaction(@RequestBody BorrowingTransaction transaction) {
        return borrowingTransactionService.saveTransaction(transaction);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMINISTRATOR')")
    public BorrowingTransaction updateTransaction(@PathVariable Integer id, @RequestBody BorrowingTransaction transactionDetails) {
        return borrowingTransactionService.updateTransaction(id, transactionDetails);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public boolean deleteTransaction(@PathVariable Integer id) {
        return borrowingTransactionService.deleteTransaction(id);
    }
}