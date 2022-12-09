package com.YourThesIs.demo.repositories;

import com.YourThesIs.demo.entities.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepoProdotto extends JpaRepository<Prodotto, Long> {
    Prodotto findById(long id);

    List<Prodotto> findByNomeContaining(String name);

    @Query("SELECT p FROM Prodotto p WHERE p.barcode=:barcode")
    List<Prodotto> findByBarCode(String barcode);

    @Query("SELECT p FROM Prodotto p")
    List<Prodotto> findAll();
}
