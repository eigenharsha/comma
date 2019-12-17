package org.comma.exception.core.message;

import org.comma.exception.Message;
import org.comma.exception.util.Utils;
import org.springframework.lang.Nullable;

import java.util.Locale;

public class Messages {

    public static String getMessage(String code) {
        return getMessage(code, null);
    }

    public static String getMessage(String code, @Nullable Object[] args) {
        Locale locale = MessageHandler.getInstance().getLocale();

        // manage internationalization (i18nMessage)
        locale = Utils.isNull(locale) ?
                new Locale("en") // default en
                : locale;

        return MessageHandler.getMessage(code, args, locale);
    }

    public static String getMessage(String code, @Nullable Object[] args, Locale locale) {
        return MessageHandler.getMessage(code, args, locale);
    }

    public static String getMessage(Message message) {
        if (message != null) {
            return getMessage(message.getCode());
        }

        return null;
    }
}
