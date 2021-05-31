package com.groupwork.pharmacy.management.system.service;

import com.groupwork.pharmacy.management.system.model.Admin;
import com.groupwork.pharmacy.management.system.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomAdminDetails implements UserDetails {

    private Admin admin;

    public CustomAdminDetails(Admin admin){
        this.admin=admin;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    @Override
    public String getUsername() {
        return admin.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getAdminFullName(){ //used to display full name of a logged in admin
        return admin.getFirstName() + "  " +admin.getLastName();
    }
    public  String getAdminEmail(){ // used to display email of a logged in admin
        return admin.getEmail() + "";

    }
}
