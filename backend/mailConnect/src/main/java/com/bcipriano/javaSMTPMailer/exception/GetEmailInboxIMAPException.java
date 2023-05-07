package com.bcipriano.javaSMTPMailer.exception;

public class GetEmailInboxIMAPException extends RuntimeException{

    public GetEmailInboxIMAPException() {
        super("Error retrieving emails using the IMAP protocol.");
    }

    public GetEmailInboxIMAPException(String message){
        super(message);
    }

}
