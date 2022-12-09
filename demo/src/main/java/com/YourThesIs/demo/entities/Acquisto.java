package com.YourThesIs.demo.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

    @Getter
    @ToString
    @Setter
    @EqualsAndHashCode
    @Entity
    @Table(name = "acquisto", schema = "ordini")
    public class Acquisto {
    @Id
    @Column( name = "id", nullable = false )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_acquisto;

    @ManyToOne
    @JoinColumn(name = "utente")
    private Utente utente;

    @Basic
    @Column( name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    @OneToMany(mappedBy = "acquisto", cascade = CascadeType.PERSIST)
    private List<ProdottoInAcquisto> listaProdotti;
}
