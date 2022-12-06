package com.sneakerhead.storeapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
//@Table(name = "client", schema = "purchases")
public class Client {

    @Id
    @Column( name = "id", nullable = false )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Basic
    @Column( name = "name", length = 40)
    private String name;

    @Basic
    @Column( name = "surname", length = 40)
    private String surname;

    @Basic
    @Column( name = "email", length = 40)
    private String email;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    @ToString.Exclude
    private List<Order> orders;

}
