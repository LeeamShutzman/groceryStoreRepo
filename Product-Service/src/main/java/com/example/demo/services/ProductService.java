package com.example.demo.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    /***************************************************************/
    //Constructors, Getters, and Setters
    public ProductService(ProductRepository productRepository) {
        super();
        this.productRepository = productRepository;
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /***************************************************************/
    //Repository Method Calls

    //Add a Product
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    //View all Products
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    //View Product by ID
    public Optional<Product> findByProductID(long productID) {
        return productRepository.findById(productID);
    }

    //View Products in the same Category
    public List<Product> findProductsByCategoryID(long categoryID) {
        return productRepository.findByCategoryID(categoryID);
    }

    //View Products from the same Supplier
    public List<Product> findProductsBySupplierID(long supplierID) {
        return productRepository.findBySupplierID(supplierID);
    }

    //View Products from the same Supplier that are in the same Category
    public List<Product> findProductsBySupplierIDAndCategoryID(long supplierID, long categoryID) {
        return productRepository.findBySupplierIDAndCategoryID(supplierID, categoryID);
    }

    //Delete a Product
    public void deleteProduct(long productID) {
        productRepository.deleteById(productID);
    }

    //Update a Product
    public Product updateProduct(long productID, Product product) {
        try {
            Product temp = productRepository.findById(productID).get();
            if (Objects.nonNull(product.getProductName()) && !"".equalsIgnoreCase(product.getProductName())) {
                temp.setProductName(product.getProductName());
            }
            if (Objects.nonNull(product.getSupplierID()) && product.getSupplierID() != 0) {
                temp.setSupplierID(product.getSupplierID());
            }
            if (Objects.nonNull(product.getCategoryID()) && product.getCategoryID() != 0) {
                temp.setCategoryID(product.getCategoryID());
            }
        if (Objects.nonNull(product.getUnitPrice()) && product.getUnitPrice() != 0) {
                temp.setUnitPrice(product.getUnitPrice());
            }
            return productRepository.save(temp);
        } catch (NoSuchElementException e) {
            System.out.println("No product with that ID was found");
            return null;
        }
    }

}
