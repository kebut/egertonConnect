package com.groupwork.pharmacy.management.system.service;

import com.groupwork.pharmacy.management.system.model.Suppliers;
import com.groupwork.pharmacy.management.system.model.User;
import com.groupwork.pharmacy.management.system.repositories.SuppliersRepository;
import com.groupwork.pharmacy.management.system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("customSupplierDetailsService")
public class CustomSupplierDetailsService implements UserDetailsService {
    @Autowired
    SuppliersRepository suppliersRepository;


    public List<Suppliers> listAll(){
        return suppliersRepository.findAll(Sort.by("email").ascending());
    }


    public List<Suppliers> listSearchedSupplier(String keyword) {
        if (keyword != null) {
            return suppliersRepository.searchSupplier(keyword);
        }
        return suppliersRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
