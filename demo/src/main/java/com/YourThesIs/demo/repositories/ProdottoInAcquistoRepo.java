package com.YourThesIs.demo.repositories;

import com.YourThesIs.demo.entities.ProdottoInAcquisto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdottoInAcquistoRepo extends JpaRepository<ProdottoInAcquisto, Long> {

        ProdottoInAcquisto findById(long id);

        List<ProdottoInAcquisto> findByAcquisto(long order);

        List<ProdottoInAcquisto> findByProdotto(long product);

}