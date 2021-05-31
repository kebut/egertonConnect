package com.groupwork.pharmacy.management.system.repositories;

import com.groupwork.pharmacy.management.system.model.Clerk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClerkRepository extends JpaRepository<Clerk,Long> {
// for number field, we must concatenate it with an empty string so its value can be compared using the LIKE operator.
    @Query("SELECT c FROM Clerk c WHERE c.firstName LIKE %?1%"
           +"OR c.lastName LIKE %?1%"
           +"OR c.email LIKE %?1%"
           +"OR CONCAT(c.id, '') LIKE %?1%")
    public List<Clerk> searchClerk(String keyword);

    @Query("SELECT c FROM Clerk c WHERE c.email= ?1 ")
    Clerk findByEmail(String email);

    @Query("SELECT c FROM Clerk c WHERE c.resetPasswordToken= ?1 ")
    public Clerk findByResetPasswordToken(String token);

}
