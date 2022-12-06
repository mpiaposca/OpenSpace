package com.sneakerhead.storeapp.entities;

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
@Table(name = "order", schema = "purchases")
public class Order {

    @Id
    @Column( name = "id", nullable = false )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "client")
    private Client client;

    @Basic
    @Column( name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<LineOfOrder> productList;
}
