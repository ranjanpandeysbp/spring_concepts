package com.mycompany.springapp.productapp.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "PRODUCT_TABLE")
public class ProductModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRODUCT_ID")
    private long id;

    @Column(name = "PRODUCT_DESCRIPTION")
    private String description;
    @Column(name = "PRODUCT_PRICE")
    private double price;

    //many Product belong to one category
    @ManyToOne(fetch = FetchType.LAZY) //FetchType.LAZY means don't fetch the child data while fetching the parent.
    //In this case whenever the product will be fetched from the data category will be returned as null, in order to improve performance.
    @JoinColumn(name = "CATEGORY_ID")
    private CategoryModel categoryModel;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CategoryModel getCategoryModel() {
        return categoryModel;
    }

    public void setCategoryModel(CategoryModel categoryModel) {
        this.categoryModel = categoryModel;
    }

}
