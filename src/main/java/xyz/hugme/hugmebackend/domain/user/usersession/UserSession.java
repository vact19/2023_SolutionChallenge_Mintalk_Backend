package xyz.hugme.hugmebackend.domain.user.usersession;

import lombok.*;
import xyz.hugme.hugmebackend.domain.common.BaseTimeEntity;
import xyz.hugme.hugmebackend.domain.user.Role;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserSession extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String sessionId;
    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Setter
    @Column(nullable = false)
    private LocalDateTime expirationDate;

    @Builder
    public UserSession(String sessionId, Role role, LocalDateTime expirationDate) {
        this.sessionId = sessionId;
        this.role = role;
        this.expirationDate = expirationDate;
    }

    // 로그아웃 시  기한을 만료시킴
    public void signOut() {
        expirationDate = LocalDateTime.now();
    }
}
