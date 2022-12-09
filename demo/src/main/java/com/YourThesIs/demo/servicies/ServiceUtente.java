package com.YourThesIs.demo.servicies;

import com.YourThesIs.demo.entities.Acquisto;
import com.YourThesIs.demo.entities.Utente;
import com.YourThesIs.demo.repositories.UtenteRepo;
import com.YourThesIs.demo.support.exceptions.MailUserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceUtente {
    @Autowired
    private UtenteRepo clientRepo;

    @Transactional
    public Utente recordClient(Utente c) throws MailUserAlreadyExistsException {
        if(clientRepo.findByEmail(c.getEmail()) != null)
            throw new MailUserAlreadyExistsException();
        return clientRepo.save(c);
    }

    @Transactional(readOnly = true)
    public void addOrder(Acquisto order){
        Utente c = clientRepo.findById(order.getUtente().getId());
    }

    @Transactional(readOnly = true)
    public Utente getClientById(long id){
        return clientRepo.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Utente> getClientByNome(String nome){
        return clientRepo.findByNome(nome);
    }

    @Transactional(readOnly = true)
    public List<Utente> getClientByNomeAndCognome(String nome, String cognome){
        return clientRepo.findByNomeAndCognome(nome, cognome);
    }

    @Transactional(readOnly = true)
    public Utente getUtenteByEmail(String email){
        return clientRepo.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<Utente> getAll() { return clientRepo.findAll();
    }

    @Transactional(readOnly = false)
    public void deleteById(Long id) {
        clientRepo.deleteById(id);
    }
}