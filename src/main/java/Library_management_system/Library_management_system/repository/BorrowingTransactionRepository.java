package Library_management_system.Library_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Library_management_system.Library_management_system.model.BorrowingTransaction;

@Repository
public interface BorrowingTransactionRepository extends JpaRepository<BorrowingTransaction, Integer> {
    
    // Find transactions by member ID
    List<BorrowingTransaction> findByMemberId(Integer memberId);
    
    // Find transactions by book ID
    List<BorrowingTransaction> findByBookId(Integer bookId);
    
    // Find transactions by status
    List<BorrowingTransaction> findByStatus(BorrowingTransaction.TransactionStatus status);
}