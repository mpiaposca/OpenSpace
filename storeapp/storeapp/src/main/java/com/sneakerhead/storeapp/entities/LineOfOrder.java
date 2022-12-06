package com.sneakerhead.storeapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "lineoforder", schema = "purchases")
public class LineOfOrder {
    @Id
    @Column( name = "id", nullable = false )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order")
    @ToString.Exclude
    @JsonIgnore
    private Order order;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product")
    private Product product;

    @Basic
    private int quantity;

    @Basic
    @Column(name = "price")
    private double price;

}
