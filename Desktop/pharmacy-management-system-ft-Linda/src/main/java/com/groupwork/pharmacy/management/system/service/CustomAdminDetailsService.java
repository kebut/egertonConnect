package com.groupwork.pharmacy.management.system.service;

import com.groupwork.pharmacy.management.system.model.Admin;

import com.groupwork.pharmacy.management.system.model.User;
import com.groupwork.pharmacy.management.system.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("repository")
public class CustomAdminDetailsService implements UserDetailsService {
    @Autowired
    AdminRepository repository;


    @Override
    public  UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin= repository.findByEmail(email);
        if(admin !=null && admin.isEnabled()){
            return new CustomAdminDetails(admin);
        }
        else {
            throw new UsernameNotFoundException("Admin Not Found");
        }

    }

    public List<Admin> listSearchedAdmin(String keyword) {
        if (keyword != null) {
            return repository.searchAdmin(keyword);
        }
        return repository.findAll();
    }


    public void updateResetPasswordToken(String token,String email) throws UserNotFoundException {
       Admin admin= repository.findByEmail(email);
        if(admin != null ){
            admin.setResetPasswordToken(token);
            repository.save(admin);
        }
        else {
            throw  new UserNotFoundException("could not find any user with email" + email);
        }

    }

    //is used to check if a user belongs to a given resetPasswordToken or not
    public Admin get(String resetPasswordToken){
        return repository.findByResetPasswordToken(resetPasswordToken);
    }

    public void updatePassword(Admin admin,String newPassword){
        BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        String encodedPassword= passwordEncoder.encode(newPassword);

        admin.setPassword(encodedPassword);
        admin.setResetPasswordToken(null); //because the user has set the password successfully hence removing the token
        repository.save(admin);

    }

    public List<Admin> listAll(){
        return repository.findAll(Sort.by("email").ascending());
    }
}
