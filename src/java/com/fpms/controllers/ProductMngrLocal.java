/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpms.controllers;

import com.fpms.persistence.entities.Product;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author aabello
 */
@Local
public interface ProductMngrLocal {
    
    Product addProduct(Product product);
    
    Product updateProduct(Product product);
    
    void deleteProduct(String productId);
    
    void deleteProducts(List<String> productIds);
    
    Product getProduct(String productId);
    
    List<Product> getProducts();
    
    List<Product> getProducts(String sectionId);
    
    List<Product> getProducts(List<String> productIds);
    
}
