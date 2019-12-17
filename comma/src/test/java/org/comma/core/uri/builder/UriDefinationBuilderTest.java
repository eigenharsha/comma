package org.comma.core.uri.builder;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Profile("default")
public class UriDefinationBuilderTest {

    @Test
    @Ignore
    public void givenObjects_whenConverting_thenCorrect()
            throws MalformedURLException, URISyntaxException {
        String aURIString = "http://somehost:80/path?thequery";
        URI uri = new URI(aURIString);
        URL url = new URL(aURIString);

        URL toURL = uri.toURL();
        URI toURI = url.toURI();

        assertNotNull(url);

        assertNotNull(uri);
        assertEquals(toURL.toString(), toURI.toString());
    }
}