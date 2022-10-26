package com.example.demo.Product;

import com.example.demo.Category.CategoryService;
import com.example.demo.Category.CategoryServiceImplement;
import com.example.demo.Receipt.Receipt;
import com.example.demo.CustomResponse;
import com.example.demo.DemoApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ServerErrorException;

import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductServiceImplement implements  ProductService{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DemoApplication.CustomPasswordEncoder customPasswordEncoder;

    @Autowired
    private CategoryService categoryService;
    public ResponseEntity<Object> getAllProduct()
    {
        ResponseEntity <Object> responseEntity = new ResponseEntity<>("request failed", HttpStatus.BAD_REQUEST);
        String sql = "select * from product";
        try
        {
            List<Product> products = this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class));
            if(products.size() != 0)
                responseEntity = new ResponseEntity<>(products, HttpStatus.OK);
            else responseEntity = new ResponseEntity<>("there are no product", HttpStatus.OK);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return responseEntity;
    }

    public ResponseEntity<Object> getProductById(Long pid)
    {
        ResponseEntity<Object> responseEntity = null;
        try
        {
            String sql = "select * from product where pid = ?";
            List<Product> product = this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class), pid);
            if(product.size() != 0)
            {
                responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
            }
            else responseEntity = new ResponseEntity<>("product id not found", HttpStatus.NOT_FOUND);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            responseEntity = new ResponseEntity<>("request failed", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    public ResponseEntity<Object> getAllActiveProduct()
    {
        ResponseEntity<Object> responseEntity = new ResponseEntity<>("request failed", HttpStatus.BAD_REQUEST);

        String sql = "select * from product where active = 1";
        try
        {
            List<Product> products = this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class));
            if(products.size() != 0)
            {
                responseEntity = new ResponseEntity<>(products, HttpStatus.OK);
            }
            else responseEntity = new ResponseEntity<>("there are no category in database", HttpStatus.NO_CONTENT);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return responseEntity;
    }

    public ResponseEntity<Object> getAllProductByCatOrderByDate(Long catid)
    {
        ResponseEntity<Object> responseEntity = null;
        ResponseEntity<Object> request = this.categoryService.getCategoryById(catid);

        if(request.getStatusCode().isError())
            responseEntity = new ResponseEntity<>("category not found", HttpStatus.NOT_FOUND);
        else
        {
            String sql = "select * from product where catid = ? order by adddate desc";
            try
            {
                List<Product> products = this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class), catid);
                if(products.size() != 0)
                {
                    responseEntity = new ResponseEntity<>(products, HttpStatus.OK);
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                responseEntity = new ResponseEntity<>("request failed", HttpStatus.BAD_REQUEST);
            }
        }
        return responseEntity;
    }

    public ResponseEntity<Object> updateProductById(Product product, Long pid)
    {
        ResponseEntity<Object> responseEntity = null;
        HttpStatus status = getProductById(pid).getStatusCode();
        if(pid != product.getPid())
        {
            responseEntity = new ResponseEntity<>("product id cant be updated", HttpStatus.CONFLICT);
        }
        else if(status.isError())
        {
        	responseEntity = new ResponseEntity<>("product id not found", HttpStatus.NOT_FOUND);
        }
        else
        {
            try
            {
                int res;
                String sql = "update product set pname = ?, price = ?, description = ?, spec = ?, catid = ?, image = ?, rate = ?, discount = ?, adddate = ?, active = ? where pid = ?";
                res = this.jdbcTemplate.update(sql, product.toArrayForUpdate());
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

    public ResponseEntity<Object> addProduct(Product product)
    {
        ResponseEntity<Object> responseEntity = null;
        if(getProductById(product.getPid()).getStatusCode() == HttpStatus.OK)
        {
            responseEntity = new ResponseEntity<>("duplicated product id", HttpStatus.CONFLICT);
        }
        if(this.categoryService.getCategoryById(product.getCatid()).getStatusCode().isError())
        {
            responseEntity = new ResponseEntity<>("category not found. you have to add a new category with this id beforehand", HttpStatus.NOT_FOUND);
        }
        else
        {
            try
            {
                String sql = "insert into product values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                int res =  this.jdbcTemplate.update(sql, product.toArray());
                if(res == 1)
                    responseEntity = new ResponseEntity<>("done", HttpStatus.OK);
                else responseEntity = new ResponseEntity<>("insert failed", HttpStatus.BAD_REQUEST);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        return responseEntity;
    }

    public ResponseEntity<Object> setActiveToFalse(Long pid)
    {
        ResponseEntity<Object> responseEntity = new ResponseEntity<>("request failed", HttpStatus.BAD_REQUEST);

        ResponseEntity<Object> productById = getProductById(pid);
        if(productById == null)
        {
            responseEntity = new ResponseEntity<>("product id not found", HttpStatus.NOT_FOUND);
        }
        else
        {
            try
            {
                String sql = "update product set active = 0 where pid = ?";
                if(this.jdbcTemplate.update(sql, pid) == 1)
                    responseEntity = new ResponseEntity<>("completed", HttpStatus.OK);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }

        return responseEntity;
    }

	@Override
	public CustomResponse getProduct() {
		CustomResponse response = new CustomResponse();
		try
		{
			String sql = "select * from product";
			List<Receipt> receipts = this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Receipt.class));
			if(receipts.size() == 0)
			{
				response.setCode(204);
				response.setStatus("Empty");
				response.setMessage("empty");
				response.setData(null);
			}
			else 
			{
				response = response.requestCompleted(receipts);
			}
		}
		catch(Exception ex)
		{
			if(ex instanceof ServerErrorException)
			{
				response = response.serverErrorException();
			}
			else if(ex instanceof NotFoundException)
			{
				response = response.notFoundException();
			}
		}
		
		return response;
	}
}
