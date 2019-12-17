package org.comma.exception.core;

import java.util.Map;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

/**
 * A class used to get all the {@link } instances from the current
 * {@link ApplicationContext} but ignoring the parent.
 *
 * @author Rob Winch
 *
 */
final class AutowiredWebSecurityConfigurersIgnoreParents {

    private final ConfigurableListableBeanFactory beanFactory;

    public AutowiredWebSecurityConfigurersIgnoreParents(
            ConfigurableListableBeanFactory beanFactory) {
        Assert.notNull(beanFactory, "beanFactory cannot be null");
        this.beanFactory = beanFactory;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Object getWebSecurityConfigurers() {
        //List<<Filter, GettingObject>> webSecurityConfigurers = new ArrayList<Filter, GettingObject>>();
        Map<String, GettingObject> beansOfType = beanFactory
                .getBeansOfType(GettingObject.class);
//        for (Entry<String, GettingObject> entry : beansOfType.entrySet()) {
//            webSecurityConfigurers.add(entry.getValue());
//        }
//        return webSecurityConfigurers;
        return beansOfType;
    }
}