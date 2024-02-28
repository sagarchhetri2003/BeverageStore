package com.system.beverage_store.repo;

import com.system.beverage_store.entity.Queries;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Collection;


import java.util.Optional;

@DataJpaTest
public class QueriesRepoTest {

    @Autowired
    private QueriesRepo queriesRepo;

    @Test
    @Order(1)
    public void testSaveQuery() {
        // Create a new query
        Queries query = new Queries();
        query.setName("Test Name");
        query.setEmail("test@example.com");
        query.setSubject("Test Subject");
        query.setMessage("Test Message");

        // Save the query
        Queries savedQuery = queriesRepo.save(query);

        // Check if the query is saved successfully
        Assertions.assertNotNull(savedQuery.getId());
        Assertions.assertEquals("Test Name", savedQuery.getName());
        Assertions.assertEquals("test@example.com", savedQuery.getEmail());
        Assertions.assertEquals("Test Subject", savedQuery.getSubject());
        Assertions.assertEquals("Test Message", savedQuery.getMessage());
    }

    @Test
    @Order(2)
    public void testFindById() {
        // Create a new query
        Queries query = new Queries();
        query.setName("Test Name");
        query.setEmail("test@example.com");
        query.setSubject("Test Subject");
        query.setMessage("Test Message");

        // Save the query
        Queries savedQuery = queriesRepo.save(query);

        // Retrieve the saved query by ID
        Optional<Queries> retrievedQueryOptional = queriesRepo.findById(savedQuery.getId());

        // Check if the query is retrieved successfully
        Assertions.assertTrue(retrievedQueryOptional.isPresent());
        Queries retrievedQuery = retrievedQueryOptional.get();
        Assertions.assertEquals("Test Name", retrievedQuery.getName());
        Assertions.assertEquals("test@example.com", retrievedQuery.getEmail());
        Assertions.assertEquals("Test Subject", retrievedQuery.getSubject());
        Assertions.assertEquals("Test Message", retrievedQuery.getMessage());
    }

    @Test
    @Order(3)
    public void testDeleteQuery() {
        // Create a new query
        Queries query = new Queries();
        query.setName("Test Name");
        query.setEmail("test@example.com");
        query.setSubject("Test Subject");
        query.setMessage("Test Message");

        // Save the query
        Queries savedQuery = queriesRepo.save(query);

        // Delete the query
        queriesRepo.delete(savedQuery);

        // Check if the query is deleted successfully
        Optional<Queries> deletedQueryOptional = queriesRepo.findById(savedQuery.getId());
        Assertions.assertTrue(deletedQueryOptional.isEmpty());
    }

    @Test
    @Order(4)
    public void testFindAllQueries() {
        // Save some queries
        Queries query1 = new Queries("Name1", "email1@example.com", "Subject1", "Message1");
        queriesRepo.save(query1);
        Queries query2 = new Queries("Name2", "email2@example.com", "Subject2", "Message2");
        queriesRepo.save(query2);
        Queries query3 = new Queries("Name3", "email3@example.com", "Subject3", "Message3");
        queriesRepo.save(query3);

        // Retrieve all queries
        Iterable<Queries> allQueries = queriesRepo.findAll();

        // Check if all queries are retrieved successfully
        Assertions.assertEquals(3, ((Collection<?>) allQueries).size());
    }

    @Test
    @Order(5)
    public void testCountAllQueries() {
        // Save some queries
        Queries query1 = new Queries("Name1", "email1@example.com", "Subject1", "Message1");
        queriesRepo.save(query1);
        Queries query2 = new Queries("Name2", "email2@example.com", "Subject2", "Message2");
        queriesRepo.save(query2);
        Queries query3 = new Queries("Name3", "email3@example.com", "Subject3", "Message3");
        queriesRepo.save(query3);

        // Count all queries
        Long count = queriesRepo.count();

        // Check if count is correct
        Assertions.assertEquals(3, count);
    }
}
