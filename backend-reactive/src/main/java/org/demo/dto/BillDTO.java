package org.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class BillDTO {
    private long id;

    private LocalDate issueDate;
    
    private UserDTO user;

    private Set<BillItemRecordDTO> items;

    private double totalAmount;
}
