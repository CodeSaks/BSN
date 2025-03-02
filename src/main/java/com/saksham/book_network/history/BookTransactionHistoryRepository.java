package com.saksham.book_network.history;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookTransactionHistoryRepository extends JpaRepository<BookTransactionHistory, UUID> {

    @Query("""
            SELECT history
            FROM BookTransactionHistory history
            Where history.user.id = :id
            """)
    Page<BookTransactionHistory> findAllBorrowedBooks(Pageable pageable, UUID id);

    @Query("""
            SELECT history
            FROM BookTransactionHistory history
            Where history.book.owner.id = :id
            """)
    Page<BookTransactionHistory> findAllReturnedBooks(Pageable pageable, UUID id);

    @Query("""
            SELECT (COUNT(*) > 0) AS isBorrowed
            FROM BookTransactionHistory history
            WHERE history.user.id = :userId
            AND history.book.id = :bookId
            AND history.returnApproved = false
            """)
    boolean isAlreadyBorrowedByUser(UUID bookId, UUID userId);

    @Query("""
            SELECT history FROM
            BookTransactionHistory history
            WHERE  history.user.id = :userId
            AND history.book.id = :bookId
            AND history.returned = false
            AND history.returnedApproved = false
            """)
    Optional<BookTransactionHistory> findByBookIdAndUserId(UUID bookId, UUID id);

    @Query("""
            SELECT history FROM
            BookTransactionHistory history
            WHERE  history.book.owner.id = :userId
            AND history.book.id = :bookId
            AND history.returned = true
            AND history.returnedApproved = false
    """)
    Optional<BookTransactionHistory> findByBookIdAndOwnerId(UUID bookId, UUID id);
}
