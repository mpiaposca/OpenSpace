package com.sneakerhead.storeapp.repo;

import com.sneakerhead.storeapp.entities.LineOfOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LineOfOrderRepo extends JpaRepository<LineOfOrder, Long> {
        LineOfOrder findById(long id);
        List<LineOfOrder> findByOrder(long order);
        List<LineOfOrder> findByProduct(long product);
    }
