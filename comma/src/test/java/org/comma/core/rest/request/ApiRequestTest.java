//package org.ebenso.comma.core.rest.request;
//
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class ApiRequestTest {
//
//    ApiRequest apiRequest;
//
//    @Before
//    public void setUp() throws CommaException {
//        try {
//            apiRequest = new ApiRequest("abc.com/api/user?id=2&name=harsha");
//        } catch (CommaException ex) {
//            System.out.println(ex);
//        }
//    }
//
//    @After
//    public void tearDown() throws CommaException {
//    }
//
//    @Test
//    public void getUrl() {
//    }
//
//    @Test
//    public void setUrl() {
//    }
//
//    @Test
//    public void getURI() {
//    }
//
//    @Test
//    public void getHttpEntity() {
//    }
//
//    @Test
//    public void getQuery() {
//    }
//
//    @Test
//    public void getPath() {
//    }
//
//    @Test
//    public void getPort() {
//    }
//
//    @Test
//    public void getProtocol() {
//        String protocol = "http";
//        assertEquals(protocol, apiRequest.getProtocol());
//    }
//
//    @Test
//    public void getHost() {
//        System.out.println("api : " +  apiRequest.getHost());
//        assertEquals("abc.com", apiRequest.getHost());
//    }
//
//    @Test
//    public void toURI() {
//    }
//
//    @Test
//    public void toURL() {
//    }
//}