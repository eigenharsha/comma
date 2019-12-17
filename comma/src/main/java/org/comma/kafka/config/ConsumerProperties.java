package org.comma.kafka.config;//package org.ebenso.acpcommon.kafka.org.ebenso.acp.common.config;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.org.ebenso.acp.common.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.listener.ContainerProperties;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//
//import java.org.ebenso.acp.common.util.HashMap;
//import java.org.ebenso.acp.common.util.Map;
//
//@Configuration
//public class ConsumerProperties {
//
//    @Value("${kafka.consumer.bootstrap-server}")
//    private String bootstrapServer;
//
//    @Value("${kafka.consumer.groupId}")
//    private String consumerGroup;
//
//    public String getBootstrapServer() {
//        return bootstrapServer;
//    }
//
//    @Bean
//    public Map<String, Object> consumerConfigs() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroup);
//        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
//        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 500000);
//
//        return props;
//    }
//
//    @Bean
//    public ConsumerFactory<String, KafkaEvent> consumerFactory() {
//        return new DefaultKafkaConsumerFactory<String, KafkaEvent>(consumerConfigs(),
//                new StringDeserializer(),
//                new JsonDeserializer<KafkaEvent>(KafkaEvent.class));
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, KafkaEvent> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, KafkaEvent> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
//        factory.setConsumerFactory(consumerFactory());
//
//        return factory;
//    }
//}