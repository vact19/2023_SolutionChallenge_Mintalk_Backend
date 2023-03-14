package xyz.hugme.hugmebackend.api.counselor.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import xyz.hugme.hugmebackend.api.counselor.dto.*;
import xyz.hugme.hugmebackend.domain.file.FileService;
import xyz.hugme.hugmebackend.domain.user.Role;
import xyz.hugme.hugmebackend.domain.user.counselor.Counselor;
import xyz.hugme.hugmebackend.domain.user.counselor.CounselorService;
import xyz.hugme.hugmebackend.domain.user.counselor.Field;
import xyz.hugme.hugmebackend.domain.user.counselor.Gender;
import xyz.hugme.hugmebackend.domain.user.counselor.review.CounselorReview;
import xyz.hugme.hugmebackend.domain.user.usersession.UserSession;
import xyz.hugme.hugmebackend.domain.user.usersession.UserSessionService;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ApiCounselorService {
    private final UserSessionService userSessionService;
    private final CounselorService counselorService;
    private final PasswordEncoder passwordEncoder;
    private final FileService fileService;

    public List<CounselorListDto> findAll(){
        List<Counselor> counselorList = counselorService.findAll();
        // Entity의 리스트를 Dto의 리스트로 변환
        List<CounselorListDto> counselorListDtoList = CounselorListDto.ofList(counselorList);

        return counselorListDtoList;
    }

    //상담사를 성별과 분야로 조회
    public List<CounselorListDto> findByGenderAndFields(Gender gender, Field field){
        List<Counselor> counselorList = counselorService.findByGenderAndField(gender, field);

        return CounselorListDto.ofList(counselorList);
    }

    public CounselorInfoDto getPublicCounselorInfo(Long id){
        // 상담사와 해당 리뷰를 한꺼번에 조회하기 위해 fetch join 사용
        Counselor counselor = counselorService.findByIdFetchReviews(id);
        List<CounselorReview> counselorReviews = counselor.getCounselorReviews();

        Hibernate.initialize(counselor.getCareers()); // 프록시 강제 초기화
        Hibernate.initialize(counselor.getFields()); // 프록시 강제 초기화

        return CounselorInfoDto.of(counselor, counselorReviews);
    }

    @Transactional
    public Counselor signUp(CounselorSignUpDto counselorSignUpDto) {
        // password encode 후 save()
        String encodedPassword = passwordEncoder.encode(counselorSignUpDto.getPassword());
        Counselor counselor = counselorSignUpDto.toEntity(encodedPassword);

        // 세션객체 생성 후 counselor 에 저장
        UserSession userSession = UserSession.builder()
                .expirationDate(LocalDateTime.now())
                .role(Role.COUNSELOR)
                .build();
        counselor.setUserSession(userSessionService.save(userSession));

        return counselorService.save(counselor);
    }

    @Transactional
    public void editCounselor(Counselor counselor, CounselorMyPageEditDto counselorMyPageEditDto) {
        MultipartFile profileImage = counselorMyPageEditDto.getProfileImage();

        // strings = {public URL, BlobName}
        String[] strings = new String[2];
        strings[0] = counselor.getProfileImageUrl();
        strings[1] = counselor.getBlobName();

        if (! profileImage.isEmpty()) { // Return whether the uploaded file is empty, that is, either no file has been chosen in the multipart form or the chosen file has no content.
            strings = fileService.saveCounselorProfileImg(counselor, profileImage);
            counselor.setDefaultProfile(false);
        }

        // DTO 내용과 {프로필사진 URL, 사진객체 이름}이 담긴 배열을 이용하여 counselor 의 필드들 바꾼다.
        counselorMyPageEditDto.editCounselor(counselor, strings);
        counselorService.save(counselor); // counselor는 detached 되어있다. 다시 persist
    }

    @Transactional
    public CounselorMyPageViewDto viewMyPage(Counselor counselor) {
        Counselor mergedCounselor = counselorService.save(counselor); // 지연로딩을 위해 detached 상태의 counselor merge
        Hibernate.initialize(mergedCounselor.getFields()); // 프록시 강제 초기화
        Hibernate.initialize(mergedCounselor.getCareers()); // 프록시 강제 초기화
        return CounselorMyPageViewDto.of(mergedCounselor);
    }
}













