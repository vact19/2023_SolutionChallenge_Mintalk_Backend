package xyz.hugme.hugmebackend.api.counselor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hugme.hugmebackend.api.common.RspsTemplate;
import xyz.hugme.hugmebackend.api.common.SingleRspsTemplate;
import xyz.hugme.hugmebackend.api.counselor.dto.CounselorInfoDto;
import xyz.hugme.hugmebackend.api.counselor.dto.CounselorListDto;
import xyz.hugme.hugmebackend.domain.counselor.Counselor;
import xyz.hugme.hugmebackend.domain.counselor.CounselorService;
import xyz.hugme.hugmebackend.domain.counselor.review.CounselorReviewService;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ApiCounselorService {
    private final CounselorService counselorService;
    private final CounselorReviewService counselorReviewService;

    public RspsTemplate<CounselorListDto> findAll(){
        List<Counselor> counselorList = counselorService.findAll();
        // Entity의 리스트를 Dto의 리스트로 변환
        List<CounselorListDto> counselorListDtoList = CounselorListDto.ofList(counselorList);

        return new RspsTemplate<>(HttpStatus.OK.value(), counselorListDtoList);
    }

    public SingleRspsTemplate<CounselorInfoDto> findCounselorAndReviewsById(Long id){
        // join 사용하지 않고, 상담사와 상담사 리뷰를 각각 조회함.
//        Counselor counselor = counselorService.findById(id);
//        List<CounselorReview> counselorReviews = counselorReviewService.findAllByCounselorId(id);

        // 그냥 fetch join 써야지
        Counselor counselor = counselorService.findByIdFetchReviews(id);
        CounselorInfoDto counselorInfoDto = CounselorInfoDto.of(counselor, counselor.getCounselorReviews());
        return new SingleRspsTemplate<>(HttpStatus.OK.value(), counselorInfoDto);
    }
}