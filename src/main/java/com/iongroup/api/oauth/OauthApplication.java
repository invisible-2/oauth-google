package com.iongroup.api.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OauthApplication {


	public static void main(String[] args){
		System.setProperty("http.proxyHost", "10.24.63.193");
		System.setProperty("http.proxyPort", "8080");

		System.setProperty("https.proxyHost", "10.24.63.193");
		System.setProperty("https.proxyPort", "8080");

		SpringApplication.run(OauthApplication.class, args);
	}

}
