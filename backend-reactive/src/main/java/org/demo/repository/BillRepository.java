package org.demo.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.demo.entity.Bill;


@ApplicationScoped
public class BillRepository implements PanacheRepositoryBase<Bill, Long> {
}
