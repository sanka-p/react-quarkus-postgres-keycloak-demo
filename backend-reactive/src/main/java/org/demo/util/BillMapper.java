package org.demo.util;

import org.demo.dto.BillDTO;
import org.demo.dto.BillItemRecordDTO;
import org.demo.entity.Bill;
import org.demo.entity.BillItemRecord;

import java.util.HashSet;
import java.util.Set;

import static org.demo.util.ItemMapper.mapItem;
import static org.demo.util.ItemMapper.mapItemDTO;
import static org.demo.util.UserMapper.mapUser;
import static org.demo.util.UserMapper.mapUserDTO;

public class BillMapper {
    /**
     * Maps a BillItemRecord entity to a BillItemRecordDTO data transfer object.
     *
     * @param billItemRecord The BillItemRecord entity to be mapped.
     * @return A BillItemRecordDTO containing the mapped data from the BillItemRecord entity.
     */
    public static BillItemRecordDTO mapBillItemRecordDTO(BillItemRecord billItemRecord){
        // NOTE: Do not map bills as it will cause a recursive loop
        BillItemRecordDTO billItemRecordDTO = new BillItemRecordDTO();
        billItemRecordDTO.setId(billItemRecord.getId());
        billItemRecordDTO.setItem(mapItemDTO(billItemRecord.getItem()));
        billItemRecordDTO.setQuantity(billItemRecord.getQuantity());
        return billItemRecordDTO;
    }

    /**
     * Maps a BillItemRecordDTO data transfer object to a BillItemRecord entity.
     *
     * @param billItemRecordDTO The BillItemRecordDTO to be mapped.
     * @return A BillItemRecord entity containing the mapped data from the BillItemRecordDTO.
     */
    public static BillItemRecord mapBillItemRecord(BillItemRecordDTO billItemRecordDTO){
        // NOTE: Do not map bills as it will cause a recursive loop
        BillItemRecord billItemRecord = new BillItemRecord();
        billItemRecord.setId(billItemRecordDTO.getId());
        billItemRecord.setItem(mapItem(billItemRecordDTO.getItem()));
        billItemRecord.setQuantity(billItemRecordDTO.getQuantity());
        return billItemRecord;
    }

    /**
     * Maps a Bill entity to a BillDTO data transfer object.
     *
     * @param bill The Bill entity to be mapped.
     * @return A BillDTO containing the mapped data from the Bill entity.
     */
    public static BillDTO mapBillDTO(Bill bill){
        BillDTO billDTO = new BillDTO();
        billDTO.setId(bill.getId());
        Set<BillItemRecordDTO> itemDTOs = new HashSet<>();
        for (BillItemRecord item : bill.getItems()){
            itemDTOs.add(mapBillItemRecordDTO(item));
        }
        billDTO.setItems(itemDTOs);
        billDTO.setIssueDate(bill.getIssueDate());
        billDTO.setUser(mapUserDTO(bill.getUser()));
        billDTO.setTotalAmount(bill.getTotalAmount());
        return billDTO;
    }

    /**
     * Maps a BillDTO data transfer object to a Bill entity.
     *
     * @param billDTO The BillDTO to be mapped.
     * @return A Bill entity containing the mapped data from the BillDTO.
     */
    public static Bill mapBill(BillDTO billDTO){
        Bill bill = new Bill();
        bill.setId(billDTO.getId());
        Set<BillItemRecord> items = new HashSet<>();
        for (BillItemRecordDTO itemDTO : billDTO.getItems()){
            items.add(mapBillItemRecord(itemDTO));
        }
        bill.setItems(items);
        bill.setIssueDate(billDTO.getIssueDate());
        bill.setUser(mapUser(billDTO.getUser()));
        return bill;
    }
}
