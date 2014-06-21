package com.system.edu.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class MailService {
    private static final String HOST = "smtp.gmail.com";
    private static final String USERNAME = "school.ejournal";
    private static final String PASSWORD = "fjjosILj23JINVksdf";
    private static final String SENDER = "school.ejournal@gmail.com";
    public static final String PROTOCOL = "smtps";

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public boolean sendMail(String subject, String content, String recipient) {
        Session session = getMailSession();
        MimeMessage msg = new MimeMessage(session);
        Transport t = null;
        try {
            setMessageContent(recipient, subject, content, msg);
            t = session.getTransport(PROTOCOL);
            t.connect(HOST, USERNAME, PASSWORD);
            t.sendMessage(msg, msg.getAllRecipients());
        } catch (MessagingException e) {
            logger.error("Error while sending an email: ", e);
            return false;
        } finally {
            if (t != null) {
                try {
                    t.close();
                } catch (MessagingException e) {
                    logger.error("Error while sending an email: ", e);
                }
            }
        }
        return true;
    }

    private void setMessageContent(String recipient, String subject, String content, MimeMessage msg)
            throws MessagingException {
        msg.setSubject(subject);
        msg.setContent(content, "text/html");
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        msg.setFrom(new InternetAddress(SENDER));
    }

    private Session getMailSession() {
        Properties props = new Properties();
        props.put("mail.smtps.auth", "true");
        return Session.getDefaultInstance(props, null);
    }
}