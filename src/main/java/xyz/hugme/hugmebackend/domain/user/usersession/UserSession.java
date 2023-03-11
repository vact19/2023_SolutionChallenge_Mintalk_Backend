package xyz.hugme.hugmebackend.domain.user.usersession;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    private String jSessionId;
    @Column(length = 10, nullable = false)
    private Role role;
    @Column(nullable = false)
    private LocalDateTime expirationDate;

    @Builder
    public UserSession(String jSessionId, Role role, LocalDateTime expirationDate) {
        this.jSessionId = jSessionId;
        this.role = role;
        this.expirationDate = expirationDate;
    }
}
