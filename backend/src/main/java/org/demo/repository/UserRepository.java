package org.demo.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.demo.entity.User;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
}
