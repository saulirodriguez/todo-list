package com.agilesolutions.test.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublisherConfiguration {
    public static final String queueName = "todo-list";
//
//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setChannelTransacted(true);
//        return rabbitTemplate;
//    }
////
//    @Bean
//    public PlatformTransactionManager transactionManager(ConnectionFactory connectionFactory) {
//        return new RabbitTransactionManager(connectionFactory);
//    }
//
//    @Bean
//    public Queue queue() {
//        return new Queue(PublisherConfiguration.queueName);
//    }
}