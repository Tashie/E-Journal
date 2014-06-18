package com.system.edu.web.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class SecurityService {

    public String getCurrentUserLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public String getRole() {
        String role = "";
        Iterator iterator = SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator();
        if (iterator.hasNext()) {
            GrantedAuthority ga = (GrantedAuthority)iterator.next();
            role = ga.getAuthority();
        }
        return role;
    }
}