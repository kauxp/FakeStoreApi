package com.param.springy.FakeStoreApi;

import com.param.springy.FakeStoreApi.dtos.ListProductDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService fakeservice;

    public ProductController(ProductService fakeservice){
        this.fakeservice = fakeservice;
    }

    @GetMapping("")
    public List<Product> getProducts(@RequestParam Map<String, String> params){
        String limit = "-1";
        String sort= "asc";
        if (params.containsKey("sort")){
            sort= params.get("sort");
        }
        if (params.containsKey("limit")) {
            limit = params.get("limit");
        }
        return fakeservice.getProducts(limit, sort);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return fakeservice.getProductById(id);
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable Long id){
        return fakeservice.deleteProduct(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product newProduct){
        return fakeservice.updateProduct(id, newProduct);
    }

    @PostMapping("")
    public Product createProduct(@RequestBody Product newProduct){
        return fakeservice.createProduct(newProduct);
    }

    @GetMapping("/categories")
    public List<String> getCategories(){
        return fakeservice.getCategories();

    }
    @GetMapping("/category/{category}")
    public List<Product> getProductByCategory(@PathVariable String category){
        return fakeservice.getProductByCategory(category);
    }




}

