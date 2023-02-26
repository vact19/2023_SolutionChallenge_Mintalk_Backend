package xyz.hugme.hugmebackend.api.counselor.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hugme.hugmebackend.api.counselor.review.dto.ReviewDto;
import xyz.hugme.hugmebackend.domain.user.client.Client;
import xyz.hugme.hugmebackend.domain.user.counselor.Counselor;
import xyz.hugme.hugmebackend.domain.user.counselor.CounselorService;
import xyz.hugme.hugmebackend.domain.user.counselor.review.CounselorReview;
import xyz.hugme.hugmebackend.domain.user.counselor.review.CounselorReviewService;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ApiCounselorReviewService {

    private final CounselorReviewService counselorReviewService;
    private final CounselorService counselorService;

    @Transactional
    public void saveReview(Long counselorId, Client client, ReviewDto reviewDto) {
        // CounselorReview Casecade Persist => Client, Counselor
        Counselor counselor = counselorService.findById(counselorId);

        CounselorReview counselorReview = reviewDto.toEntity(counselor, client);
        counselorReviewService.save(counselorReview);
    }

    @Transactional
    public void editReview(Long reviewId, Client client, ReviewDto reviewDto) {
        CounselorReview counselorReview = counselorReviewService.findById(reviewId);
        counselorReview.validateUpdateReview(client.getId());
        counselorReview.updateReview(reviewDto.getRate(), reviewDto.getContent());
    }

    @Transactional
    public void deleteReview(Long reviewId, Client client) {
        CounselorReview counselorReview = counselorReviewService.findById(reviewId);
        counselorReview.validateUpdateReview(client.getId());
        counselorReviewService.deleteById(reviewId);
    }
}
