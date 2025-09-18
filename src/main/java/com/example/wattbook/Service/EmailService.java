package com.example.wattbook.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void enviarCodigoVerificacion(String destinatario, String codigoVerificacion) {
        MimeMessage mensaje = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(mensaje, true);
            helper.setFrom(senderEmail);
            helper.setTo(destinatario);
            helper.setSubject("Código de Verificación");
            helper.setText("Tu código de verificación es: " + codigoVerificacion);

            javaMailSender.send(mensaje);
            System.out.println("Correo enviado a: " + destinatario);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Error al enviar el correo: " + e.getMessage());
        }
    }
}