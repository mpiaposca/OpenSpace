package com.YourThesIs.demo.controllers;

import com.YourThesIs.demo.entities.Acquisto;
import com.YourThesIs.demo.entities.ProdottoInAcquisto;
import com.YourThesIs.demo.entities.Utente;
import com.YourThesIs.demo.repositories.UtenteRepo;
import com.YourThesIs.demo.servicies.ServiceAcquisto;
import com.YourThesIs.demo.servicies.ServiceUtente;
import com.YourThesIs.demo.support.MessaggioRisposta;
import com.YourThesIs.demo.support.exceptions.DateWrongRangeException;
import com.YourThesIs.demo.support.exceptions.QuantityProductUnavailableException;
import com.YourThesIs.demo.support.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/order")
public class ControllerAcquisto {
    @Autowired
    private ServiceAcquisto serviceAcquisto;

    @Autowired
    private UtenteRepo clientRepo;

    @Autowired
    private ServiceUtente clientService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity create(@RequestBody @Valid Acquisto order) { // è buona prassi ritornare l'oggetto inserito
        try {
            long id = clientRepo.findIdByEmail(order.getUtente().getEmail());
            order.getUtente().setId(id);
            clientService.addOrder(order);
            return new ResponseEntity<>(serviceAcquisto.addAcquisto(order), HttpStatus.OK);
        } catch (QuantityProductUnavailableException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantità non disponibile!", e);
        }
    }

    @GetMapping("/getByEmail/{email}")
    public List<Acquisto> getByEmail(@PathVariable String email){
        return serviceAcquisto.getByEmail(email);
    }

    @GetMapping("/lineOfOrder/{id}")
    public List<ProdottoInAcquisto> getOrderLines(@PathVariable long id){
        Acquisto o = serviceAcquisto.getById(id);
        return o.getListaProdotti();
    }

    @GetMapping("/{client}")
    public List<Acquisto> getPurchases(@RequestBody @Valid Utente user) {
        try {
            return serviceAcquisto.getByUtente(user);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Utente non trovato!", e);
        }
    }

    @GetMapping("/{user}/{startDate}/{endDate}")
    public ResponseEntity getAcquistoInPeriod(@Valid @PathVariable("user") Utente user, @PathVariable("startDate") @DateTimeFormat(pattern = "dd-MM-yyyy")
    Date start, @PathVariable("endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date end) {
        try {
            List<Acquisto> result = serviceAcquisto.getByUtenteInPeriod(user, start, end);
            if ( result.size() <= 0 ) {
                return new ResponseEntity<>(new MessaggioRisposta("Nessun risultato!"), HttpStatus.OK);
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Utente non trovato", e);
        } catch (DateWrongRangeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La data di inizio deve essere precendete a quella di fine!!", e);
        }
    }
}
