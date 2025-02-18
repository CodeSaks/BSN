package com.saksham.book_network.book;

import org.aspectj.weaver.ast.And;
import org.hibernate.cache.spi.entry.StructuredCacheEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID>, JpaSpecificationExecutor<Book> {

    @Query("""
            SELECT book
            FROM Book book
            WHERE book.archived = false
            AND book.shareable = true
            And book.owner.id != :userID
            """
    )
    Page<Book> findAllDisplayableBooks(Pageable pageable, UUID id);
}
