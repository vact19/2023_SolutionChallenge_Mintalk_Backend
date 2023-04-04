package xyz.hugme.hugmebackend.domain.user.counselor.review;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.hugme.hugmebackend.domain.user.client.Client;
import xyz.hugme.hugmebackend.domain.user.counselor.Counselor;

import java.util.List;

public interface CounselorReviewRepository extends JpaRepository<CounselorReview, Long> {
    List<CounselorReview> findAllByCounselorId(Long counselorId);

    List<CounselorReview> findByClientAndCounselorId(Client client, Long id);

    Slice<CounselorReview> findByCounselorId(Pageable pageable, Long id);
    List<CounselorReview> findAll();
}