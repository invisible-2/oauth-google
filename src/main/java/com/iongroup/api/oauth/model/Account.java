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
public class Account {

    private String firstName;
    private String lastName;
    private String email;
    private String pictureUrl;
    private Date issuedAt;

    public static Account deserialize(Map<String, Object> map) {
        Account account = new Account();
        account.firstName = (String) map.get("given_name");
        account.lastName = (String) map.get("family_name");
        account.email = (String) map.get("email");
        account.pictureUrl = (String) map.get("picture");
        account.issuedAt = toDate(map.get("iat"));
        return account;
    }

    private static Date toDate(Object date) {
        Instant instant = Instant.parse(date.toString());
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, ZoneId.of("UTC"));
        return Date.from(zonedDateTime.toInstant());
    }
}
