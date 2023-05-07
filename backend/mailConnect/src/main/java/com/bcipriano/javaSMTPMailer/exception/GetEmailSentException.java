package com.bcipriano.javaSMTPMailer.exception;

public class GetEmailSentException extends RuntimeException{

    public GetEmailSentException() {
        super("Error get sent e-mails");
    }

    public GetEmailSentException(String message){
        super(message);
    }

}
