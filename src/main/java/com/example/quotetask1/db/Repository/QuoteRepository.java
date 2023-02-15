package com.example.quotetask1.db.Repository;

import com.example.quotetask1.db.Entity.Quote;
import org.springframework.data.repository.CrudRepository;

public interface QuoteRepository extends CrudRepository<Quote, Integer> {
}
