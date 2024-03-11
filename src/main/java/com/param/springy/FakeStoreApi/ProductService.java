package com.param.springy.FakeStoreApi;

import com.param.springy.FakeStoreApi.dtos.ListProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
    private String url = "https://fakestoreapi.com/products";

    private final RestTemplate restTemplate;

    public ProductService() {
        this.restTemplate = new RestTemplate();
    }

    public List<Product> getProducts(String limit, String sort) {
        String callUrl = url;
        callUrl+="?sort="+sort;
        if(!limit.equals("-1")) {
            callUrl += "&limit="+limit;
        }
        Product[] products = restTemplate.getForObject(callUrl, Product[].class);
        if(products != null)
            return Arrays.stream(products).toList();
        return null;
    }

    public Product getProductById(Long id){
        return restTemplate.getForObject(url+"/"+id, Product.class);
    }

    public Product deleteProduct(Long id){
        Product p = getProductById(id);
        if(p != null)
            restTemplate.delete(url+"/"+id);
        return p;
    }

    public Product updateProduct(Long id, Product newProduct){
        Product p = getProductById(id);
        if(p != null) {
            restTemplate.put(url+"/"+id, newProduct);
        }
        return newProduct;
    }

    public Product createProduct(Product newProduct){
        return restTemplate.postForObject(url, newProduct, Product.class);
    }

    public List<String> getCategories(){
        String[] categories= restTemplate.getForObject(url+"/categories",  String[].class);
        assert categories != null;
        return Arrays.stream(categories).toList();
    }

    public List<Product> getProductByCategory(String category){
        Product[] p= restTemplate.getForObject(url+"/category/"+category, Product[].class);
        return Arrays.stream(p).toList();
    }
}
