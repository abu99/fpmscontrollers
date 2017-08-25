/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpms.controllers;

import com.fpms.persistence.entities.Expenses;
import com.fpms.persistence.entities.Stock;
import com.fpms.persistence.entities.StockPK;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author aabello
 */
@Local
public interface StockMngrLocal {
    
    Stock addStock(Stock stock);
    
    Stock updateStock(Stock stock);
    
    void deleteStock(String productId, String sectionId, Date date);
    
    void deleteStocks(List<StockPK> stockPKs);
    
    Stock getStock(String productId, String sectionId, Date date);
    
    List<Stock> getStocks();
    
    List<Stock> getStocks(List<StockPK> stockPKs);
    
    List<Stock> getStocks(String sectionId, Date StartDate);
    
    List<Stock> getStocks(Date from, Date to);
    
    List<Stock> getStocks(String sectionId, Date StartDate, String stockType);
    
    List<Stock> getStocks(int month, int year);
    
    List<Stock> getStocks(int month, int year, String stockType);
    
    List<Stock> getStocks(Date from, Date to , String stockType);
    
    List<Stock> getStocksBySection(String sectionId);
    
    List<Stock> getStockBySection(String sectionId, int month, int year);
    
    List<Stock> getStockBySection(String section, int month, int year, String stockType);
    
    List<Stock> getStockBySection(String sectionId, Date from, Date to);
    
    List<Stock> getStockBySection(String sectionId, Date from, Date to, String stockType);
    
    List<Stock> getStockByProduct(String productId);
    
    List<Stock> getStockByProduct(String sectionId, Date StartDate, String productId);
    
    List<Stock> getStockByProduct(String sectionId, Date StartDate, String productId, String stockType);
    
    List<Stock> getStockByProduct(String productId, int month, int year);
    
    List<Stock> getStockByProduct(String productId, int month, int year, String stockType);
    
    List<Stock> getStockByProduct(String productId, Date from, Date to);
    
    List<Stock> getStockByProduct(String productId, Date from, Date to, String stockType);
    
    List<Stock> getStockByStockType(String stockType);
    
    List<Stock> getStockByProductAndSection(String productId, String sectionId);
    /**
     * Returns all the stock in and out
     * @param seasonId
     * @return 
     */
    List<Stock> getStock();
    /**
     * Returns all the expenses made during a given farming season
     * @param seasonId
     * @return 
     */
    List<Expenses> getExpenses(String seasonId);
    
    double getTotalStocksBySection(String section);
    
    double getTotalStocksBySectionAndDate(String section, Date date);
    
    double getTotalStocksByDate(Date from, Date to);
    
    double getTotalStocksByYear(int month, int year);
    
    double getTotalStocksByStockType(String stockType);
    
    double getStocksByDateAndStockType(Date from, Date to , String stockType);
    
    double getTotalStocksByStockTypeAndYear(int month, int year, String stockType);
    
    double getTotalStocksBySectionAndStockType(String sectionId, String stockType);
    
    double getTotalStocksBySectionDateAndStockType(String sectionId, Date date, String stockType);
    
    double getTotalStocks(List<Stock> stocks);
    
    
}
