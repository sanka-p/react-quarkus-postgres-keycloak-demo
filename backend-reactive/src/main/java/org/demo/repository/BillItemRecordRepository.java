package org.demo.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.demo.entity.BillItemRecord;

@ApplicationScoped
public class BillItemRecordRepository implements PanacheRepositoryBase<BillItemRecord, Long> {
}
