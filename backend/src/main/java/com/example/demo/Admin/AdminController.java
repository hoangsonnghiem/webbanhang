package com.example.demo.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Category.Category;
import com.example.demo.Customer.CustomerService;
import com.example.demo.Customer.CustomerServiceImplement;


@RestController
@RequestMapping("/Admin")
public class AdminController {
	private final AdminService adminService;
    @Autowired
    public AdminController(AdminService adminService, CustomerService customerService) {
        this.adminService = adminService;
    }

    @GetMapping("/")
    public Object getAllAdmin()
    {
        return this.adminService.getAllAdmin().getBody();
    }

    @GetMapping("/{email}")
    public Object getAdminByEmail(@PathVariable(name = "email") String email)
    {
        return this.adminService.getAllAdminByEmail(email).getBody();
    }
    @PostMapping("/add")
    public Object addAdmin(@RequestBody Admin admin)
    {
        return this.adminService.addNewAdmin(admin).getBody();
    }

    @PutMapping("/update/{email}")
    public Object updateAdmin(@RequestBody Admin admin, @PathVariable(name = "email") String email)
    {
        return this.adminService.updateAdmin(admin, email);
    }
}
