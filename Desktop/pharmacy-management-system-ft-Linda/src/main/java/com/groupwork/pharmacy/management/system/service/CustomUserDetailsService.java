package com.groupwork.pharmacy.management.system.service;

import com.groupwork.pharmacy.management.system.model.User;
import com.groupwork.pharmacy.management.system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository repo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user= repo.findByEmail(email);
        if(user!=null && user.isEnabled()){
            return new CustomUserDetails(user);

        }
        else {
            throw new UsernameNotFoundException("User Not Found");
        }

    }

    public List<User> listSearchedUser(String keyword) {
        if (keyword != null) {
            return repo.searchUser(keyword);
        }
        return repo.findAll();
    }

    public void updateResetPasswordToken(String token,String email) throws UserNotFoundException {
        User user= repo.findByEmail(email);
        if(user != null){
            user.setResetPasswordToken(token);
            repo.save(user);
        }
        else {
            throw  new UserNotFoundException("could not find any user with email" + email);
        }

    }

    //is used to check if a user belongs to a given resetPasswordToken or not
    public User get(String resetPasswordToken){
        return repo.findByResetPasswordToken(resetPasswordToken);
    }

    public void updatePassword(User user,String newPassword){
        BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        String encodedPassword= passwordEncoder.encode(newPassword);

        user.setPassword(encodedPassword);
        user.setResetPasswordToken(null); //because the user has set the password successfully hence removing the token
        repo.save(user);

    }


    public List<User> listAll(){
        return repo.findAll(Sort.by("email").ascending());
    }


}
