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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class SessionCounselorArgResolver implements HandlerMethodArgumentResolver {
    private final CounselorService counselorService;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isSessionCounselorAnnotation = parameter.getParameterAnnotation(SessionCounselor.class) != null;
        boolean isCounselorType = Counselor.class.equals(parameter.getParameterType());
        return isSessionCounselorAnnotation && isCounselorType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession(false);

        Long sessionCounselorId = session == null ?
                null : (Long) session.getAttribute("id");
        return counselorService.findBySessionCounselorId(sessionCounselorId);
    }
}
