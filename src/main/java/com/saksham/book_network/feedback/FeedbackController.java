package com.saksham.book_network.feedback;

import com.saksham.book_network.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("feedbacks")
@RequiredArgsConstructor
@Tag(name = "Feedback")
public class FeedbackController {

    private final FeedBackService feedBackService;

    @PostMapping
    public ResponseEntity<UUID> saveFeedback(@Valid @RequestBody FeedBackRequest request, Authentication connectedUser) {
        return ResponseEntity.ok(feedBackService.saveFeedback(request, connectedUser));
    }

    @GetMapping("/book/{book-id}")
    public ResponseEntity<PageResponse<FeedBackResponse>> getAllFeedBackByBook(@PathVariable("book-id") UUID bookId, @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                                               @RequestParam(name = "size", defaultValue = "0", required = false) int size , Authentication connectedUser) {
        return ResponseEntity.ok(feedBackService.getAllFeedBackByBook(bookId, connectedUser, page, size));
    }
}
