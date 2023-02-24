package xyz.hugme.hugmebackend.api.counselor.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SaveReviewDto {
    private String content;
    private int rate;
}

