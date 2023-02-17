package xyz.hugme.hugmebackend.domain.counselor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CounselorService {
    private final CounselorRepository counselorRepository;

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
}
