package xyz.hugme.hugmebackend.domain.counselor;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Field {

    ELDER, DEPRESSION, RELATIONSHIP, YOUTH;

    // 역직렬화를 위함. FieldConverter에서 사용.
    @JsonCreator
    public static Field from(String field){
        return Field.valueOf(field.toUpperCase());
    }
}
