package xyz.hugme.hugmebackend.api.counselor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.hugme.hugmebackend.api.common.RspsTemplate;
import xyz.hugme.hugmebackend.api.common.SingleRspsTemplate;
import xyz.hugme.hugmebackend.api.counselor.dto.*;
import xyz.hugme.hugmebackend.api.counselor.service.ApiCounselorService;
import xyz.hugme.hugmebackend.domain.user.counselor.Counselor;
import xyz.hugme.hugmebackend.domain.user.counselor.Field;
import xyz.hugme.hugmebackend.domain.user.counselor.Gender;
import xyz.hugme.hugmebackend.global.auth.SessionCounselor;

import javax.validation.Valid;
import java.net.URI;

@RequiredArgsConstructor
@RestController
public class CounselorController {

    private final ApiCounselorService apiCounselorService;


    // 테스트용 메소드. 나중에 조회기능 만들때 이거 바꾸면 될 듯
    @GetMapping
    public RspsTemplate<CounselorListDto> getList(){
        RspsTemplate<CounselorListDto> rspsTemplate = apiCounselorService.findAll();
        return rspsTemplate;
    }

    // 상담사 전체 목록 조회
    @GetMapping ("/counselors/list")
    public RspsTemplate<CounselorListDto> getCounselorList(){
        RspsTemplate<CounselorListDto> listDtoRspsTemplate = apiCounselorService.findAll();
        return listDtoRspsTemplate;
    }

    //성별과 분야로 상담사 검색
    @GetMapping("/counselors")
    public RspsTemplate<CounselorListDto> getCounselor(@RequestParam(value = "gender") Gender  gender,@RequestParam(value="field") Field field){
        RspsTemplate<CounselorListDto> rspsTemplate = apiCounselorService.findByGenderAndFields(gender, field);
        return rspsTemplate;
    }


    // 상담사 회원가입
    @PostMapping("/counselors")
    public ResponseEntity<Void> signIn(@RequestBody @Valid CounselorSignUpDto counselorSignUpDto){
        Counselor savedCounselor = apiCounselorService.signUp(counselorSignUpDto);
        return ResponseEntity.created(URI.create("/counselors/" + savedCounselor.getId())).build();
    }
    // 외부 공개용 상담사 마이페이지 조회
    @GetMapping("/counselors/{id}")
    public SingleRspsTemplate<CounselorInfoDto> getPublicCounselorInfo(@PathVariable Long id){
        // id로 리뷰, 상담사 찾고
        // 해당 정보들을 Dto에 넣어준다
        SingleRspsTemplate<CounselorInfoDto> rspsTemplate = apiCounselorService.getPublicCounselorInfo(id);
        return rspsTemplate;
    }
    //상담사 계정으로 본인 마이페이지 진입
    @GetMapping("/counselors/my-page")
    public SingleRspsTemplate<CounselorMyPageViewDto> viewMyPage(@SessionCounselor Counselor counselor){
        CounselorMyPageViewDto resultDto = apiCounselorService.viewMyPage(counselor);
        return new SingleRspsTemplate<>(HttpStatus.OK.value(), resultDto);
    }
    // 상담사 마이페이지 수정
    // 자기가 자기 페이지를 수정하는 것이므로, PathVariable 사용할 필요 없다.
    @PatchMapping("/counselors/my-page")
    public ResponseEntity<Void> editMyPage(@SessionCounselor Counselor counselor, CounselorMyPageEditDto counselorMyPageEditDto){
        apiCounselorService.editCounselor(counselor, counselorMyPageEditDto);
        return ResponseEntity.noContent().build();
    }

//    @PostMapping("/upload")
//    public String testGCSUpload(@RequestParam("img")MultipartFile multipartFile) throws IOException {
//        // 인증 설정
//        Bucket bucket = storage.get("mintalk-image-storage");
//        // 파일 이름 설정. 중복을 피해 UUID 사용할 것.
//        // OriginalFilename 은 쓰지 않는다. 파일이름에 포함된 특수문자나 공백 등이 urlEncoded 되어, 생성될 조회 URL 예측이 어려워짐
//        String fileName = COUNSELOR_IMAGE_PREFIX + UUID.randomUUID();
//
//        // 버킷에 파일 업로드. create new Blob in this bucket. bucket.delete()는 버킷 삭제임.
//        Blob blob = bucket.create(fileName, multipartFile.getBytes());
//        return blob.toString();
//        // 아래는 현재 필요없음
////        blob.createAcl("allUsers", Acl.Role.READER); // 해당 파일을 모든 유저에게 읽기권한만 주고 싶을 경우 사용. 버킷의 모든 오브젝트가 public 하게 공개되어 있으므로 지금은 필요없다.
////        Path filePath = Paths.get(fileName);
////        Files.write(filePath, multipartFile.getBytes()); // 로컬 저장용. 이렇게 하면 default FilePath 인 프로젝트의 루트 디렉토리에 해당 파일이 저장된다.
////        // 업로드 완료 후 파일 삭제
////        Files.deleteIfExists(filePath);
//    }
//
//    @GetMapping("/delete")
//    public boolean testGCSDelete(@RequestParam String blobName) throws IOException {
//        boolean deleted = storage.delete("mintalk-image-storage", blobName);
//        return deleted;
//        // 아래는 현재 필요없음
////        blob.createAcl("allUsers", Acl.Role.READER); // 해당 파일을 모든 유저에게 읽기권한만 주고 싶을 경우 사용. 버킷의 모든 오브젝트가 public 하게 공개되어 있으므로 지금은 필요없다.
////        Path filePath = Paths.get(fileName);
////        Files.write(filePath, multipartFile.getBytes()); // 로컬 저장용. 이렇게 하면 default FilePath 인 프로젝트의 루트 디렉토리에 해당 파일이 저장된다.
////        // 업로드 완료 후 파일 삭제
////        Files.deleteIfExists(filePath);
//    }


//    @GetMapping("/test")
//    public String testGCS() throws IOException {
//        // 버킷과 오브젝트 이름을 넣어 Blob객체 반환
//        Blob blob = storage.get(BlobId.of("mintalk-image-storage", "counselors/anajouryo.png"));
//        blob.downloadTo(Paths.get("C://Users//woo//txt/newfile.png"));
//
//        return blob.toString();
//    }




}













