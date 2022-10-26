package com.example.demo.Store;

import com.example.demo.CustomResponse;
import com.example.demo.Admin.Admin;
import com.example.demo.Admin.AdminService;
import com.example.demo.Customer.CustomerService;
import com.example.demo.Receipt.Receipt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ServerErrorException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StoreServiceImplement implements StoreService{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AdminService adminService;

    @Override
    public ResponseEntity<Object> getAllStoreList()
    {
        ResponseEntity<Object> responseEntity = null;
        List<StoreView> storeViews = new ArrayList<>();
        try
        {
            String sql = "select * from storeview order by storeid asc";
            storeViews = this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(StoreView.class));
            if(storeViews.size() != 0)
            {
                responseEntity = new ResponseEntity<>(storeViews, HttpStatus.OK);
            }
            else responseEntity = new ResponseEntity<>("empty", HttpStatus.NOT_FOUND);
        }
        catch (Exception ex)
        {
            responseEntity = new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    public ResponseEntity<Object> getStoreById(Long storeid)
    {
        ResponseEntity<Object> responseEntity = null;
        List<StoreView> storeView = new ArrayList<>();
        try
        {
            String sql = "select * from storeview where storeid = ?";
            storeView = this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(StoreView.class), storeid);
            if(storeView.size() == 0)
            {
                responseEntity = new ResponseEntity<>("store not found", HttpStatus.NOT_FOUND);
            }
            else responseEntity = new ResponseEntity<>(storeView, HttpStatus.OK);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            responseEntity = new ResponseEntity<>("request failed", HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

    @Override
    public ResponseEntity<?> getActiveStore() {
        ResponseEntity<?> responseEntity = null;
        try
        {
            String sql = "select * from storeview where active = 1 order by storeid asc";
            List<StoreView> storeViews = this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(StoreView.class));
            if(storeViews.size() == 0)
            {
                responseEntity = new ResponseEntity<>("all store are inactive", HttpStatus.NO_CONTENT);
            }
            else responseEntity = new ResponseEntity<>(storeViews, HttpStatus.OK);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            responseEntity = new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @Override
    public ResponseEntity<?> addStore(Store store)
    {
        ResponseEntity<?> responseEntity = null;
        HttpStatus storeStatus = getStoreById(store.getStoreid()).getStatusCode();
        @SuppressWarnings("unchecked")
		List<Admin> adminStatus = new ArrayList<>();
        adminStatus.add(Admin.class.cast(this.adminService.getAdminByEmail(store.getEmail()).getBody()));
        if(storeStatus == HttpStatus.OK)
        {
            responseEntity = new ResponseEntity<>("store id existed", HttpStatus.CONFLICT);
        }
        else if(adminStatus.size() == 0)
        {
            responseEntity = new ResponseEntity<>("user not found or does not have admin authority", HttpStatus.NOT_FOUND);
        }
        else
        {
            try
            {
                String sql = "insert into store values (?, ?, ?, ?, '1')";
                int res = this.jdbcTemplate.update(sql, store.toArray());
                if (res == 1)
                    responseEntity = new ResponseEntity<>("completed", HttpStatus.CREATED);
                else responseEntity = new ResponseEntity<>("insert failed", HttpStatus.BAD_REQUEST);
            }
            catch (Exception ex)
            {
                responseEntity = new ResponseEntity<>("request failed", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return  responseEntity;
    }

    @Override
    public ResponseEntity<?> updateStore(Store store, Long storeid) {
        ResponseEntity<?> responseEntity = null;
        if(storeid != store.getStoreid())
        {
            responseEntity = new ResponseEntity<>("store id cant be updated", HttpStatus.CONFLICT);
        }
        else if(getStoreById(storeid).getStatusCode() != HttpStatus.OK)
        {
            responseEntity = new ResponseEntity<>("store id not exists", HttpStatus.NOT_FOUND);
        }
        else if(this.adminService.getAdminByEmail(store.getEmail()).getBody() == null)
        {
            responseEntity = new ResponseEntity<>("user not found or does not have admin authority", HttpStatus.NOT_FOUND);
        }
        else
        {
            try
            {
                String sql = "update store set city = ?, district = ?, email = ? where storeid = ?";
                int res = this.jdbcTemplate.update(sql, store.toArrayForUpdate());
                if(res == 1)
                {
                    responseEntity = new ResponseEntity<>("completed", HttpStatus.OK);
                }
                else responseEntity = new ResponseEntity<>("request failed", HttpStatus.BAD_REQUEST);
            }
            catch (Exception ex)
            {
                responseEntity = new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
                ex.printStackTrace();
            }
        }

        return responseEntity;
    }

    @Override
    public ResponseEntity<?> deleteStore(Long storeid) {
        ResponseEntity<?> responseEntity = null;
        if(getStoreById(storeid).getStatusCodeValue() != 200)
        {
            responseEntity = new ResponseEntity<>("store not found", HttpStatus.NOT_FOUND);
        }
        else
        {
            try
            {
                String sql = "update store set active = 0 where storeid = ?";
                int res = this.jdbcTemplate.update(sql, storeid);
                if (res == 1) {
                    responseEntity = new ResponseEntity<>("completed", HttpStatus.OK);
                } else responseEntity = new ResponseEntity<>("request failed", HttpStatus.BAD_REQUEST);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                responseEntity = new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return responseEntity;
    }


}
