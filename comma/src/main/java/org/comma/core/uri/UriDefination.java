//package org.ebenso.comma.core.uri;
//
//import UriBuilder;
//import CommaException;
//import org.springframework.validation.annotation.Validated;
//
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;
//import java.net.URL;
//import java.util.UUID;
//
//@Validated
//public class UriDefination {
//    @NotEmpty
//    private String id = UUID.randomUUID().toString();
//
//    @NotNull
//    private URL url;
//
//
//
//    @NotNull
//    private UriDefination uriDefination;
//
//    /**
//     * Default constructor
//     */
//    private UriDefination() {
//    }
//
//    /**
//     * @param scheme
//     * @param host
//     * @param port
//     * @param path
//     * @param query
//     */
//    public UriDefination(String scheme, String host, int port, String path, String query) throws CommaException {
//        url = new UriBuilder()
//                .scheme(scheme)
//                .host(host)
//                .port(port)
//                .resourcePath(path)
//                .queryParam(query)
//                .build();
//    }
//
//    /**
//     * @param host
//     * @param path
//     * @param query
//     */
//    public UriDefination(String host, String path, String query) {
//
//    }
//
//
//}