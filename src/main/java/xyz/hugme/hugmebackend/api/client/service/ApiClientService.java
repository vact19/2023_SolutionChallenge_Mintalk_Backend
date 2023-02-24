package xyz.hugme.hugmebackend.api.client.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hugme.hugmebackend.api.auth.dto.LoginDto;
import xyz.hugme.hugmebackend.api.client.dto.SaveClientDto;
import xyz.hugme.hugmebackend.domain.user.client.Client;
import xyz.hugme.hugmebackend.domain.user.client.ClientService;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ApiClientService {
    private final ClientService clientService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Client signUpClient(SaveClientDto saveClientDto) {
        String encodedPassword = passwordEncoder.encode(saveClientDto.getPassword());
        Client client = saveClientDto.toEntity(encodedPassword);
        return clientService.save(client);
    }

    public Client validateSignIn(LoginDto loginDto) {
        return clientService.validate(loginDto.getEmail(), loginDto.getPassword());
    }
}















