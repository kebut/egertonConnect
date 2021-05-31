package com.groupwork.pharmacy.management.system.repositories;

import com.groupwork.pharmacy.management.system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
// for number field, we must concatenate it with an empty string so its value can be compared using the LIKE operator.
    @Query("SELECT u FROM User u WHERE u.firstName LIKE %?1%"
           +"OR u.lastName LIKE %?1%"
           +"OR u.email LIKE %?1%"
           +"OR CONCAT(u.id, '') LIKE %?1%")
    public List<User> searchUser(String keyword);

    @Query("SELECT u FROM User u WHERE u.email= ?1 ")
    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.resetPasswordToken= ?1 ")
    public User findByResetPasswordToken(String token);

}
