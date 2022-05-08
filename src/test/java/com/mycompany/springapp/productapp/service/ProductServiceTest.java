package com.mycompany.springapp.productapp.service;

import com.mycompany.springapp.productapp.exception.BusinessException;
import com.mycompany.springapp.productapp.model.CategoryModel;
import com.mycompany.springapp.productapp.model.ProductModel;
import com.mycompany.springapp.productapp.repository.CategoryRepository;
import com.mycompany.springapp.productapp.repository.ProductCrudRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductCrudRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    public void test_createProduct_productExists()
        {
        ProductModel pm1 = new ProductModel();
        pm1.setPrice(1000.1);
        pm1.setDescription("Garments");

        List<ProductModel> productModelList = new ArrayList<>();
        productModelList.add(pm1);

        Mockito.when(productRepository.findByDescription(pm1.getDescription())).thenReturn(Optional.of(productModelList));
        //Mockito.when(productRepository.save(pm1)).thenReturn(pm2);

        Assertions.assertThrows(BusinessException.class,
                ()->{
            productService.createProduct(pm1);
        });
    }

    @Test
    public void test_createProduct_productDoesNotExists() throws BusinessException {
        CategoryModel cm = new CategoryModel();
        cm.setCategoryId(1L);

        ProductModel pm1 = new ProductModel();
        pm1.setPrice(1000.1);
        pm1.setDescription("Garments");
        pm1.setCategoryModel(cm);

        ProductModel pm2 = new ProductModel();
        pm2.setCategoryModel(cm);
        pm2.setId(1);
        pm2.setPrice(1000.1);
        pm2.setDescription("Garments");

        Mockito.when(productRepository.findByDescription(pm1.getDescription())).thenReturn(Optional.empty());
        Mockito.when(categoryRepository.findById(pm1.getCategoryModel().getCategoryId())).thenReturn(Optional.of(cm));
        Mockito.when(productRepository.save(pm1)).thenReturn(pm2);

        ProductModel pm = productService.createProduct(pm1);

        Assertions.assertEquals(pm2.getId(), pm.getId());
    }

    @Test
    public void test_createProduct_productDoesNotExists1() throws BusinessException {
        CategoryModel cm1 = new CategoryModel();
        cm1.setCategoryId(1L);

        ProductModel pm1 = new ProductModel();
        pm1.setPrice(1000.1);
        pm1.setDescription("Garments");
        pm1.setCategoryModel(cm1);

        CategoryModel cm2 = new CategoryModel();
        cm2.setCategoryId(1L);
        cm2.setCategoryName("Clothing");

        ProductModel pm2 = new ProductModel();
        pm2.setId(1);
        pm2.setPrice(1000.1);
        pm2.setDescription("Garments");
        pm2.setCategoryModel(cm2);

        Mockito.when(productRepository.findByDescription(pm1.getDescription())).thenReturn(Optional.empty());
        Mockito.when(categoryRepository.findById(pm1.getCategoryModel().getCategoryId())).thenReturn(Optional.of(cm2));
        Mockito.when(productRepository.save(pm1)).thenReturn(pm2);
        //Assertions.assertEquals(pm1.getCategoryModel().getCategoryName(), pm2.getCategoryModel().getCategoryName());
        ProductModel pm = productService.createProduct(pm1);

        Assertions.assertEquals(pm2.getId(), pm.getId());
        Assertions.assertEquals(pm2.getCategoryModel().getCategoryName(), pm.getCategoryModel().getCategoryName());
    }
}
