package org.comma.exception.provider;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public abstract class AbstractBeanProvider implements ApplicationContextAware {

    private static ApplicationContext context;

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext ac)
            throws BeansException {
        context = ac;
    }

    /**
     * get spring context bean at runtime
     *
     * @param tClass
     * @param <T>
     * @return
     */
    public static final <T> T getBean(Class<T> tClass) {
        return context.getBean(tClass);
    }

    /**
     * get spring context bean at runtime
     *
     * @param name
     * @param requiredType
     * @param <T>
     * @return
     * @throws BeansException
     */
    public static final <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return context.getBean(name, requiredType);
    }

//    @Nullable
//    private <T> T resolveBean(ResolvableType requiredType, @Nullable Object[] args, boolean nonUniqueAsNull) {
//        NamedBeanHolder<T> namedBean = this.resolveNamedBean(requiredType, args, nonUniqueAsNull);
//        if (namedBean != null) {
//            return namedBean.getBeanInstance();
//        } else {
//            BeanFactory parent = this.getParentBeanFactory();
//            if (parent instanceof DefaultListableBeanFactory) {
//                return ((DefaultListableBeanFactory)parent).resolveBean(requiredType, args, nonUniqueAsNull);
//            } else if (parent != null) {
//                ObjectProvider<T> parentProvider = parent.getBeanProvider(requiredType);
//                if (args != null) {
//                    return parentProvider.getObject(args);
//                } else {
//                    return nonUniqueAsNull ? parentProvider.getIfUnique() : parentProvider.getIfAvailable();
//                }
//            } else {
//                return null;
//            }
//        }
//    }

//    @Nullable
//    private <T> NamedBeanHolder<T> resolveNamedBean(ResolvableType requiredType, @Nullable Object[] args, boolean nonUniqueAsNull) throws BeansException {
//        Assert.notNull(requiredType, "Required type must not be null");
//        String[] candidateNames = this.getBeanNamesForType(requiredType);
//        String[] var6;
//        int var7;
//        int var8;
//        String beanName;
//        if (candidateNames.length > 1) {
//            List<String> autowireCandidates = new ArrayList(candidateNames.length);
//            var6 = candidateNames;
//            var7 = candidateNames.length;
//
//            for(var8 = 0; var8 < var7; ++var8) {
//                beanName = var6[var8];
//                if (!this.containsBeanDefinition(beanName) || this.getBeanDefinition(beanName).isAutowireCandidate()) {
//                    autowireCandidates.add(beanName);
//                }
//            }
//
//            if (!autowireCandidates.isEmpty()) {
//                candidateNames = StringUtils.toStringArray(autowireCandidates);
//            }
//        }
//
//        if (candidateNames.length == 1) {
//            String beanName = candidateNames[0];
//            return new NamedBeanHolder(beanName, this.getBean(beanName, requiredType.toClass(), args));
//        } else {
//            if (candidateNames.length > 1) {
//                Map<String, Object> candidates = new LinkedHashMap(candidateNames.length);
//                var6 = candidateNames;
//                var7 = candidateNames.length;
//
//                for(var8 = 0; var8 < var7; ++var8) {
//                    beanName = var6[var8];
//                    if (this.containsSingleton(beanName) && args == null) {
//                        Object beanInstance = this.getBean(beanName);
//                        candidates.put(beanName, beanInstance instanceof NullBean ? null : beanInstance);
//                    } else {
//                        candidates.put(beanName, this.getType(beanName));
//                    }
//                }
//
//                String candidateName = this.determinePrimaryCandidate(candidates, requiredType.toClass());
//                if (candidateName == null) {
//                    candidateName = this.determineHighestPriorityCandidate(candidates, requiredType.toClass());
//                }
//
//                if (candidateName != null) {
//                    Object beanInstance = candidates.get(candidateName);
//                    if (beanInstance == null || beanInstance instanceof Class) {
//                        beanInstance = this.getBean(candidateName, requiredType.toClass(), args);
//                    }
//
//                    return new NamedBeanHolder(candidateName, beanInstance);
//                }
//
//                if (!nonUniqueAsNull) {
//                    throw new NoUniqueBeanDefinitionException(requiredType, candidates.keySet());
//                }
//            }
//
//            return null;
//        }
//    }
}
