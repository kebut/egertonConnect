package com.groupwork.pharmacy.management.system.service;

import com.groupwork.pharmacy.management.system.model.Clerk;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomClerkDetails implements UserDetails {

    private Clerk clerk;

    public CustomClerkDetails(Clerk clerk){
        this.clerk=clerk;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

      //  SimpleGrantedAuthority authority=new SimpleGrantedAuthority();
        return null;
    }

    @Override
    public String getPassword() {
        return clerk.getPassword();
    }

    @Override
    public String getUsername() {
        return clerk.getEmail();
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

    public String getClerkFullName(){
        return clerk.getFirstName() + "  " + clerk.getLastName();
    }
    //public String getEmail(){return user.getEmail();}
}
