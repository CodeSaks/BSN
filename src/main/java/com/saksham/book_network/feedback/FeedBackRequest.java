package com.saksham.book_network.feedback;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record FeedBackRequest(
        @Positive(message = "200")
        @Min(value = 0, message = "201")
        @Max(value = 0, message = "202")
        Double note,

        @NotNull(message = "203")
        @NotEmpty(message = "203")
        @NotBlank(message = "203")
        String comments,

        @NotNull(message = "204")
        UUID bookId
) {
}
