package com.groupwork.pharmacy.management.system.service;

import com.groupwork.pharmacy.management.system.model.Admin;
import com.groupwork.pharmacy.management.system.model.Medicine;
import com.groupwork.pharmacy.management.system.repositories.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomMedicineService {
    @Autowired
    MedicineRepository medicineRepository;


    public List<Medicine> listSearchedMedicine(String keyword) {
        if (keyword != null) {
            return medicineRepository.searchMedicine(keyword);
        }
        return medicineRepository.findAll();
    }

    public List<Medicine> listAll(){
        return medicineRepository.findAll(Sort.by("id").ascending());
    }
}
