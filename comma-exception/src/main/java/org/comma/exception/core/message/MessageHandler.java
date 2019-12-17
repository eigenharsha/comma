package org.comma.exception.core.message;

import org.comma.exception.ExceptionHelper;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;
import java.util.Set;

/**
 * This is a helper class, responsible to read messages
 * from properties file. it also able to support internationalization messages.
 * it reads messages as per locale configuration.
 * default locale is - en-us
 */
public class MessageHandler {

    private static final MessageHandler instance = new MessageHandler();

    private ResourceBundleMessageSource messageSource;
    private String resource = "";
    private String encoding = "UTF-8";
    private static Locale locale;

    private MessageHandler() {
        // manage internationalization (i18nMessage)
        locale = new Locale("en"); // default en
    }

    public static MessageHandler getInstance() {
        return instance;
    }

    public static String getMessage(String message, Object... args) {
        return instance.messageSource.getMessage(message, args, locale);
    }

    public ResourceBundleMessageSource initialize() throws Exception {
        ExceptionHelper.throwIfArgumentIsNull(resource);
        messageSource = new MessageSource();
        messageSource.setDefaultEncoding(encoding);
        messageSource.addBasenames(resource);
        return messageSource;
    }

    public Set<String> getResource() {
        return this.messageSource.getBasenameSet();
    }

    public void setResource(String file) {
        this.resource = file;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

}
