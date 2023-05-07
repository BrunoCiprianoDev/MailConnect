package com.bcipriano.javaSMTPMailer.resource.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailWriteDTO {

    @NotBlank(message = "Destinatário não informado.")
    @ApiModelProperty(value = "Email from", example = "emailFrom@gmail.com", required = true)
    @Email(message = "Destinatario inválido.")
    private String emailFrom;

    @ApiModelProperty(value = "Email to", example = "emailTo@gmail.com", required = true)
    @NotBlank(message = "E-mail de orginem não informado.")
    @Email(message = "E-mail inválido.")
    private String emailTo;

    @ApiModelProperty(value = "Title email", example = "Subject email", required = true)
    @NotBlank(message = "Titulo inválido.")
    private String subject;

    @ApiModelProperty(value = "E-mail content", example = "Email contact from...", required = true)
    @NotBlank(message = "Conteúdo do e-mail está vazio.")
    @Column(columnDefinition = "TEXT")
    private String text;

}
