package com.example.Ecommerce.EcommerceApp;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    private final ProductRepository productRepository;
    private java.lang.Long Long;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    public Product addNewProduct(Product  product) {
        Optional<Product> productOptional = productRepository.findById(product.getId());
        if (productOptional.isPresent()) {
            throw new IllegalStateException("Not allowed");
        }
        productRepository.save(product);
        return product;
    }

    public void deleteProduct(Long productId) {

        boolean exists = productRepository.existsById(productId);
        if (!exists) {
            throw new IllegalStateException(
                    "product with id " + productId + " does not exist");

        }
        productRepository.deleteById(productId);
    }


    @Transactional
    public Product updateProduct(Long productId)
//                                 String name,
//                                 String description, Double price, Integer quantity)
    {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalStateException(
                "product with id " + productId + " does not exist "));

//        if (name != null &&
//                name.length()
//                        > 0 && !Objects.equals(product.getName(), name)) {
//            product.setName(name);
//        }
//        if (description != null && description.length() > 0 &&
//                !Objects.equals(product.getDescription(), description)) {
//            Optional<Product> productOptional = productRepository.findById(Long);
//            if (productOptional.isPresent()) {
//                throw new IllegalStateException("Not Valid");
//            }
//            product.setDescription(description);
//        }
//        if (price != null && price > 0) {
//            product.setPrice(price);
//        }
//        if (quantity != null && quantity > 0) {
//            product.setQuantity(quantity);
//        }


        return product;
    }


    public Product sellProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        product.setQuantity
                (product.getQuantity() - 1);
        productRepository.save(product);
        return product;
    }
}
