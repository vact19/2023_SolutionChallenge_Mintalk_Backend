package xyz.hugme.hugmebackend.domain.user.usersession;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hugme.hugmebackend.global.exception.BusinessException;
import xyz.hugme.hugmebackend.global.exception.ErrorCode;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

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

    public UserSession findBySessionId(String sessionId){
        return userSessionRepository.findBySessionId(sessionId)
                .orElseThrow(() -> new BusinessException(ErrorCode.SESSION_NOT_FOUND));
    }

    @Transactional
    public void signOut(HttpServletRequest request) {
        String sessionId = getSessionIdFromCookie(request);
        HttpSession session = request.getSession(false);

        // 세션이 살아있을 경우 무효화
        if (session != null)
            session.invalidate(); // Invalidates this session then unbinds any objects bound to it.

        UserSession userSession = findBySessionId(sessionId);
        userSession.signOut(LocalDateTime.now());
    }

    private String getSessionIdFromCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if ("session_id".equals(cookie.getName()))
                    return cookie.getValue();
            }
        }
        return "";
    }
}





















