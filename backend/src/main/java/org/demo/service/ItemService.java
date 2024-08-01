package org.demo.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.demo.dto.ItemDTO;
import org.demo.response.Response;

@ApplicationScoped
public interface ItemService {
    /**
     * Retrieves all items.
     *
     * @return A Response object containing the HTTP status code and body.
     *         The body includes the list of items.
     */
    public Response<?> getAllItems();

    /**
     * Retrieve item by id.
     *
     * @return A Response object containing the HTTP status code and body.
     *         The body includes the item.
     */
    public ItemDTO getItem(Long id);

    /**
     * Persists a new item.
     *
     * @param itemDTO The ItemDTO object containing the item information to be saved.
     * @return A Response object containing the HTTP status code and body.
     *         The body contains the newly created item details.
     */
    public Response<?> saveItem(ItemDTO itemDTO);
}
