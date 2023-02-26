package xyz.hugme.hugmebackend.domain.user.client;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    // 로그인 비밀번호 일치 검증
    public Client validate(String email, String rawPassword) {
        Client client = validateOptionalClient(clientRepository.findByEmail(email));
        if (! passwordEncoder.matches(rawPassword, client.getPassword()))
            throw new RuntimeException("비밀번호 불일치");
        return client;
    }

    // Optional 클라이언트 null check
    private Client validateOptionalClient(Optional<Client> optionalClient){
        return optionalClient.orElseThrow(() -> new RuntimeException("해당 id로 client를 찾을 수 없음"));
    }
}











