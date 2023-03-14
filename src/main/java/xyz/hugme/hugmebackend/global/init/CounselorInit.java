package xyz.hugme.hugmebackend.global.init;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import xyz.hugme.hugmebackend.domain.user.Role;
import xyz.hugme.hugmebackend.domain.user.client.Client;
import xyz.hugme.hugmebackend.domain.user.counselor.Counselor;
import xyz.hugme.hugmebackend.domain.user.counselor.Field;
import xyz.hugme.hugmebackend.domain.user.counselor.Gender;
import xyz.hugme.hugmebackend.domain.user.counselor.review.CounselorReview;
import xyz.hugme.hugmebackend.domain.user.usersession.UserSession;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RequiredArgsConstructor
@Component
public class CounselorInit {
    private final PasswordEncoder passwordEncoder;
    private final EntityManagerFactory emf;

    @PostConstruct
    public void init(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Set<Field> fieldsSet1 = new HashSet<>();
        fieldsSet1.add(Field.ANXIETY);
        fieldsSet1.add(Field.DEPRESSION);
        Set<Field> fieldsSet2 = new HashSet<>();
        fieldsSet2.add(Field.ELDER);
        fieldsSet2.add(Field.DEPRESSION);
        fieldsSet2.add(Field.RELATIONSHIP);
        fieldsSet2.add(Field.ANXIETY);
        fieldsSet2.add(Field.YOUTH);
        List<String> careers1 = new ArrayList<>();
        careers1.add("서울대 자퇴");
        careers1.add("뭐시기 자격증 2급");
        careers1.add("성공회대 졸업");
        List<String> careers2 = new ArrayList<>();
        careers2.add("침착맨 유튜브 누적 4372시간 시청");
        careers2.add("뭐시기 자격증 2급");
        careers2.add("골든리트리버 조련사 2급");

        // 상담사 생성
        Counselor counselor1 = Counselor.builder()
                .name("한현수")
                .shortIntroduction("알고보면따뜻한남자")
                .introduction("마에스트로 한현수")
                .gender(Gender.FEMALE)
                .fields(fieldsSet1)
                .contact("010-현수현수-0100")
                .email("csrf1@gmail.com")
                .password(passwordEncoder.encode("1234"))
                .location("어디시 어디구 어디동")
                .profileImageUrl("https://storage.googleapis.com/mintalk-image-storage/counselors/anajouryo.png")
                .careers(careers1)
                .build();

        Counselor counselor2 = Counselor.builder()
                .name("이한길")
                .shortIntroduction("정열맨이한길")
                .introduction("매력적인 저음을 가진 남자 이한길. 도서관에 가면 볼 수 있습니다")
                .gender(Gender.MALE)
                .fields(fieldsSet2)
                .contact("010-한길한길-0100")
                .email("csrf2@gmail.com")
                .password(passwordEncoder.encode("1234"))
                .location("멋짐")
                .profileImageUrl("https://storage.googleapis.com/mintalk-image-storage/counselors/show-me-pocky.jpg")
                .careers(careers2)
                .build();

        Counselor counselor3 = Counselor.builder()
                .name("쿼카")
                .shortIntroduction("쿼카 == 족제비 == 다람쥐 == 물개")
                .introduction("쿼카는 어떻게 울게요 닝닝닝닝닝닝닝닝닝닝닝닝닝닝닝닝닝닝닝닝닝닝닝닝닝닝닝닝닝닝닝닝닝닝닝닝닝닝닝닝닝닝")
                .gender(Gender.FEMALE)
                .fields(fieldsSet2)
                .contact("010-3434-4343")
                .email("csrf3@gmail.com")
                .password(passwordEncoder.encode("1234"))
                .location("깊은산속 옹달샘")
                .profileImageUrl("https://storage.googleapis.com/mintalk-image-storage/counselors/Quokka.jfif")
                .careers(careers2)
                .build();
        em.persist(counselor3);

        Counselor counselor4 = Counselor.builder()
                .name("주호민")
                .shortIntroduction("상남자")
                .introduction("나 주호민 53세 나처럼 살지 마시오")
                .gender(Gender.FEMALE)
                .fields(fieldsSet2)
                .contact("010-3434-4343")
                .email("csrf4@gmail.com")
                .password(passwordEncoder.encode("1234"))
                .location("고기동 호민촌")
                .profileImageUrl("https://storage.googleapis.com/mintalk-image-storage/counselors/homin.jfif")
                .careers(careers2)
                .build();
        em.persist(counselor4);

        Counselor counselor5 = Counselor.builder()
                .name("김경민")
                .shortIntroduction("수염이 매력적인 남자")
                .introduction("김경민.equals(이찬균) => TRUE 김경민.equals(이찬균) => TRUE 김경민.equals(이찬균) => TRUE 김경민.equals(이찬균) => TRUE 김경민.equals(이찬균) => TRUE 김경민.equals(이찬균) => TRUE 김경민.equals(이찬균) => TRUE")
                .gender(Gender.MALE)
                .fields(fieldsSet2)
                .contact("010-3434-4343")
                .email("csrf5@gmail.com")
                .password(passwordEncoder.encode("1234"))
                .location("경기 김포시 고촌읍")
                .profileImageUrl("https://storage.googleapis.com/mintalk-image-storage/counselors/loveYou.png")
                .careers(careers2)
                .build();
        em.persist(counselor5);

        Counselor counselor6 = Counselor.builder()
                .name("도토리")
                .shortIntroduction("나를 도토리라고 부르는 것은 괜찮아 사실이니까")
                .introduction("하지만 나를 도토리라고 부르는 것만은 참을 수 없다!")
                .gender(Gender.MALE)
                .fields(fieldsSet2)
                .contact("010-3434-4343")
                .email("csrf6@gmail.com")
                .password(passwordEncoder.encode("1234"))
                .location("설악산")
                .profileImageUrl("https://storage.googleapis.com/mintalk-image-storage/counselors/dotory.jpg")
                .careers(careers2)
                .build();
        em.persist(counselor6);

        Counselor counselor7 = Counselor.builder()
                .name("침착맨")
                .shortIntroduction("이병건")
                .introduction("저는 나와의 싸움에서 자꾸 져요. 그런데 다르게 생각해보면 이긴 쪽도 나잖아?")
                .gender(Gender.MALE)
                .fields(fieldsSet2)
                .contact("010-3434-4343")
                .email("csrf7@gmail.com")
                .password(passwordEncoder.encode("1234"))
                .location("왕십리")
                .profileImageUrl("https://storage.googleapis.com/mintalk-image-storage/counselors/sorry.jpg")
                .careers(careers2)
                .build();
        em.persist(counselor7);

        // 내담자 생성
        Client client1 = Client.builder()
                .gender(Gender.MALE)
                .name("내담자1")
                .email("csrf1@gmail.com")
                .password(passwordEncoder.encode("1234"))
                .build();
        Client client2 = Client.builder()
                .gender(Gender.FEMALE)
                .name("내담자2")
                .email("csrf2@gmail.com")
                .password(passwordEncoder.encode("1234"))
                .build();

        // 리뷰 생성
        CounselorReview review1 = CounselorReview.builder()
                .rate(5)
                .content("한길선생님 감사합니다")
                .counselor(counselor1)
                .client(client1)
                .build();

        // CounselorReview의 자식 엔티티 Counselor, Client cascade persist
        // persist counselor 1
        // persist client 1
        // persist review => Auto Increment 이므로 Insert SQL
        em.persist(review1);

        CounselorReview review2 = CounselorReview.builder()
                .rate(3)
                .content("한길선생님 너무 감사합니다")
                .counselor(counselor1)
                .client(client2)
                .build();

        em.persist(review2);

        CounselorReview review3 = CounselorReview.builder()
                .rate(2)
                .content("한길선생님 저는 기어다닐거에요")
                .counselor(counselor2)
                .client(client2)
                .build();
        em.persist(review3);

        CounselorReview review4 = CounselorReview.builder()
                .rate(1)
                .content("한길선생님 저는 누울거에요")
                .counselor(counselor2)
                .client(client2)
                .build();
        em.persist(review4);

        CounselorReview review5 = CounselorReview.builder()
                .rate(3)
                .content("한길선생님 저 취했ㅆㅓ여")
                .counselor(counselor2)
                .client(client2)
                .build();
        em.persist(review5);

        CounselorReview review6 = CounselorReview.builder()
                .rate(5)
                .content("이한길에게 상담받고 나를 찾는 회사 많아졌다")
                .counselor(counselor2)
                .client(client2)
                .build();
        em.persist(review6);

        CounselorReview review7 = CounselorReview.builder()
                .rate(3)
                .content("이한길에게 상담받고 암이 나았습니다")
                .counselor(counselor2)
                .client(client2)
                .build();
        em.persist(review7);

        CounselorReview review8 = CounselorReview.builder()
                .rate(4)
                .content("이한길에게 상담받고 암이 나았습니다")
                .counselor(counselor2)
                .client(client2)
                .build();
        em.persist(review8);

        CounselorReview review9 = CounselorReview.builder()
                .rate(1)
                .content("이 상담사 말고 침착맨 상담사님이 더 잘해주세요")
                .counselor(counselor2)
                .client(client2)
                .build();
        em.persist(review9);

        CounselorReview review10 = CounselorReview.builder()
                .rate(1)
                .content("한길님 저랑 미즈컨테이너 가주세요")
                .counselor(counselor2)
                .client(client2)
                .build();
        em.persist(review10);

        // 세션 생성
        UserSession userSession1 = createUserSession();UserSession userSession2 = createUserSession();UserSession userSession3 = createUserSession();
        UserSession userSession4 = createUserSession();UserSession userSession5 = createUserSession();UserSession userSession6 = createUserSession();
        UserSession userSession7 = createUserSession();UserSession userSession8 = createUserSession();UserSession userSession9 = createUserSession();

        em.persist(userSession1);em.persist(userSession2);em.persist(userSession3);em.persist(userSession4);
        em.persist(userSession5);em.persist(userSession6);em.persist(userSession7);em.persist(userSession8);em.persist(userSession9);

        counselor1.setUserSession(userSession1);counselor2.setUserSession(userSession2);
        counselor3.setUserSession(userSession3);counselor4.setUserSession(userSession4);
        counselor5.setUserSession(userSession5);counselor6.setUserSession(userSession6);
        counselor7.setUserSession(userSession7);

        client1.setUserSession(userSession8);
        client2.setUserSession(userSession9);

        tx.commit();
        em.close();
    }

    private UserSession createUserSession() {
        return UserSession.builder()
                .expirationDate(LocalDateTime.now())
                .role(Role.COUNSELOR)
                .build();
    }
}





















