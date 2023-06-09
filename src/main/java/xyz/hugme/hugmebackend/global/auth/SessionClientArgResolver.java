package xyz.hugme.hugmebackend.global.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import xyz.hugme.hugmebackend.domain.user.client.Client;
import xyz.hugme.hugmebackend.domain.user.client.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class SessionClientArgResolver implements HandlerMethodArgumentResolver {
    private final HttpSession session;
    private final ClientService clientService;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isSessionClientAnnotation = parameter.getParameterAnnotation(SessionClient.class) != null;
        boolean isClientType = Client.class.equals(parameter.getParameterType());
        return isSessionClientAnnotation && isClientType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession(false);

        Long sessionClientId = session == null ?
                null : (Long) session.getAttribute("id");

        return clientService.findBySessionClientId(sessionClientId);
    }
}
