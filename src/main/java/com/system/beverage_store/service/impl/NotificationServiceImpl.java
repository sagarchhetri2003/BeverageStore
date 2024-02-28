package com.system.beverage_store.service.impl;

import com.system.beverage_store.entity.Notifications;
import com.system.beverage_store.pojo.NotificationsPojo;
import com.system.beverage_store.repo.NotificationsRepo;
import com.system.beverage_store.service.NotificationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationsService {
    private final NotificationsRepo notificationsRepo;

    @Override
    public String save(NotificationsPojo notificationsPojo) {
        Notifications notifications;
        if (notificationsPojo.getId() != null) {
            notifications = notificationsRepo.findById(notificationsPojo.getId()).orElseThrow(() -> new RuntimeException("Not Found"));
        } else {
            notifications = new Notifications();
        }
        if(notifications.getId()!=null){
            notifications.setId(notificationsPojo.getId());
        }
        notifications.setTopic(notificationsPojo.getTopic());
        notifications.setDescription(notificationsPojo.getDescription());
        notificationsRepo.save(notifications);
        return "created";
    }

    @Override
    public List<Notifications> fetchAll() {
        return this.notificationsRepo.findAll();
    }
    @Override
    public Notifications fetchById(Integer id) {
        return notificationsRepo.findById(id).orElseThrow(()->new RuntimeException("not found"));

    }
    @Override
    public void deleteById(Integer id) {
        notificationsRepo.deleteById(id);
    }

}
