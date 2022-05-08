package com.mycompany.springapp.productapp.repository;

import com.mycompany.springapp.productapp.model.CategoryModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryModel, Long> {
    Optional<List<CategoryModel>> findByCategoryName(String categoryName);
}
