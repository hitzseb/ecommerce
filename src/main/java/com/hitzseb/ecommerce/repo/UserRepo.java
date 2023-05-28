package com.hitzseb.ecommerce.repo;

import com.hitzseb.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
