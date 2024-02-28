package com.system.beverage_store.service;

import com.system.beverage_store.entity.Notifications;
import com.system.beverage_store.pojo.NotificationsPojo;
import com.system.beverage_store.pojo.UserPojo;

import java.util.List;

public interface NotificationsService {
    String save(NotificationsPojo notificationsPojo);
    List<Notifications> fetchAll();
    Notifications fetchById(Integer id);

    void deleteById(Integer id);
}
