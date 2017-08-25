/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpms.controllers;

import com.fpms.controllers.test.common.ServiceLocator;
import com.fpms.persistence.entities.FarmSection;
import com.fpms.persistence.entities.Product;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aabello
 */
public class ProductMngrTest {
    @EJB
    private static ProductMngrLocal instance;
    
    public ProductMngrTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception{
        instance = new ServiceLocator().lookup(ProductMngrLocal.class);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addProduct method, of class ProductMngr.
     */
    @Test
    public void testAddProduct() throws Exception {
        System.out.println("addProduct");
        Product product = new Product("PD03", "Rice", "Sack");
        product.setSectionId(new FarmSection("SC01"));
        Product expResult = new Product("PD03");
        Product result = instance.addProduct(product);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of updateProduct method, of class ProductMngr.
     */
    @Test
    public void testUpdateProduct() throws Exception {
        System.out.println("updateProduct");
        Product product = new Product("PD03", "Rice", "Sack");
        product.setSectionId(new FarmSection("SC01"));
        String productName = "White Rice";
        product.setProductName(productName);
        Product result = instance.updateProduct(product);
        assertEquals(productName, result.getProductName());
        
    }

    /**
     * Test of deleteProduct method, of class ProductMngr.
     */
    @Test
    public void testDeleteProduct() throws Exception {
        System.out.println("deleteProduct");
        String productId = "PD03";
        instance.deleteProduct(productId);
        
    }

    /**
     * Test of deleteProducts method, of class ProductMngr.
     */
    @Test
    public void testDeleteProducts() throws Exception {
        System.out.println("deleteProducts");
        List<String> productIds = new ArrayList<String>();
        Product product1 = new Product("PD03", "Rice", "Sack");
        product1.setSectionId(new FarmSection("SC01"));
        Product product2 = new Product("PD04", "Maize", "Sack");
        product2.setSectionId(new FarmSection("SC02"));
        instance.addProduct(product1);
        instance.addProduct(product2);
        productIds.add("PD03");
        productIds.add("PD04");
        instance.deleteProducts(productIds);
        
    }

    /**
     * Test of getProduct method, of class ProductMngr.
     */
    @Test
    public void testGetProduct() throws Exception {
        System.out.println("getProduct");
        String productId = "PD01";
        
        Product expResult = new Product(productId);
        Product result = instance.getProduct(productId);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getProducts method, of class ProductMngr.
     */
    @Test
    public void testGetProducts_0args() throws Exception {
        System.out.println("getProducts");
        
        int expResult = 2;
        List result = instance.getProducts();
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getProducts method, of class ProductMngr.
     */
    @Test
    public void testGetProducts_String() throws Exception {
        System.out.println("getProducts");
        String sectionId = "SC01";
        
        int expResult = 1;
        List result = instance.getProducts(sectionId);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getProducts method, of class ProductMngr.
     */
    @Test
    public void testGetProducts_List() throws Exception {
        System.out.println("getProducts");
        List<String> productIds = new ArrayList();
        productIds.add("PD01");
        productIds.add("PD02");
        
        int expResult = 2;
        List result = instance.getProducts(productIds);
        assertEquals(expResult, result.size());
        
    }
}