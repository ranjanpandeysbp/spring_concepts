package com.mycompany.springapp.productapp.controller;

import com.mycompany.springapp.productapp.model.CategoryModel;
import com.mycompany.springapp.productapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService cs;

    @PostMapping(path = "/categories")
    public ResponseEntity<CategoryModel> createCategory(@RequestBody CategoryModel categoryModel)
    {
        categoryModel = cs.createCategory(categoryModel);
        ResponseEntity<CategoryModel> res = new ResponseEntity<>(categoryModel, HttpStatus.CREATED);

        return res;
    }

    @GetMapping(path = "/categories")
    public ResponseEntity<Iterable<CategoryModel>> getAllCategories()
    {
        Iterable<CategoryModel> categoryModels = cs.getAllCategories();
        ResponseEntity<Iterable<CategoryModel>> res = new ResponseEntity<Iterable<CategoryModel>>(categoryModels, HttpStatus.OK);
        return res;
    }

    @DeleteMapping(path = "/categories/{id}")
    public ResponseEntity<CategoryModel> deleteCategory(@PathVariable(name = "id") Long id)
    {
        CategoryModel categoryModel = cs.deleteCategory(id);
        ResponseEntity<CategoryModel> res = new ResponseEntity<>(categoryModel, HttpStatus.NO_CONTENT);
        return res;
    }

    @PutMapping(path = "/categories/{id}")
    public ResponseEntity<CategoryModel> updateCategory(@PathVariable(name = "id") Long id, @RequestBody CategoryModel categoryModel)
    {
        CategoryModel categoryModel1 = cs.updateCategory(id,categoryModel);
        ResponseEntity<CategoryModel> res = new ResponseEntity<>(categoryModel1, HttpStatus.CREATED);
        return res;
    }

    @GetMapping("/categories/search")
    public ResponseEntity<List<CategoryModel>> searchCategory(@RequestParam(value = "name") String name)
    {
        List<CategoryModel> categoriesList = cs.searchCategory(name);
        ResponseEntity<List<CategoryModel>> res = new ResponseEntity<>(categoriesList, HttpStatus.OK);
        return res;
    }
}
