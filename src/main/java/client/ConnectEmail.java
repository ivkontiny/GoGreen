package client;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
public class ConnectEmail {


    public static void recoverPassword(){
        String mail = ConnectAccount.getAccount().getMail();
    }

    public static void sendMail(String gmail) {

        String htmlMessage = "You requested to change your GOGreen password \n" + "Please click on the link:";
        String link = Connect.url_default + "recovery/id";
        String toMailId = gmail;
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
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(toMailId);
            helper.setSubject("GO Green Account recovery");
            helper.setText(htmlMessage, true);

            //Checking Internet Connection and then sending the mail
                mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}