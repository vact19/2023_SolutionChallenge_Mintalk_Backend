package xyz.hugme.hugmebackend.global.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import xyz.hugme.hugmebackend.domain.user.counselor.Counselor;
import xyz.hugme.hugmebackend.domain.user.counselor.CounselorService;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class SessionCounselorArgumentResolver implements HandlerMethodArgumentResolver {
    private final HttpSession session; // Yes, if you inject an HttpSession object via the Spring Boot framework, it will be the same HttpSession object that is associated with the request's JSESSIONID cookie. The Spring Boot framework will use this JSESSIONID cookie to retrieve the associated HttpSession object and inject it into your application
    private final CounselorService counselorService;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isSessionCounselorAnnotation = parameter.getParameterAnnotation(SessionCounselor.class) != null;
        boolean isCounselorType = Counselor.class.equals(parameter.getParameterType());
        return isSessionCounselorAnnotation && isCounselorType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String email = (String) session.getAttribute("email");
        return counselorService.findByEmail(email);
    }
}
