package com.YourThesIs.demo.controllers;

import com.YourThesIs.demo.entities.Prodotto;
import com.YourThesIs.demo.servicies.ServiceProdotto;
import com.YourThesIs.demo.support.MessaggioRisposta;
import com.YourThesIs.demo.support.exceptions.BarCodeAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ControllerProdotto {
    @Autowired
    private ServiceProdotto productService;

    @PostMapping
    public ResponseEntity<MessaggioRisposta> create(@RequestBody @Valid Prodotto product) {
        try {
            productService.addProdotto(product);
        } catch (BarCodeAlreadyExistException e) {
            return new ResponseEntity<>(new MessaggioRisposta("CODICE_PRODOTTO_ESISTENTE"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new MessaggioRisposta("Aggiunto con successo!"), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Prodotto>> getAll(){
        return new ResponseEntity<List<Prodotto>>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/paged")
    public ResponseEntity getAll(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "15") int pageSize, @RequestParam(value = "sortBy", defaultValue = "id") String sortBy) {
        List<Prodotto> result = productService.getAll(pageNumber, pageSize, sortBy);
        if ( result.size() <= 0 ) {
            return new ResponseEntity<>(new MessaggioRisposta("No results!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prodotto> getById(@PathVariable long id){
        return new ResponseEntity<>(productService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/search/by_name")
    public ResponseEntity getByName(@RequestParam(required = false) String name) {
        List<Prodotto> result = productService.getByName(name);
        if ( result.size() <= 0 ) {
            return new ResponseEntity<>(new MessaggioRisposta("No results!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}