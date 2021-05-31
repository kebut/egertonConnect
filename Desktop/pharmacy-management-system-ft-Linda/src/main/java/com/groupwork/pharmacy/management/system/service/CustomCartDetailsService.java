package com.groupwork.pharmacy.management.system.service;

import com.groupwork.pharmacy.management.system.model.Cart;
import com.groupwork.pharmacy.management.system.model.Customer;
import com.groupwork.pharmacy.management.system.repositories.CartRepository;
import com.groupwork.pharmacy.management.system.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("customCartDetailsService")
public class CustomCartDetailsService implements UserDetailsService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    CustomerRepository customerRepository;

/*
    @Override
    public  UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer= customerRepository.findByEmail(email);
        //Cart cart= cartRepository.findByCustomer(customer);
        if(customer!=null && customer.isEnabled()){
            return new CustomCustomerDetails(customer);
        }
        else {
            throw new UsernameNotFoundException("Customer Not Found");
        }

    }
    */
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
            //customer=getByEmail(email);
            customer=customerRepository.findByEmail(email);
        }
        return customer;
    }


    public List<Cart> listCartItems(Customer customer) {
        return cartRepository.findByCustomer(customer);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
