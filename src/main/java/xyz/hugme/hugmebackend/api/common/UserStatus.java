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
}
