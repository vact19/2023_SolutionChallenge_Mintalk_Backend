package xyz.hugme.hugmebackend.domain.user.client;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.hugme.hugmebackend.domain.user.usersession.UserSession;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);

    Client findByUserSession(UserSession userSession);
}