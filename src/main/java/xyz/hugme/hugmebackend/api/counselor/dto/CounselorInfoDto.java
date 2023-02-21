package xyz.hugme.hugmebackend.api.counselor.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.hugme.hugmebackend.domain.counselor.Counselor;
import xyz.hugme.hugmebackend.domain.counselor.Field;
import xyz.hugme.hugmebackend.domain.counselor.Gender;
import xyz.hugme.hugmebackend.domain.counselor.review.CounselorReview;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// 상담사 정보 페이지용 Dto. 상담사 정보와 리뷰를 담고 있음.
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
    private double averageRate;
    private List<String> careers;
    private Set<Field> fields;

    // 리뷰 관련정보
    private List<CounselorReviewListDto> reviews;

    // Counselor 객체와 CounselorReview 리스트의 객체를 받아서 생성자를 실행한다.
    // 평균 평점을 구한다.
    public static CounselorInfoDto of(Counselor counselor, List<CounselorReview> counselorReviews){
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
                .averageRate(averageRate)
                .careers(counselor.getCareers())
                .fields(counselor.getFields())
                .reviews(CounselorReviewListDto.ofList(counselorReviews))
                .build();
    }

    @Builder
    public CounselorInfoDto(Long id, String name, Gender gender, String shortIntroduction, String introduction, String contact, String email, String location, double averageRate, List<String> careers, Set<Field> fields, List<CounselorReviewListDto> reviews) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.shortIntroduction = shortIntroduction;
        this.introduction = introduction;
        this.contact = contact;
        this.email = email;
        this.location = location;
        this.averageRate = averageRate;
        this.careers = careers;
        this.fields = fields;
        this.reviews = reviews;
    }

    @Getter
    @NoArgsConstructor
    static class CounselorReviewListDto {
        private Integer rate;
        private String content;

        public static CounselorReviewListDto of(CounselorReview counselorReview){
            return CounselorReviewListDto.builder()
                    .rate(counselorReview.getRate())
                    .content(counselorReview.getContent())
                    .build();
        }

        public static List<CounselorReviewListDto> ofList(List<CounselorReview> counselorReviews){
            return counselorReviews.stream()
                    .map(CounselorReviewListDto::of)
                    .collect(Collectors.toList());
        }

        @Builder
        public CounselorReviewListDto(Integer rate, String content) {
            this.rate = rate;
            this.content = content;
        }
    }
}
