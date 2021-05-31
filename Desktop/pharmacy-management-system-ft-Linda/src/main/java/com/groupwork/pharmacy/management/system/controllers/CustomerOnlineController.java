package com.groupwork.pharmacy.management.system.controllers;

import com.groupwork.pharmacy.management.system.model.Cart;
import com.groupwork.pharmacy.management.system.model.Customer;
import com.groupwork.pharmacy.management.system.service.CustomCartDetailsService;
import com.groupwork.pharmacy.management.system.service.CustomCustomerDetailsService;
import org.springframework.beans.CachedIntrospectionResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CustomerOnlineController {
    @Autowired
    CustomCustomerDetailsService customerDetailsService;

    @Autowired
    CustomCartDetailsService cartDetailsService;

    @GetMapping("/CustomerOnlineHome")
    public String viewOnlineProductsPage(){
        return "Store/Home";
    }

    @GetMapping("/ProductsOnlineHome")
    public String viewProductsPage(){
        return "Store/Products";
    }

    @GetMapping("/customer/cart")
    public String viewCartPage(Model model, @AuthenticationPrincipal Authentication authentication){
        Customer customer=customerDetailsService.getCurrentlyLoggedInCustomer(authentication);
        List<Cart> cartItems=cartDetailsService.listCartItems(customer);

        model.addAttribute("cartItems",cartItems);

        return "Store/Cart";
    }


    @GetMapping("/onlineLogin")
    public String viewOnlineLoginPage(){
        return "Store/OnlineLogin";
    }
    @GetMapping("/onlineRegistration")
    public String viewOnlineRegisterPage(){
        return "Store/OnlineRegistration";
    }
    @GetMapping("/details")
    public String viewOnlineDetailsPage(){
        return "Store/Details";
    }

}
