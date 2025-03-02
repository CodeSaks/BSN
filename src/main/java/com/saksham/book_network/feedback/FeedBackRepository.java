package com.saksham.book_network.feedback;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface FeedBackRepository extends JpaRepository<FeedBack, UUID> {

    @Query("""
            SELECT feedback 
            FROM FEEDBACK feedback
            WHERE feedback.book.id = :bookId
            """)
    Page<FeedBack> findAllByBookId(UUID bookId, Pageable pageable);
}
