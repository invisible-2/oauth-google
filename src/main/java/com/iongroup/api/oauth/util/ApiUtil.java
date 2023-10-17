package com.iongroup.api.oauth.util;

import java.util.Date;

public class ApiUtil {
    public static final String BASE_URL = "http://localhost:8080/api/v1/";

    public static String generateUsername() {
        return "User"  + new Date().getTime();
    }

}
