package com.groupwork.pharmacy.management.system.repositories;

import com.groupwork.pharmacy.management.system.model.Cart;
import com.groupwork.pharmacy.management.system.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    public  List<Cart> findByCustomer(Customer customer);

}
