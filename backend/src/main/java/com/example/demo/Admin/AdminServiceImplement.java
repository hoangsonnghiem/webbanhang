package com.example.demo.Admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.CustomResponse;

@Service
public class AdminServiceImplement implements AdminService{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ResponseEntity<Object> getAllAdmin()
    {
        ResponseEntity<Object> responseEntity = new ResponseEntity<>("request failed", HttpStatus.BAD_REQUEST);
        try
        {
            String sql = "select * from admin";
            List<Admin> admin = this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Admin.class));
            if(admin.size() != 0)
                responseEntity = new ResponseEntity<>(admin, HttpStatus.OK);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<Object> getAdminByEmail(String email)
    {
        ResponseEntity<Object> responseEntity = null;
        try
        {
            String sql = "select * from admin where email = ?";
            List<Admin> admin = this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Admin.class), email);
            if(admin.size() != 0)
                responseEntity = new ResponseEntity<>(admin, HttpStatus.OK);
            else responseEntity = new ResponseEntity<>("admin not found", HttpStatus.NOT_FOUND);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            responseEntity = new ResponseEntity<>("request failed", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<Object> addNewAdmin(Admin admin) {
        ResponseEntity<Object> responseEntity = null;
        HttpStatus status = getAdminByEmail(admin.getEmail()).getStatusCode();
        if(status == HttpStatus.OK)
            responseEntity = new ResponseEntity<>("admin email already existed", HttpStatus.CONFLICT);
        if(status == HttpStatus.NOT_FOUND)
        {
            try
            {
                String sql = "insert into admin values (?, ?)";
                int res = this.jdbcTemplate.update(sql, new Object[]{admin.getEmail(), admin.getRole()});
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

    @Override
    public ResponseEntity<Object> updateAdmin(Admin admin, String email) {
        ResponseEntity<Object> responseEntity = null;
        if(admin.getEmail() == email)
        {
            responseEntity = new ResponseEntity<>("you cant update admin email", HttpStatus.CONFLICT);
        }
        else if(getAdminByEmail(email).getStatusCode().isError())
        {
           responseEntity = new ResponseEntity<>("admin not found", HttpStatus.NOT_FOUND);
        }
        else
        {
            try
            {
                String sql = "update admin set role = ? where email = ?";
                int res = this.jdbcTemplate.update(sql, new Object[]{admin.getRole(), email});
                if(res == 1)
                {
                    responseEntity = new ResponseEntity<>("update completed", HttpStatus.OK);
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

	@Override
	public ResponseEntity<Object> getAllAdminByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomResponse getAdmin() {
		// TODO Auto-generated method stub
		return null;
	}

}