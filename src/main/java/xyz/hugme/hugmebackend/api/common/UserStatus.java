package xyz.hugme.hugmebackend.api.common;

import lombok.Getter;
import xyz.hugme.hugmebackend.domain.user.Role;

@Getter
public class UserStatus {
    private boolean isLoggedIn;
    private Role role;
    private String username;

    public UserStatus(boolean isLoggedIn, Role role, String username) {
        this.isLoggedIn = isLoggedIn;
        this.role = role;
        this.username = username;
    }

    // 로그인 여부만이 필요할 때 (즉, 로그인되어있지 않은 경우)
    public UserStatus(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }
}
