package com.mycompany.springapp.productapp.controller;

import com.mycompany.springapp.productapp.exception.BusinessException;
import com.mycompany.springapp.productapp.model.ProductModel;
import com.mycompany.springapp.productapp.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Value("${myproperty}")
    private String dummyField;

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Autowired
    private ProductService ps;

    @GetMapping(value = "/products")
    public ResponseEntity<Iterable<ProductModel>> getAllProducts()
    {
        System.out.println(dummyField);
        System.out.println(dbUser);
        Iterable<ProductModel> list = ps.getAllProducts();

        //ResponseEntity<List<ProductModel>> responseEntity = new ResponseEntity<>(displayAllProducts, HttpStatus.OK);
        //return responseEntity;
        return (new ResponseEntity<Iterable<ProductModel>>(list, HttpStatus.OK));
    }

    @ApiOperation(value = "createProduct",
            notes = "This method creates a new product")
    @PostMapping(path = "/products", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<ProductModel> createProduct(@ApiParam(
            name = "product",
            type = "ProductModel",
            value = "product data",
            example = "id and description and price",
            required = true)
            @RequestBody ProductModel productModel) throws BusinessException {
        System.out.println("create product");

        productModel = ps.createProduct(productModel);
        ResponseEntity<ProductModel> res = new ResponseEntity<ProductModel>(productModel, HttpStatus.CREATED);

        return res;
    }

    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "id") long id)
    {
        ProductModel productModel = ps.deleteProduct(id);
        ResponseEntity<?> res = null;
        if(null != productModel)
        {
            res = new ResponseEntity<ProductModel>(productModel, HttpStatus.OK);
        }
        else
        {
            res = new ResponseEntity<String>("product not found", HttpStatus.NOT_FOUND);
        }
        return res;
    }

    @PutMapping(value="/products/{id}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable("id") long id,
                                                      @RequestBody ProductModel productModel)
    {
        ProductModel productModel1 = ps.updateProduct(id,productModel);
        ResponseEntity<ProductModel> res = new ResponseEntity<ProductModel>(productModel1, HttpStatus.OK);

        return res;
    }

    @PutMapping(value="/products/update/{id}")
    public ResponseEntity<ProductModel> partialUpdateProduct(@PathVariable("id") long id,
                                                      @RequestBody ProductModel productModel)
    {
        ProductModel productModel1 = ps.partialUpdateProduct(id,productModel);
        ResponseEntity<ProductModel> res = new ResponseEntity<ProductModel>(productModel1, HttpStatus.OK);

        return res;
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<ProductModel>> searchProduct(
            @RequestParam("desc") String desc,
            @RequestParam("fromPrice") double fromPrice,
            @RequestParam("toPrice") double toPrice)
    {
        List<ProductModel> productsList = ps.searchProduct(desc,fromPrice,toPrice);
        ResponseEntity<List<ProductModel>> res = new ResponseEntity<>(productsList, HttpStatus.OK);

        return res;
    }

    @GetMapping("/products/searchByDescription")
    public ResponseEntity<List<ProductModel>> searchProductByDescription(@RequestParam("desc") String desc)
    {
        List<ProductModel> productsList = ps.searchProductByDescription(desc);
        ResponseEntity<List<ProductModel>> res = new ResponseEntity<>(productsList, HttpStatus.OK);

        return res;
    }
}
