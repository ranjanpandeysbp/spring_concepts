package com.mycompany.springapp.productapp.controller;

import com.mycompany.springapp.productapp.model.CategoryModel;
import com.mycompany.springapp.productapp.service.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    @Test
    public void test_createCategory_success()
    {
        CategoryModel cm1 = new CategoryModel();
        cm1.setCategoryName("Electronics");

        CategoryModel cm2 = new CategoryModel();
        cm2.setCategoryId(1L);
        cm2.setCategoryName("Electronics");

        Mockito.when(categoryService.createCategory(cm1)).thenReturn(cm2);

        ResponseEntity<CategoryModel> responseEntity = categoryController.createCategory(cm1);

        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
        Assertions.assertNotNull(responseEntity.getBody().getCategoryId());
    }

    @Test
    public void test_createCategory_failure()
    {
        CategoryModel cm1 = new CategoryModel();
        cm1.setCategoryName("Electronics");

        CategoryModel cm2 = new CategoryModel();
        cm2.setCategoryId(1L);
        cm2.setCategoryName("Electronics");

        Mockito.when(categoryService.createCategory(cm1)).thenReturn(cm2);

        ResponseEntity<CategoryModel> responseEntity = categoryController.createCategory(cm1);

        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
        Assertions.assertNotNull(responseEntity.getBody().getCategoryId());
    }
}
