package com.bcipriano.javaSMTPMailer.config.sessionConfig;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;


public class SessionPO3 {

    public static Session createSession(String email, String password) {

        Properties properties = System.getProperties();
        properties.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.pop3.socketFactory.fallback", "false");
        properties.put("mail.pop3.socketFactory.port", "995");
        properties.put("mail.pop3.port", "995");
        properties.put("mail.pop3.host", "pop.gmail.com");
        properties.put("mail.pop3.user", email);
        properties.put("mail.store.protocol", "pop3");
        properties.put("mail.pop3.ssl.protocols", "TLSv1.2");

        return Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password );
            }
        });

    }

}
