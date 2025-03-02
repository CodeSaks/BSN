package com.saksham.book_network.feedback;

import com.saksham.book_network.book.Book;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class FeedBackMapper {

    public FeedBack toFeedBack(FeedBackRequest feedBackRequest) {

        return FeedBack.builder()
                .note(feedBackRequest.note())
                .comment(feedBackRequest.comments())
                .book(Book.builder()
                        .id(feedBackRequest.bookId())
                        .archived(false)
                        .shareable(false)
                        .build())
                .build();
    }

    public FeedBackResponse toFeedBackResponse(FeedBack feedBack, UUID id) {
        return FeedBackResponse.builder()
                .note(feedBack.getNote())
                .comment(feedBack.getComment())
                .ownFeedback(Objects.equals(feedBack.getCreatedBy(), id))
                .build();
    }
}
