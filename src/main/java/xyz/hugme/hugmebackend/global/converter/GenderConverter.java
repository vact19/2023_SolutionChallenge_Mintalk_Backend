package xyz.hugme.hugmebackend.global.converter;

import org.springframework.core.convert.converter.Converter;
import xyz.hugme.hugmebackend.domain.user.counselor.Gender;

public class GenderConverter implements Converter<String, Gender> {
    @Override
    public Gender convert(String source) {
        return Gender.valueOf(source.toUpperCase());
    }
}
