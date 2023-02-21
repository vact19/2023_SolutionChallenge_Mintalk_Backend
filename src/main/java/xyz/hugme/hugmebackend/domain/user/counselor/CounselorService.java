package xyz.hugme.hugmebackend.domain.user.counselor;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hugme.hugmebackend.domain.user.counselor.review.CounselorReviewRepository;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CounselorService {
    private final CounselorRepository counselorRepository;
    private final PasswordEncoder passwordEncoder;
    private final CounselorReviewRepository counselorReviewRepository;

    @Transactional
    public Counselor save(Counselor counselor) {
        return counselorRepository.save(counselor);
    }

    public List<Counselor> findAll(){
        return counselorRepository.findAll();
    }

    public Counselor findById(Long id) {
        return counselorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Counselor id 잘못됨"));
    }

    public Counselor findByIdFetchReviews(Long id) {
        return counselorRepository.findByIdFetchReviews(id);
    }

    public Counselor validate(String email, String rawPassword) {
        Counselor counselor = counselorRepository.findByEmail(email);
        if (! passwordEncoder.matches(rawPassword, counselor.getPassword()))
            throw new RuntimeException("비밀번호 불일치");
        return counselor;
    }
}
