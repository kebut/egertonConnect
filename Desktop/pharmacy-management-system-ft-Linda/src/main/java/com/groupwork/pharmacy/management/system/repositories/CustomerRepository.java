package com.groupwork.pharmacy.management.system.repositories;
import com.groupwork.pharmacy.management.system.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query(value = "SELECT c FROM Customer c")
    List<Customer> findAllCustomer();

    @Query("SELECT c FROM Customer c WHERE c.firstName LIKE %?1%"
            +"OR c.lastName LIKE %?1%"
            +"OR c.email LIKE %?1%"
            +"OR CONCAT(c.id, '') LIKE %?1%")
    public List<Customer> searchCustomer(String keyword);

    @Query("SELECT c FROM Customer c WHERE c.email= ?1 ")
    Customer findByEmail(String email);


    @Query("SELECT c FROM Customer c WHERE c.resetPasswordToken= ?1 ")
    public Customer findByResetPasswordToken(String token);

}
