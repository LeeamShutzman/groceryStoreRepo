package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Product;
import com.example.demo.services.ProductService;

@RestController
@RequestMapping("products") //localhost:portNum/categories
public class ProductController {

	private ProductService productService;

	/***************************************************************/
	//Constructors, Getters, and Setters

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	/***************************************************************/
	//Service endpoint mapping

	//Add a Product
	@PostMapping("add") //localhost:portNum/products/add
	public Product addProduct(@RequestBody Product product) {
		return productService.addProduct(product);
	}

	//View all Products //localhost:portNum/products/all
	@GetMapping("all")
	public List<Product> getAllProducts(){
		return productService.findAll();
	}

	//View Product by ID
	@GetMapping("getProductByID") //localhost:portNum/products/getProductByID?productID=#
	public Optional<Product> getProductByID(@RequestParam long productID) {
		return productService.findByProductID(productID);
	}

	//View Products in the same Category
	@GetMapping("getProductsByCategoryID") //localhost:portNum/products/getProductsByCategoryID?categoryID=#
	public List<Product> getProductsByCategoryID(@RequestParam long categoryID) {
		return productService.findProductsByCategoryID(categoryID);
	}

	//View Products from the same Supplier
	@GetMapping("getProductsBySupplierID") //localhost:portNum/products/getProductsBySupplierID?supplierID=#
	public List<Product> getProductsBySupplierID(@RequestParam long supplierID) {
		return productService.findProductsBySupplierID(supplierID);
	}
	
	//View Products from the same Supplier that are in the same Category
	@GetMapping("getProductsBySupplierIDAndCategoryID") //localhost:portNum/products/getProductsBySupplierIDAndCategoryID?supplierID=#&categoryID=#
	public List<Product> getProductsBySupplierIDAndCategoryID(@RequestParam long supplierID, @RequestParam long categoryID ){
		return productService.findProductsBySupplierIDAndCategoryID(supplierID, categoryID);
	}

	//Delete a Product
	@DeleteMapping("delete")//localhost:portNum/products/delete?productID=#
	public void deleteProduct(@RequestParam long productID){
		productService.deleteProduct(productID);
	}
	
	//Update an Product
	@PutMapping("/update") //localhost:portNum/products/update
	public Product updateProduct(@RequestParam long productID, @RequestBody Product product) {
		return productService.updateProduct(productID, product);
	}
	
}
	
	
	
	
	
	

