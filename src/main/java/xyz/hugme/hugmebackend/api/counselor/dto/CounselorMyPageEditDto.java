package xyz.hugme.hugmebackend.api.counselor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.hugme.hugmebackend.domain.user.counselor.Counselor;
import xyz.hugme.hugmebackend.domain.user.counselor.Field;

import java.util.List;
import java.util.Set;

// 상담사 마이페이지 수정 DTO
@Getter
@AllArgsConstructor // ModelAttribute. Setter or 생성자로 객체 바인딩한다.
public class CounselorMyPageEditDto {
    private String name;
    private String email;
    private String shortIntroduction;
    private String introduction;
    private String contact;
    private String location;
    private List<String> careers;
    private Set<Field> fields;

    public void editCounselor(Counselor counselor) {
        counselor.editMyPage(name, email, shortIntroduction, introduction, contact, location
                            , careers, fields);
    }
}























