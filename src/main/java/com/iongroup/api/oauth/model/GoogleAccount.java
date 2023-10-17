package com.iongroup.api.oauth.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
@ToString
public class GoogleAccount {

    private String firstName;
    private String lastName;
    private String email;
    private String pictureUrl;
    private Date issuedAt;
    private String sub;

    public static GoogleAccount deserialize(Map<String, Object> map) {
        GoogleAccount googleAccount = new GoogleAccount();
        googleAccount.firstName = (String) map.get("given_name");
        googleAccount.lastName = (String) map.get("family_name");
        googleAccount.email = (String) map.get("email");
        googleAccount.pictureUrl = (String) map.get("picture");
        googleAccount.issuedAt = toDate(map.get("iat"));
        googleAccount.sub = (String) map.get("sub");
        return googleAccount;
    }

    private static Date toDate(Object date) {
        Instant instant = Instant.parse(date.toString());
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, ZoneId.of("UTC"));
        return Date.from(zonedDateTime.toInstant());
    }
}
