package com.sneakerhead.storeapp.serv;

import com.sneakerhead.storeapp.entities.Client;
import com.sneakerhead.storeapp.entities.LineOfOrder;
import com.sneakerhead.storeapp.entities.Order;
import com.sneakerhead.storeapp.entities.Product;
import com.sneakerhead.storeapp.help.exceptions.DateWrongRangeException;
import com.sneakerhead.storeapp.help.exceptions.QuantityProductUnavailableException;
import com.sneakerhead.storeapp.help.exceptions.UserNotFoundException;
import com.sneakerhead.storeapp.repo.ClientRepo;
import com.sneakerhead.storeapp.repo.LineOfOrderRepo;
import com.sneakerhead.storeapp.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

    @Service
    public class OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private LineOfOrderRepo lineOfOrderRepo;
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Order addOrder(Order order) throws QuantityProductUnavailableException {
        Order result = orderRepo.save(order);
        for ( LineOfOrder lo : result.getProductList() ) {
            lo.setOrder(result);
            LineOfOrder justAdded = lineOfOrderRepo.saveAndFlush(lo);
            entityManager.refresh(justAdded);
            Product product = justAdded.getProduct();
            int newQuantity = product.getQuantity() - lo.getQuantity();
            if ( newQuantity < 0 ) {
                throw new QuantityProductUnavailableException();
            }
            product.setQuantity(newQuantity);
            entityManager.refresh(lo);
        }
        entityManager.refresh(result);
        return result;
    }

    @Transactional(readOnly = true)
    public List<Order> getByEmail(String email){
        return orderRepo.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<Order> getAll() {
        return orderRepo.findAll();
    }

    @Transactional(readOnly = true)
    public List<Order> getByClient(Client c) throws UserNotFoundException {
        if ( !clientRepo.existsById(c.getId()) ) {
            throw new UserNotFoundException();
        }
        return orderRepo.findByClient(c);
    }

    @Transactional(readOnly = true)
    public List<Order> getByClientInPeriod(Client c, Date dataI, Date dataF) throws UserNotFoundException, DateWrongRangeException {
        if ( !clientRepo.existsById(c.getId()) ) {
            throw new UserNotFoundException();
        }
        if ( dataI.compareTo(dataF) >= 0 ) {
            throw new DateWrongRangeException();
        }
        return orderRepo.findByClientInPeriod(dataI, dataF, c);
    }

    @Transactional(readOnly = true)
    public List<Order> getByData(Date d){
        return orderRepo.findByData(d);
    }

    @Transactional(readOnly = true)
    public Order getById(long id) {
        return orderRepo.findById(id);
    }
}

