package xyz.hugme.hugmebackend.api.counselor.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.hugme.hugmebackend.domain.user.counselor.review.CounselorReviewService;

@RequiredArgsConstructor
@Service
public class ApiCounselorReviewService {

    private final CounselorReviewService counselorReviewService;
}
