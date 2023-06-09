package xyz.hugme.hugmebackend.domain.user.counselor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.hugme.hugmebackend.domain.user.usersession.UserSession;

import java.util.List;
import java.util.Optional;

public interface CounselorRepository extends JpaRepository<Counselor, Long> {

    // Review 가 Null 일 경우를 대비해, LEFT JOIN 한다. 모든 교집합과 A 차집합 (A - B)을 조회
    @Query("SELECT distinct c FROM Counselor c LEFT JOIN FETCH c.counselorReviews cr where c.id = :id")
    Optional<Counselor> findByIdFetchReviews(@Param("id") Long id);




//    @EntityGraph(attributePaths = { "careers", "fields" }) PK 조회가 아닌 email 조회와 같은 경우는 entityManager를 사용하지 않기 때문에, Entity Class에 명시한 fetch 전략이 아닌 기본 fetch 전략을 따라간다고 한다. 내가 명시한 전략을 따르고 싶을 때 해당 애노테이션 사용. fetchType이 맘대로 되지 않을 때 해당 옵션을 사용해보자.
    Optional<Counselor> findByEmail(String email);

    Counselor findByUserSession(UserSession userSession);

    //다중 검색 메소드
    // @Query("SELECT distinct c FROM Counselor c WHERE c.gender = :gender AND c.fields = :field")
    List<Counselor> findByGenderAndFields(Gender gender, Field field);

    List<Counselor> findByFields(Field field);

    List<Counselor> findByGender(Gender gender);
}