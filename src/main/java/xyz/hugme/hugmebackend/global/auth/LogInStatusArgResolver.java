package xyz.hugme.hugmebackend.global.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import xyz.hugme.hugmebackend.api.common.UserStatus;
import xyz.hugme.hugmebackend.domain.user.Role;
import xyz.hugme.hugmebackend.domain.user.client.Client;
import xyz.hugme.hugmebackend.domain.user.client.ClientService;
import xyz.hugme.hugmebackend.domain.user.counselor.Counselor;
import xyz.hugme.hugmebackend.domain.user.counselor.CounselorService;
import xyz.hugme.hugmebackend.domain.user.usersession.UserSession;
import xyz.hugme.hugmebackend.domain.user.usersession.UserSessionRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class LogInStatusArgResolver implements HandlerMethodArgumentResolver {
    private final UserSessionRepository userSessionRepository;
    private final ClientService clientService;
    private final CounselorService counselorService;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isLogInStatusAnnotation = parameter.getParameterAnnotation(LogInStatus.class) != null;
        boolean isUserStatusType = UserStatus.class.equals(parameter.getParameterType());
        return isLogInStatusAnnotation && isUserStatusType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if ("session_id".equals(cookie.getName())){ // session_id를 찾은 경우
                    String sessionId = cookie.getValue();
                    HttpSession session = request.getSession(false);
                    if(session == null){ // 세션이 만료되었을 경우
                        UserSession userSession = userSessionRepository.findBySessionId(sessionId);
                        if (userSession.getExpirationDate().isBefore(LocalDateTime.now())){ // 쿠키 만료시간도 지났으면 다시 로그인
                            return new Object();
                        }
                        // 쿠키 만료시간이 지나지 않았으면
                        Long id = null;
                        String name;
                        Role role;
                        if (userSession.getRole() == Role.CLIENT){
                            Client client = clientService.findByUserSession(userSession);
                            id = client.getId();
                            name = client.getName();
                            role = Role.CLIENT;
                        } else { // counselor면.
                            Counselor counselor = counselorService.findByUserSession(userSession);
                            id = counselor.getId();
                            name = counselor.getName();
                            role = Role.COUNSELOR;
                        }
                        // 이거 이렇게 해도 Set-Cookie 되나?
                        HttpSession newSession = request.getSession();
                        newSession.setAttribute("id", id);
                        newSession.setAttribute("role", name);
                        newSession.setAttribute("name", role);
                        return new UserStatus(true, role, name);
                    } else { // null이 아니면 현재 세션이 살아있다는 뜻.
                        Role role = (Role) session.getAttribute("role");
                        String name = (String) session.getAttribute("name");
                        return new UserStatus(true, role, name);
                    }
                }
            }
        }


        return new Object();
    }
}
