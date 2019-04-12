package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pojos.Account;
import services.AccountService;
import util.SessionIdGenerator;

import java.util.HashMap;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;




@RestController
public class ResetPasswordController {

    protected static HashMap<String,String> recoverRequests = new HashMap<>();
    private static String user;
    private static String email;
    AccountService as = new AccountService();

    @Autowired
    private TemplateEngine templateEngine; // From Thymeleaf


    /**
     * Changes the password.
     * @param recoverId the recoverId
     * @param newPassword the newPassword
     */
    @PostMapping("/recover/{recoverId}")
    public ModelAndView changePassword(@PathVariable("recoverId") String recoverId,
                                       @RequestBody String newPassword) {
        ModelAndView mv = new ModelAndView();
        if (!recoverRequests.containsKey(recoverId)) {
            mv.setViewName("failed.html");
        }
        if (newPassword == null || newPassword.length() < 6) {

            mv.setViewName("gogreen.html");
            return  mv;
        }
        System.out.println(newPassword);
        String user = recoverRequests.get(recoverId);
        String[] piece = newPassword.split("=");
        String password = piece[1];
        System.out.println(password);
        as.updatePassword(user,password);
        mv.setViewName("redirect.html");
        recoverRequests.remove(recoverId);
        return mv;
    }

    /**
     * Sends email.
     * @param mail of th account to be recovered
     * @return if the recovery was successful
     */
    @PostMapping("/recover")
    public boolean recoverPassword(@RequestBody String mail) {
        Account recoverAccount = as.getEmail(mail);
        if (recoverAccount == null) {
            return false;
        }
        user = recoverAccount.getUsername();
        email = mail;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                sendMail(email,user);
            }
        }, 1000);
        return true;
    }


    /**
     * Sends email from the gmail server.
     * @param gmail of the account to be recovered
     * @param username of the account to be recovered
     * @throws MessagingException when the meail was unable to be sent
     */
    public void sendMail(String gmail,String username) {

        try {
            SessionIdGenerator generator = new SessionIdGenerator();
            String id = generator.getAlphaNumericString(20);
            String user = username;
            String link = "http://localhost:8080/recover/";
            link += id;
            Context context = new Context();
            recoverRequests.put(id, user);
            user = "Hello " + user;
            context.setVariable("name", user);
            context.setVariable("link", link);
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("smtp.gmail.com");
            mailSender.setPort(465);
            mailSender.setUsername("gogreen.group57");
            mailSender.setPassword("oopgroup57");

            //from email id and password

            Properties mailProp = mailSender.getJavaMailProperties();
            mailProp.put("mail.transport.protocol", "smtp");
            mailProp.put("mail.smtp.auth", "true");
            mailProp.put("mail.smtp.starttls.enable", "true");
            mailProp.put("mail.smtp.starttls.required", "true");
            mailProp.put("mail.debug", "true");
            mailProp.put("mail.smtp.ssl.enable", "true");

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            String toMailId = gmail;
            helper.setTo(toMailId);
            helper.setSubject("Recover Password GoGreen");
            helper.setText(templateEngine.process("mail", context), true);
            //Checking Internet Connection and then sending the mail
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            return;
        }

    }

}
