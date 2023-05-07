package com.bcipriano.javaSMTPMailer.exception;

public class GetEmailInboxPOP3Exception extends RuntimeException {

    public GetEmailInboxPOP3Exception() {
        super("Error retrieving emails using the POP3 protocol.");
    }

    public GetEmailInboxPOP3Exception(String message){
        super(message);
    }
}
