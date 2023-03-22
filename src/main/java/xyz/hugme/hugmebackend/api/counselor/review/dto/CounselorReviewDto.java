package xyz.hugme.hugmebackend.api.counselor.review.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.hugme.hugmebackend.domain.user.client.Client;
import xyz.hugme.hugmebackend.domain.user.counselor.Counselor;
import xyz.hugme.hugmebackend.domain.user.counselor.review.CounselorReview;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// 리뷰 저장 Dto
@Builder
@Getter
public class CounselorReviewDto {
    private int rate;
    private String content;

    @Getter
    @NoArgsConstructor
    public static class Request{
        private int rate;
        private String content;

        private Counselor counselor;

        private Long id;

        public CounselorReview toEntity(Counselor counselor, Client client){
            return CounselorReview.builder()
                    .rate(rate)
                    .content(content)
                    .counselor(counselor)
                    .client(client)
                    .build();
        }
    }

    @Getter
   // @AllArgsConstructor
    public static class Response{
        private Long id;
        private int rate;
        private String content;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime createTime;

        public Response(Long id, int rate, String content, LocalDateTime createTime) {
            this.id = id;
            this.rate = rate;
            this.content = content;
            this.createTime = createTime;
        }

        public static List<Response> of(List<CounselorReview> counselorReviewList) {
            return counselorReviewList.stream()
                    .map(Response::of)
                    .collect(Collectors.toList());
        }

        public static Response of(CounselorReview counselorReview){
            return new Response(counselorReview.getId(), counselorReview.getRate(),
                    counselorReview.getContent(), counselorReview.getCreateTime());
        }
    }



}

