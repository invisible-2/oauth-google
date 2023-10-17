package com.iongroup.api.oauth.controller;

import com.iongroup.api.oauth.dto.AccountDto;
import com.iongroup.api.oauth.dto.LoginRequest;
import com.iongroup.api.oauth.dto.RegistrationRequest;
import com.iongroup.api.oauth.dto.UserDto;
import com.iongroup.api.oauth.model.GoogleAccount;
import com.iongroup.api.oauth.service.AccountService;
import com.iongroup.api.oauth.service.AuthService;
import com.iongroup.api.oauth.util.ApiUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

@RestController
@RequiredArgsConstructor
public class GoogleAuthController {

    private final AuthService authService;
    private final AccountService accountService;


    @GetMapping("/authorize")
    public String authorize(OAuth2AuthenticationToken oAuth2AuthenticationToken,
                            HttpServletResponse response) {

        GoogleAccount googleAccount = GoogleAccount.deserialize(oAuth2AuthenticationToken.getPrincipal().getAttributes());
        String userToken = null;

        try {
            String adminToken = authService.login(new LoginRequest("admin", "admin"));
            System.out.println(adminToken);

            if (!accountService.existsAccountWithEmail(googleAccount.getEmail(), adminToken)) {

               userToken = authService.register(new RegistrationRequest(
                        ApiUtil.generateUsername(),
                        googleAccount.getSub(),
                        new UserDto(
                                googleAccount.getFirstName(),
                                googleAccount.getLastName(),
                                null,
                                googleAccount.getEmail()
                        )
                ));


            } else {

                AccountDto accountDto = accountService.getAccountByEmail(googleAccount.getEmail(), adminToken);
                userToken = authService.login(new LoginRequest(accountDto.getUsername(), googleAccount.getSub()));

            }

        } catch (ResourceAccessException | HttpClientErrorException e) {
            e.printStackTrace();
        }

        createCookie(userToken, response);
        return userToken;
    }

    @GetMapping("/")
    public String secured() {
        return "Welcome Home!";
    }

    private void createCookie(String token,  HttpServletResponse response) {
        if (token != null) {
            Cookie cookie = new Cookie("jwtToken", token);
            cookie.setMaxAge(2 * 3600);
            cookie.setHttpOnly(true);

            response.addCookie(cookie);
        }
    }
}
