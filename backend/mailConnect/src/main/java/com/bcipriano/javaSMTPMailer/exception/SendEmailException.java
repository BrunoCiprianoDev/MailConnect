package com.bcipriano.javaSMTPMailer.exception;

public class SendEmailException extends RuntimeException{

    public SendEmailException() {
        super("Error sending the email.");
    }

    public SendEmailException(String message){
        super(message);
    }

}
