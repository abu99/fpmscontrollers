/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpms.controllers;

import com.fpms.controllers.test.common.ServiceLocator;
import com.fpms.persistence.entities.FarmSection;
import com.fpms.persistence.entities.FarmingSeasonPK;
import com.fpms.persistence.entities.Product;
import com.fpms.persistence.entities.Stock;
import com.fpms.persistence.entities.StockPK;
import java.util.ArrayList;
import java.util.Date;
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
public class StockMngrTest {
    
    @EJB
    private static StockMngrLocal instance;
    
    public StockMngrTest() {
    }
    
    @BeforeClass
    public static void setUpClass()  throws Exception{
        instance = new ServiceLocator().lookup(StockMngrLocal.class);
        
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
     * Test of addStock method, of class StockMngr.
     */
    @Test
    public void testAddStock() throws Exception {
        System.out.println("addStock");
        StockPK stockPK = new StockPK("PD01", "SC01", new Date(2013-1900, 8, 5));
        Stock stock = new Stock(stockPK,10, 10000, "IN", "white");
        
        stock.setProduct(new Product("PD01"));
        stock.setFarmSection(new FarmSection("SC01"));
        
        Stock expResult = new Stock(stockPK);
        Stock result = instance.addStock(stock);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of updateStock method, of class StockMngr.
     */
    @Test
    public void testUpdateStock() throws Exception {
        System.out.println("updateStock");
        
        StockPK stockPK = new StockPK("PD01", "SC01", new Date(2013-1900, 8, 5));
        Stock stock = new Stock(stockPK,10, 10000, "IN", "white");
        
        stock.setProduct(new Product("PD01"));
        stock.setFarmSection(new FarmSection("SC01"));
        
        double amount = 12000;
        stock.setAmount(amount);
        Stock result = instance.updateStock(stock);
        assertEquals(amount, result.getAmount(), 0.0);
       
    }

    /**
     * Test of deleteStock method, of class StockMngr.
     */
    @Test
    public void testDeleteStock() throws Exception {
        System.out.println("deleteStock");
        StockPK stockPK = new StockPK("PD01", "SC01", new Date(2013-1900, 8, 5));
        
        instance.deleteStock("PD01", "SC01", new Date(2013-1900, 8, 5));
        
    }

    /**
     * Test of deleteStocks method, of class StockMngr.
     */
    @Test
    public void testDeleteStocks() throws Exception {
        System.out.println("deleteStocks");
        StockPK stockPK1 = new StockPK("PD01", "SC01", new Date(2013-1900, 7, 5));
        Stock stock1 = new Stock(stockPK1,10, 10000, "IN", "white");
        stock1.setProduct(new Product("PD01"));
        stock1.setFarmSection(new FarmSection("SC01"));
        instance.addStock(stock1);
        
        StockPK stockPK2 = new StockPK("PD02", "SC02", new Date(2013-1900, 8, 5));
        Stock stock2 = new Stock(stockPK2,100, 100000, "OUT", "blue");
        stock2.setProduct(new Product("PD02"));
        stock2.setFarmSection(new FarmSection("SC02"));
        instance.addStock(stock2);
        
        List<StockPK> stockPKs = new ArrayList<StockPK>();
        stockPKs.add(stockPK1);
        stockPKs.add(stockPK2);
        
        instance.deleteStocks(stockPKs);
        
    }

    /**
     * Test of getStock method, of class StockMngr.
     */
    @Test
    public void testGetStock() throws Exception {
        System.out.println("getStock");
        StockPK stockPK = new StockPK("PD01", "SC01", new Date(2013-1900, 7, 14));
        
        Stock expResult = new Stock(stockPK);
        Stock result = instance.getStock("PD01", "SC01", new Date(2013-1900, 7, 14));
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getStocks method, of class StockMngr.
     */
    @Test
    public void testGetStocks_0args() throws Exception {
        System.out.println("getStocks");
        
        int expResult = 2;
        List result = instance.getStocks();
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getStocks method, of class StockMngr.
     */
    @Test
    public void testGetStocks_List() throws Exception {
        System.out.println("getStocks");
        List<StockPK> stockPKs = new ArrayList();
        stockPKs.add(new StockPK("PD01", "SC01", new Date(2013-1900, 7, 14)));
        stockPKs.add(new StockPK("PD02", "SC02", new Date(2013-1900, 7, 15)));
        
        int expResult = 2;
        List result = instance.getStocks(stockPKs);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getStocks method, of class StockMngr.
     */
    @Test
    public void testGetStocks_FarmingSeasonPK() throws Exception {
        System.out.println("getStocks");
        FarmingSeasonPK seasonPK = new FarmingSeasonPK("SC01", new Date(2013-1900,0,1));
        
        int expResult = 2;
        List result = instance.getStocks("SC01", new Date(2013-1900,0,1));
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getStocks method, of class StockMngr.
     */
    @Test
    public void testGetStocks_Date_Date() throws Exception {
        System.out.println("getStocks");
        Date from = new Date(2013-1900, 4, 1);
        Date to = new Date(2013-1900, 9, 1);
        
        int expResult = 2;
        List result = instance.getStocks(from, to);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getStocks method, of class StockMngr.
     */
    @Test
    public void testGetStocks_FarmingSeasonPK_String() throws Exception {
        System.out.println("getStocks");
        FarmingSeasonPK seasonPK = new FarmingSeasonPK("SC01", new Date(2013-1900,0,1));
        String stockType = "IN";
        
        int expResult = 1;
        List result = instance.getStocks("SC01", new Date(2013-1900,0,1), stockType);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getStocks method, of class StockMngr.
     */
    @Test
    public void testGetStocks_int_int() throws Exception {
        System.out.println("getStocks");
        int month = 7;
        int year = 2013-1900;
        
        int expResult = 2;
        List result = instance.getStocks(month, year);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getStocks method, of class StockMngr.
     */
    @Test
    public void testGetStocks_3args_1() throws Exception {
        System.out.println("getStocks");
        int month = 7;
        int year = 2013-1900;
        String stockType = "OUT";
        
        int expResult = 1;
        List result = instance.getStocks(month, year, stockType);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getStocks method, of class StockMngr.
     */
    @Test
    public void testGetStocks_3args_2() throws Exception {
        System.out.println("getStocks");
        Date from = new Date(2013-1900, 3, 1);
        Date to = new Date(2013-1900, 9, 1);
        String stockType = "OUT";
        
        int expResult = 1;
        List result = instance.getStocks(from, to, stockType);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getStockBySection method, of class StockMngr.
     */
    @Test
    public void testGetStockBySection_3args_1() throws Exception {
        System.out.println("getStockBySection");
        String sectionId = "SC01";
        int month = 7;
        int year = 2013-1900;
        
        int expResult = 1;
        List result = instance.getStockBySection(sectionId, month, year);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getStockBySection method, of class StockMngr.
     */
    @Test
    public void testGetStockBySection_4args_1() throws Exception {
        System.out.println("getStockBySection");
        String section = "SC02";
        int month = 7;
        int year = 2013-1900;
        String stockType = "OUT";
        
        int expResult = 1;
        List result = instance.getStockBySection(section, month, year, stockType);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getStockBySection method, of class StockMngr.
     */
    @Test
    public void testGetStockBySection_3args_2() throws Exception {
        System.out.println("getStockBySection");
        String sectionId = "SC02";
        Date from = new Date(2013-1900, 7, 1);
        Date to = new Date(2013-1900, 7, 30);
        
        int expResult = 1;
        List result = instance.getStockBySection(sectionId, from, to);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getStockBySection method, of class StockMngr.
     */
    @Test
    public void testGetStockBySection_4args_2() throws Exception {
        System.out.println("getStockBySection");
        String sectionId = "SC01";
        Date from = new Date(2013-1900, 6, 1);
        Date to = new Date(2013-1900, 8, 1);
        String stockType = "IN";
        
        int expResult = 1;
        List result = instance.getStockBySection(sectionId, from, to, stockType);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getStockByProduct method, of class StockMngr.
     */
    @Test
    public void testGetStockByProduct_FarmingSeasonPK_String() throws Exception {
        System.out.println("getStockByProduct");
        FarmingSeasonPK farmingSeasonPK = new FarmingSeasonPK("SC01", new Date(2013-1900,0,1));
        String productId = "PD01";
        
        int expResult = 1;
        List result = instance.getStockByProduct("SC01", new Date(2013-1900,0,1), productId);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getStockByProduct method, of class StockMngr.
     */
    @Test
    public void testGetStockByProduct_3args_1() throws Exception {
        System.out.println("getStockByProduct");
        FarmingSeasonPK farmingSeasonPK = new FarmingSeasonPK("SC01", new Date(2013-1900,0,1));
        String productId = "PD01";
        String stockType = "IN";
       
        int expResult = 1;
        List result = instance.getStockByProduct("SC01", new Date(2013-1900,0,1), productId, stockType);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getStockByProduct method, of class StockMngr.
     */
    @Test
    public void testGetStockByProduct_3args_2() throws Exception {
        System.out.println("getStockByProduct");
        String productId = "PD01";
        int month = 7;
        int year = 2013-1900;
        
        int expResult = 1;
        List result = instance.getStockByProduct(productId, month, year);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getStockByProduct method, of class StockMngr.
     */
    @Test
    public void testGetStockByProduct_4args_1() throws Exception {
        System.out.println("getStockByProduct");
        String productId = "PD02";
        int month = 7;
        int year = 2013-1900;
        String stockType = "OUT";
        
        int expResult = 1;
        List result = instance.getStockByProduct(productId, month, year, stockType);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getStockByProduct method, of class StockMngr.
     */
    @Test
    public void testGetStockByProduct_3args_3() throws Exception {
        System.out.println("getStockByProduct");
        String productId = "PD02";
        Date from = new Date(2013-1900, 7, 1);
        Date to = new Date(2013-1900, 7, 30);
        
        int expResult = 1;
        List result = instance.getStockByProduct(productId, from, to);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getStockByProduct method, of class StockMngr.
     */
    @Test
    public void testGetStockByProduct_4args_2() throws Exception {
        System.out.println("getStockByProduct");
        String productId = "PD01";
        Date from = new Date(2013-1900, 7, 1);
        Date to = new Date(2013-1900, 7, 30);
        String stockType = "IN";
        
        int expResult = 1;
        List result = instance.getStockByProduct(productId, from, to, stockType);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getStocks method, of class StockMngr.
     */
    @Test
    public void testGetStocks_String_Date() throws Exception {
        System.out.println("getStocks");
        String sectionId = "SC01";
        Date date = new Date(2013-1900, 7, 14);
        
        int expResult = 1;
        List result = instance.getStocks(sectionId, date);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getStocks method, of class StockMngr.
     */
    @Test
    public void testGetStocks_3args_3() throws Exception {
        System.out.println("getStocks");
        Date from = new Date(2013-1900, 7, 1);
        Date to = new Date(2013-1900, 7, 30);
        String stockType = "IN";
        
        int expResult = 1;
        List result = instance.getStocks(from, to, stockType);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getStockByProduct method, of class StockMngr.
     */
    @Test
    public void testGetStockByProduct_4args_3() throws Exception {
        System.out.println("getStockByProduct");
        String productId = "PD01";
        Date from = new Date(2013-1900, 7, 1);
        Date to = new Date(2013-1900, 7, 30);
        String stockType = "IN";
        
        int expResult = 1;
        List result = instance.getStockByProduct(productId, from, to, stockType);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getStocksBySection method, of class StockMngr.
     */
    @Test
    public void testGetStocksBySection() throws Exception {
        System.out.println("getStocksBySection");
        String sectionId = "SC01";
        
        int expResult = 1;
        List result = instance.getStocksBySection(sectionId);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getStockByProduct method, of class StockMngr.
     */
    @Test
    public void testGetStockByProduct_String() throws Exception {
        System.out.println("getStockByProduct");
        String productId = "PD01";
        
        int expResult = 1;
        List result = instance.getStockByProduct(productId);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getStockByStockType method, of class StockMngr.
     */
    @Test
    public void testGetStockByStockType() throws Exception {
        System.out.println("getStockByStockType");
        String stockType = "OUT";
        
        int expResult = 1;
        List result = instance.getStockByStockType(stockType);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getStockByProductAndSection method, of class StockMngr.
     */
    @Test
    public void testGetStockByProductAndSection() throws Exception {
        System.out.println("getStockByProductAndSection");
        String productId = "PD01";
        String sectionId = "SC01";
        
        int expResult = 1;
        List result = instance.getStockByProductAndSection(productId, sectionId);
        assertEquals(expResult, result.size());
        
    }
}