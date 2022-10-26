package com.example.demo.Product;

import com.example.demo.Category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/product")
@CrossOrigin(origins = "localhost://8081")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public Object getAllProduct()
    {
        return this.productService.getAllProduct().getBody();
    }

    @GetMapping("/{pid}")
    public Object getProductById(@PathVariable(name = "pid") String pid)
    {
    	if(pid.matches("[a-zA-Z0-9]"))
    		return this.productService.getProductById(Long.parseLong(pid)).getBody();
    	else return "format exception";
    }

    @GetMapping("/active")
    public Object getAllActiveProduct()
    {
        return this.productService.getAllActiveProduct().getBody();
    }

    @PostMapping("/change/add")
    public Object addProduct(@RequestBody Product product)
    {
        return this.productService.addProduct(product).getBody();
    }

    @PutMapping("/change/update/{pid}")
    public Object updateProductById(@RequestBody Product product, @PathVariable(name = "pid") Long pid)
    {
        return this.productService.updateProductById(product, pid).getBody();
    }

    @PutMapping("/change/deactivate/{pid}")
    public Object setActiveToFalse(@PathVariable(name = "pid") Long pid)
    {
        return this.productService.setActiveToFalse(pid);
    }
}
