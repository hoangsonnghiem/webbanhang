package com.example.demo.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.CustomResponse;

import java.util.List;

@Service
public class CategoryServiceImplement implements CategoryService{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ResponseEntity<Object> getAllCategory()
    {
        ResponseEntity<Object> responseEntity = new ResponseEntity<>("request failed", HttpStatus.BAD_REQUEST);;
        try
        {
            String sql = "select * from category";
            List<Category> categories = this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Category.class));
            if(categories.size() != 0)
                responseEntity = new ResponseEntity<>(categories, HttpStatus.OK);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<Object> getCategoryById(Long catid)
    {
        ResponseEntity<Object> responseEntity = null;
        try
        {
            String sql = "select * from category where catid = ?";
            List<Category> category = this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Category.class), catid);
            if(category.size() != 0)
                responseEntity = new ResponseEntity<>(category, HttpStatus.OK);
            else responseEntity = new ResponseEntity<>("category not found", HttpStatus.NOT_FOUND);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            responseEntity = new ResponseEntity<>("request failed", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<Object> addNewCategory(Category category) {
        ResponseEntity<Object> responseEntity = null;
        HttpStatus status = getCategoryById(category.getCatid()).getStatusCode();
        if(status == HttpStatus.OK)
            responseEntity = new ResponseEntity<>("category id already existed", HttpStatus.CONFLICT);
        if(status == HttpStatus.NOT_FOUND)
        {
            try
            {
                String sql = "insert into category values (?, ?)";
                int res = this.jdbcTemplate.update(sql, new Object[]{category.getCatid(), category.getCatname()});
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
    public ResponseEntity<Object> updateCategory(Category category, Long catid) {
        ResponseEntity<Object> responseEntity = null;
        if(category.getCatid() == catid)
        {
            responseEntity = new ResponseEntity<>("you cant update category id", HttpStatus.CONFLICT);
        }
        else if(getCategoryById(catid).getStatusCode().isError())
        {
            responseEntity = new ResponseEntity<>("category not found", HttpStatus.NOT_FOUND);
        }
        else
        {
            try
            {
                String sql = "update category set catname = ? where catid = ?";
                int res = this.jdbcTemplate.update(sql, new Object[]{category.getCatname(), catid});
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
	public CustomResponse getCategory() {
		// TODO Auto-generated method stub
		return null;
	}
}
