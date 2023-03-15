package xyz.hugme.hugmebackend.domain.user.counselor.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hugme.hugmebackend.domain.user.client.Client;
import xyz.hugme.hugmebackend.global.exception.BusinessException;
import xyz.hugme.hugmebackend.global.exception.ErrorCode;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CounselorReviewService {
    private final CounselorReviewRepository counselorReviewRepository;

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
}
