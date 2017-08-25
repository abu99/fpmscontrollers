/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpms.controllers;

import com.fpms.persistence.entities.Product;
import com.fpms.persistence.mngrs.ProductEntityMngrLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author aabello
 */
@Stateless
public class ProductMngr implements ProductMngrLocal{
    
    @EJB
    private ProductEntityMngrLocal productEntityMngr;

    @Override
    public Product addProduct(Product product) {
        return productEntityMngr.create(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productEntityMngr.update(product);
    }

    @Override
    public void deleteProduct(String productId) {
        productEntityMngr.delete(productId);
    }

    @Override
    public void deleteProducts(List<String> productIds) {
        for(String productId : productIds){
            deleteProduct(productId);
        }
    }

    @Override
    public Product getProduct(String productId) {
        return productEntityMngr.get(productId);
    }

    @Override
    public List<Product> getProducts() {
        return productEntityMngr.getAll();
    }

    @Override
    public List<Product> getProducts(String sectionId) {
        return productEntityMngr.getProducts(sectionId);
    }

    @Override
    public List<Product> getProducts(List<String> productIds) {
        
        List<Product> products = new ArrayList<Product>();
        for (String productId : productIds){
            Product result = productEntityMngr.get(productId);
            if(result != null){
                products.add(result);
            }
        }
        
        if(products.isEmpty()) return null;
        
        return products;
    } 
    
}
