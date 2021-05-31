package com.groupwork.pharmacy.management.system.service;

import com.groupwork.pharmacy.management.system.model.Clerk;
import com.groupwork.pharmacy.management.system.model.User;
import com.groupwork.pharmacy.management.system.repositories.ClerkRepository;
import com.groupwork.pharmacy.management.system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("customClerkDetailsService")
public class CustomClerkDetailsService implements UserDetailsService {
    @Autowired
    ClerkRepository clerkRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Clerk clerk= clerkRepository.findByEmail(email);
        if(clerk !=null && clerk.isEnabled()){
            return new CustomClerkDetails(clerk);

        }
        else {
            throw new UsernameNotFoundException("Clerk Not Found");
        }

    }

    public List<Clerk> listSearchedClerk(String keyword) {
        if (keyword != null) {
            return clerkRepository.searchClerk(keyword);
        }
        return clerkRepository.findAll();
    }

    public void updateResetPasswordToken(String token,String email) throws UserNotFoundException {
       Clerk clerk= clerkRepository.findByEmail(email);
        if(clerk != null){
            clerk.setResetPasswordToken(token);
            clerkRepository.save(clerk);
        }
        else {
            throw  new UserNotFoundException("could not find any user with email" + email);
        }

    }

    //is used to check if a user belongs to a given resetPasswordToken or not
    public Clerk get(String resetPasswordToken){
        return clerkRepository.findByResetPasswordToken(resetPasswordToken);
    }

    public void updatePassword(Clerk clerk,String newPassword){
        BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        String encodedPassword= passwordEncoder.encode(newPassword);

        clerk.setPassword(encodedPassword);
        clerk.setResetPasswordToken(null); //because the user has set the password successfully hence removing the token
        clerkRepository.save(clerk);

    }


    public List<Clerk> listAll(){
        return clerkRepository.findAll(Sort.by("email").ascending());
    }


}
