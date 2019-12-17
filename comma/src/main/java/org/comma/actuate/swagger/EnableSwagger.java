package org.comma.actuate.swagger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@EnableSwagger2
@ConditionalOnProperty(prefix = "comma.swagger.ui", value = "enable", matchIfMissing = true)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableSwagger {
}
