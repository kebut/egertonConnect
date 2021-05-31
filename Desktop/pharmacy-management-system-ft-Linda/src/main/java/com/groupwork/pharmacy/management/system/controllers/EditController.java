package com.groupwork.pharmacy.management.system.controllers;

import com.groupwork.pharmacy.management.system.model.Admin;
import com.groupwork.pharmacy.management.system.repositories.AdminRepository;
import com.groupwork.pharmacy.management.system.service.CustomAdminDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class EditController {

    @Autowired
    private AdminRepository repository;

    @Autowired
    private CustomAdminDetailsService adminDetailsService;

    @GetMapping("/edit_admin/{id}")
    public  String editAdminDetails(@PathVariable("id") long id, @ModelAttribute Admin admin){
        return "admin_edit_form";
    }
    @PostMapping("/update_admin/{id}")
    public String editAdmin(@PathVariable("id") long id,HttpServletRequest request,Admin admin){
        if(repository.findByEmail(admin.getEmail()) == null) {
            String password = request.getParameter("password");
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(password);
            admin.setId(id);
            admin.setPassword(encodedPassword);

            repository.save(admin);


            return "redirect:/admin/list_admins";
        }
        else
            return "errorMessage";

    }
}
