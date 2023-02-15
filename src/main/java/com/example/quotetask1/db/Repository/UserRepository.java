package com.example.quotetask1.db.Repository;

import com.example.quotetask1.db.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
