package by.gstu.edu.service.impl;

import by.gstu.edu.exception.EmailSendException;
import by.gstu.edu.model.TempUser;
import by.gstu.edu.model.User;
import by.gstu.edu.service.EmailService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * createdAt: 11/23/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@Service
public class EmailServiceImpl implements EmailService {

    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMail;

    public EmailServiceImpl(TemplateEngine templateEngine, JavaMailSender javaMail) {
        this.templateEngine = templateEngine;
        this.javaMail = javaMail;
    }

    @Override
    public void sendActivationLink(String email, String link, TempUser tempUser) {
        try {
            Context context = new Context();
            context.setVariable("link", link);
            context.setVariable("tempUser", tempUser);
            String text = templateEngine.process("activation_email", context);
            MimeMessage mimeMessage = mimeMessage(email, "Email activation", text);
            javaMail.send(mimeMessage);
        } catch (MessagingException e) {
            throw new EmailSendException(e);
        }
    }

    private MimeMessage mimeMessage(String to, String subject, String text) throws MessagingException {
        MimeMessage message = javaMail.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        return message;
    }
}
