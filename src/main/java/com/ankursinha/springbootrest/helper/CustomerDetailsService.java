package com.ankursinha.springbootrest.helper;

import com.ankursinha.springbootrest.entity.Customer;
import com.ankursinha.springbootrest.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer user = repo.findByEmail(username); //////////////
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomerPrincipal(user);
    }
}
