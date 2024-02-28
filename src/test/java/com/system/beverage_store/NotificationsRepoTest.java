package com.system.beverage_store.repo;

import com.system.beverage_store.entity.Notifications;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class NotificationsRepoTest {

    @Autowired
    private NotificationsRepo notificationsRepo;

    @Test
    public void testSaveNotification() {
        // Create a new notification
        Notifications notification = new Notifications();
        notification.setTopic("Test Topic");
        notification.setDescription("Test Description");

        // Save the notification
        Notifications savedNotification = notificationsRepo.save(notification);

        // Check if the notification is saved successfully
        Assertions.assertNotNull(savedNotification.getId());
        Assertions.assertEquals("Test Topic", savedNotification.getTopic());
        Assertions.assertEquals("Test Description", savedNotification.getDescription());
    }

    @Test
    public void testFindById() {
        // Create a new notification
        Notifications notification = new Notifications();
        notification.setTopic("Test Topic");
        notification.setDescription("Test Description");

        // Save the notification
        Notifications savedNotification = notificationsRepo.save(notification);

        // Retrieve the saved notification by ID
        Optional<Notifications> retrievedNotificationOptional = notificationsRepo.findById(savedNotification.getId());

        // Check if the notification is retrieved successfully
        Assertions.assertTrue(retrievedNotificationOptional.isPresent());
        Notifications retrievedNotification = retrievedNotificationOptional.get();
        Assertions.assertEquals("Test Topic", retrievedNotification.getTopic());
        Assertions.assertEquals("Test Description", retrievedNotification.getDescription());
    }

    @Test
    public void testDeleteNotification() {
        // Create a new notification
        Notifications notification = new Notifications();
        notification.setTopic("Test Topic");
        notification.setDescription("Test Description");

        // Save the notification
        Notifications savedNotification = notificationsRepo.save(notification);

        // Delete the notification
        notificationsRepo.delete(savedNotification);

        // Check if the notification is deleted successfully
        Optional<Notifications> deletedNotificationOptional = notificationsRepo.findById(savedNotification.getId());
        Assertions.assertTrue(deletedNotificationOptional.isEmpty());
    }
}
