package Library_management_system.Library_management_system.controller;

import Library_management_system.Library_management_system.model.BorrowingTransaction;
import Library_management_system.Library_management_system.service.BorrowingTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowing")
@CrossOrigin(origins = "*")
public class BorrowingTransactionController {
    
    @Autowired
    private BorrowingTransactionService borrowingTransactionService;
    
    @GetMapping
    public List<BorrowingTransaction> getAllTransactions() {
        return borrowingTransactionService.getAllTransactions();
    }
    
    @GetMapping("/{id}")
    public BorrowingTransaction getTransactionById(@PathVariable Integer id) {
        return borrowingTransactionService.getTransactionById(id);
    }
    
    @GetMapping("/member/{memberId}")
    public List<BorrowingTransaction> getTransactionsByMember(@PathVariable Integer memberId) {
        return borrowingTransactionService.getTransactionsByMember(memberId);
    }
    
    @GetMapping("/book/{bookId}")
    public List<BorrowingTransaction> getTransactionsByBook(@PathVariable Integer bookId) {
        return borrowingTransactionService.getTransactionsByBook(bookId);
    }
    
    @GetMapping("/status/{status}")
    public List<BorrowingTransaction> getTransactionsByStatus(@PathVariable String status) {
        BorrowingTransaction.TransactionStatus transactionStatus = 
            BorrowingTransaction.TransactionStatus.valueOf(status.toUpperCase());
        return borrowingTransactionService.getTransactionsByStatus(transactionStatus);
    }
    
    @PostMapping("/borrow")
    public ResponseEntity<BorrowingTransaction> borrowBook(@RequestBody BorrowRequest request) {
        BorrowingTransaction transaction = borrowingTransactionService.borrowBook(
            request.getBookId(), 
            request.getMemberId(), 
            request.getBorrowedById(), 
            request.getDaysToReturn()
        );
        
        if (transaction != null) {
            return new ResponseEntity<>(transaction, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/{id}/return")
    public ResponseEntity<BorrowingTransaction> returnBook(@PathVariable Integer id) {
        BorrowingTransaction transaction = borrowingTransactionService.returnBook(id);
        if (transaction != null) {
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}/lost")
    public ResponseEntity<BorrowingTransaction> markAsLost(@PathVariable Integer id) {
        BorrowingTransaction transaction = borrowingTransactionService.markAsLost(id);
        if (transaction != null) {
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Simple inner class for borrow request
    public static class BorrowRequest {
        private Integer bookId;
        private Integer memberId;
        private Integer borrowedById;
        private int daysToReturn = 14; // Default 14 days
        
        // Getters and setters
        public Integer getBookId() { return bookId; }
        public void setBookId(Integer bookId) { this.bookId = bookId; }
        
        public Integer getMemberId() { return memberId; }
        public void setMemberId(Integer memberId) { this.memberId = memberId; }
        
        public Integer getBorrowedById() { return borrowedById; }
        public void setBorrowedById(Integer borrowedById) { this.borrowedById = borrowedById; }
        
        public int getDaysToReturn() { return daysToReturn; }
        public void setDaysToReturn(int daysToReturn) { this.daysToReturn = daysToReturn; }
    }
}
