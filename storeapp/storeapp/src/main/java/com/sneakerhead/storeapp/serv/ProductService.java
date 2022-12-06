package com.sneakerhead.storeapp.serv;

import com.sneakerhead.storeapp.entities.Product;
import com.sneakerhead.storeapp.help.exceptions.BarCodeAlreadyExistException;
import com.sneakerhead.storeapp.repo.ProductRepo;
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
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Transactional
    public Product addProduct(Product p) throws BarCodeAlreadyExistException {
        if(productRepo.findById(p.getId()) != null)
            throw new BarCodeAlreadyExistException();
        return productRepo.save(p);
    }

    @Transactional(readOnly = true)
    public List<Product> getAll(){
        return productRepo.findAll();
    }

    @Transactional(readOnly = true)
    public List<Product> getAll(int pageNumber, int pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Product> pagedResult = productRepo.findAll(paging);
        if ( pagedResult.hasContent() ) {
            return pagedResult.toList();
        }
        else {
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Product> getByName(String name){
        return productRepo.findByNameContaining(name);
    }

    @Transactional(readOnly = true)
    public List<Product> getByType(String type){
        return productRepo.findByType(type);
    }

    @Transactional(readOnly = true)
    public Product getById(long id){
        return productRepo.findById(id);
    }

}