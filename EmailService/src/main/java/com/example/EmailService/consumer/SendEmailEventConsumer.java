package com.example.EmailService.consumer;

import com.example.EmailService.dtos.EmailDTO;
import com.example.EmailService.utils.EmailUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Component
public class SendEmailEventConsumer {

    @Autowired
    ObjectMapper objectMapper;

    @KafkaListener(topics = "sendEmail", groupId = "emailService")
    public void handleEmailEvent(String message) throws JsonProcessingException {
        System.out.println();
        EmailDTO emailDTO = objectMapper.readValue(message, EmailDTO.class);
        String to = emailDTO.getTo();
        String subject = emailDTO.getSubject();
        String body = emailDTO.getBody();

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("#######@gmail.com", "##############");
            }
        };
        Session session = Session.getInstance(props, auth);

        EmailUtil.sendEmail(session, to, subject, body);
    }
}
