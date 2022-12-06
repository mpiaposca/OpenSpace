package com.sneakerhead.storeapp.repo;

import com.sneakerhead.storeapp.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
    Client findById(long id);

    List<Client> findByName(String name);

    List<Client> findByNameAndSurname(String name, String surname);

    Client findByEmail(String email);

    @Query("SELECT c.id FROM Client c WHERE c.email=:email")
    long findIdByEmail(String email);
}