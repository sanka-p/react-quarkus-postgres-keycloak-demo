package org.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="items")
@NoArgsConstructor
@Getter
@Setter
public class Item {
    @Id
    @SequenceGenerator(name = "items_seq", sequenceName = "items_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "items_seq")
    private Long id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "stock_quantity")
    private int stockQuantity;
}
