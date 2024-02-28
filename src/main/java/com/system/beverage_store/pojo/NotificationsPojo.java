package com.system.beverage_store.pojo;

import com.system.beverage_store.entity.Notifications;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationsPojo {
    private Integer id;
    @NotEmpty(message = "Topic can't be empty")
    private String topic;
    @NotEmpty(message = "Description can't be empty")
    private String description;

    public NotificationsPojo(Notifications notifications) {
        this.id = notifications.getId();
        this.topic = notifications.getTopic();
        this.description = notifications.getDescription();
    }
}
