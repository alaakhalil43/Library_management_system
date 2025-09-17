package Library_management_system.Library_management_system.repository;

import Library_management_system.Library_management_system.model.BorrowingTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BorrowingTransactionRepository extends JpaRepository<BorrowingTransaction, Integer> {
    
    List<BorrowingTransaction> findByMemberId(Integer memberId);
    
    List<BorrowingTransaction> findByBookId(Integer bookId);
    
    List<BorrowingTransaction> findByStatus(BorrowingTransaction.TransactionStatus status);
    
    List<BorrowingTransaction> findByBorrowedById(Integer userId);
    
    @Query("SELECT bt FROM BorrowingTransaction bt WHERE bt.member.id = :memberId AND bt.status = :status")
    List<BorrowingTransaction> findByMemberIdAndStatus(@Param("memberId") Integer memberId, 
                                                      @Param("status") BorrowingTransaction.TransactionStatus status);
    
    @Query("SELECT bt FROM BorrowingTransaction bt WHERE bt.book.id = :bookId AND bt.status = :status")
    List<BorrowingTransaction> findByBookIdAndStatus(@Param("bookId") Integer bookId, 
                                                    @Param("status") BorrowingTransaction.TransactionStatus status);
    
    @Query("SELECT bt FROM BorrowingTransaction bt WHERE bt.dueDate < :currentDate AND bt.status = 'BORROWED'")
    List<BorrowingTransaction> findOverdueTransactions(@Param("currentDate") LocalDateTime currentDate);
    
    @Query("SELECT bt FROM BorrowingTransaction bt WHERE bt.borrowDate BETWEEN :startDate AND :endDate")
    List<BorrowingTransaction> findByBorrowDateBetween(@Param("startDate") LocalDateTime startDate, 
                                                      @Param("endDate") LocalDateTime endDate);
}
