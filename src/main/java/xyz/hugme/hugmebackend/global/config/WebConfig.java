package xyz.hugme.hugmebackend.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.hugme.hugmebackend.global.auth.SessionCounselorArgumentResolver;
import xyz.hugme.hugmebackend.global.converter.FieldConverter;
import xyz.hugme.hugmebackend.global.converter.GenderConverter;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final SessionCounselorArgumentResolver sessionCounselorArgumentResolver;

    // Cors 모두에게 활성화
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*") // Request Header의 Origin을, Response Header의 Access-Control-Allow-Origin에 그대로 넣어준다.
                .allowedMethods(HttpMethod.GET.name()
                        ,HttpMethod.POST.name()
                        ,HttpMethod.PATCH.name()
                        ,HttpMethod.PUT.name()
                        ,HttpMethod.DELETE.name()
                )
                .allowCredentials(true)
                .maxAge(1800)
                // maxage 만큼 preflight 캐싱은 기본값이 1800sec(30m), 즉 Access-Control-Max-Age=1800

        ;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new FieldConverter());
        registry.addConverter(new GenderConverter());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(sessionCounselorArgumentResolver);
    }
}
