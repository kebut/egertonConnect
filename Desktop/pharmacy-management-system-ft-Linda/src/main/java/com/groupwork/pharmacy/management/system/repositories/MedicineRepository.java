package com.groupwork.pharmacy.management.system.repositories;

import com.groupwork.pharmacy.management.system.model.Admin;
import com.groupwork.pharmacy.management.system.model.Category;
import com.groupwork.pharmacy.management.system.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine,Long> {
    @Query(value = "SELECT m FROM Medicine m")
    List<Medicine> findAllMedicine();

    @Query(value = "SELECT m FROM Medicine m WHERE m.name LIKE %?1%")
    List<Medicine> findByName(String name);


    @Query("SELECT m FROM Medicine m WHERE m.name LIKE %?1%"
            +"OR m.price LIKE %?1%"
            +"OR m.dateOfExpiry LIKE %?1%"
            +"OR m.drugStatus LIKE %?1%"
            +"OR m.category LIKE %?1%"
            +"OR CONCAT(m.id, '') LIKE %?1%")
    public List<Medicine> searchMedicine(String keyword);

    public List<Medicine> findByCategory(Category category);

    @Modifying
    @Query("DELETE FROM Medicine m where m.id = :id")
    public void deletem(@Param("id") Long id) ;


}
