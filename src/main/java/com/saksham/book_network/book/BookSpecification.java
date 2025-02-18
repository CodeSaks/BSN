package com.saksham.book_network.book;

import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class BookSpecification {

    public static Specification<Book> withOwnerId(UUID ownerId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("owner").get("id"), ownerId);
    }
}
