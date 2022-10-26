package com.example.demo.Customer;

import com.example.demo.CustomResponse;
import com.example.demo.DemoApplication;
import com.example.demo.Category.Category;
import com.example.demo.Product.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Repository
public class CustomerServiceImplement implements UserDetailsService, CustomerService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private DemoApplication.CustomPasswordEncoder customPasswordEncoder;

    public ResponseEntity<Object> getAllCustomer()
    {
        ResponseEntity <Object> responseEntity = new ResponseEntity<>("request failed", HttpStatus.BAD_REQUEST);
        String sql = "select * from customer";
        try
        {
            List<Customer> customer = this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Customer.class));
            if(customer.size() != 0)
                responseEntity = new ResponseEntity<>(customer, HttpStatus.OK);
            else responseEntity = new ResponseEntity<>("there are no customer", HttpStatus.OK);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return responseEntity;
    }

    public ResponseEntity<Object> getCustomerByEmail(String email)
    {
        ResponseEntity<Object> responseEntity = null;
        try
        {
            String sql = "select * from customer where email = ?";
            List<Customer> customer = this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Customer.class), email);
            if(customer.size() != 0)
            {
                responseEntity = new ResponseEntity<>(customer, HttpStatus.OK);
            }
            else responseEntity = new ResponseEntity<>("customer id not found", HttpStatus.NOT_FOUND);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            responseEntity = new ResponseEntity<>("request failed", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    public ResponseEntity<Object> addNewCustomer(Customer customer) {
        ResponseEntity<Object> responseEntity = null;
        HttpStatus status = getCustomerByEmail(customer.getEmail()).getStatusCode();
        if(status == HttpStatus.OK)
            responseEntity = new ResponseEntity<>("customer id already existed", HttpStatus.CONFLICT);
        if(status == HttpStatus.NOT_FOUND)
        {
            try
            {
                String sql = "insert into customer values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                int res = this.jdbcTemplate.update(sql, customer.toArray());
                if(res == 1)
                {
                    responseEntity = new ResponseEntity<>("completed", HttpStatus.OK);
                }
                else responseEntity = new ResponseEntity<>("request failed", HttpStatus.BAD_REQUEST);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                responseEntity = new ResponseEntity<>("request failed", HttpStatus.BAD_REQUEST);
            }
        }

        return responseEntity;
    }
    public ResponseEntity<Object> updateCustomerByEmail(Customer customer, String email)
    {
        ResponseEntity<Object> responseEntity = null;
        HttpStatus status = getCustomerByEmail(email).getStatusCode();
        if(email != customer.getEmail())
        {
            responseEntity = new ResponseEntity<>("customer email cant be updated", HttpStatus.CONFLICT);
        }
        else if(status.isError())
        {
        	responseEntity = new ResponseEntity<>("customer id not found", HttpStatus.NOT_FOUND);
        }
        else
        {
            try
            {
                int res;
                String sql = "update customer set name = ?, dob = ?, gender = ?, phone = ?, address = ?, district = ?, city = ?, pass = ?, active = ?, where email = ?";
                res = this.jdbcTemplate.update(sql, customer.toArrayForUpdate());
                if (res == 1) {
                    responseEntity = new ResponseEntity<>("request completed", HttpStatus.OK);
                }
                else responseEntity = new ResponseEntity<>("request failed", HttpStatus.BAD_REQUEST);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        return responseEntity;
    }
    public ResponseEntity<Object> login(String email)
    {
        ResponseEntity<Object> responseEntity = null;
        try
        {
            String sql = "select * from customer where email = ?";
            List<Customer> customer = this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Customer.class), email);
            if(customer.size() != 0)
            {
                responseEntity = new ResponseEntity<>(customer, HttpStatus.OK);
            }
            else responseEntity = new ResponseEntity<>("customer id not found", HttpStatus.NOT_FOUND);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            responseEntity = new ResponseEntity<>("request failed", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object changePassword(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomResponse getCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

}
