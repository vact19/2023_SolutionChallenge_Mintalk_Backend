package xyz.hugme.hugmebackend.domain.file;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.common.io.Files;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.hugme.hugmebackend.domain.user.counselor.Counselor;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileService {
    private final Storage storage;
    private final String COUNSELOR_IMAGE_PREFIX = "counselors/";
    private final String UNIQUE_BUCKET_NAME = "mintalk-image-storage"; // 현재 버킷은 하나만 사용
    private final String PUBLIC_URL_PREFIX = "https://storage.googleapis.com/";

    // 반환값 배열: {저장된 파일의 공개 URL, blobName, default profile (true or false)}
    public String[] saveCounselorProfileImg(Counselor counselor, MultipartFile profileImage){
        Bucket bucket = storage.get(UNIQUE_BUCKET_NAME);
        String fileName = profileImage.getOriginalFilename();
        String ext = Files.getFileExtension(fileName);

        // 기본 프사가 아닐 경우 현재 이미지 삭제
        if (! counselor.isDefaultProfile()){
            boolean isDeleted = storage.delete(UNIQUE_BUCKET_NAME, counselor.getBlobName());
            log.info(String.valueOf(isDeleted));
        }


        // 파일 이름 설정. 중복을 피해 UUID 사용할 것.
        // OriginalFilename 은 쓰지 않는다. 파일이름에 포함된 공백 등의 Non-ASCII 문자가 urlEncoded 되어, 생성될 조회 URL 예측이 어려워짐
        String blobName = COUNSELOR_IMAGE_PREFIX + UUID.randomUUID() + "." +ext;

        Blob blob;
        try {
            // 버킷에 파일 업로드. create new Blob in this bucket.
            // bucket.delete()는 버킷 삭제임.
            blob = bucket.create(blobName, profileImage.getBytes(), profileImage.getContentType());
        } catch (IOException e){
            // 체크 예외는 트랜잭션 롤백 X
            // todo 나중에 getBytes()에서 IOEXCEPTION 이 발생하는 예시 예외상황을 찾고, 알맞은 응답을 내리는 Custom Exception 을 throw 하자.
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        // public 조회 url 반환
        return new String[]{PUBLIC_URL_PREFIX + UNIQUE_BUCKET_NAME + "/" + blobName
                , blob.getName()};
    }
}














