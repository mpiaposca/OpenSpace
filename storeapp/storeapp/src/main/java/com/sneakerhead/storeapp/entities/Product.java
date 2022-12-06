package com.sneakerhead.storeapp.entities;

//prodotto soggetto a

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@ToString
@Setter
@EqualsAndHashCode
@Entity
//@Table(name = "product", schema = "purchases")
public class Product {

    @Id
    @Column( name = "id", nullable = false )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Basic
    @Column(name = "name", length = 50)
    private String name;

    @Basic
    @Column(name = "barcode", nullable = false, length = 70)
    private String barCode;

    @Basic
    @Column(name = "quantity")
    private int quantity;

    @Basic
    @Column(name = "type", length = 50)
    private String type;

    @Basic
    @Column(name = "price")
    private float price;

    @OneToMany(targetEntity = LineOfOrder.class, mappedBy = "product", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @JsonIgnore
    private List<LineOfOrder> linesOfOrders;
}
