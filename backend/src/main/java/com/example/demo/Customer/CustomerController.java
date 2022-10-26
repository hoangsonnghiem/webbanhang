package com.example.demo.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Admin.AdminService;
import com.example.demo.Admin.AdminServiceImplement;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    
    private final CustomerService customerService;
    private final AdminService adminService;
    
    @Autowired
    public CustomerController(CustomerService customerService, AdminService adminService) {
        this.customerService = customerService;
		this.adminService = adminService;
    }

    @GetMapping("/")
    public Object getAllCustomer()
    {
        return this.customerService.getAllCustomer().getBody();
    }

    @GetMapping("/{email}")
    public Object getCustomerByEmail(@PathVariable(name = "email") String email)
    {
        return this.customerService.getCustomerByEmail(email);
    }

    @PostMapping("/add")
    public Object addCustomer(@RequestBody Customer customer)
    {
        return this.customerService.addNewCustomer(customer).getBody();
    }

    @PutMapping("/update/{email}")
    public Object updateByEmail(@RequestBody Customer customer, @PathVariable(name = "email") String email)
    {
       return this.customerService.updateCustomerByEmail(customer, email);
    }

//    @PutMapping("/changepassword/{email}")
//    public Object changePassword(@RequestBody Customer customer)
//    {
//        return this.customerService.changePassword(customer);
//    }
}
