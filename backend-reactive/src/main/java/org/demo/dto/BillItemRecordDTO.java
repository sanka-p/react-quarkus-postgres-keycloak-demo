package org.demo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BillItemRecordDTO {
    private long id;

    private ItemDTO item;

    @Min(value = 1, message = "Minimum order quantity for item is 1")
    @Max(value = 99, message = "Maximum order quantity for item is 99")
    private int quantity;
}
