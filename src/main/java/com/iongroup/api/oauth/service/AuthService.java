package com.iongroup.api.oauth.service;

import com.iongroup.api.oauth.dto.LoginRequest;
import com.iongroup.api.oauth.dto.RegistrationRequest;
import com.iongroup.api.oauth.util.ApiUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class AuthService {

    private final RestTemplate restTemplate;

    @SneakyThrows
    public String register(RegistrationRequest request) {
        String url = ApiUtil.BASE_URL + "auth/registration";
        return restTemplate.postForObject(url, request, String.class);
    }

    @SneakyThrows
    public String login(LoginRequest loginRequest) {
        String url = ApiUtil.BASE_URL + "auth/login";
        return restTemplate.postForObject(url, loginRequest, String.class);
    }

}
