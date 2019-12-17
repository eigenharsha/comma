//package org.ebenso.comma.scheduler;
//
//import org.ebenso.mm.ss.core.JobInfo;
//import org.ebenso.platform.logger.Level;
//import org.ebenso.platform.logger.Logger;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//
///**
// * Scheduler job format
// * <p>
// * {
// * "type":"CALLBACK",
// * "data":{
// * "type":"CALLBACK",
// * "callbackUrl":"http://localhost:8080/ping",
// * "data":{
// * "k1":"v1"
// * }
// * },
// * "triggerInfo":{
// * "startTime":1530702077236,
// * "expireTime":null,
// * "group":"grp1",
// * "misfireInstruction":"FIRE_NOW"
// * }
// * }
// */
//public abstract class SchedulerManager {
//
//    public static final Logger logger = new Logger("", SchedulerManager.class);
//    private final static String scheduleJobUrl = "/schedule/job";
//
//    private String schedulerBaseUrl;
//
//    private JobInfo jobInfo;
//
//    private HttpHeaders getRequestHeaders() {
//        HttpHeaders requestHeaders = new HttpHeaders();
//        requestHeaders.setContentType(new MediaType("application", "json"));
//
//        return requestHeaders;
//    }
//
//
//    public void setScheduleJob(JobInfo job) {
//        this.jobInfo = job;
//    }
//
//    public String scheduleJob(JobInfo jobInfo) {
//        String schedulerJobId = null;
//        try {
//            schedulerJobId = super.post(schedulerBaseUrl,
//                    scheduleJobUrl,
//                    null,
//                    jobInfo,
//                    getRequestHeaders(),
//                    String.class);
//
//            if (schedulerJobId == null || schedulerJobId.isEmpty()) {
//                logger.log(Level.INFO, "Error while scheduling next job for campaign redemption");
//                return null;
//            }
//
//        } catch (CommaException e) {
//            logger.log(Level.ERROR, "Error while scheduling next job for campaign redemption. \n " +
//                    "debug-message: {}", e.getMessage());
//            return null;
//        }
//
//        logger.log(Level.INFO, "Retrying Campaign redemption has been scheduled with delay {} hours.", jobInfo.getTriggerInfo().getStartTime());
//        return schedulerJobId;
//    }
//
//
//    public String scheduleJob() {
//        String schedulerJobId = null;
//        if (jobInfo == null) {
//            logger.log(Level.ERROR, "Error while scheduling job, " +
//                    "either you provide null or empty scheduled job info.");
//            return null;
//        }
//
//        try {
//            schedulerJobId = super.post(schedulerBaseUrl,
//                    scheduleJobUrl,
//                    null,
//                    jobInfo,
//                    getRequestHeaders(),
//                    String.class);
//
//            if (schedulerJobId == null || schedulerJobId.isEmpty()) {
//                logger.log(Level.INFO, "Error while scheduling next job for campaign redemption");
//                return null;
//            }
//
//        } catch (CommaException e) {
//            logger.log(Level.ERROR, "Error while scheduling next job for campaign redemption. \n " +
//                    "debug-message: {}", e.getMessage());
//            return null;
//        }
//
//        logger.log(Level.INFO, "Retrying Campaign redemption has been scheduled with delay {} hours.",
//                jobInfo.getTriggerInfo().getStartTime());
//        return schedulerJobId;
//    }
//
//}
