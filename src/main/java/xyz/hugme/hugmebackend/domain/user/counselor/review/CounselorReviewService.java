package xyz.hugme.hugmebackend.domain.user.counselor.review;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hugme.hugmebackend.api.counselor.review.dto.CounselorReviewDto;
import xyz.hugme.hugmebackend.domain.user.client.Client;
import xyz.hugme.hugmebackend.domain.user.counselor.Counselor;
import xyz.hugme.hugmebackend.domain.user.counselor.CounselorService;
import xyz.hugme.hugmebackend.global.exception.BusinessException;
import xyz.hugme.hugmebackend.global.exception.ErrorCode;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CounselorReviewService {
    private final CounselorReviewRepository counselorReviewRepository;
    private final CounselorService counselorService;
    @Transactional
    public CounselorReview save(CounselorReview review){
        return counselorReviewRepository.save(review);
    }

    public List<CounselorReview> findAllByCounselorId(Long id) {
        return counselorReviewRepository.findAllByCounselorId(id);
    }

    public CounselorReview findById(Long reviewId) {
        return counselorReviewRepository.findById(reviewId)
                .orElseThrow(() -> new BusinessException(ErrorCode.REVIEW_NOT_FOUND));
    }

    @Transactional
    public void deleteById(Long reviewId) {
        counselorReviewRepository.deleteById(reviewId);
    }

    public List<CounselorReview> findByClientAndCounselorId(Client client, Long id) {
        return counselorReviewRepository.findByClientAndCounselorId(client, id);
    }

    public Slice<CounselorReviewDto> findAll(Pageable pageable, Long id){
        Slice<CounselorReview> slice = counselorReviewRepository.findByCounselorId(pageable, id);
        return slice.map(CounselorReview::toDto);
    }
}
