package com.bcipriano.javaSMTPMailer.exception.resourceExceptions;

import com.bcipriano.javaSMTPMailer.exception.GetEmailInboxIMAPException;
import com.bcipriano.javaSMTPMailer.exception.GetEmailInboxPOP3Exception;
import com.bcipriano.javaSMTPMailer.exception.GetEmailSentException;
import com.bcipriano.javaSMTPMailer.exception.SendEmailException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionController {

    @ExceptionHandler(SendEmailException.class)
    public ResponseEntity<StandartError> sendEmailException(SendEmailException sendEmailException, HttpServletRequest servletRequest) {
        StandartError standartError = StandartError.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(sendEmailException.getMessage())
                .error("SendEmailException")
                .path(servletRequest.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standartError);
    }

    @ExceptionHandler(GetEmailInboxPOP3Exception.class)
    public ResponseEntity<StandartError> getEmailInboxPOP3Exception(GetEmailInboxPOP3Exception getEmailInboxPOP3Exception, HttpServletRequest servletRequest) {
        StandartError standartError = StandartError.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(getEmailInboxPOP3Exception.getMessage())
                .error("GetEmailInboxPOP3Exception")
                .path(servletRequest.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standartError);
    }

    @ExceptionHandler(GetEmailInboxIMAPException.class)
    public ResponseEntity<StandartError> getEmailInboxIMAPException(GetEmailInboxIMAPException getEmailInboxIMAPException, HttpServletRequest servletRequest) {
        StandartError standartError = StandartError.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(getEmailInboxIMAPException.getMessage())
                .error("GetEmailInboxIMAPException")
                .path(servletRequest.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standartError);
    }

    @ExceptionHandler(GetEmailSentException.class)
    public ResponseEntity<StandartError> getEmailSentException(GetEmailSentException getEmailSentException, HttpServletRequest servletRequest) {
        StandartError standartError = StandartError.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(getEmailSentException.getMessage())
                .error("GetEmailSentException")
                .path(servletRequest.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standartError);
    }

}
