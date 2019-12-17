package org.comma.exception.util;

import org.comma.exception.ExceptionHelper;

import java.time.Instant;
import java.util.Date;

/**
 * Utility class that handle Date and Time common functionality.
 * This is used to parse date format and also provide the epoc time format conversion
 * @author kumar harsha (kumar.harsha@oyorooms.com)
 */
public class DateTimeUtils {

    /**
     * Convert and return epoc std. time in second format of given date
     * @param date : java.lang date required
     * @return long (second)
     */
    public static long getEpoc(Date date) throws Exception {
        ExceptionHelper.throwIfArgumentIsNull(date);
        return Instant.ofEpochMilli(date.getTime()).getEpochSecond();
    }
}
