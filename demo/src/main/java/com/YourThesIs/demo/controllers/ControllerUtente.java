package com.YourThesIs.demo.controllers;


import com.YourThesIs.demo.entities.Utente;
import com.YourThesIs.demo.servicies.ServiceUtente;
import com.YourThesIs.demo.support.MessaggioRisposta;
import com.YourThesIs.demo.support.exceptions.MailUserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/cliente")
public class ControllerUtente {
    @Autowired
    private ServiceUtente serviceUtente;


    @PostMapping
    public ResponseEntity create(@RequestBody @Valid Utente user) {
        try {
            Utente added = serviceUtente.recordClient(user);
            return new ResponseEntity(added, HttpStatus.OK);
        } catch (MailUserAlreadyExistsException e) {
            return new ResponseEntity<>(new MessaggioRisposta("EMAIL_UTENTE_ESISTENTE"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<Utente> getAll() {
        return serviceUtente.getAll();
    }

    @GetMapping("{id}")
    public Utente getById(@PathVariable Long id) {
        return serviceUtente.getClientById(id);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id){
        serviceUtente.deleteById(id);
    }

}