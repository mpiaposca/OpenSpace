package com.YourThesIs.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "prodotto", schema = "ordini")
public class Prodotto {
    @Id
    @Column( name = "id", nullable = false )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Basic
    @Column(name = "nome", length = 50)
    private String nome;

    @Basic
    @Column(name = "barcode", nullable = false, length = 70)
    private String barcode;

    @Basic
    @Column(name = "quantita")
    private int quantita;

    @Basic
    @Column(name = "prezzo")
    private float prezzo;

    //@Version
    @Basic
    @Column(name = "version", length = 50)
    private String version;

    @OneToMany(targetEntity = ProdottoInAcquisto.class, mappedBy = "prodotto", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @JsonIgnore
    private List<ProdottoInAcquisto> prodottoInAcquistoList;
}