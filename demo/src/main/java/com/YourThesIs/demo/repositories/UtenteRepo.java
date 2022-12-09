package com.YourThesIs.demo.repositories;

import com.YourThesIs.demo.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UtenteRepo extends JpaRepository<Utente, Long> {
    Utente findById(long id);

    List<Utente> findByNome(String nome);

    List<Utente> findByNomeAndCognome(String nome, String cognome);

    Utente findByEmail(String email);

    long findIdByEmail(String email);
}