package xyz.hugme.hugmebackend.api.counselor.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.hugme.hugmebackend.domain.user.counselor.Counselor;
import xyz.hugme.hugmebackend.domain.user.counselor.Field;

import java.util.List;
import java.util.Set;

// 마이페이지 접근시 개인정보 출력용
@Getter
@NoArgsConstructor
public class CounselorMyPageViewDto {
    private String name;
    private String email;
    private String shortIntroduction;
    private String introduction;
    private String contact;
    private String location;
    private String profileImageUrl;
    private List<String> careers;
    private Set<Field> fields;

    public static CounselorMyPageViewDto of(Counselor counselor){
        return CounselorMyPageViewDto.builder()
                .name(counselor.getName())
                .email(counselor.getEmail())
                .shortIntroduction(counselor.getShortIntroduction())
                .introduction(counselor.getIntroduction())
                .contact(counselor.getContact())
                .location(counselor.getLocation())
                .profileImageUrl(counselor.getProfileImageUrl())
                .careers(counselor.getCareers())
                .fields(counselor.getFields())
                .build();
    }
    @Builder
    public CounselorMyPageViewDto(String name, String email, String shortIntroduction, String introduction, String contact, String location, String profileImageUrl,List<String> careers, Set<Field> fields) {
        this.name = name;
        this.email = email;
        this.shortIntroduction = shortIntroduction;
        this.introduction = introduction;
        this.contact = contact;
        this.location = location;
        this.profileImageUrl = profileImageUrl;
        this.careers = careers;
        this.fields = fields;
    }
}
