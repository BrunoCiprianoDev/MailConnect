package com.bcipriano.javaSMTPMailer.repository;

import com.bcipriano.javaSMTPMailer.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
