package com.sneakerhead.storeapp.contr;

import com.sneakerhead.storeapp.entities.Client;
import com.sneakerhead.storeapp.help.ResponseMessage;
import com.sneakerhead.storeapp.help.exceptions.MailUserAlreadyExistsException;
import com.sneakerhead.storeapp.serv.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ControllerClient {
    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid Client user) {
        try {
            Client added = clientService.recordClient(user);
            return new ResponseEntity(added, HttpStatus.OK);
        } catch (MailUserAlreadyExistsException e) {
            return new ResponseEntity<>(new ResponseMessage("EMAIL_UTENTE_ESISTENTE"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<Client> getAll() {
        return clientService.getAll();
    }

    @GetMapping("{id}")
    public Client getById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id){
        clientService.deleteById(id);
    }

}