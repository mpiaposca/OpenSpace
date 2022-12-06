package com.sneakerhead.storeapp.contr;


import com.sneakerhead.storeapp.entities.Client;
import com.sneakerhead.storeapp.entities.LineOfOrder;
import com.sneakerhead.storeapp.entities.Order;
import com.sneakerhead.storeapp.help.ResponseMessage;
import com.sneakerhead.storeapp.help.exceptions.DateWrongRangeException;
import com.sneakerhead.storeapp.help.exceptions.QuantityProductUnavailableException;
import com.sneakerhead.storeapp.help.exceptions.UserNotFoundException;
import com.sneakerhead.storeapp.repo.ClientRepo;
import com.sneakerhead.storeapp.serv.ClientService;
import com.sneakerhead.storeapp.serv.OrderService;
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
public class ControllerOrder {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private ClientService clientService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity create(@RequestBody @Valid Order order) { // è buona prassi ritornare l'oggetto inserito
        try {
            long id = clientRepo.findIdByEmail(order.getClient().getEmail());
            order.getClient().setId(id);
            clientService.addOrder(order);
            return new ResponseEntity<>(orderService.addOrder(order), HttpStatus.OK);
        } catch (QuantityProductUnavailableException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantità non disponibile!", e);
            // realmente il messaggio dovrebbe essrere più esplicativo (es. specificare il prodotto di cui non vi è disponibilità)
        }
    }

    @GetMapping("/getByEmail/{email}")
    public List<Order> getByEmail(@PathVariable String email){
        return orderService.getByEmail(email);
    }

    @GetMapping("/lineOfOrder/{id}")
    public List<LineOfOrder> getOrderLines(@PathVariable long id){
        Order o = orderService.getById(id);
        return o.getProductList();
    }

    @GetMapping("/{client}")
    public List<Order> getPurchases(@RequestBody @Valid Client user) {
        try {
            return orderService.getByClient(user);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Utente non trovato!", e);
        }
    }
    @GetMapping("/{user}/{startDate}/{endDate}")
    public ResponseEntity getPurchasesInPeriod( @Valid @PathVariable("user") Client user, @PathVariable("startDate") @DateTimeFormat(pattern = "dd-MM-yyyy")
            Date start, @PathVariable("endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date end) {
        try {
            List<Order> result = orderService.getByClientInPeriod(user, start, end);
            if ( result.size() <= 0 ) {
                return new ResponseEntity<>(new ResponseMessage("Nessun risultato!"), HttpStatus.OK);
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Utente non trovato", e);
        } catch (DateWrongRangeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La data di inizio deve essere precendete a quella di fine!!", e);
        }
    }
}
