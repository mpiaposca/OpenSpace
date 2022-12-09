package com.YourThesIs.demo.servicies;

import com.YourThesIs.demo.entities.Acquisto;
import com.YourThesIs.demo.entities.Prodotto;
import com.YourThesIs.demo.entities.ProdottoInAcquisto;
import com.YourThesIs.demo.entities.Utente;
import com.YourThesIs.demo.repositories.AcquistoRepo;
import com.YourThesIs.demo.repositories.ProdottoInAcquistoRepo;
import com.YourThesIs.demo.repositories.UtenteRepo;
import com.YourThesIs.demo.support.exceptions.DateWrongRangeException;
import com.YourThesIs.demo.support.exceptions.QuantityProductUnavailableException;
import com.YourThesIs.demo.support.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Service
public class ServiceAcquisto {
    @Autowired
    private AcquistoRepo acquistoRepo;
    @Autowired
    private ProdottoInAcquistoRepo lineOfOrderRepo;
    @Autowired
    private UtenteRepo clientRepo;
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Acquisto addAcquisto(Acquisto order) throws QuantityProductUnavailableException {
        Acquisto result = acquistoRepo.save(order);
        for ( ProdottoInAcquisto po : result.getListaProdotti() ) {
            po.setAcquisto(result);
            ProdottoInAcquisto justAdded = lineOfOrderRepo.saveAndFlush(po);
            entityManager.refresh(justAdded);
            Prodotto product = justAdded.getProdotto();
            int newQuantity = product.getQuantita() - po.getQuantita();
            if ( newQuantity < 0 ) {
                throw new QuantityProductUnavailableException();
            }
            product.setQuantita(newQuantity);
            entityManager.refresh(po);
        }
        entityManager.refresh(result);
        return result;
    }

    @Transactional(readOnly = true)
    public List<Acquisto> getByEmail(String email){
        return acquistoRepo.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<Acquisto> getAll() {
        return acquistoRepo.findAll();
    }

    @Transactional(readOnly = true)
    public List<Acquisto> getByUtente(Utente c) throws UserNotFoundException {
        if ( !clientRepo.existsById(c.getId()) ) {
            throw new UserNotFoundException();
        }
        return acquistoRepo.findByUtente(c);
    }

    @Transactional(readOnly = true)
    public List<Acquisto> getByUtenteInPeriod(Utente c, Date dataI, Date dataF) throws UserNotFoundException, DateWrongRangeException {
        if ( !clientRepo.existsById(c.getId()) ) {
            throw new UserNotFoundException();
        }
        if ( dataI.compareTo(dataF) >= 0 ) {
            throw new DateWrongRangeException();
        }
        return acquistoRepo.findByUtenteInPeriod(dataI, dataF, c);
    }

    @Transactional(readOnly = true)
    public List<Acquisto> getByData(Date d){
        return acquistoRepo.findByData(d);
    }

    @Transactional(readOnly = true)
    public Acquisto getById(long id) {
        return acquistoRepo.findById(id);
    }
}
