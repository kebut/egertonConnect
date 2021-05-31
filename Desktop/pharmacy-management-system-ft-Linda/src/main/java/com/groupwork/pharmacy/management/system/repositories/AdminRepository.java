package com.groupwork.pharmacy.management.system.repositories;

import com.groupwork.pharmacy.management.system.model.Admin;
import com.groupwork.pharmacy.management.system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin,Long> {

    @Query(value = "SELECT a FROM Admin a")
    List<Admin> findAllAdmin();

    @Query("SELECT a FROM Admin a WHERE a.firstName LIKE %?1%"
            +"OR a.lastName LIKE %?1%"
            +"OR a.email LIKE %?1%"
            +"OR CONCAT(a.id, '') LIKE %?1%")
    public List<Admin> searchAdmin(String keyword);

    @Query("SELECT a FROM Admin a WHERE a.email= ?1 ")
    Admin findByEmail(String email);


    @Query("SELECT a FROM Admin a WHERE a.resetPasswordToken= ?1 ")
    public Admin findByResetPasswordToken(String token);

}
