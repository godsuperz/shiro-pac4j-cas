package com.superz.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

/**
 * @author lovez
 */
@Service
public class MailService {
    @Value("${spring.mail.username}")
    private String from;
    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sayHello() {
        System.out.println("Hello World!!");
    }

    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setSentDate(new Date());
        message.setText(content);
        mailSender.send(message);
    }

    public void sendHtmlMail(String to, String subject, String content) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setFrom(from);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setSentDate(new Date());
        messageHelper.setText(content, true);
        mailSender.send(message);
    }

    public void sendAttachmentMail(String to, String subject, String content, String attachment) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setFrom(from);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setSentDate(new Date());
        messageHelper.setText(content, true);
        FileSystemResource resource = new FileSystemResource(new File(attachment));
        String filename = resource.getFilename();
        messageHelper.addAttachment(filename, resource);
        messageHelper.addAttachment(filename+"_test", resource);
        mailSender.send(message);
    }

    public void sendImgMail(String to, String subject, String content, String path, String rid) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setFrom(from);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(content, true);
        messageHelper.setSentDate(new Date());
        FileSystemResource resource = new FileSystemResource(new File(path));
        messageHelper.addInline(rid, resource);
        mailSender.send(message);
    }
}
