package xyz.hugme.hugmebackend.domain.user.counselor;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CounselorService {
    private final CounselorRepository counselorRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Counselor save(Counselor counselor) {
        return counselorRepository.save(counselor);
    }

    public List<Counselor> findAll(){
        return counselorRepository.findAll();
    }

    public Counselor findById(Long id) {
        return validateOptionalCounselor(counselorRepository.findById(id));
    }
    public Counselor findByIdFetchReviews(Long id) {
        return validateOptionalCounselor(counselorRepository.findByIdFetchReviews(id));
    }
    public Counselor validatePassword(String email, String rawPassword) {
        Counselor counselor = validateOptionalCounselor(counselorRepository.findByEmail(email));
        if (! passwordEncoder.matches(rawPassword, counselor.getPassword()))
            throw new RuntimeException("비밀번호 불일치");
        return counselor;
    }
    public Counselor findByEmail(String email) {
        return validateOptionalCounselor(counselorRepository.findByEmail(email));
    }

    private Counselor validateOptionalCounselor(Optional<Counselor> counselor){
        return counselor.orElseThrow(() -> new RuntimeException("해당 id로 counselor를 찾을 수 없음"));
    }

}
