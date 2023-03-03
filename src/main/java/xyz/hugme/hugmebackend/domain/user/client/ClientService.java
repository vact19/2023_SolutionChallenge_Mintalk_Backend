package xyz.hugme.hugmebackend.domain.user.client;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hugme.hugmebackend.domain.common.FindBy;
import xyz.hugme.hugmebackend.global.exception.BusinessException;
import xyz.hugme.hugmebackend.global.exception.ErrorCode;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Client save(Client client) {
        try {
            return clientRepository.save(client);
        } catch (DataIntegrityViolationException e){
            throw new BusinessException(ErrorCode.CLIENT_EMAIL_ALREADY_EXISTS);
        }
    }

    // 로그인 비밀번호 일치 검증
    public Client validateSignIn(String email, String rawPassword) {
        Client client = validateOptionalClient(clientRepository.findByEmail(email), FindBy.EMAIL);
        if (! passwordEncoder.matches(rawPassword, client.getPassword()))
            throw new BusinessException(ErrorCode.PASSWORD_NOT_MATCHING);
        return client;
    }

    public Client findByEmail(String email) {
        return validateOptionalClient(clientRepository.findByEmail(email), FindBy.EMAIL);
    }

    public Client findBySessionClientId(Long id){
        if (id == null)
            throw new BusinessException(ErrorCode.CLIENT_NOT_AUTHENTICATED);
        return clientRepository.findById(id).
                orElseThrow(() -> new BusinessException(ErrorCode.CLIENT_ID_NOT_FOUND));
    }

    public Client findById(Long id) {
        return validateOptionalClient(clientRepository.findById(id), FindBy.ID);
    }

    // Optional 클라이언트 null check
    private Client validateOptionalClient(Optional<Client> optionalClient, FindBy findBy){
        switch (findBy){
            case ID: return optionalClient.orElseThrow(() -> new BusinessException(ErrorCode.CLIENT_ID_NOT_FOUND));
            case EMAIL: return optionalClient.orElseThrow(() -> new BusinessException(ErrorCode.CLIENT_EMAIL_NOT_FOUND));
            default: throw new RuntimeException("Enum FindBy를 올바르게 명시하지 않음");
        }
    }


}











