package com.system.beverage_store.service;

import com.system.beverage_store.entity.Queries;
import com.system.beverage_store.pojo.QueriesPojo;

import java.util.List;

public interface QueryService {
    List<Queries> fetchAll();

    String save(QueriesPojo queriesPojo);
}
