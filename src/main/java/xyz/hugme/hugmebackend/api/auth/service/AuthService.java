package xyz.hugme.hugmebackend.api.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hugme.hugmebackend.api.auth.dto.LoginDto;
import xyz.hugme.hugmebackend.domain.user.Role;
import xyz.hugme.hugmebackend.domain.user.client.Client;
import xyz.hugme.hugmebackend.domain.user.client.ClientService;
import xyz.hugme.hugmebackend.domain.user.counselor.Counselor;
import xyz.hugme.hugmebackend.domain.user.counselor.CounselorService;
import xyz.hugme.hugmebackend.domain.user.usersession.UserSession;
import xyz.hugme.hugmebackend.domain.user.usersession.UserSessionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Transactional
@Service
public class AuthService {

    private final ClientService clientService;
    private final CounselorService counselorService;
    private final UserSessionService userSessionService;

    // 로그인 처리 후 사용자명을 반환한다.
    @Transactional
    public String signInCounselor(LoginDto loginDto, HttpServletRequest request) {
        // username, password 검사
        Counselor validatedCounselor = counselorService.validateSignIn(loginDto.getEmail(), loginDto.getPassword());

        // session 생성
        HttpSession session = request.getSession();
        session.setAttribute("id", validatedCounselor.getId());
        session.setAttribute("name", validatedCounselor.getName());
        session.setAttribute("role", Role.COUNSELOR);

        // UserSession 의 만료기간 +14일
        validatedCounselor.getUserSession().
                signIn(session.getId());

        return validatedCounselor.getName();
    }

    @Transactional
    public String singInClient(LoginDto loginDto, HttpServletRequest request) {
        //  username, password 검사
        Client validatedClient = clientService.validateSignIn(loginDto.getEmail(), loginDto.getPassword());
        HttpSession session = request.getSession();
        session.setAttribute("id", validatedClient.getId());
        session.setAttribute("name", validatedClient.getName());
        session.setAttribute("role", Role.CLIENT);

        // UserSession 의 만료기간 +14일
        validatedClient.getUserSession().
                setExpirationDate(LocalDateTime.now().plusDays(14));

        return validatedClient.getName();
    }

    @Transactional
    public void signOut(HttpServletRequest request) {
        String sessionId = getSessionIdFromCookie(request);
        HttpSession session = request.getSession(false);

        // 세션이 살아있을 경우 무효화
        if (session != null)
            session.invalidate(); // Invalidates this session then unbinds any objects bound to it.

        // 사용자가 의도적으로 sessionId값을 변경시키지 않는 이상. null이 나오지 않는다.
        UserSession userSession = userSessionService.findBySessionId(sessionId);
        userSession.signOut();
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
