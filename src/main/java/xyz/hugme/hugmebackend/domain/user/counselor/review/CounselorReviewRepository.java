package xyz.hugme.hugmebackend.domain.user.counselor.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CounselorReviewRepository extends JpaRepository<CounselorReview, Long> {
    List<CounselorReview> findAllByCounselorId(Long counselorId);
}