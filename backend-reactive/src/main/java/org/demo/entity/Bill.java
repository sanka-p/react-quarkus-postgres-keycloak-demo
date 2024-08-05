package org.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.demo.dto.BillItemRecordDTO;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="bills")
@NoArgsConstructor
@Getter
@Setter
public class Bill {
    @Id
    @SequenceGenerator(name = "bills_seq", sequenceName = "bills_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bills_seq")
    @Column(name = "id")
    private long id;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    private Set<BillItemRecord> items;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PROTECTED)
    @Column(name = "total_amount")
    private double totalAmount;

    @PreUpdate
    @PrePersist
    public void calculateTotalAmount() {
        double total = 0.0;
        for (BillItemRecord billItemRecord: items){
            total += billItemRecord.getPurchasedPrice();
        }
        setTotalAmount(total);
    }
}
