package org.comma.exception.util;

import org.springframework.lang.Nullable;

public class Strings {

    public static boolean isNullOrEmpty(@Nullable String s) {
        return s == null || s.isEmpty();
    }

    public static String trim(final String str) {
        if (str == null) {
            throw new NullPointerException("null string not allowed.");
        }

        return str.trim();
    }
}
