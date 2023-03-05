package xyz.hugme.hugmebackend.api.counselor.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;
import xyz.hugme.hugmebackend.domain.user.counselor.Counselor;
import xyz.hugme.hugmebackend.domain.user.counselor.Field;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// 상담사 목록 조회 페이지용 DTO
@Getter
@NoArgsConstructor
public class CounselorListDto {

    private Long id;
    private String name;
    private String shortIntroduction;
    private String location;
    private String profileImageUrl;
    private Set<Field> fields;

    public static CounselorListDto of(Counselor counselor){
        Hibernate.initialize(counselor.getFields());
        return CounselorListDto.builder()
                .id(counselor.getId())
                .name(counselor.getName())
                .shortIntroduction(counselor.getShortIntroduction())
                .profileImageUrl(counselor.getProfileImageUrl())
                .location(counselor.getLocation())
                .fields(counselor.getFields())
                .build();
    }

    public static List<CounselorListDto> ofList(List<Counselor> counselorList){
        return counselorList.stream()
                .map(CounselorListDto::of)
                .collect(Collectors.toList());
    }

    @Builder
    public CounselorListDto(Long id, String name, String shortIntroduction, String location, String profileImageUrl, Set<Field> fields) {
        this.id = id;
        this.name = name;
        this.shortIntroduction = shortIntroduction;
        this.location = location;
        this.fields = fields;
        this.profileImageUrl = profileImageUrl;
    }
}
