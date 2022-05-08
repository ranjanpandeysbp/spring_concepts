package com.mycompany.springapp.productapp.repository;

import com.mycompany.springapp.productapp.model.ProductModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCrudRepository extends CrudRepository<ProductModel, Long> {

    /*@Query("SELECT pm FROM ProductModel pm WHERE pm.categoryModel.id = :catId")
    List<ProductModel> getAllProductsForCategory(@Param("catId") Long catId);*/
    Optional<List<ProductModel>> findByDescription(String description);
    Optional<List<ProductModel>> findByDescriptionAndPriceBetween(String description, double fromPrice, double toPrice);
}
