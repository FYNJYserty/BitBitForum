package com.bitbitforum.service;

import com.bitbitforum.rabbitmq.RegistrationEmailMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    // Variables
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    /**
     * RabbitMQ constructor
     * @param rabbitTemplate
     * @param objectMapper
     */
    public NotificationService(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    /**
     * Send message about registration
     * @param userEmail
     * @param userName
     * @param confirmationToken
     */
    public void sendRegistrationEmail(String userEmail, String userName, String confirmationToken) {
        RegistrationEmailMessage message = new RegistrationEmailMessage(
                userEmail,
                userName,
                confirmationToken,
                "registration" // Тип шаблона
        );

        try {
            // 2. Конвертируем в JSON и отправляем в очередь
            String jsonMessage = objectMapper.writeValueAsString(message);
            rabbitTemplate.convertAndSend(
                    "notification_exchange", // Имя обменника
                    "email.registration",    // Routing key
                    jsonMessage              // Само сообщение
            );
        } catch (Exception e) {
            // Логируем ошибку, но НЕ прерываем регистрацию
            System.err.println("Failed to queue email: " + e.getMessage());
        }
    }
}
