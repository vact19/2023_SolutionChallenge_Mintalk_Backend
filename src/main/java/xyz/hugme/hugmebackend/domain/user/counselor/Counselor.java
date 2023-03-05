package xyz.hugme.hugmebackend.domain.user.counselor;

import lombok.*;
import xyz.hugme.hugmebackend.domain.common.BaseTimeEntity;
import xyz.hugme.hugmebackend.domain.user.counselor.review.CounselorReview;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Counselor extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email; // 회원 조회용으로 쓰인다. unique true
    @Column(nullable = false)
    private String password;
    // "Male" "male" "MALE" 등으로 저장되지 않게 하기 위해 String이 아닌 Enum 사용
    // EnumType Ordinal은 DB에 1, 2, 3... 으로 저장됨
    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(nullable = false)
    private String shortIntroduction = "한줄 자기소개를 작성해 주세요";
    @Lob // JPA에서 알아서 clob이나 blob으로 바꿔줌
    @Column(nullable = false)
    private String introduction = "나를 소개하는 글을 써 주세요.";
    @Column(nullable = false)
    private String contact = "연락처를 '-' 없이 적어 주세요.";
    @Column(nullable = false)
    private String location = "근무지를 입력해 주세요.";

    // image url 은 빌더에 없음. 아래의 속성값은 기본 프로필사진
    @Column(nullable = false)
    private String profileImageUrl = "https://storage.googleapis.com/mintalk-image-storage/counselors/show-me-pocky.jpg";
    // blobName 은 빌더에 없음. 아래의 속성값은 기본 프로필사진
    // blobName 이란, GCS 버킷에 저장된 Object 가 가진 이름이다. 이 이름은 하나의 버킷 안에서는 유일하다.
    @Column(nullable = false)
    private String blobName = "counselors/show-me-pocky.jpg";

    @Column(nullable = false, length = 1)
    @Setter
    private boolean defaultProfile = true;

    @ElementCollection
    private List<String> careers;
    // 여러가지 field(전문분야) 를 가질 수 있음
    @ElementCollection
    @Enumerated(value = EnumType.STRING)
    private Set<Field> fields;

    // 리뷰 fetch join 용
    @OneToMany(mappedBy = "counselor", fetch = FetchType.LAZY)
    private List<CounselorReview> counselorReviews;

    // 더미데이터 삽입용 생성자
    @Builder
    public Counselor(String name, String email, String password, Gender gender, String shortIntroduction, String introduction, String contact, String location, List<String> careers, Set<Field> fields) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.shortIntroduction = shortIntroduction;
        this.introduction = introduction;
        this.contact = contact;
        this.location = location;
        this.careers = careers;
        this.fields = fields;
    }

    // 회원가입용 생성자.
    @Builder(builderMethodName = "signInBuilder", builderClassName = "signInBuilder")
    public Counselor(String name, String email, String password, Gender gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }

    public void editMyPage(String name, String email, String shortIntroduction, String introduction, String contact
                        , String location, String profileImageUrl, String blobName,List<String> careers, Set<Field> fields){
        this.name = name;
        this.email = email;
        this.shortIntroduction = shortIntroduction;
        this.introduction = introduction;
        this.contact = contact;
        this.location = location;
        this.profileImageUrl = profileImageUrl;
        this.blobName = blobName;
        this.careers = careers;
        this.fields = fields;
    }

}
