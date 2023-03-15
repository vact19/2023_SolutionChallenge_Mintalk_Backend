package xyz.hugme.hugmebackend.domain.user.counselor.review;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.hugme.hugmebackend.domain.user.client.Client;

import java.util.List;

public interface CounselorReviewRepository extends JpaRepository<CounselorReview, Long> {
    List<CounselorReview> findAllByCounselorId(Long counselorId);

    List<CounselorReview> findByClientAndCounselorId(Client client, Long id);
}