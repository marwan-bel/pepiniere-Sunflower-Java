package service;

/**
 *
 * @author Naveen
 */
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSend {
private String to,nom;

           private String host ="smtp.gmail.com" ;
         private       String user = "bouchokothmen@gmail.com";
          private   String pass = "Azerty u";
         
          private   String from = "bouchokothmen@gmail.com";
       
         private   String messageText = "Merci pour votre intérée :";
         private  boolean sessionDebug = false;

    public String getHost() {
        return host;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getFrom() {
        return from;
    }



    public String getMessageText() {
        return messageText;
    }

    public boolean isSessionDebug() {
        return sessionDebug;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setFrom(String from) {
        this.from = from;
    }



    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public void setSessionDebug(boolean sessionDebug) {
        this.sessionDebug = sessionDebug;
    }
            
            
    public void setTo(String to) {
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    public EmailSend(String to,String nom) {
        this.to = to; this.nom = nom;
    }

    public void  mail(){
        try{

      String subject = "Monsieur "+nom+" sunflower";
            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
        }catch(Exception ex)
        {
            System.out.println(ex);
        }

    }
}