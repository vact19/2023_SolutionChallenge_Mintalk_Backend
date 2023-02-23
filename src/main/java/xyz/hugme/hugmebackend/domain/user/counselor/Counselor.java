package xyz.hugme.hugmebackend.domain.user.counselor;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(nullable = false)
    private String shortIntroduction;
    @Lob // JPA에서 알아서 clob이나 blob으로 바꿔줌
    @Column(nullable = false)
    private String introduction;
    @Column(nullable = false)
    private String contact;

    @Column(nullable = false)
    private String location;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> careers;
    // 여러가지 field(전문분야) 를 가질 수 있음
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(value = EnumType.STRING)
    private Set<Field> fields;
    // 리뷰 fetch join 용
    @OneToMany(mappedBy = "counselor", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<CounselorReview> counselorReviews;

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

    public void editMyPage(String name, String email, String shortIntroduction, String introduction, String contact
                        , String location, List<String> careers, Set<Field> fields){
        this.name = name;
        this.email = email;
        this.shortIntroduction = shortIntroduction;
        this.introduction = introduction;
        this.contact = contact;
        this.location = location;
        this.careers = careers;
        this.fields = fields;
    }

}
