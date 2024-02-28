package com.system.beverage_store.pojo;

import com.system.beverage_store.entity.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPojo {
    private Integer id;
    @NotEmpty(message = "Name can't be empty")
    private String name;
    @NotEmpty(message = "E-mail can't be empty")
    private String email;
    @NotEmpty(message = "Number can't be empty")
    private String number;
    @NotEmpty(message = "Address can't be empty")
    private String address;
    @NotEmpty(message = "Password can't be empty")
    private String password;
    private String OTP;
    public UserPojo(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.number = user.getNumber();
        this.address = user.getAddress();
        this.password = user.getPassword();
        this.OTP = user.getOTP();
    }
}
