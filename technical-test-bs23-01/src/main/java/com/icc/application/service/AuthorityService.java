package com.icc.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icc.application.model.Role;
import com.icc.application.repositories.AuthorityRepository;

@Service
public class AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    public Role create(Role role) {

        if (exists(role.getAuthority())) {
            return null;
        } else {
            authorityRepository.save(role);
            return role;
        }
    }

    public Role findByRoleName(String roleName) {
        return authorityRepository.findByRoleName(roleName);
    }

    public boolean exists(String role) {
        if (findByRoleName(role) != null) {
            return true;
        } else {
            return false;
        }
    }

    public List<Role> listAllAuthorities() {
        return authorityRepository.findAll();
    }
}