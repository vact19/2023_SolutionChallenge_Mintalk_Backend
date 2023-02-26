package xyz.hugme.hugmebackend.api.counselor.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hugme.hugmebackend.api.counselor.review.dto.SaveReviewDto;
import xyz.hugme.hugmebackend.domain.user.client.Client;
import xyz.hugme.hugmebackend.domain.user.client.ClientService;
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
    private final ClientService clientService;

    @Transactional
    public void saveReview(Long counselorId, Client client, SaveReviewDto saveReviewDto) {
        // CounselorReview Casecade Persist => Client, Counselor
        Counselor counselor = counselorService.findById(counselorId);

        CounselorReview counselorReview = saveReviewDto.toEntity(counselor, client);
        counselorReviewService.save(counselorReview);
    }
}
