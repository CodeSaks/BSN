package com.saksham.book_network.feedback;

import com.saksham.book_network.book.Book;
import com.saksham.book_network.book.BookRepository;
import com.saksham.book_network.book.BookService;
import com.saksham.book_network.common.PageResponse;
import com.saksham.book_network.exception.OperationNotPermittedException;
import com.saksham.book_network.user.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FeedBackService {

    private final BookRepository bookRepository;
    private final FeedBackMapper feedBackMapper;
    private final FeedBackRepository feedBackRepository;

    public UUID saveFeedback(@Valid FeedBackRequest request, Authentication connectedUser) {

        Book book = bookRepository.findById(request.bookId()).orElseThrow(() -> new EntityNotFoundException("No Book Found with the id:: " + request.bookId()));

        if(book.isArchived() || !book.isShareable()) {
            throw new OperationNotPermittedException("The requested book cannot be given feedback since it doesn't have shareable privileges");
        }

        User user = (User) connectedUser.getPrincipal();

        if (!Objects.equals(book.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("You cannot give feedback on your own book");
        }

        FeedBack feedBack = feedBackMapper.toFeedBack(request);
        return feedBackRepository.save(feedBack).getId();
    }


    public PageResponse<FeedBackResponse> getAllFeedBackByBook(UUID bookId, Authentication connectedUser, int page, int size) {

        Pageable pageable = PageRequest.of(page,size);
        User user = (User) connectedUser.getPrincipal();

        Page<FeedBack> feedBacks = feedBackRepository.findAllByBookId(bookId, pageable);
        List<FeedBackResponse> feedBackResponses = feedBacks.stream()
                .map(f -> feedBackMapper.toFeedBackResponse(f,user.getId())).toList();

        return new PageResponse<>(
                feedBackResponses,
                feedBacks.getNumber(),
                feedBacks.getSize(),
                feedBacks.getTotalElements(),
                feedBacks.getTotalPages(),
                feedBacks.isFirst(),
                feedBacks.isLast()
        );
    }
}
