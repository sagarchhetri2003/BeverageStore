//package com.system.beverage_store.repo;
//
//import com.system.beverage_store.entity.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface UserRepo extends JpaRepository<User, Integer> {
//    @Query(value = "select * from KOS_USERS where email=?1", nativeQuery = true)
//    Optional<User> findByEmail(String email);
//    User findByEmailAndOTP(String email, String OTP);
//
//    @Query(value = "select COUNT(*) from kos_users", nativeQuery = true)
//    Long countAllRows();
//
//}
package com.system.beverage_store.repo;

import com.system.beverage_store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    @Query(value = "select * from KOS_USERS where email=?1", nativeQuery = true)
    Optional<User> findByEmail(String email);
    User findByEmailAndOTP(String email, String OTP);

    @Query(value = "select COUNT(*) from kos_users", nativeQuery = true)
    Long countAllRows();

}
