package xyz.hugme.hugmebackend.api.client.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.hugme.hugmebackend.api.client.dto.SaveClientDto;
import xyz.hugme.hugmebackend.api.client.service.ApiClientService;
import xyz.hugme.hugmebackend.api.common.SingleRspsTemplate;
import xyz.hugme.hugmebackend.api.common.UserStatus;
import xyz.hugme.hugmebackend.domain.user.client.Client;
import xyz.hugme.hugmebackend.global.auth.SessionStatus;

import javax.validation.Valid;
import java.net.URI;

@RequiredArgsConstructor
@RestController
public class ClientController {

    private final ApiClientService apiClientService;

    // 내담자 회원가입
    @PostMapping("/clients")
    public ResponseEntity<SingleRspsTemplate<Void>> signUpClient(@RequestBody @Valid SaveClientDto saveClientDto, @SessionStatus UserStatus userStatus){
        Client savedClient = apiClientService.signUpClient(saveClientDto);
        SingleRspsTemplate<Void> rspsTemplate = new SingleRspsTemplate<>(HttpStatus.CREATED.value(), userStatus);
        return ResponseEntity.created(URI.create("/clients/my-page/" + savedClient.getId())).body(rspsTemplate);
    }
}














