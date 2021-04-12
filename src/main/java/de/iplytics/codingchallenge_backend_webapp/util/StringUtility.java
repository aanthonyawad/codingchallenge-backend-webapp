package de.iplytics.codingchallenge_backend_webapp.util;

public class StringUtility {
    public static boolean isEmptyOrNull(String s) {
        if(s!=null)
            return s.isEmpty();
        return true;
    }
}
