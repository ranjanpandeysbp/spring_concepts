package com.mycompany.springapp.productapp.service;

import com.mycompany.springapp.productapp.exception.BusinessException;
import com.mycompany.springapp.productapp.model.CategoryModel;
import com.mycompany.springapp.productapp.model.ProductModel;
import com.mycompany.springapp.productapp.repository.CategoryRepository;
import com.mycompany.springapp.productapp.repository.ProductCrudRepository;
import com.mycompany.springapp.productapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ProductService {

    @Autowired
    private ProductRepository pr;

    @Autowired
    private ProductCrudRepository pcr;

    @Autowired
    private CategoryRepository cr;

    public Iterable<ProductModel> getAllProducts()
    {
        Iterable<ProductModel> productModelList = pcr.findAll();//pr.getAllProducts();
        return productModelList;
    }

    /*
    public ProductModel createProduct(ProductModel productModel)
    {
        Optional<CategoryModel> optCategory = cr.findById(productModel.getCategoryModel().getCategoryId());
        productModel.setCategoryModel(optCategory.get());
        productModel = pcr.save(productModel);//pr.createProduct(productModel);
        return productModel;
    }
    */

    public ProductModel createProduct(ProductModel productModel) throws BusinessException {
        Optional<List<ProductModel>> optProductModelList = pcr.findByDescription(productModel.getDescription());
        if(optProductModelList.isPresent())
        {
            BusinessException be = new BusinessException("CREATE_001","Product with this description already exists. Please try with another product description");
            throw be;
        }
        else
        {
            Optional<CategoryModel> optCategory = cr.findById(productModel.getCategoryModel().getCategoryId());
            productModel.setCategoryModel(optCategory.get());
            productModel = pcr.save(productModel);//pr.createProduct(productModel);
        }
        return productModel;
    }

    public ProductModel deleteProduct(Long id)
    {
        ProductModel productModel = null;
        Optional<ProductModel> optProduct = pcr.findById(id);
        if(optProduct.isPresent())
        {
            ProductModel product = optProduct.get();
            productModel = product;
            pcr.delete(product);
        }
        else
        {
            System.out.println("No matching product found");
        }
        //ProductModel productModel = pr.deleteProduct(id);
        return productModel;
    }

    public ProductModel updateProduct(long id, ProductModel productModel)
    {
        ProductModel productModel1 = null;
        Optional<ProductModel> optProduct = pcr.findById(id);
        if(optProduct.isPresent())
        {
            ProductModel product = optProduct.get();
            product.setDescription(productModel.getDescription());
            product.setPrice(productModel.getPrice());
            productModel1 = product;
            pcr.save(product);
        }
        else
        {
            System.out.println("No matching product found");
        }
        //ProductModel productModel1 = pr.updateProduct(id,productModel);
        return productModel1;
    }

    public ProductModel partialUpdateProduct(long id, ProductModel productModel)
    {
        ProductModel productModel1 = null;
        Optional<ProductModel> optProduct = pcr.findById(id);
        if(optProduct.isPresent())
        {
            ProductModel product = optProduct.get();
            /*if(productModel.getPrice() == 0.0 && productModel.getDescription() != null)
            {
                product.setDescription(productModel.getDescription());
            }
            else if(productModel.getPrice() != 0.0 && productModel.getDescription() == null)
            {
                product.setPrice(productModel.getPrice());
            }
            else
            {
                product.setPrice(productModel.getPrice());
                product.setDescription(productModel.getDescription());
            }*/
            if(productModel.getPrice() != 0.0)
            {
                product.setPrice(productModel.getPrice());
            }
            if(productModel.getDescription() != null)
            {
                product.setDescription(productModel.getDescription());
            }
            productModel1 = product;
            pcr.save(product);
        }
        else
        {
            System.out.println("No matching product found");
        }
        return productModel1;
    }
    /*
    public List<ProductModel> searchProductByDescription(String desc)
    {
        List<ProductModel> productsList = null;
        productsList = pr.searchProductByDescription(desc);
        return productsList;
    }
    */
    public List<ProductModel> searchProductByDescription(String desc)
    {
        List<ProductModel> productModelList = null;
        Optional<List<ProductModel>> optProductModel = pcr.findByDescription(desc);
        if(optProductModel.isPresent())
        {
            productModelList = optProductModel.get();
        }
        else
        {
            System.out.println("No matching product found");
        }
        return productModelList;
    }
    /*
    public List<ProductModel> searchProduct(String desc, double fromPrice, double toPrice)
    {
        List<ProductModel> productsList = null;
        productsList = pr.searchProduct(desc,fromPrice,toPrice);
        return productsList;
    }
    */
    public List<ProductModel> searchProduct(String desc, double fromPrice, double toPrice)
    {
        List<ProductModel> productModelList = null;
        Optional<List<ProductModel>> optProductModel = pcr.findByDescriptionAndPriceBetween(desc, fromPrice, toPrice);
        if(optProductModel.isPresent())
        {
            productModelList = optProductModel.get();
        }
        else
        {
            System.out.println("No matching product found");
        }
        return productModelList;
    }
}
