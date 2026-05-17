package com.bitbitforum.service;

import com.bitbitforum.dto.FormMessageDto;
import com.bitbitforum.dto.FormSuccessDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.queue.name}")
    private String queueName;
    @Value("${rabbitmq.queue_help.name}")
    private String queueHelp;

    @Autowired
    public MessageService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Messages about successful registration of user
     * @param message
     */
    public void sendMessage(FormSuccessDto message) {
        rabbitTemplate.convertAndSend(queueName, message);
    }

    /**
     * Messages for help
     * @param message
     */
    public void sendMessageHelp(FormMessageDto message) {
        rabbitTemplate.convertAndSend(queueHelp, message);
    }
}
