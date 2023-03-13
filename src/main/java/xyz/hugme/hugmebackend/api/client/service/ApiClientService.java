package xyz.hugme.hugmebackend.api.client.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hugme.hugmebackend.api.client.dto.SaveClientDto;
import xyz.hugme.hugmebackend.domain.user.Role;
import xyz.hugme.hugmebackend.domain.user.client.Client;
import xyz.hugme.hugmebackend.domain.user.client.ClientService;
import xyz.hugme.hugmebackend.domain.user.usersession.UserSession;
import xyz.hugme.hugmebackend.domain.user.usersession.UserSessionService;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ApiClientService {
    private final ClientService clientService;
    private final PasswordEncoder passwordEncoder;
    private final UserSessionService userSessionService;

    @Transactional
    public Client signUpClient(SaveClientDto saveClientDto) {
        // 입력값 비밀번호 암호화 후 저장
        String encodedPassword = passwordEncoder.encode(saveClientDto.getPassword());
        Client client = saveClientDto.toEntity(encodedPassword);

        // 세션객체 생성 후 client 에 저장
        UserSession userSession = UserSession.builder()
                .sessionId("")
                .expirationDate(LocalDateTime.now())
                .role(Role.CLIENT)
                .build();
        client.setUserSession(userSessionService.save(userSession));

        return clientService.save(client);
    }


}















