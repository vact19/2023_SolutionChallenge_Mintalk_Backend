package xyz.hugme.hugmebackend.domain.user.counselor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CounselorRepository extends JpaRepository<Counselor, Long> {

    // Review 가 Null 일 경우를 대비해, LEFT JOIN 한다. 모든 교집합과 A 차집합 (A - B)을 조회
    @Query("SELECT distinct c FROM Counselor c LEFT JOIN FETCH c.counselorReviews cr where c.id = :id")
    Optional<Counselor> findByIdFetchReviews(@Param("id") Long id);

//    @Query("SELECT c.password FROM Counselor c WHERE c.email = :email")
//    String findPasswordByEmail(@Param("email") String email);

    Optional<Counselor> findByEmail(String email);
}