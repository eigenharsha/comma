package org.comma.kafka.config;//package org.ebenso.acpcommon.kafka.org.ebenso.acp.common.config;
//
//import org.ebenso.redeem.model.KafkaEvent;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.org.ebenso.acp.common.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.org.ebenso.acp.common.core.KafkaTemplate;
//import org.springframework.kafka.org.ebenso.acp.common.core.ProducerFactory;
//import org.springframework.kafka.support.serializer.JsonSerializer;
//
//import java.org.ebenso.acp.common.util.HashMap;
//import java.org.ebenso.acp.common.util.Map;
//
//@Configuration
//public class ProducerProperties {
//
//    @Value(value = "${kafka.producer.bootstrap-server}")
//    private String bootstrapServer;
//
//    @Bean
//    public KafkaTemplate<String, KafkaEvent> createTemplate() {
//        Object keySer = StringSerializer.class;
//        Object valSer = JsonSerializer.class;
//        Map<String, Object> senderProps = senderProps(keySer, valSer);
//        ProducerFactory<String, KafkaEvent> pf =
//                new DefaultKafkaProducerFactory<>(senderProps);
//        KafkaTemplate<String, KafkaEvent> template = new KafkaTemplate<>(pf);
//        return template;
//    }
//
//    private Map<String, Object> senderProps(Object keySerializer, Object valueSerializer) {
//        Map<String, Object> props = new HashMap<>();
//        props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
//        props.put(ProducerConfig.RETRIES_CONFIG, 0);
//        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
//        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
//        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
//        return props;
//    }
//
//    public String getBootstrapServer() {
//        return bootstrapServer;
//    }
//}
