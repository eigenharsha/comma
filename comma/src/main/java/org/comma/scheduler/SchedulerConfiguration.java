//package org.ebenso.comma.scheduler;
//
//import org.ebenso.mm.ss.util.SchedulerCoreConstants;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//
//@ConditionalOnProperty(prefix = "comma.swagger.ui", value = "enable", matchIfMissing = true)
//public class SchedulerConfiguration {
//
//    @Autowired
//    private SchedulerProperties properties;
//
//    @Autowired
//    private SchedulerCoreConstants schedulerCoreConstants;
//
//    /**
//     * @return
//     * @EnableScheduler auto initiate the Scheduler Bean
//     */
//    @Bean(
//            "org.ebenso.comma.scheduler.Scheduler"
//    )
//    public Scheduler defaultScheduler() {
//        return new Scheduler();
//    }
//}
