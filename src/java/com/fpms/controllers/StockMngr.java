/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpms.controllers;

import com.fpms.persistence.entities.Expenses;
import com.fpms.persistence.entities.FarmingSeason;
import com.fpms.persistence.entities.Stock;
import com.fpms.persistence.entities.StockPK;
import com.fpms.persistence.mngrs.ExpensesEntityMngrLocal;
import com.fpms.persistence.mngrs.FarmSectionEntityMngrLocal;
import com.fpms.persistence.mngrs.FarmingSeasonEntityMngrLocal;
import com.fpms.persistence.mngrs.ProductEntityMngrLocal;
import com.fpms.persistence.mngrs.StockEntityMngrLocal;
import com.fpms.util.MaxDay;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author aabello
 */
@Stateless
public class StockMngr implements StockMngrLocal{
    
    @EJB
    private StockEntityMngrLocal stockEntityMngr;
    @EJB
    private FarmingSeasonEntityMngrLocal seasonEntityMngr;
    @EJB
    private FarmSectionEntityMngrLocal sectionEntityMngr;
    @EJB
    private ProductEntityMngrLocal productEntityMngr; 
    @EJB
    private ExpensesEntityMngrLocal expensesEntityMngr;

    @Override
    public Stock addStock(Stock stock) {
        return stockEntityMngr.create(stock);
    }

    @Override
    public Stock updateStock(Stock stock) {
        return stockEntityMngr.update(stock);
    }

    @Override
    public void deleteStock(String productId, String sectionId, Date date) {
        stockEntityMngr.delete(productId, sectionId, date);
    }

    @Override
    public void deleteStocks(List<StockPK> stockPKs) {
        for (StockPK stockPK : stockPKs){
            deleteStock(stockPK.getProductId(),stockPK.getSectionId(),
                    stockPK.getDateCreated());
        }
    }

    @Override
    public Stock getStock(String productId, String sectionId, Date date) {
        return stockEntityMngr.get(productId, sectionId, date);
    }

    @Override
    public List<Stock> getStocks() {
        return stockEntityMngr.getAll();
    }

    @Override
    public List<Stock> getStocks(List<StockPK> stockPKs) {
        List<Stock> stocks = new ArrayList<Stock>();
        
        for(StockPK stockPK : stockPKs){
            
            Stock result = stockEntityMngr.get(stockPK.getProductId(),
                    stockPK.getSectionId(),
                    stockPK.getDateCreated());
            if(result!=null)
            stocks.add(result);
        }
        if(stocks.isEmpty()) return null;
        
        return stocks;
    }

    @Override
    public List<Stock> getStocks(String sectionId, Date date) {
        FarmingSeason season = seasonEntityMngr.get(sectionId, date);
        Date from = season.getFarmingSeasonPK().getStartDate();
        Date to = season.getEndDate();
        
        return getStocks(from, to);
    }

    @Override
    public List<Stock> getStocks(Date from, Date to) {
        return stockEntityMngr.getStocksBetween(from, to);
    }

    @Override
    public List<Stock> getStocks(String sectionId,Date date, String stockType) {
        FarmingSeason season = seasonEntityMngr.get(sectionId, date);
        Date from = season.getFarmingSeasonPK().getStartDate();
        Date to = season.getEndDate();
        
        return getStocks(from, to, stockType);
    }

    @Override
    public List<Stock> getStocks(int month, int year) {
        Date from = new Date(year, month, 1);
        Date to = new Date(year, month, MaxDay.getMaxDay(month));
        
        return getStocks(from, to);
    }

    @Override
    public List<Stock> getStocks(int month, int year, String stockType) {
        Date from = new Date(year, month, 1);
        Date to = new Date(year, month, MaxDay.getMaxDay(month));
        
        return getStocks(from, to, stockType);
    }

    @Override
    public List<Stock> getStocks(Date from, Date to, String stockType) {
        return stockEntityMngr.getStocksBetweenAndStockType(from, to, stockType);
    }

    @Override
    public List<Stock> getStockBySection(String sectionId, int month, int year) {
        
        Date from = new Date(year, month, 1);
        Date to = new Date(year, month, MaxDay.getMaxDay(month));
        
        return getStockBySection(sectionId, from, to);
        
    }

    @Override
    public List<Stock> getStockBySection(String section, int month, int year, String stockType) {
        
        Date from = new Date(year, month, 1);
        Date to = new Date(year, month, MaxDay.getMaxDay(month));
        
        return getStockBySection(section, from, to, stockType);
    }

    @Override
    public List<Stock> getStockBySection(String sectionId, Date from, Date to) {
        return stockEntityMngr.getStocksBySectionBetween(sectionId, from, to);
    }

    @Override
    public List<Stock> getStockBySection(String sectionId, Date from, Date to, String stockType) {
        return stockEntityMngr.getStockBySectionAndStockTypeBetween(sectionId,from,to,stockType);
    }

    @Override
    public List<Stock> getStockByProduct(String sectionId, Date date, String productId) {
        FarmingSeason season = seasonEntityMngr.get(sectionId, date);
        Date from  = season.getFarmingSeasonPK().getStartDate();
        Date to = season.getEndDate();
        
        return getStockByProduct(productId, from, to);
        
    }

    @Override
    public List<Stock> getStockByProduct(String sectionId, Date date, String productId, String stockType) {
        FarmingSeason season = seasonEntityMngr.get(sectionId, date);
        Date from  = season.getFarmingSeasonPK().getStartDate();
        Date to = season.getEndDate();
        
        return getStockByProduct(productId, from, to, stockType);
    }

    @Override
    public List<Stock> getStockByProduct(String productId, int month, int year) {
        Date from = new Date(year, month, 1);
        Date to = new Date(year, month, MaxDay.getMaxDay(month));
        
        return getStockByProduct(productId, from, to);
    }

    @Override
    public List<Stock> getStockByProduct(String productId, int month, int year, String stockType) {
        Date from = new Date(year, month, 1);
        Date to = new Date(year, month, MaxDay.getMaxDay(month));
        
        return getStockByProduct(productId, from, to, stockType);
    }

    @Override
    public List<Stock> getStockByProduct(String productId, Date from, Date to) {
        return stockEntityMngr.getStocksByproductBetween(productId, from, to);
    }

    @Override
    public List<Stock> getStockByProduct(String productId, Date from, Date to, String stockType) {
        return stockEntityMngr.getStocksByProductAndStockTypeBetween(productId,from, to, stockType);
    }

    @Override
    public List<Stock> getStocksBySection(String sectionId) {
        return stockEntityMngr.getStocksBySection(sectionId);
    }

    @Override
    public List<Stock> getStockByProduct(String productId) {
        return stockEntityMngr.getStockByProduct(productId);
    }

    @Override
    public List<Stock> getStockByStockType(String stockType) {
        return stockEntityMngr.getStocksByStockType(stockType);
    }
    
    @Override
    public List<Stock> getStockByProductAndSection(String productId, String sectionId){
        return stockEntityMngr.getStocksByProductAndSection(productId, sectionId);
    }

    @Override
    public double getTotalStocksBySection(String section) {
        List<Stock> stocks = stockEntityMngr.getStocksBySection(section);
        
        return getTotalStocks(stocks);
    }

    @Override
    public double getTotalStocksBySectionAndDate(String section, Date date) {
        List<Stock> stocks = stockEntityMngr.getStocksBySectionAndDate(section, date);
        
        return getTotalStocks(stocks);
    }

    @Override
    public double getTotalStocksByDate(Date from, Date to) {
        List<Stock> stocks = stockEntityMngr.getStocksBetween(from, to);
        
        return getTotalStocks(stocks);
    }

    @Override
    public double getTotalStocksByYear(int month, int year) {
        Date from = new Date(year, month, 1);
        Date to = new Date(year, month, MaxDay.getMaxDay(month));
        
        return getTotalStocksByDate(from, to);
    }

    @Override
    public double getTotalStocksByStockType(String stockType) {
        List<Stock> stocks = stockEntityMngr.getStocksByStockType(stockType);
        
        return getTotalStocks(stocks);
    }

    @Override
    public double getTotalStocksBySectionAndStockType(String section, String stockType) {
        List<Stock> stocks = stockEntityMngr
                .getStocKsBySectionAndStockType(section, stockType);
        return getTotalStocks(stocks);
    }

    @Override
    public double getTotalStocks(List<Stock> stocks) {
        double totalAmount = 0.0f;
        
        for(Stock s: stocks){
        
            totalAmount += s.getAmount();
        }
        
        return totalAmount;
    }

    @Override
    public double getStocksByDateAndStockType(Date from, Date to, String stockType) {
        List<Stock> stocks = stockEntityMngr.getStocksBetweenAndStockType(from, to, stockType);
        
        return getTotalStocks(stocks);
    }

    @Override
    public double getTotalStocksByStockTypeAndYear(int month, int year, String stockType) {
        Date from = new Date(year, month, 1);
        Date to = new Date(year, month, MaxDay.getMaxDay(month));
        
        return getStocksByDateAndStockType(from, to, stockType);
    }

    @Override
    public double getTotalStocksBySectionDateAndStockType(String sectionId,
    Date date, String stockType) {
        return getTotalStocks(getStocks(sectionId, date, stockType));
    }

    @Override
    public List<Stock> getStock() {
        return stockEntityMngr.getAll();
    }

    @Override
    public List<Expenses> getExpenses(String seasonId) {
        return expensesEntityMngr.getAll();
    }
}
