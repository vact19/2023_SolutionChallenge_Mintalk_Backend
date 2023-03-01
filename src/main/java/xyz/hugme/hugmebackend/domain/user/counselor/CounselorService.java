package xyz.hugme.hugmebackend.domain.user.counselor;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hugme.hugmebackend.domain.common.FindBy;
import xyz.hugme.hugmebackend.global.exception.BusinessException;
import xyz.hugme.hugmebackend.global.exception.ErrorCode;

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

    public Counselor findByIdFetchReviews(Long id) {
        return validateOptionalCounselor(counselorRepository.findByIdFetchReviews(id), FindBy.ID);
    }

    public Counselor validatePassword(String email, String rawPassword) {
        Counselor counselor = validateOptionalCounselor(counselorRepository.findByEmail(email), FindBy.EMAIL);
        if (! passwordEncoder.matches(rawPassword, counselor.getPassword()))
            throw new BusinessException(ErrorCode.NOT_MATCHING_PASSWORD);
        return counselor;
    }

    public Counselor findByEmail(String email) {
        return validateOptionalCounselor(counselorRepository.findByEmail(email), FindBy.EMAIL);
    }

    public Counselor findById(Long id) {
        return validateOptionalCounselor(counselorRepository.findById(id), FindBy.ID);
    }

    public Counselor findBySessionCounselorId(Long id) {
        return counselorRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.COUNSELOR_NOT_AUTHENTICATED));
    }

    private Counselor validateOptionalCounselor(Optional<Counselor> counselor, FindBy findBy){
        switch (findBy){
            case ID: return counselor.orElseThrow(() -> new BusinessException(ErrorCode.COUNSELOR_ID_NOT_FOUND));
            case EMAIL: return counselor.orElseThrow(() -> new BusinessException(ErrorCode.COUNSELOR_EMAIL_NOT_FOUND));
            default: throw new RuntimeException("Enum FindBy를 올바르게 명시하지 않음");
        }
    }
}




