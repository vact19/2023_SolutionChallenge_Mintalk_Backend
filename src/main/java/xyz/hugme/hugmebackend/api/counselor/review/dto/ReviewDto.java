package xyz.hugme.hugmebackend.api.counselor.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.hugme.hugmebackend.domain.user.client.Client;
import xyz.hugme.hugmebackend.domain.user.counselor.Counselor;
import xyz.hugme.hugmebackend.domain.user.counselor.review.CounselorReview;

// 리뷰 저장 Dto
@Getter
@NoArgsConstructor
public class ReviewDto {
    private int rate;
    private String content;

    public CounselorReview toEntity(Counselor counselor, Client client){
        return CounselorReview.builder()
                .rate(rate)
                .content(content)
                .counselor(counselor)
                .client(client)
                .build();
    }
}

