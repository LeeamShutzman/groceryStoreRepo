package com.example.demo.services;

import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@WebMvcTest(ProductService.class)
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @MockBean
    ProductRepository productRepository;

    public final Product product1 = new Product(1,"testName1",2,2,25);
    public final Product product2 = new Product(2,"testName2",3,2,30);
    public final Product product3 = new Product(3,"testName3",2,3,35);
    public final Product product4 = new Product(4,"testName4",3,3,40);

    public List<Product> testList = new ArrayList<>(Arrays.asList(product1,product2,product3,product4));


    @Test
    void addProduct() {
        when(productRepository.save(product1)).thenReturn(product1);
        assertEquals(product1.toString(),productService.addProduct(product1).toString());
    }

    @Test
    void findAll() {
        when(productRepository.findAll()).thenReturn(testList);
        assertEquals(testList,productService.findAll());
    }

    @Test
    void findByProductID() {
        Optional<Product> productOptional = Optional.of(product1);
        when(productRepository.findById(1L)).thenReturn(productOptional);
        assertEquals(productOptional,productService.findByProductID(1L));
    }

    @Test
    void findProductsByCategoryID() {
        List<Product> cat2List = new ArrayList<>(Arrays.asList(testList.get(0),testList.get(1)));
        when(productRepository.findByCategoryID(2L)).thenReturn(cat2List);
        assertEquals(cat2List,productService.findProductsByCategoryID(2L));
    }

    @Test
    void findProductsBySupplierID() {
        List<Product> supp2List = new ArrayList<>(Arrays.asList(testList.get(0),testList.get(2)));
        when(productRepository.findBySupplierID(2L)).thenReturn(supp2List);
        assertEquals(supp2List,productService.findProductsBySupplierID(2L));
    }

    @Test
    void findProductsBySupplierIDAndCategoryID() {
        List<Product> supp2cat2List = new ArrayList<>(Arrays.asList(testList.get(0)));
        when(productRepository.findBySupplierIDAndCategoryID(2L, 2L)).thenReturn(supp2cat2List);
        assertEquals(supp2cat2List,productService.findProductsBySupplierIDAndCategoryID(2L,2L));
    }

    @Test
    void deleteProduct() {
        Optional<Product> productOptional = Optional.of(product1);
        productService.deleteProduct(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateProduct() {
        Product expectedProduct = new Product(product1.getProductID(),"New Name",product1.getSupplierID(),product1.getCategoryID(),product1.getUnitPrice());
        Product newProduct = new Product(); //Request body
        newProduct.setProductName("New Name");
        Optional<Product> productOptional = Optional.of(new Product(product1.getProductID(),product1.getProductName(),product1.getSupplierID(),product1.getCategoryID(),product1.getUnitPrice()));
        when(productRepository.findById(1L)).thenReturn(productOptional);
        when(productRepository.save(any(Product.class))).thenReturn(expectedProduct);
        when(productRepository.findById(5L)).thenReturn(Optional.empty());
        assertEquals(expectedProduct.toString(), productService.updateProduct(1L,newProduct).toString());
        assertEquals(null,productService.updateProduct(5L,newProduct));
    }
}