package xyz.hugme.hugmebackend.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.hugme.hugmebackend.global.converter.FieldConverter;
import xyz.hugme.hugmebackend.global.converter.GenderConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

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
                // maxage 만큼 preflight 캐싱은 기본 1800sec(30m)
                // Access-Control-Max-Age=1800
        ;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new FieldConverter());
        registry.addConverter(new GenderConverter());
    }


}