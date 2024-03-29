package org.comma.exception.core;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectPostProcessorConfiguration {

    @Bean
    public ObjectPostProcessor<Object> objectPostProcessor(
            AutowireCapableBeanFactory beanFactory) {
        return new AutowireBeanFactoryObjectPostProcessor(beanFactory);
    }
}
