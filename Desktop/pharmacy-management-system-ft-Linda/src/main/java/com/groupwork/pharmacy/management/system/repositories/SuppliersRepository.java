package com.groupwork.pharmacy.management.system.repositories;

import com.groupwork.pharmacy.management.system.model.Admin;
import com.groupwork.pharmacy.management.system.model.Suppliers;
import com.groupwork.pharmacy.management.system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SuppliersRepository extends JpaRepository<Suppliers,Long> {

    @Query(value = "SELECT s FROM Suppliers s")
    List<Admin> findAllSuppliers();

    @Query("SELECT s FROM Suppliers s WHERE s.nameOfCompany LIKE %?1%"
            +"OR s.location LIKE %?1%"
            +"OR s.phoneNumber LIKE %?1%"
            +"OR s.email LIKE %?1%"
            +"OR CONCAT(s.id, '') LIKE %?1%")
    public List<Suppliers> searchSupplier(String keyword);

    @Query("SELECT s FROM Suppliers s WHERE s.email= ?1 ")
    Admin findByEmail(String email);

}
