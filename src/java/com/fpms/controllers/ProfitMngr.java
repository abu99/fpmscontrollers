/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpms.controllers;

import com.fpms.persistence.entities.Expenses;
import com.fpms.persistence.entities.Stock;
import com.makkah.opd.reports.mngrs.ProfitReportMngr;
import com.makkah.opd.reports.model.ProfitReport;
import com.makkah.opd.reports.model.ProfitReportBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;

/**
 *
 * @author aabello
 */
@Stateless
public class ProfitMngr implements ProfitMngrLocal{
    
    @EJB
    private StockMngrLocal stockMngr;
    @EJB
    private ExpensesMngrLocal expensesMngr;
    
    @Override
    public double getTotalProfitBySection(String sectionId) {
        
        double totalProfit = 0.0;
        
        totalProfit += (stockMngr.getTotalStocksBySectionAndStockType(sectionId, "OUT")
                - stockMngr.getTotalStocksBySectionAndStockType(sectionId, "IN")) 
                - expensesMngr.getTotalExpensesBySection(sectionId);
        
        return totalProfit;
    }

    @Override
    public double getTotalProfitBySeason(String sectionId, Date date) {
        double totalProfit = 0.0f;
        
        totalProfit += (stockMngr.getTotalStocksBySectionDateAndStockType(sectionId, date, "OUT") 
                - stockMngr.getTotalStocksBySectionDateAndStockType(sectionId,date, "IN")) 
                - expensesMngr.getTotalExpenses(sectionId, date);
        
        return totalProfit;
    }

    @Override
    public double getTotalProfitByDate(Date from, Date to) {
        double totalProfit = 0.0f;
        
        totalProfit += (stockMngr.getStocksByDateAndStockType(from, to, "OUT") -
                stockMngr.getStocksByDateAndStockType(from, to, "IN")) -
                expensesMngr.getTotalExpenses(from, to);
        
        return totalProfit;
    }

    @Override
    public double getTotalProfitByYear(int Month, int Year) {
        double totalProfit = 0.0f;

        totalProfit += (stockMngr.getTotalStocksByStockTypeAndYear(Month, 
                Year, "OUT") - stockMngr.getTotalStocksByStockTypeAndYear
                (Month, Year, "IN"))- expensesMngr.getTotalExpenses(Month, Year);
        
        return totalProfit;
    }

    @Override
    public double getTotalProfit() {
        double totalProfit = 0.0f;
        List<Expenses> expenses = expensesMngr.getExpenses();
       
        
        totalProfit += (stockMngr.getTotalStocksByStockType("OUT") - 
                stockMngr.getTotalStocksByStockType("IN")) - 
                expensesMngr.getTotalExpenses(expenses);
    
         return totalProfit;
    }

    @Override
    public byte[] getProfitReport() {
        ProfitReport report = new ProfitReport();
        
        report.setData(getProfitData(report));
        
        ProfitReportMngr mngr = new ProfitReportMngr(report);
        
        try {
            return mngr.getPdf();
        } catch (Exception e) {
            throw new EJBException(e);
        }

    }

    private List<ProfitReportBean> getProfitData(ProfitReport report) {
        List<ProfitReportBean> data = new ArrayList<ProfitReportBean>();
        double totalExpended = 0;
        double totalGain = 0;
        
        int i = 1;
        
        for (Expenses exp : expensesMngr.getExpenses()) {
            ProfitReportBean profit = new ProfitReportBean();
            profit.setId(""+ i);
            profit.setAmount(exp.getAmount());
            profit.setIncome("Expenses");
            profit.setType(exp.getTypeId().getTypeName());
            profit.setSection(exp.getSectionId().getSectionName());
            
            data.add(profit);
            
            totalExpended = totalExpended + exp.getAmount();
            i++;
        }
        for (Stock stock : stockMngr.getStock()) {
            ProfitReportBean profitDto = new ProfitReportBean();
            profitDto.setId(""+i);
            profitDto.setAmount(stock.getAmount());
            profitDto.setIncome(stock.getStockType().equalsIgnoreCase("IN") ? 
                    "Income" : "Expenses");
            
            if (stock.getStockType().equals("IN")) {
                totalExpended += totalExpended;
            } else {
                totalGain = totalGain + stock.getAmount();
            }
            data.add(profitDto);
            i++;
        }
        report.setExpenseTotal(totalExpended);
        report.setIncomeTotal(totalGain);
        report.setProfitTotal(totalGain - totalGain);
        
        return data;
    }
}
