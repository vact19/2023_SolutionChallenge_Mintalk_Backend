package xyz.hugme.hugmebackend.api.counselor.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

// 리뷰 저장 Dto
@Getter
@NoArgsConstructor
public class SaveReviewDto {
    private String content;
    private int rate;
}

