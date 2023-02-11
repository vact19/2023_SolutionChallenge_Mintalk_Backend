package xyz.hugme.hugmebackend.domain.counselor;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.hugme.hugmebackend.domain.common.BaseTimeEntity;

import javax.persistence.*;
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
    // "Male" "male" "MALE" 등으로 저장되지 않게 하기 위해 String이 아닌 Enum 사용
    // EnumType Ordinal은 DB에 1, 2, 3... 으로 저장됨
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(nullable = false)
    private String shortIntroduction;
    @Column(nullable = false)
    private String contact;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String location;


    // 여러가지 field(전문분야) 를 가질 수 있음
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(value = EnumType.STRING)
    private Set<Field> fields;

    @Builder
    public Counselor(String name, Gender gender, String shortIntroduction, String contact, String email, String location, Set<Field> fields) {
        this.name = name;
        this.gender = gender;
        this.shortIntroduction = shortIntroduction;
        this.contact = contact;
        this.email = email;
        this.location = location;
        this.fields = fields;
    }
}
