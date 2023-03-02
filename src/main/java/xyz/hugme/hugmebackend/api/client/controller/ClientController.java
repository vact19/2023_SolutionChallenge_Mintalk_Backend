package xyz.hugme.hugmebackend.api.client.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.hugme.hugmebackend.api.client.dto.SaveClientDto;
import xyz.hugme.hugmebackend.api.client.service.ApiClientService;
import xyz.hugme.hugmebackend.domain.user.client.Client;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class ClientController {

    private final ApiClientService apiClientService;

    // 내담자 회원가입
    @PostMapping("/clients")
    public ResponseEntity<Void> signUpClient(@RequestBody SaveClientDto saveClientDto){
        Client savedClient = apiClientService.signUpClient(saveClientDto);
        return ResponseEntity.created(URI.create("/clients/my-page/" + savedClient.getId())).build();
    }



}














