package xyz.hugme.hugmebackend.domain.user.usersession;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hugme.hugmebackend.global.exception.BusinessException;
import xyz.hugme.hugmebackend.global.exception.ErrorCode;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserSessionService {
    private final UserSessionRepository userSessionRepository;

    @Transactional
    public UserSession save(UserSession userSession) {
        try {
            return userSessionRepository.save(userSession);
        } catch (DataIntegrityViolationException e){
            throw new BusinessException(ErrorCode.SESSION_ID_ALREADY_EXISTS);
        }
    }
}





















