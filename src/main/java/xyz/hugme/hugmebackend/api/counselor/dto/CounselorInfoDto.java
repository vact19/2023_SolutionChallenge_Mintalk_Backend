package xyz.hugme.hugmebackend.api.counselor.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Slice;
import xyz.hugme.hugmebackend.domain.user.counselor.Counselor;
import xyz.hugme.hugmebackend.domain.user.counselor.Field;
import xyz.hugme.hugmebackend.domain.user.counselor.Gender;
import xyz.hugme.hugmebackend.domain.user.counselor.review.CounselorReview;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// 상담사 정보 페이지용 Dto. 상담사 정보와 리뷰를 담고 있음.
// 해당 페이지는 내담자가 상담사 정보를 보기 위해 접근한다.
@Getter
@NoArgsConstructor
public class CounselorInfoDto {
    private Long id;
    private String name;
    private Gender gender;
    private String shortIntroduction;
    private String introduction;
    private String contact;
    private String email;
    private String location;
    private String profileImageUrl;
    private double averageRate;
    private List<String> careers;
    private Set<Field> fields;

    // 리뷰 관련정보
    private Slice<CounselorReviewListDto> reviews;

    // Counselor 객체와 CounselorReview 리스트의 객체를 받아서 생성자를 실행한다.
    // 평균 평점을 구한다.
    public static CounselorInfoDto of(Counselor counselor, List<CounselorReview> counselorReviews,
                                      Slice<CounselorReview> counselorReviewSlice){
        double averageRate = counselorReviews.stream().
                mapToInt(CounselorReview::getRate)
                .average().orElse(0);
        averageRate = Double.parseDouble(String.format("%.1f", averageRate));

        return CounselorInfoDto.builder()
                .id(counselor.getId())
                .name(counselor.getName())
                .gender(counselor.getGender())
                .shortIntroduction(counselor.getShortIntroduction())
                .introduction(counselor.getIntroduction())
                .contact(counselor.getContact())
                .email(counselor.getEmail())
                .location(counselor.getLocation())
                .profileImageUrl(counselor.getProfileImageUrl())
                .averageRate(averageRate)
                .careers(counselor.getCareers())
                .fields(counselor.getFields())
                .reviews(CounselorReviewListDto.of(counselorReviewSlice))
                .build();
    }

    @Builder
    public CounselorInfoDto(Long id, String name, Gender gender, String shortIntroduction, String introduction, String contact, String email, String location, String profileImageUrl, double averageRate, List<String> careers, Set<Field> fields, Slice<CounselorReviewListDto> reviews) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.shortIntroduction = shortIntroduction;
        this.introduction = introduction;
        this.contact = contact;
        this.email = email;
        this.location = location;
        this.profileImageUrl = profileImageUrl;
        this.averageRate = averageRate;
        this.careers = careers;
        this.fields = fields;
        this.reviews = reviews;
    }

    // id가 포함되지 않은 리뷰 DTO
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CounselorReviewListDto {
        private Integer rate;
        private String content;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime createTime;

        public static CounselorReviewListDto of(CounselorReview counselorReview){
            return new CounselorReviewListDto(counselorReview.getRate(), counselorReview.getContent(), counselorReview.getCreateTime());
        }

        public static List<CounselorReviewListDto> of(List<CounselorReview> counselorReviews){
            return counselorReviews.stream()
                    .map(CounselorReviewListDto::of)
                    .collect(Collectors.toList());
        }

        public static Slice<CounselorReviewListDto> of(Slice<CounselorReview> counselorReviewSlice){
            return counselorReviewSlice.map(CounselorReviewListDto::of);
        }
    }
}
