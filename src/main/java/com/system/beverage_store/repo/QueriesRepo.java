package com.system.beverage_store.repo;

import com.system.beverage_store.entity.Queries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueriesRepo extends JpaRepository<Queries, Integer> {

}
