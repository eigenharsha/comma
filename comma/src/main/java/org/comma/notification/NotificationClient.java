package org.comma.notification;

import org.comma.core.rest.DefaultRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationClient {

    @Autowired
    DefaultRestClient restApiClient;

    public void sendEmail(String body) {
        // process body
        // and send mail
    }
}
