package xyz.hugme.hugmebackend.domain.user.counselor.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
