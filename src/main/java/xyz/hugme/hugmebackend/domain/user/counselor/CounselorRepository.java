package xyz.hugme.hugmebackend.domain.user.counselor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CounselorRepository extends JpaRepository<Counselor, Long> {

    @Query("SELECT distinct c FROM Counselor c JOIN FETCH c.counselorReviews cr where c.id = :id")
    Counselor findByIdFetchReviews(@Param("id") Long id);

//    @Query("SELECT c.password FROM Counselor c WHERE c.email = :email")
//    String findPasswordByEmail(@Param("email") String email);

    Counselor findByEmail(String email);
}