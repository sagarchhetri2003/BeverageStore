package com.system.beverage_store.service.impl;

import com.system.beverage_store.config.PasswordEncoderUtil;
import com.system.beverage_store.entity.Product;
import com.system.beverage_store.entity.User;
import com.system.beverage_store.exception.AppException;
import com.system.beverage_store.pojo.UserPojo;
import com.system.beverage_store.repo.ProductRepo;
import com.system.beverage_store.repo.UserRepo;
import com.system.beverage_store.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    public final UserRepo userRepo;
    private final JavaMailSender getJavaMailSender;
    private final ProductRepo productRepo;

    @Override
    public String save(UserPojo userPojo) {
        User user = new User();
        if(userPojo.getId()!=null){
            user.setId(userPojo.getId());
        }
        user.setName(userPojo.getName());
        user.setEmail(userPojo.getEmail());
        user.setNumber(userPojo.getNumber());
        user.setAddress(userPojo.getAddress());
        user.setPassword(PasswordEncoderUtil.getInstance().encode(userPojo.getPassword()));
        userRepo.save(user);
        return "created";
    }
    @Override
    public UserPojo findByEmail(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new AppException("Invalid User email", HttpStatus.BAD_REQUEST));
        return new UserPojo(user);
    }

    @Override
    public String update(UserPojo userPojo) {
        User user = new User();
        if(userPojo.getId()!=null){
            user.setId(userPojo.getId());
        }
        user.setName(userPojo.getName());
        user.setEmail(userPojo.getEmail());
        user.setNumber(userPojo.getNumber());
        user.setAddress(userPojo.getAddress());
        userRepo.save(user);
        return "created";
    }

    @Override
    public void processPasswordResetRequest(String email) {
        Optional<User> optionalUser = userRepo.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String OTP = generateOTP();
            user.setOTP(OTP);
            userRepo.save(user);
            sendOTPEmail(email, OTP);
        }
    }

    @Override
    public void resetPassword(String email, String OTP, String password) {
        User user = userRepo.findByEmailAndOTP(email, OTP);
        if (user != null) {
            if (password == null) {
                throw new IllegalArgumentException("Password cannot be null");
            }
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(password);
            user.setPassword(encodedPassword);
            user.setOTP(null);
            userRepo.save(user);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public List<User> fetchAll() {
        return this.userRepo.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        userRepo.deleteById(id);
    }

    @Override
    public Product fetchById(Integer id) {
        return this.productRepo.findById(id).orElseThrow(()->new RuntimeException("not found"));
    }

    private String generateOTP() {
        return String.format("%06d", new Random().nextInt(1000000));
    }

    private void sendOTPEmail(String email, String OTP) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Reset OTP");
        message.setText("Your OTP for resetting your password is: " + OTP);
        getJavaMailSender.send(message);
    }
    public Long countRows() {
        return userRepo.countAllRows();
    }

}

