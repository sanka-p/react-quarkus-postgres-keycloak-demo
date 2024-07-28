package org.demo.service;

import org.demo.dto.BillDTO;
import org.demo.response.Response;

public interface BillService {
    /**
     * Retrieves all bills for a specific.
     *
     * @param userId The ID of the user whose bills are to be retrieved.
     * @return A Response object containing the HTTP status code and body.
     *         The body includes the bills associated with the user retrieved from the data source.
     */
    Response<?> getAllBillsForUser(Long userId);

    /**
     * Retrieves a specific bill for a specific user.
     *
     * @param userId The ID of the user to whom the bill belongs.
     * @param billId The ID of the bill to retrieve.
     * @return A Response object containing the HTTP status code and body.
     *         The body includes the bill information retrieved from the data source.
     */

    Response<?> getBillForUser(Long userId, Long billId);

    /**
     * Saves a new bill for the user.
     *
     * @param userId The ID of the user to whom the bill belongs.
     * @param billDTO The BillDTO object containing the bill information to be saved or updated.
     * @return A Response object containing the HTTP status code and body.
     *         The body includes the result of the operation returned from the data source.
     */
    Response<?> saveBill(Long userId, BillDTO billDTO);

    /**
     * Retrieves the total sales amount across all bills.
     *
     * @return A Response object containing the HTTP status code and body.
     *         The body includes the total sales amount.
     */
    Response<?> getTotalSales();

    /**
     * Retrieves the item(s) that was sold the least across all bills.
     *
     * @return A Response object containing the HTTP status code and body.
     *         The body contains the list of minimally sold items.
     */
    Response<?> getMinSaleItems();

    /**
     * Retrieves the item(s) that was sold the most across all bills.
     *
     * @return A Response object containing the HTTP status code and body.
     *         The body contains the list of maximally sold items.
     */
    Response<?> getMaxSaleItems();

    /**
     * Retrieves the average amount spent by users across all bills.
     *
     * @return A Response object containing the HTTP status code and body.
     *         The body contains the average spending's.
     */
    Response<?> getAverageUserSpendings();

    /**
     * Retrieves the count of active users across all bills.
     *
     * @return A Response object containing the HTTP status code and body.
     *         The body contains the count of active users.
     */
    Response<?> getActiveUserCount();
}
