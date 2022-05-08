package com.mycompany.springapp.productapp.service;

import com.mycompany.springapp.productapp.model.CategoryModel;
import com.mycompany.springapp.productapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository cr;

    public CategoryModel createCategory(CategoryModel categoryModel)
    {
        categoryModel = cr.save(categoryModel);// when cr.save() then return a category model which has id inside it
        return categoryModel;
    }
    public Iterable<CategoryModel> getAllCategories()
    {
        Iterable<CategoryModel> categoryModels = cr.findAll();
        return categoryModels;
    }
    public CategoryModel deleteCategory(Long id)
    {
        CategoryModel categoryModel = null;
        Optional<CategoryModel> optCategoryModel = cr.findById(id);
        if (optCategoryModel.isPresent())
        {
            CategoryModel category = optCategoryModel.get();
            categoryModel = category;
            cr.delete(category);
        }
        else
        {
            System.out.println("No matching category found");
        }
        return categoryModel;
    }
    public CategoryModel updateCategory(Long id, CategoryModel categoryModel)
    {
        CategoryModel categoryModel1 = null;
        Optional<CategoryModel> optCategoryModel = cr.findById(id);
        if(optCategoryModel.isPresent())
        {
            CategoryModel category = optCategoryModel.get();
            if(categoryModel.getCategoryName() != null)
            {
                category.setCategoryName(categoryModel.getCategoryName());
            }
            if(categoryModel.getProductList() != null)
            {
                category.setProductList(categoryModel.getProductList());
            }
            categoryModel1 = category;
            cr.save(category);
        }
        else
        {
            System.out.println("No matching category found");
        }
        return categoryModel1;
    }
    public List<CategoryModel> searchCategory(String name)
    {
        List<CategoryModel> categoriesList = null;
        Optional<List<CategoryModel>> optCategoryList= cr.findByCategoryName(name);
        if(optCategoryList.isPresent())
        {
            categoriesList = optCategoryList.get();
        }
        else
        {
            System.out.println("No matching category found");
        }
        return categoriesList;
    }
}
