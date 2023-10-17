package com.iongroup.api.oauth.controller;

import com.iongroup.api.oauth.dto.AccountDto;
import com.iongroup.api.oauth.dto.LoginRequest;
import com.iongroup.api.oauth.dto.RegistrationRequest;
import com.iongroup.api.oauth.dto.UserDto;
import com.iongroup.api.oauth.model.GoogleAccount;
import com.iongroup.api.oauth.service.AccountService;
import com.iongroup.api.oauth.service.AuthService;
import com.iongroup.api.oauth.util.ApiUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class GoogleAuthController {

    private final AuthService authService;
    private final AccountService accountService;
    private final RestTemplate restTemplate;


    @GetMapping("/secured")
    public String welcome(OAuth2AuthenticationToken oAuth2AuthenticationToken) {

        GoogleAccount googleAccount = GoogleAccount.deserialize(oAuth2AuthenticationToken.getPrincipal().getAttributes());


        try {
            String token = authService.login(new LoginRequest("admin", "admin"));
            System.out.println(token);

            if (!accountService.existsAccountWithEmail(googleAccount.getEmail(), token)) {

                authService.register(new RegistrationRequest(
                        ApiUtil.generateUsername(),
                        "1",
                        new UserDto(
                                googleAccount.getFirstName(),
                                googleAccount.getLastName(),
                                null,
                                googleAccount.getEmail()
                        )
                ));
            }

        } catch (ResourceAccessException | HttpClientErrorException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(oAuth2AuthenticationToken.getPrincipal().getAttributes());
        return "Connected as: " + googleAccount.getFirstName() + " " + googleAccount.getLastName() + ", " + googleAccount.getEmail();
    }

    @GetMapping("/")
    public String secured() {
        return "Welcome Home!";
    }
}
