package org.comma.util;

import org.comma.exception.ExceptionHelper;
import org.comma.exception.model.CommaException;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Utils {

    public static String encode(String input) throws CommaException {
        ExceptionHelper.throwIfArgumentIsNull(input);
        try {
            byte[] inputBytes = input.toString().getBytes("UTF-8");
            return Base64.getEncoder().encodeToString(inputBytes);
        } catch (UnsupportedEncodingException ex) {
            ExceptionHelper.propagate(ex);
        }
        return null;
    }

    public static String decode(String input) throws CommaException {
        ExceptionHelper.throwIfArgumentIsNull(input);
        try {
            byte[] inputBytes = input.getBytes("UTF-8");
            byte[] output = Base64.getDecoder().decode(inputBytes);
            return new String(output);
        } catch (UnsupportedEncodingException ex) {
            ExceptionHelper.propagate(ex);
        }
        return null;
    }
}
