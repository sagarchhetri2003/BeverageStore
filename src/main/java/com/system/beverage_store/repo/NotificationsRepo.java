package com.system.beverage_store.repo;

import com.system.beverage_store.entity.Notifications;
import com.system.beverage_store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationsRepo extends JpaRepository<Notifications, Integer> {
}
