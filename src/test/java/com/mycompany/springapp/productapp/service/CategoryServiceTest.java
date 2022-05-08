package com.mycompany.springapp.productapp.service;

import com.mycompany.springapp.productapp.model.CategoryModel;
import com.mycompany.springapp.productapp.model.ProductModel;
import com.mycompany.springapp.productapp.repository.CategoryRepository;
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
public class CategoryServiceTest {

    @InjectMocks //creates a singleton dummy object for category service
    private CategoryService categoryService;

    @Mock //creates a dummy singleton object of category repository and also injects it inside category service
    private CategoryRepository categoryRepository;

    @Test
    public void test_createCategory()
    {
        CategoryModel categoryModel1 = new CategoryModel();
        categoryModel1.setCategoryName("Food");

        CategoryModel categoryModel3 = new CategoryModel();
        categoryModel3.setCategoryId(1L);
        categoryModel3.setCategoryName("Food");

        //when cr.save() then return a category model which has id inside it
        Mockito.when(categoryRepository.save(categoryModel1)).thenReturn(categoryModel3);

        CategoryModel categoryModel2 = categoryService.createCategory(categoryModel1);
        Assertions.assertNotNull(categoryModel2.getCategoryId(),"Test failed because new category should have an Id");
    }

    @Test
    public void test_getAllCategories_with_0_size()
    {
        List<CategoryModel> categories = (List<CategoryModel>) categoryService.getAllCategories();
        Assertions.assertEquals(categories.size(), 0);
    }

    @Test
    public void test_getAllCategories_with_non_zero_size()
    {
        CategoryModel cm = new CategoryModel();
        cm.setCategoryId(1L);
        cm.setCategoryName("Food");

        List<CategoryModel> listOfCat = new ArrayList<>();
        listOfCat.add(cm);

        Mockito.when(categoryRepository.findAll()).thenReturn(listOfCat);

        List<CategoryModel> categories = (List<CategoryModel>) categoryService.getAllCategories();
        Assertions.assertEquals(categories.size(), 1);
    }

    @Test
    public void test_deleteCategory_categoryFound()
    {
        CategoryModel cm1 = new CategoryModel();
        cm1.setCategoryId(1L);
        cm1.setCategoryName("Food");

        Mockito.when(categoryRepository.findById(cm1.getCategoryId())).thenReturn(Optional.of(cm1));
        Mockito.doNothing().when(categoryRepository).delete(cm1);

        CategoryModel cm = categoryService.deleteCategory(cm1.getCategoryId());
        Assertions.assertEquals(cm1.getCategoryId(), cm.getCategoryId(), "Test failed because we should get the category id of the category to be deleted");
    }

    @Test
    public void test_deleteCategory_categoryNotFound()
    {
        CategoryModel cm1 = new CategoryModel();
        cm1.setCategoryId(1L);
        cm1.setCategoryName("Food");

        Mockito.when(categoryRepository.findById(cm1.getCategoryId())).thenReturn(Optional.empty());

        CategoryModel cm = categoryService.deleteCategory(cm1.getCategoryId());
        Assertions.assertNull(cm,"Test failed because we should get the category id as Null when the category to be deleted is not present");
    }

    @Test
    public void test_updateCategory_categoryFound_categoryNameNotNullAndProductListNull()
    {
        CategoryModel cm1 = new CategoryModel();
        cm1.setCategoryId(1L);
        cm1.setCategoryName("Food");

        CategoryModel cm2 = new CategoryModel();
        cm2.setCategoryId(1L);
        cm2.setCategoryName("Updated Food");

        Mockito.when(categoryRepository.findById(cm1.getCategoryId())).thenReturn(Optional.of(cm1));
        Mockito.when(categoryRepository.save(cm1)).thenReturn(cm2);

        CategoryModel cm = categoryService.updateCategory(cm1.getCategoryId(), cm2);

        Assertions.assertEquals(cm.getCategoryName(), cm2.getCategoryName(), "Test failed because we should get the updated category Name");
    }

    @Test
    public void test_updateCategory_categoryFound_categoryNameNullAndProductListNotNull()
    {
        CategoryModel cm1 = new CategoryModel();
        cm1.setCategoryId(1L);
        cm1.setCategoryName("Food");

        ProductModel pm = new ProductModel();
        pm.setId(1L);
        List<ProductModel> productModelList = new ArrayList<>();
        productModelList.add(pm);
        CategoryModel cm2 = new CategoryModel();
        cm2.setCategoryId(1L);
        cm2.setProductList(productModelList);

        Mockito.when(categoryRepository.findById(cm1.getCategoryId())).thenReturn(Optional.of(cm1));
        Mockito.when(categoryRepository.save(cm1)).thenReturn(cm2);

        CategoryModel cm = categoryService.updateCategory(cm1.getCategoryId(), cm2);

        Assertions.assertEquals(cm.getProductList().size(), cm2.getProductList().size(), "Test failed because we should get the updated size of product list");
    }

    @Test
    public void test_updateCategory_categoryFound_categoryNameNotNullAndProductListNotNull()
    {
        CategoryModel cm1 = new CategoryModel();
        cm1.setCategoryId(1L);
        cm1.setCategoryName("Food");

        ProductModel pm = new ProductModel();
        pm.setId(1L);
        List<ProductModel> productModelList = new ArrayList<>();
        productModelList.add(pm);

        CategoryModel cm2 = new CategoryModel();
        cm2.setCategoryId(1L);
        cm2.setCategoryName("Updated Food");
        cm2.setProductList(productModelList);

        Mockito.when(categoryRepository.findById(cm1.getCategoryId())).thenReturn(Optional.of(cm1));
        Mockito.when(categoryRepository.save(cm1)).thenReturn(cm2);

        CategoryModel cm = categoryService.updateCategory(cm1.getCategoryId(), cm2);

        Assertions.assertEquals(cm.getCategoryName(), cm2.getCategoryName(), "Test failed because we should get the updated category Name");
        Assertions.assertEquals(cm.getProductList().size(), cm2.getProductList().size(), "Test failed because we should get the updated size of product list");
    }

    @Test
    public void test_updateCategory_categoryFound_categoryNameNullAndProductListNull()
    {
        ProductModel pm = new ProductModel();
        pm.setId(1L);
        List<ProductModel> productModelList = new ArrayList<>();
        productModelList.add(pm);

        CategoryModel cm1 = new CategoryModel();
        cm1.setCategoryId(1L);
        cm1.setCategoryName("Food");
        cm1.setProductList(productModelList);

        CategoryModel cm2 = new CategoryModel();
        cm2.setCategoryId(1L);

        Mockito.when(categoryRepository.findById(cm1.getCategoryId())).thenReturn(Optional.of(cm1));
        Mockito.when(categoryRepository.save(cm1)).thenReturn(cm2);

        CategoryModel cm = categoryService.updateCategory(cm1.getCategoryId(), cm2);

        //Assertions.assertEquals(cm.getCategoryName(), cm1.getCategoryName(), "Test failed because we should get the original category Name");
        //Assertions.assertEquals(cm.getProductList().size(), cm1.getProductList().size(), "Test failed because we should get the original size of product list");
        Assertions.assertNotNull(cm.getCategoryName());
        Assertions.assertNotNull(cm.getProductList());
    }

    @Test
    public void test_updateCategory_categoryNotFound()
    {
        CategoryModel cm1 = new CategoryModel();
        cm1.setCategoryId(1L);
        cm1.setCategoryName("Updated Food");

        Mockito.when(categoryRepository.findById(cm1.getCategoryId())).thenReturn(Optional.empty());

        CategoryModel cm = categoryService.updateCategory(cm1.getCategoryId(), cm1);

        Assertions.assertNull(cm);
    }

    @Test
    public void test_searchCategory_categoryFound()
    {
        CategoryModel cm1 = new CategoryModel();
        cm1.setCategoryId(1L);
        cm1.setCategoryName("Food");

        List<CategoryModel> categoryModelList1 = new ArrayList<>();
        categoryModelList1.add(cm1);

        Mockito.when(categoryRepository.findByCategoryName(cm1.getCategoryName())).thenReturn(Optional.of(categoryModelList1));

        List<CategoryModel> categoryModelList = categoryService.searchCategory(cm1.getCategoryName());
        Assertions.assertEquals(categoryModelList.get(0).getCategoryId(), 1L);
    }

    @Test
    public void test_searchCategory_categoryNotFound()
    {
        CategoryModel cm1 = new CategoryModel();
        cm1.setCategoryName("Food");

        Mockito.when(categoryRepository.findByCategoryName(cm1.getCategoryName())).thenReturn(Optional.empty());

        List<CategoryModel> categoryModelList = categoryService.searchCategory(cm1.getCategoryName());
        Assertions.assertNull(categoryModelList);
    }
}
