package com.mycompany.springapp.productapp.repository;

import com.mycompany.springapp.productapp.model.ProductModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/*
 * author name: Pabitra
 * class name: ProductRepository
 * date: 06-06-2021
 * description: This class performs the CRUD operation on product
 */
@Repository
public class ProductRepository {

    List<ProductModel> productsList = new ArrayList<>();

    public List<ProductModel> getAllProducts()
    {
        /*
        ProductModel pm = new ProductModel();
        pm.setId(1);
        pm.setDescription("Laptop");
        pm.setPrice(29000);
        productsList.add(pm);

        pm = new ProductModel();
        pm.setId(2);
        pm.setDescription("Shampoo");
        pm.setPrice(125);
        productsList.add(pm);

        pm = new ProductModel();
        pm.setId(3);
        pm.setDescription("Comb");
        pm.setPrice(20);
        productsList.add(pm);
        */

        return productsList;
    }

    public ProductModel createProduct(ProductModel productModel)
    {
        this.productsList.add(productModel);
        return productModel;
    }

    public ProductModel deleteProduct(Long id)
    {
        ProductModel productModel = null;
        //logic for searching a product based on product id
        for(int i=0; i<this.productsList.size(); i++)
        {
            if(this.productsList.get(i).getId() == id)
            {
                productModel = this.productsList.get(i);
                //if product found then remove
                this.productsList.remove(i);
                break;
            }
        }
        return productModel;
    }

    //logic for updating a product based on Product id
    public ProductModel updateProduct(long id, ProductModel productModel)
    {
        ProductModel pm = null;
        //logic for updating the product based on Product id
        for(int i=0; i<this.productsList.size(); i++)
        {
            pm = this.productsList.get(i);
            if(pm.getId() == id)
            {
                //if product is found then update the product
                pm.setPrice(productModel.getPrice());
                pm.setDescription(productModel.getDescription());
                this.productsList.set(i,pm);
                break;
            }
        }
        return pm;
    }

    //logic for updating a product based on Product id with either Price or Description
    public ProductModel updateProduct1(long id, ProductModel productModel)
    {
        ProductModel pm = null;
        //logic for updating the product based on Product id
        for(int i=0; i<this.productsList.size(); i++)
        {
            pm = this.productsList.get(i);
            if(pm.getId() == id)
            {
                //if product is found then update the product
                if(productModel.getPrice() == 0.0)
                {
                    //pm.setPrice(productModel.getPrice());
                    pm.setDescription(productModel.getDescription());
                    this.productsList.set(i,pm);
                }
                else if(productModel.getDescription() == null)
                {
                    pm.setPrice(productModel.getPrice());
                    //pm.setDescription(productModel.getDescription());
                    this.productsList.set(i,pm);
                }
                else
                {
                    pm.setPrice(productModel.getPrice());
                    pm.setDescription(productModel.getDescription());
                    this.productsList.set(i,pm);
                }
            }
            break;
        }
        return pm;
    }

    /**
     * method name: searchProductByDescription
     * method parameters: String
     * method return type: List<ProductModel>
     * description: This method searches a product based on description
     * **/
    public List<ProductModel> searchProductByDescription(String desc)
    {
        ProductModel pm = null;
        List<ProductModel> listOfProducts = new ArrayList<>();

        //loops through all the products
        for(int i=0; i<this.productsList.size(); i++)
        {
            pm = this.productsList.get(i);
            //checks if the current product description matches with the user input
            if(pm.getDescription().contains(desc))
            {
                //keep adding the matching product to a new list
                listOfProducts.add(pm);
            }
        }
        //return the new matching product list
        return listOfProducts;
    }

    public List<ProductModel> searchProduct(String desc, double fromPrice, double toPrice)
    {
        ProductModel pm = null;
        List<ProductModel> listOfProducts = new ArrayList<>();

        //loops through all the products
        for(int i=0; i<this.productsList.size(); i++)
        {
            pm = this.productsList.get(i);
            //checks if the current product description matches with the user input
            //and whether the product lies within the given price range
            if(pm.getDescription().contains(desc) && (pm.getPrice()>=fromPrice && pm.getPrice()<=toPrice))
            {
                //keep adding the matching product to a new list
                listOfProducts.add(pm);
            }
        }
        //return the new matching product list
        return listOfProducts;
    }
}
