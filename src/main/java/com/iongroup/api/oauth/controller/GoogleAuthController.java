package com.iongroup.api.oauth.controller;

import com.iongroup.api.oauth.model.Account;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class GoogleAuthController {

    @GetMapping("/secured")
    public String welcome(OAuth2AuthenticationToken oAuth2AuthenticationToken) {

        Account account = Account.deserialize(oAuth2AuthenticationToken.getPrincipal().getAttributes());

        return account.toString();
    }

    @GetMapping("/logout")
    public String logout() {
        return "Logout";
    }

    @GetMapping("/")
    public String secured() {
        return "Welcome Home!";
    }
}
