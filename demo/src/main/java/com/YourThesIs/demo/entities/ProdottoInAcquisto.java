package com.YourThesIs.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "prodotto_in_acquisto", schema = "ordini")
    public class ProdottoInAcquisto {
        @Id
        @Column( name = "id", nullable = false )
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;

        @ManyToOne(cascade = CascadeType.PERSIST)
        @JoinColumn(name = "rispettivo_acquisto")
        @ToString.Exclude
        @JsonIgnore
        private Acquisto acquisto;

        @ManyToOne(cascade = CascadeType.PERSIST)
        @JoinColumn(name = "prodotto")
        private Prodotto prodotto;

        @Basic
        private int quantita;

        @Basic
        @Column(name = "prezzo")
        private double prezzo;


}
