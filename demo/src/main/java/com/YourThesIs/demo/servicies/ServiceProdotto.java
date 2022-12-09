package com.YourThesIs.demo.servicies;

import com.YourThesIs.demo.entities.Prodotto;
import com.YourThesIs.demo.repositories.RepoProdotto;
import com.YourThesIs.demo.support.exceptions.BarCodeAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceProdotto{
    @Autowired
    private RepoProdotto productRepo;

    @Transactional
    public Prodotto addProdotto(Prodotto p) throws BarCodeAlreadyExistException {
        if(productRepo.findById(p.getId()) != null)
            throw new BarCodeAlreadyExistException();
        return productRepo.save(p);
    }

    @Transactional(readOnly = true)
    public List<Prodotto> getAll(){
        return productRepo.findAll();
    }

    @Transactional(readOnly = true)
    public List<Prodotto> getAll(int pageNumber, int pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Prodotto> pagedResult = productRepo.findAll(paging);
        if ( pagedResult.hasContent() ) {
            return pagedResult.toList();
        }
        else {
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Prodotto> getByName(String name){
        return productRepo.findByNomeContaining(name);
    }

    @Transactional(readOnly = true)
    public List<Prodotto> getByBarCode(String barcode){
        return productRepo.findByBarCode(barcode);
    }

    @Transactional(readOnly = true)
    public Prodotto getById(long id){
        return productRepo.findById(id);
    }

}
