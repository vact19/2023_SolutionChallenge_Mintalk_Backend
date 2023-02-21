package xyz.hugme.hugmebackend.global.converter;

import org.springframework.core.convert.converter.Converter;
import xyz.hugme.hugmebackend.domain.user.counselor.Field;

/**
 *  Enum Field 역직렬화 바인딩을 위한 커스텀 컨버터
*/
public class FieldConverter implements Converter<String, Field> {
    @Override
    public Field convert(String source) {
        return Field.from(source);
    }
}
