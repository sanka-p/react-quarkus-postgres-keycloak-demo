package org.demo.repository;


import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import org.demo.entity.Category;

public class CategoryRepository implements PanacheRepository<Category> {
}
