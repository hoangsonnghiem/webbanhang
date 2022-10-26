package com.example.demo.Security;

import com.example.demo.Admin.Admin;
import com.example.demo.Admin.AdminService;
import com.example.demo.Customer.Customer;
import com.example.demo.Customer.CustomerServiceImplement;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomerDetailService implements UserDetailsService {

    @Autowired
    private AdminService adminService;
    private CustomerServiceImplement customerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Admin admin = (Admin) ArrayList.class.cast(this.adminService.getAdminByEmail(username)).get(0);
        final Customer customer = (Customer) ArrayList.class.cast(this.customerService.getCustomerByEmail(username)).get(0);
        if(admin == null || customer == null) {
            throw new UsernameNotFoundException(username);
        }
        UserDetails userDetails = User.withUsername(customer.getEmail())
                .password(customer.getPass())
                .roles(String.valueOf(admin.getRole())).build();
        return userDetails;
    }
}
