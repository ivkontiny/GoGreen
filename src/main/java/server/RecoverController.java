package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
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

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Properties;


@RestController
public class RecoverController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine; // From Thymeleaf

    protected static HashMap<String,String> recoverRequests = new HashMap<>();
    AccountService as = new AccountService();

    static class Send {
        String name;
        public Send( String name) {
            this.name = name;
        }
    }


    /**
     * Changes the password.
     * @param recoverId the recoverId
     * @param newPassword the newPassword
     */
    @PostMapping("/recover/{recoverId}")
    public ModelAndView changePassword(@PathVariable("recoverId") String recoverId,
                                       @RequestBody String newPassword) {
        if (newPassword == null || newPassword.length() <= 6) {

            ModelAndView mv = new ModelAndView();
            mv.setViewName("gogreen.html");
            return  mv;
        }
        System.out.println(newPassword);
        String user = recoverRequests.get(recoverId);
        String[] piece = newPassword.split("=");
        String password = piece[1];
        as.updatePassword(user,password);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect.html");
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
        try {
            sendMail(mail,recoverAccount.getUsername());
        } catch (MessagingException e) {
            return false;
        }
        return true;
    }


    /**
     * Sends email from the gmail server.
     * @param gmail of the account to be recovered
     * @param username of the account to be recovered
     * @throws MessagingException when the meail was unable to be sent
     */
    public void sendMail(String gmail,String username) throws MessagingException {

        SessionIdGenerator generator = new SessionIdGenerator();
        String id = generator.getAlphaNumericString(20);
        String user = username;
        String link = "http://localhost:8080/recover/";
        link += id;
        Context context = new Context();
        context.setVariable("name", user);
        context.setVariable("link", link);
        recoverRequests.put(id,user);
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

    }

}
