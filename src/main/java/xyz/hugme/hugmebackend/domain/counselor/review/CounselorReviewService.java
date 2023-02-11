package xyz.hugme.hugmebackend.domain.counselor.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CounselorReviewService {
    private final CounselorReviewRepository counselorReviewRepository;

    @Transactional
    public CounselorReview save(CounselorReview review){
        return counselorReviewRepository.save(review);
    }
}
