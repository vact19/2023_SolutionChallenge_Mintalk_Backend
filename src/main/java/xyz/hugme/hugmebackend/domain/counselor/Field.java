package xyz.hugme.hugmebackend.domain.counselor;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Field { // 상담사 전문분야

    ELDER("노인"), DEPRESSION("우울"),
    RELATIONSHIP("인간관계"), YOUTH("청소년"),
    ANXIETY("불안");

    private final String desc;

    Field(String desc) {
        this.desc = desc;
    }

    // 역직렬화를 위함. FieldConverter에서 사용.
    @JsonCreator
    public static Field from(String field){
        return Field.valueOf(field.toUpperCase());
    }
}
