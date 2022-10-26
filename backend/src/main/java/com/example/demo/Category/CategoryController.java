package com.example.demo.Category;

import com.example.demo.Product.ProductServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final ProductServiceImplement productService;
    private final CategoryServiceImplement categoryService;


    @Autowired
    public CategoryController(ProductServiceImplement productService, CategoryServiceImplement categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public Object getAllCategory()
    {
        return this.categoryService.getAllCategory().getBody();
    }

    @GetMapping("/{catid}")
    public Object getProductByCatOrderByDate(@PathVariable(name = "catid") Long catId)
    {
        return this.productService.getAllProductByCatOrderByDate(catId).getBody();
    }

    @PostMapping("/add")
    public Object addCategory(@RequestBody Category category)
    {
        return this.categoryService.addNewCategory(category).getBody();
    }

    @PutMapping("/update/{catid}")
    public Object updateCategory(@RequestBody Category category, @PathVariable(name = "catid") Long catid)
    {
        return this.categoryService.updateCategory(category, catid);
    }
}
