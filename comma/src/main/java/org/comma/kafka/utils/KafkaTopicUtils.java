package org.comma.kafka.utils;

import java.io.UnsupportedEncodingException;

public final class KafkaTopicUtils {

    private KafkaTopicUtils() {

    }

    /**
     * Validate topic name. Allowed chars are ASCII alphanumerics, '.', '_' and '-'.
     *
     * @param topicName name of the topic
     */
    public static void validateTopicName(String topicName) {
        try {
            byte[] utf8 = topicName.getBytes("UTF-8");
            for (byte b : utf8) {
                if (!((b >= 'a') && (b <= 'z') || (b >= 'A') && (b <= 'Z')
                        || (b >= '0') && (b <= '9') || (b == '.') || (b == '-')
                        || (b == '_'))) {
                    throw new IllegalArgumentException(
                            "Topic name can only have ASCII alphanumerics, '.', '_' and '-', but was: '"
                                    + topicName + "'");
                }
            }
        } catch (UnsupportedEncodingException ex) {
            throw new AssertionError(ex); // Can't happen
        }
    }

}
