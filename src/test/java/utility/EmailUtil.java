package utility;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;

public class EmailUtil {

    public static void sendEmailWithAttachments(String fromEmail, String password, String toEmail,
                                                String subject, String body, String[] attachmentPaths) {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);

            // Email body
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Attach multiple files
            if (attachmentPaths != null) {
                for (String path : attachmentPaths) {
                    if (path != null) {
                        MimeBodyPart attachPart = new MimeBodyPart();
                        attachPart.attachFile(new File(path));
                        multipart.addBodyPart(attachPart);
                    }
                }
            }

            message.setContent(multipart);
            Transport.send(message);

            System.out.println("Email sent successfully to " + toEmail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
