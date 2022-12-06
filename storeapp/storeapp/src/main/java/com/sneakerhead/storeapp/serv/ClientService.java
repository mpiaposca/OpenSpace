package com.sneakerhead.storeapp.serv;


import com.sneakerhead.storeapp.entities.Client;
import com.sneakerhead.storeapp.entities.Order;
import com.sneakerhead.storeapp.help.exceptions.MailUserAlreadyExistsException;
import com.sneakerhead.storeapp.repo.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepo clientRepo;

    @Transactional
    public Client recordClient(Client c) throws MailUserAlreadyExistsException {
        if(clientRepo.findByEmail(c.getEmail()) != null)
            throw new MailUserAlreadyExistsException();
        return clientRepo.save(c);
    }

    @Transactional(readOnly = true)
    public void addOrder(Order order){
        Client c = clientRepo.findById(order.getClient().getId());
    }

    @Transactional(readOnly = true)
    public Client getClientById(long id){
        return clientRepo.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Client> getClientByName(String nome){
        return clientRepo.findByName(nome);
    }

    @Transactional(readOnly = true)
    public List<Client> getClientByNameAndSurname(String name, String surname){
        return clientRepo.findByNameAndSurname(name, surname);
    }

    @Transactional(readOnly = true)
    public Client getClientByEmail(String email){
        return clientRepo.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<Client> getAll() { return clientRepo.findAll();
    }

    @Transactional(readOnly = false)
    public void deleteById(Long id) {
        clientRepo.deleteById(id);
    }
}