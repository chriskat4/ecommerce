package ecommerceBESB.ecommerce.Products.Controllers;

import java.util.List;
import java.util.UUID;


import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecommerceBESB.ecommerce.Errors.Exceptions.ProductNotFoundException;
import ecommerceBESB.ecommerce.Products.Product;
import ecommerceBESB.ecommerce.Products.Requests.ProductSaveRequest;
import ecommerceBESB.ecommerce.Products.Requests.ProductUpdateRequest;
import ecommerceBESB.ecommerce.Products.Services.ProductService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/Api/Product")
@Transactional
public class ProductController {
    private final ProductService productService;
    
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductByName(name));
    }
    

    @PostMapping
    public ResponseEntity<Product> saveUser(@Valid @RequestBody ProductSaveRequest prodReq){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(prodReq));
    }



    @PutMapping("/{id}")
    public ResponseEntity<Product> updateUser(@PathVariable UUID id, @Valid @RequestBody ProductUpdateRequest prodReq) throws ProductNotFoundException{
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.updateProduct(id, prodReq));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProduct(id));
    }
}
