package xyz.hugme.hugmebackend.api.counselor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hugme.hugmebackend.api.auth.dto.LoginDto;
import xyz.hugme.hugmebackend.api.common.RspsTemplate;
import xyz.hugme.hugmebackend.api.common.SingleRspsTemplate;
import xyz.hugme.hugmebackend.api.counselor.dto.CounselorInfoDto;
import xyz.hugme.hugmebackend.api.counselor.dto.CounselorListDto;
import xyz.hugme.hugmebackend.api.counselor.dto.CounselorSignUpDto;
import xyz.hugme.hugmebackend.domain.user.counselor.Counselor;
import xyz.hugme.hugmebackend.domain.user.counselor.CounselorService;
import xyz.hugme.hugmebackend.domain.user.counselor.review.CounselorReview;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ApiCounselorService {
    private final CounselorService counselorService;
    private final PasswordEncoder passwordEncoder;

    public RspsTemplate<CounselorListDto> findAll(){
        List<Counselor> counselorList = counselorService.findAll();
        // Entity의 리스트를 Dto의 리스트로 변환
        List<CounselorListDto> counselorListDtoList = CounselorListDto.ofList(counselorList);

        return new RspsTemplate<>(HttpStatus.OK.value(), counselorListDtoList);
    }

    public SingleRspsTemplate<CounselorInfoDto> findCounselorAndReviewsById(Long id){
        // 상담사와 해당 리뷰를 한꺼번에 조회하기 위해 fetch join 사용
        Counselor counselor = counselorService.findByIdFetchReviews(id);
        List<CounselorReview> counselorReviews = counselor.getCounselorReviews();
        CounselorInfoDto counselorInfoDto = CounselorInfoDto.of(counselor, counselorReviews);

        return new SingleRspsTemplate<>(HttpStatus.OK.value(), counselorInfoDto);
    }

    public Counselor signIn(LoginDto.Request request) {
        // 비밀번호 검증
        return counselorService.validate(request.getEmail(), request.getPassword());
    }

    public Counselor signUp(CounselorSignUpDto counselorSignUpDto) {
        // password encode 후 save()
        String encodedPassword = passwordEncoder.encode(counselorSignUpDto.getPassword());
        Counselor counselor = counselorSignUpDto.toEntity(encodedPassword);
        return counselorService.save(counselor);
    }
}













