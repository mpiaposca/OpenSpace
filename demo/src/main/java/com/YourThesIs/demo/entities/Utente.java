package com.YourThesIs.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "utente", schema = "ordini")
public class Utente {

    @Id
    @Column( name = "id_utente", nullable = false )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Basic
    @Column( name = "nome_utente", length = 40)
    private String nome;

    @Basic
    @Column( name = "cognome_utente", length = 40)
    private String cognome;

    @Basic
    @Column( name = "email", length = 40)
    private String email;

    @OneToMany(mappedBy = "utente")
    @JsonIgnore
    @ToString.Exclude
    private List<Acquisto> acquisti;

}
