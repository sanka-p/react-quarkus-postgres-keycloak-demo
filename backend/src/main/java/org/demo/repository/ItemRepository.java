package org.demo.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.demo.entity.Item;

@ApplicationScoped
public class ItemRepository implements PanacheRepository<Item> {
}
