package com.bcipriano.javaSMTPMailer.config.sessionConfig;

import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import java.util.Properties;

public class SessionSSL {

    public static Session createSession(String userEmail, String password) {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        properties.put("mail.smtp.port", "465"); //SMTP Port
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        properties.put("mail.smtp.ssl.protocols","TLSv1.2");
        Session session =  Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userEmail, password );
            }
        });
        return session;
    }
}
