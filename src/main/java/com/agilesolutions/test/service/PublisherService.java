package com.agilesolutions.test.service;

import org.apache.log4j.Logger;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class PublisherService {
    private static final Logger LOGGER = Logger.getLogger(PublisherService.class);
    private ConnectionFactory factory;
    public static final String queueName = "todo-list";
    public PublisherService() {
        this.factory = new ConnectionFactory();
        this.factory.setHost("rabbitmq");
        this.factory.setPort(5672);
    }

    public void sendMessage(String message) throws Exception {
        try (Connection conn = this.factory.newConnection()) {
            LOGGER.debug("SENDING MESSAGE");
            Channel channel = conn.createChannel();
            channel.queueDeclare(PublisherService.queueName, false, false, false, null);
            channel.basicPublish("", PublisherService.queueName, null, message.getBytes());
            channel.close();
            conn.close();
        } catch (Exception e) {
            LOGGER.debug("ERROR: " + e);
            throw e;
        }
    }
}
