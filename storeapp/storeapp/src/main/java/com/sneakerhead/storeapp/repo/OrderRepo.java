package com.sneakerhead.storeapp.repo;

import com.sneakerhead.storeapp.entities.Client;
import com.sneakerhead.storeapp.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    Order findById(long id);
    List<Order> findByClient(Client c);
    List<Order> findByData(Date data);
    @Query("SELECT o FROM Order o WHERE o.data >= :dataI AND o.data <=:dataF AND o.client =:c")
    List<Order> findByClientInPeriod(Date dataI, Date dataF, Client c);

    @Query("SELECT o FROM Order o, Client c WHERE c.email=:email AND o.client=:c")
    List<Order> findByEmail(String email);
}