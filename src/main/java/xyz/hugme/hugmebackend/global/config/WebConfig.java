package xyz.hugme.hugmebackend.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
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
                .allowedOrigins("*")
                .allowedMethods("*");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new FieldConverter());
        registry.addConverter(new GenderConverter());
    }
}
