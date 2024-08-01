package org.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name="items")
public class Category {
    @Id
    @SequenceGenerator(name="cat_seq", sequenceName = "cat_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cat_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "tag_name", length = 50)
    private String name;
}
