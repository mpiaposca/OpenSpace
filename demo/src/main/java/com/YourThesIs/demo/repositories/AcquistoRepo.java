package com.YourThesIs.demo.repositories;

import com.YourThesIs.demo.entities.Acquisto;
import com.YourThesIs.demo.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface AcquistoRepo extends JpaRepository<Acquisto, Long> {
    Acquisto findById(long id);

    List<Acquisto> findByUtente(Utente c);
    List<Acquisto> findByData(Date data);

    @Query(value = "SELECT o FROM Acquisto o WHERE o.data >= :dataI AND o.data <=:dataF AND o.utente =:c", nativeQuery = true)
    List<Acquisto> findByUtenteInPeriod(Date dataI, Date dataF, Utente c);

    @Query(value = "SELECT o FROM Acquisto o, Utente c WHERE c.email=:email AND o.utente=:c", nativeQuery = true)
    List<Acquisto> findByEmail(String email);
}
