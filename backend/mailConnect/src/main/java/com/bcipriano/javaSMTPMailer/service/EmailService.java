package com.bcipriano.javaSMTPMailer.service;

import com.bcipriano.javaSMTPMailer.config.sessionConfig.SessionPO3;
import com.bcipriano.javaSMTPMailer.config.sessionConfig.SessionSSL;
import com.bcipriano.javaSMTPMailer.exception.GetEmailInboxIMAPException;
import com.bcipriano.javaSMTPMailer.exception.GetEmailInboxPOP3Exception;
import com.bcipriano.javaSMTPMailer.exception.GetEmailSentException;
import com.bcipriano.javaSMTPMailer.exception.SendEmailException;
import com.bcipriano.javaSMTPMailer.model.Email;
import com.bcipriano.javaSMTPMailer.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Service
public class EmailService {


    @Value("${email.userEmail}")
    private String userEmail;

    @Value("${email.password}")
    private String password;

    @Autowired
    private EmailRepository emailRepository;

    public Email sendEmail(Email email){

        try {
            MimeMessage mimeMessage = new MimeMessage(SessionSSL.createSession(userEmail, password));
            mimeMessage.addHeader("Content-type", "text/HTML; charset=UTF-8");
            mimeMessage.addHeader("format", "flowed");
            mimeMessage.addHeader("Content-Transfer-Encoding", "8bit");
            mimeMessage.setFrom(new InternetAddress(userEmail, "NoReply-JD"));

            mimeMessage.setReplyTo(InternetAddress.parse(email.getEmailTo(), false));

            mimeMessage.setSubject(email.getSubject(), "UTF-8");

            mimeMessage.setText(email.getText(), "UTF-8");

            mimeMessage.setSentDate(new Date());

            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getEmailTo(), false));
            Transport.send(mimeMessage);

            return email;
        } catch(Exception e) {
            throw new SendEmailException(e.getMessage());
        }
    }

    public void sendEmailAttachment(Email email){
        try{
            MimeMessage mimeMessage = new MimeMessage(SessionSSL.createSession(userEmail, password));
            mimeMessage.addHeader("Content-type", "text/HTML; charset=UTF-8");
            mimeMessage.addHeader("format", "flowed");
            mimeMessage.addHeader("Content-Transfer-Encoding", "8bit");

            mimeMessage.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));

            mimeMessage.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

            mimeMessage.setSubject(email.getSubject(), "UTF-8");

            mimeMessage.setSentDate(new Date());

            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getEmailTo(), false));

            BodyPart messageBodyPart = new MimeBodyPart();

            messageBodyPart.setText(email.getText());

            Multipart multipart = new MimeMultipart();

            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            String filename = "C:\\Users\\cipri\\OneDrive\\Documentos\\Repositorios_git\\JavaSMTPMailer\\javaSMTPMailer\\src\\main\\java\\com\\bcipriano\\javaSMTPMailer\\attachment\\attachment.txt";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            mimeMessage.setContent(multipart);

            Transport.send(mimeMessage);
        }catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public List<Email> readInboxPO3Emails() {

        try {
            Session sessionPOP3 = SessionPO3.createSession(userEmail, password);
            Store store = sessionPOP3.getStore("pop3");
            store.connect("pop.gmail.com", userEmail, password);
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            Message[] messages = inbox.getMessages();

            List<Email> emails = new ArrayList<>();
            for (Message message : messages) {
                emails.add(Email.builder()
                                .emailTo(userEmail)
                                .emailFrom(Arrays.toString(message.getFrom()))
                                .subject(message.getSubject())
                                .text((String) message.getContent())
                        .build());
            }

            emails = emailRepository.saveAll(emails);
            inbox.close(false);
            store.close();
            return emailRepository.findAll();
        } catch(Exception e){
            throw new GetEmailInboxPOP3Exception(e.getMessage());
        }
    }


    public List<Email> readInboxEmailsIMAP() {

        try {
            Properties props = new Properties();
            props.setProperty("mail.store.protocol", "imaps");
            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com", userEmail, password);
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            Message[] messages = inbox.getMessages();

            List<Email> emails = new ArrayList<>();
            for (Message message : messages) {
                emails.add(Email.builder()
                        .emailTo(userEmail)
                        .emailFrom(Arrays.toString(message.getFrom()))
                        .subject(message.getSubject())
                        .text(message.getContent().toString())
                        .build());
            }
            inbox.close(false);
            store.close();
            return emails;
        } catch (Exception e) {
           throw new GetEmailInboxIMAPException(e.getMessage());
        }
    }

    public List<Email> readSentEmails() {

        try {
            Properties props = new Properties();
            props.setProperty("mail.store.protocol", "imaps");
            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com", userEmail, password);
            Folder sent = store.getFolder("[Gmail]/Sent Mail");
            sent.open(Folder.READ_ONLY);
            Message[] messages = sent.getMessages();
            List<Email> emails = new ArrayList<>();
            for (Message message : messages) {
                emails.add(Email.builder()
                        .emailTo(userEmail)
                        .emailFrom(Arrays.toString(message.getFrom()))
                        .subject(message.getSubject())
                        .text(message.getContent().toString())
                        .build());
            }
            sent.close(false);
            store.close();
            return emails;
        } catch (Exception e) {
            throw new GetEmailSentException(e.getMessage());
        }
    }

}
