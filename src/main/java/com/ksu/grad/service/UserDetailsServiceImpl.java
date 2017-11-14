package com.ksu.grad.service;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ksu.grad.dao.ApplicationUserRepository;
import com.ksu.grad.entity.Login;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	 private ApplicationUserRepository applicationUserRepository;

	    public UserDetailsServiceImpl(ApplicationUserRepository applicationUserRepository) {
	        this.applicationUserRepository = applicationUserRepository;
	    }

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        Login applicationUser = applicationUserRepository.findByUserName(username);
	        if (applicationUser == null) {
	            throw new UsernameNotFoundException(username);
	        }
	        
	        
	        return new User(applicationUser.getUserName(), applicationUser.getPassword(), Collections.emptyList());
	    }
}