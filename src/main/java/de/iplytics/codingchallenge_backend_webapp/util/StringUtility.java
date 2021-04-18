package de.iplytics.codingchallenge_backend_webapp.util;

import java.util.UUID;

public class StringUtility {
    public static boolean isEmptyOrNull(String s) {
        if(s!=null)
            return s.isEmpty();
        return true;
    }

    public static String generateID(String prefix) {
        return prefix+ "_"+UUID.randomUUID().toString();
    }
}
