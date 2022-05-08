package com.mycompany.springapp.productapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "CATEGORY")
public class CategoryModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @ApiModelProperty(
            value = "category name",
            name = "categoryName",
            dataType = "String",
            example = "electronics")
    @Column(name = "CATEGORY_NAME")
    private String categoryName;

    //One Category has many products
    //This is Reverse mapping
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoryModel", cascade = CascadeType.ALL) //mappedBy means the Category table should not create a foreign key of Product Table rather the category Table should use the already created foreign key inside Product Table.
    //@JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<ProductModel> productList;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<ProductModel> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductModel> productList) {
        this.productList = productList;
    }
}
