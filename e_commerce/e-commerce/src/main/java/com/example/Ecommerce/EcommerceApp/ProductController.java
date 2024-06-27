package com.example.Ecommerce.EcommerceApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//@SuppressWarnings("InfiniteRecursion")
@RestController
@RequestMapping(path = "api/v1/e-commerce")
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;
    private com.example.Ecommerce.EcommerceApp.Product Product;

    @Autowired
    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getProduct() {
        return productService.getProduct();
    }
    @PostMapping
    public Product registerNewProduct(@RequestBody Product product) {
        productService.addNewProduct(product);
        return product;
    }
    @DeleteMapping(path = "{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId){
        productService.deleteProduct(productId);
//        return deleteProduct(productId);
        return "Product deleted successfully";
    }
    @PutMapping(path = "{productId}")
    public Product updateProduct(
            @RequestBody Product product,
            @PathVariable("productId") Long productId)
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) String description,
//            @RequestParam(required = false) Double price,
//            @RequestParam(required = false)Integer quantity)
    {
        productService.updateProduct(productId);

        return product;
    }



    @PatchMapping(path = "/{productId}")
    public Object sellProduct(
            @RequestBody Product product,
            @PathVariable("productId") Long productId) {
        productService.sellProduct(productId);
        return productService.getProduct().get(0);

    }




}

