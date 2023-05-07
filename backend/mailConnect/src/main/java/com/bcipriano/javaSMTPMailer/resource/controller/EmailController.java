package com.bcipriano.javaSMTPMailer.resource.controller;

import com.bcipriano.javaSMTPMailer.resource.dto.EmailReadDTO;
import com.bcipriano.javaSMTPMailer.resource.dto.EmailWriteDTO;
import com.bcipriano.javaSMTPMailer.model.Email;
import com.bcipriano.javaSMTPMailer.service.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/email")
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/sending")
    public ResponseEntity<Email> sendingEmail(@RequestBody @Valid EmailWriteDTO emailWriteDTO){
        Email email = Email.builder().build();
        BeanUtils.copyProperties(emailWriteDTO, email);
        Email emailSaved = emailService.sendEmail(email);
        return new ResponseEntity("E-mail enviado com sucesso!", HttpStatus.CREATED);
    }

    @PostMapping("/sending/attachment")
    public ResponseEntity<Email> sendingEmailAttachment(@RequestBody @Valid EmailWriteDTO emailWriteDTO){
        Email email = Email.builder().build();
        BeanUtils.copyProperties(emailWriteDTO, email);
        emailService.sendEmailAttachment(email);
        return new ResponseEntity("E-mail enviado com sucesso!", HttpStatus.CREATED);
    }

    @GetMapping("/POP3/inbox")
    public ResponseEntity<List<EmailReadDTO>> getInboxPOP3Email(){
        List<Email> emails = emailService.readInboxPO3Emails();
        return new ResponseEntity(EmailReadDTO.createListEmailReadDTO(emails), HttpStatus.OK);
    }


    @GetMapping("/IMAP/inbox")
    public ResponseEntity<List<EmailReadDTO>> getInboxIMAPEmail(){
        List<Email> emails = emailService.readInboxEmailsIMAP();
        return new ResponseEntity(EmailReadDTO.createListEmailReadDTO(emails), HttpStatus.OK);
    }

    @GetMapping("/sent")
    public ResponseEntity<List<EmailReadDTO>> getSentEmails(){
        List<Email> emails = emailService.readSentEmails();
        return new ResponseEntity(EmailReadDTO.createListEmailReadDTO(emails), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException) {
        Map<String, String> errors = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error) -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                }
        );
        return errors;
    }

}
