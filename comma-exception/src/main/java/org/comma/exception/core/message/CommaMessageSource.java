package org.comma.exception.core.message;

import java.util.Locale;
import java.util.ResourceBundle;

public class CommaMessageSource extends org.springframework.context.support.ResourceBundleMessageSource {

    @Override
    protected ResourceBundle doGetBundle(String basename, Locale locale) {
        return super.doGetBundle(basename, locale);
    }
}
