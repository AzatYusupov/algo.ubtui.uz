package algo.services.Impl;

import algo.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


/**
 * Created by AzatYusupov on 01.01.2018.
 */
@Service
public class MailServiceImpl implements MailService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${Mail.subject.signUp}")
    String signUpSubject;

    @Value("${Mail.subject.forgot}")
    String forgotPasswordSubject;

    @Value("${server.url}")
    String serverContextPath;

    @Value("${server.port}")
    String serverPort;

    @Override
    @Async
    public void sendGreetingMessage(String to, String username) {
        try {
            Context context = new Context();
            context.setVariable("username", username);
            sendTemplate(to, signUpSubject, "greeting", context);
        }
        catch (MailException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Async
    public void sendForgotPasswordMessage(String to, String username, String code) {
        try {
            Context context = new Context();
            context.setVariable("username", username);
            String url = serverContextPath;
            if (!serverPort.equals("80"))
                url += ":" + serverPort;
            url += "/passwordChange/" + code;
            context.setVariable("url", url);
            sendTemplate(to, forgotPasswordSubject, "forgotPassword",context);
        }
        catch (MailException e) {
            e.printStackTrace();
        }
    }

    public void sendTemplate(String to, String subject, String template, Context context) {

        try {
            MimeMessage msg = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, false, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
//            helper.setFrom("No-reply Productcard");
//            ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
//            templateResolver.setTemplateMode("HTML5");
//            templateResolver.setSuffix(".html");
//            templateResolver.setCacheTTLMs(3600000L);
//            templateEngine.setTemplateResolver(templateResolver);

            String text = templateEngine.process("mail/" + template, context);
            helper.setText(text, true);
            javaMailSender.send(msg);
            System.out.println("Message sent: "+to+" | "+subject);
        }
        catch (MailException e) {
            e.printStackTrace();
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
