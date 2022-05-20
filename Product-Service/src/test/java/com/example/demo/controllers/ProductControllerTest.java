package com.example.demo.controllers;

import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductController productController;

    @MockBean
    ProductService productService;

    public Product product1 = new Product(1,"testName1",2,2,25);
    public Product product2 = new Product(2,"testName2",3,2,30);
    public Product product3 = new Product(3,"testName3",2,3,35);
    public Product product4 = new Product(4,"testName4",3,3,40);

    public List<Product> testList = new ArrayList<>(Arrays.asList(product1,product2,product3,product4));

    @Test
    void addProduct() throws Exception {
        when(productService.addProduct(any(Product.class))).thenReturn(product1);
        mockMvc.perform(MockMvcRequestBuilders.post("/products/add")
                .content(new ObjectMapper().writeValueAsString(product1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productID").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productName").value("testName1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.supplierID").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoryID").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.unitPrice").value(25));

    }

    @Test
    void getAllProducts() throws Exception {
        when(productService.findAll()).thenReturn(testList);
        mockMvc.perform(MockMvcRequestBuilders.get("/products/all"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]productID").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]productName").value("testName1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]supplierID").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]categoryID").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]unitPrice").value(25))

                .andExpect(MockMvcResultMatchers.jsonPath("$[1]productID").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]productName").value("testName2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]supplierID").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]categoryID").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]unitPrice").value(30))

                .andExpect(MockMvcResultMatchers.jsonPath("$[2]productID").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2]productName").value("testName3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2]supplierID").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2]categoryID").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2]unitPrice").value(35))

                .andExpect(MockMvcResultMatchers.jsonPath("$[3]productID").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3]productName").value("testName4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3]supplierID").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3]categoryID").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3]unitPrice").value(40));

    }

    @Test
    void getProductByID() throws Exception {
        Optional<Product> productOptional = Optional.of(product1);
        when(productService.findByProductID(1L)).thenReturn(productOptional);
        mockMvc.perform(MockMvcRequestBuilders.get("/products/getProductByID?productID=1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productID").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productName").value("testName1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.supplierID").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoryID").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.unitPrice").value(25));
    }

    @Test
    void getProductsByCategoryID() throws Exception {
        List<Product> cat2List = new ArrayList<>(Arrays.asList(testList.get(0),testList.get(1)));
        when(productService.findProductsByCategoryID(2L)).thenReturn(cat2List);
        mockMvc.perform(MockMvcRequestBuilders.get("/products/getProductsByCategoryID?categoryID=2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]productID").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]productName").value("testName1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]supplierID").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]categoryID").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]unitPrice").value(25))

                .andExpect(MockMvcResultMatchers.jsonPath("$[1]productID").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]productName").value("testName2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]supplierID").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]categoryID").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]unitPrice").value(30));
    }

    @Test
    void getProductsBySupplierID() throws Exception {
        List<Product> supp2List = new ArrayList<>(Arrays.asList(testList.get(0),testList.get(2)));
        when(productService.findProductsBySupplierID(2L)).thenReturn(supp2List);
        mockMvc.perform(MockMvcRequestBuilders.get("/products/getProductsBySupplierID?supplierID=2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]productID").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]productName").value("testName1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]supplierID").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]categoryID").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]unitPrice").value(25))

                .andExpect(MockMvcResultMatchers.jsonPath("$[1]productID").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]productName").value("testName3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]supplierID").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]categoryID").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]unitPrice").value(35));
    }

    @Test
    void getProductsBySupplierIDAndCategoryID() throws Exception {
        List<Product> supp2cat2List = new ArrayList<>(Arrays.asList(testList.get(0)));
        when(productService.findProductsBySupplierIDAndCategoryID(2L,2L)).thenReturn(supp2cat2List);
        mockMvc.perform(MockMvcRequestBuilders.get("/products/getProductsBySupplierIDAndCategoryID?supplierID=2&categoryID=2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]productID").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]productName").value("testName1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]supplierID").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]categoryID").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]unitPrice").value(25));
    }

    @Test
    void deleteProduct() throws Exception {
        Optional<Product> productOptional = Optional.of(product1);
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/delete?productID=1"));
        verify(productService,times(1)).deleteProduct(1L);
    }

    @Test
    void updateProduct() throws Exception {
        Product newProduct = new Product();
        newProduct.setProductName("New Name");
        Product expectedProduct = new Product(product1.getProductID(), "New Name", product1.getSupplierID(),product1.getCategoryID(),product1.getUnitPrice());
        when(productService.updateProduct(eq(1L),any(Product.class))).thenReturn(expectedProduct);
        when(productService.updateProduct(eq(5L),any(Product.class))).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.put("/products/update?productID=1")
                .content(new ObjectMapper().writeValueAsString(newProduct))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productID").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productName").value("New Name"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.supplierID").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoryID").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.unitPrice").value(25));

        mockMvc.perform(MockMvcRequestBuilders.put("/products/update?productID=5")
                .content(new ObjectMapper().writeValueAsString(newProduct))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").doesNotExist());
    }
}