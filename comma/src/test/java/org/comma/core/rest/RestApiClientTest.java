//package org.ebenso.comma.core.rest;
//
//import ApiRequest;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpMethod;
//
//import java.net.URISyntaxException;
//
//public class RestApiClientTest {
//
//    @Autowired
//    private DefaultRestClient restApiClient;
//
//    @Test
//    public void execute() throws URISyntaxException {
//        restApiClient.execute(
//                new ApiRequest("abc.com"),
//                HttpMethod.GET,
//                String.class);
//    }
//
//    @Test
//    public void get() {
//    }
//
//    @Test
//    public void post() {
//    }
//}