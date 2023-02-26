package xyz.hugme.hugmebackend.domain.user.client;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.hugme.hugmebackend.domain.common.BaseTimeEntity;
import xyz.hugme.hugmebackend.domain.user.counselor.Gender;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Client extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    @Builder
    public Client(Gender gender, String name, String email, String password) {
        this.gender = gender;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
















