package org.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "bill_item_records")
public class BillItemRecord {
    @Id
    @SequenceGenerator(name = "bill_item_record_seq", sequenceName = "bill_item_record_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bill_item_record_seq")
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "purchased_unit_price")
    private double purchasedUnitPrice;

    public double getPurchasedPrice(){
        return quantity * purchasedUnitPrice;
    }
}
