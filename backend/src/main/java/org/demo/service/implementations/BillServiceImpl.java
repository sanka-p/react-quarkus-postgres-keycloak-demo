package org.demo.service.implementations;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.demo.dto.BillDTO;
import org.demo.dto.BillItemRecordDTO;
import org.demo.dto.ItemDTO;
import org.demo.entity.Bill;
import org.demo.entity.BillItemRecord;
import org.demo.entity.User;
import org.demo.entity.Item;
import org.demo.repository.BillItemRecordRepository;
import org.demo.repository.BillRepository;
import org.demo.repository.ItemRepository;
import org.demo.repository.UserRepository;
import org.demo.response.Response;
import org.demo.response.SuccessResponse;
import org.demo.service.BillService;
import org.demo.util.ItemMapper;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static org.demo.util.BillMapper.*;
import static org.demo.util.ItemMapper.mapItemDTO;

@ApplicationScoped
public class BillServiceImpl implements BillService {
    @Inject
    BillRepository billRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    ItemRepository itemRepository;

    @Inject
    BillItemRecordRepository billItemRecordRepository;

    /**
     * {@inheritDoc}
     */
    public Response<?> getAllBillsForUser(Long userId){
        User user = userRepository.findById(userId);
        List<Bill> allBills = billRepository.list("user", user);
        List<BillDTO> allBillDTOs = new ArrayList<>();

        for (Bill bill: allBills){
            allBillDTOs.add(mapBillDTO(bill));
        }

        Response<List<BillDTO>> response = new SuccessResponse<>();
        response.setStatusCode(RestResponse.StatusCode.ACCEPTED);
        response.setBody(allBillDTOs);
        return response;

    }

    /**
     * {@inheritDoc}
     */
    public Response<?> getBillForUser(Long userId, Long billId){
        User user = userRepository.findById(userId);
        Bill bill = billRepository.find("user", user).firstResult();
        //Bill bill = billRepository.findByUser(user);
        BillDTO billDTO = mapBillDTO(bill);

        Response<BillDTO> response = new SuccessResponse<>();
        response.setStatusCode(RestResponse.StatusCode.ACCEPTED);
        response.setBody(billDTO);
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public Response<?> saveBill(Long userId, BillDTO billDTO){
        User user = userRepository.findById(userId);
        Bill bill = new Bill();
        bill.setUser(user);
        Set<BillItemRecord> billItemRecords = new HashSet<>();
        for(BillItemRecordDTO billItemRecordDTO : billDTO.getItems()){
            BillItemRecord billItemRecord = new BillItemRecord();
            billItemRecord.setBill(bill);
            billItemRecord.setItem(itemRepository.findById(billItemRecordDTO.getId()));
            billItemRecord.setQuantity(billItemRecordDTO.getQuantity());
            billItemRecordRepository.persist(billItemRecord);
            billItemRecords.add(billItemRecord);
        }
        bill.setItems(billItemRecords);
        bill.setIssueDate(billDTO.getIssueDate());

        billRepository.persist(bill);

        Bill savedBill = billRepository.findById(bill.getId());

        Response<BillDTO> response = new SuccessResponse<>();
        response.setStatusCode(RestResponse.StatusCode.ACCEPTED);
        response.setBody(mapBillDTO(savedBill));
        return response;
    }

    /**
     * {@inheritDoc}
     */
    public Response<?> getTotalSales(){
        Double totalSales = billRepository.listAll()
                                .stream()
                                .map(Bill::getTotalAmount)
                                .reduce(0.0, Double::sum);

        Response<Double> response = new SuccessResponse<>();
        response.setStatusCode(RestResponse.StatusCode.ACCEPTED);
        response.setBody(totalSales);
        return response;
    }

    /**
     * {@inheritDoc}
     */
    public Response<?> getMinSaleItems(){
        Optional<Map.Entry<Item, Integer>> minSoldItemEntry =
                billItemRecordRepository.listAll().stream().
                collect(groupingBy(BillItemRecord::getItem, Collectors.summingInt(BillItemRecord::getQuantity)))
                .entrySet()
                .stream()
                .min(Comparator.comparing(Map.Entry::getValue));

        int minSoldQuantity;
        if (minSoldItemEntry.isPresent()){
            minSoldQuantity = minSoldItemEntry.get().getValue();

            //TODO: Handle null case
        } else {
            minSoldQuantity = 0;
        }

        List<ItemDTO> maxSoldItemDTOs = billItemRecordRepository.listAll().stream().
                collect(groupingBy(BillItemRecord::getItem, Collectors.summingInt(BillItemRecord::getQuantity)))
                .entrySet().stream().filter(item -> item.getValue() == minSoldQuantity)
                .map(Map.Entry::getKey)
                .map(ItemMapper::mapItemDTO).toList();

        Response<List<ItemDTO>> response = new SuccessResponse<>();
        response.setStatusCode(RestResponse.StatusCode.ACCEPTED);
        response.setBody(maxSoldItemDTOs);
        return response;
    }

    /**
     * {@inheritDoc}
     */
    public Response<?> getMaxSaleItems(){
        Optional<Map.Entry<Item, Integer>> maxSoldItemEntry =
                billItemRecordRepository.listAll().stream().
                collect(groupingBy(BillItemRecord::getItem, Collectors.summingInt(BillItemRecord::getQuantity)))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue));

        int maxSoldQuantity;
        if (maxSoldItemEntry.isPresent()){
            maxSoldQuantity = maxSoldItemEntry.get().getValue();

            //TODO: Handle null case, send empty list
        } else {
            maxSoldQuantity = 0;
        }

        List<ItemDTO> maxSoldItemDTOs = billItemRecordRepository.listAll().stream().
                collect(groupingBy(BillItemRecord::getItem, Collectors.summingInt(BillItemRecord::getQuantity)))
                .entrySet().stream().filter(item -> item.getValue() == maxSoldQuantity)
                .map(Map.Entry::getKey)
                .map(ItemMapper::mapItemDTO).toList();

        Response<List<ItemDTO>> response = new SuccessResponse<>();
        response.setStatusCode(RestResponse.StatusCode.ACCEPTED);
        response.setBody(maxSoldItemDTOs);
        return response;
    }

    /**
     * {@inheritDoc}
     */
    public Response<?> getAverageUserSpendings(){
        double averageSpendAmount = billRepository.listAll().stream()
                .collect(groupingBy(Bill::getUser, Collectors.summingDouble(Bill::getTotalAmount)))
                .values().stream()
                .mapToDouble(totalAmount -> totalAmount)
                .average()
                .orElse(0.0);

        Response<Double> response = new SuccessResponse<>();
        response.setStatusCode(RestResponse.StatusCode.ACCEPTED);
        response.setBody(averageSpendAmount);
        return response;
    }

    /**
     * {@inheritDoc}
     */
    public Response<?> getActiveUserCount(){
        Long activeUserCount = userRepository.listAll().stream()
                .filter(x -> !x.getBills().isEmpty())
                .count();

        Response<Long> response = new SuccessResponse<>();
        response.setStatusCode(RestResponse.StatusCode.ACCEPTED);
        response.setBody(activeUserCount);
        return response;
    }

}
