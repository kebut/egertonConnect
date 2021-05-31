package com.groupwork.pharmacy.management.system.service;

import com.groupwork.pharmacy.management.system.model.Admin;
import com.groupwork.pharmacy.management.system.model.Customer;
import com.groupwork.pharmacy.management.system.model.Suppliers;
import com.groupwork.pharmacy.management.system.repositories.CustomerRepository;
import com.groupwork.pharmacy.management.system.repositories.SuppliersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("customCustomerDetailsService")
public class CustomCustomerDetailsService implements UserDetailsService {
    @Autowired
    CustomerRepository customerRepository;


    @Override
    public  UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer= customerRepository.findByEmail(email);
        if(customer!=null && customer.isEnabled()){
            return new CustomCustomerDetails(customer);
        }
        else {
            throw new UsernameNotFoundException("Customer Not Found");
        }

    }

    public List<Customer> listSearchedCustomer(String keyword) {
        if (keyword != null) {
            return customerRepository.searchCustomer(keyword);
        }
        return customerRepository.findAll();
    }


    public void updateResetPasswordToken(String token,String email) throws UserNotFoundException {
        Customer customer= customerRepository.findByEmail(email);
        if(customer != null ){
            customer.setResetPasswordToken(token);
            customerRepository.save(customer);
        }
        else {
            throw  new UserNotFoundException("could not find any Customer with email" + email);
        }

    }

    //is used to check if a Customer belongs to a given resetPasswordToken or not
    public Customer get(String resetPasswordToken){
        return customerRepository.findByResetPasswordToken(resetPasswordToken);
    }

    public Customer getByEmail(String email){
        return customerRepository.findByEmail(email);
    }

    public void updatePassword(Customer customer,String newPassword){
        BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        String encodedPassword= passwordEncoder.encode(newPassword);

        customer.setPassword(encodedPassword);
        customer.setResetPasswordToken(null); //because the Customer has set the password successfully hence removing the token
        customerRepository.save(customer);

    }

    public  Customer getCurrentlyLoggedInCustomer(Authentication authentication){
        if (authentication==null){
            return null;
        }
        Customer customer=null;
        Object principal=authentication.getPrincipal();
        if (principal instanceof CustomCustomerDetails){
            customer=((CustomCustomerDetails)principal).getCustomer();
        }
        else {
            String email=((CustomCustomerDetails)principal).getEmail();
            customer=getByEmail(email);
        }
        return customer;
    }

    public List<Customer> listAll(){
        return customerRepository.findAll(Sort.by("email").ascending());
    }
}
