package org.comma.test;

import org.comma.exception.ExceptionHelper;
import org.comma.exception.Message;
import org.comma.exception.core.context.Comma;
import org.comma.exception.core.message.MessageHandler;
import org.comma.exception.model.CommaException;
import org.comma.test.exception.TestExceptionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
//@EnableSwagger
public class Application {
    static {
        Comma.exceptionBuilder(new TestExceptionBuilder());
    }

    @Autowired
    TestExceptionBuilder builder;

    public static void main(String[] args) throws Exception {
        MessageHandler messageHandler = MessageHandler.getInstance();
        messageHandler.setResource("message-test-en");
        messageHandler.initialize();
        SpringApplication.run(Application.class, args);
        CommaException ex = new CommaException(Message.ARGUMENTS_ARE_EMPTY_OR_NULL);
        ex.addCause("Error found");
        ex.addCause("Error found2345");
        ex.addCause("Error foundfdgs");
        ex.addCause("Error founddsfadsfasfasdfsadfasdfasd");
        ExceptionHelper.propagate(ex);

    }
}
