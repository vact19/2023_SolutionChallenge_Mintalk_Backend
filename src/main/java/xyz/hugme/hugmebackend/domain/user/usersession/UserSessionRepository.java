package xyz.hugme.hugmebackend.domain.user.usersession;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSessionRepository extends JpaRepository<UserSession, Long> {
}
