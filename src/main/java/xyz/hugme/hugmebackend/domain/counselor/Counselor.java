package xyz.hugme.hugmebackend.domain.counselor;

import lombok.Getter;
import xyz.hugme.hugmebackend.domain.common.BaseTimeEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
public class Counselor extends BaseTimeEntity {
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String contact;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private String shortIntroduction;

    // 여러가지 field(전문분야) 를 가질 수 있음
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(value = EnumType.STRING)
    private Set<Field> fields;

    // "Male" "male" "MALE" 등으로 저장되지 않게 하기 위해 String이 아닌 Enum 사용
    // EnumType Ordinal은 DB에 1, 2, 3... 으로 저장됨
    @Enumerated(EnumType.STRING)
    private Gender gender;
}
