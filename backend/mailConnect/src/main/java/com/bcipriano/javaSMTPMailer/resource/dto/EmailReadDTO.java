package com.bcipriano.javaSMTPMailer.resource.dto;

import com.bcipriano.javaSMTPMailer.model.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailReadDTO {

    private Long id;

    private String emailFrom;

    private String emailTo;

    private String subject;

    private String text;

    public static List<EmailReadDTO> createListEmailReadDTO(List<Email> emails) {
        List<EmailReadDTO> emailReadDTOList = new ArrayList<>();
        for(Email email : emails){
            EmailReadDTO emailReadDTO = EmailReadDTO.builder().build();
            BeanUtils.copyProperties(email, emailReadDTO);
            emailReadDTOList.add(emailReadDTO);
        }
        return emailReadDTOList;
    }
}
