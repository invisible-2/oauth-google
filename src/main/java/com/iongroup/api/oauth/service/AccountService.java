package com.iongroup.api.oauth.service;

import com.iongroup.api.oauth.dto.AccountDto;
import com.iongroup.api.oauth.util.ApiUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class AccountService {

    private final RestTemplate restTemplate;

    @SneakyThrows
    public Boolean existsAccountWithEmail(String email, String token) {
        String url = ApiUtil.BASE_URL + "accounts/email?email={email}";
        return restTemplate.exchange(url, HttpMethod.GET, setAuthorizationHeader(token), Boolean.class, email).getBody();
    }

    @SneakyThrows
    public AccountDto getAccountByEmail(String email, String token) {
        String url = ApiUtil.BASE_URL + "accounts/info/email?email={email}";
        return restTemplate.exchange(url, HttpMethod.GET, setAuthorizationHeader(token), AccountDto.class, email).getBody();
    }

    private HttpEntity<String> setAuthorizationHeader(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);
        return new HttpEntity<>(headers);
    }
}
