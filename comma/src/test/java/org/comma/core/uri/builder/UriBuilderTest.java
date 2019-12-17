//package org.ebenso.comma.core.uri.builder;
//
//import CommaException;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.net.URL;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class UriBuilderTest {
//
//    @Before
//    public void setUp() throws CommaException {
//    }
//
//    @After
//    public void tearDown() throws CommaException {
//    }
//
//    @Test
//    public void buildUrlTest() {
//        try {
//            URL url = new UriBuilder()
//                    .host("http://www.example.com/")
//                    .resourcePath("/hello")
//                    .build();
//            Assert.assertEquals(url.toString(), "http://www.example.com/hello" );
//        } catch (CommaException e) {
//            e.printStackTrace();
//        } catch (java.lang.CommaException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void buildUrlTest2() {
//        try {
//            URL url1 = new UriBuilder()
//                    .host("www.example.com")
//                    .resourcePath("hello")
//                    .build();
//            Assert.assertEquals(url1.toString(), "http://www.example.com/hello" );
//        } catch (CommaException e) {
//            e.printStackTrace();
//        }
//    }
//
////    @Test
////    public void buildUrlTest3() {
////        try {
////            URL url1 = new UriBuilder()
////                    .host("")
////                    .resourcePath("hello")
////                    .build();
////            Assert.assertEquals(url1.toString(), "http://www.example.com/hello" );
////        } catch (CommaException e) {
////            e.printStackTrace();
////        }
////    }
//}