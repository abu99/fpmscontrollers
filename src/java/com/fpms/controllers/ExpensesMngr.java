/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpms.controllers;

import com.fpms.persistence.entities.Expenses;
import com.fpms.persistence.entities.FarmingSeason;
import com.fpms.persistence.mngrs.ExpensesEntityMngrLocal;
import com.fpms.persistence.mngrs.FarmSectionEntityMngrLocal;
import com.fpms.persistence.mngrs.FarmingSeasonEntityMngrLocal;
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
public class ExpensesMngr implements ExpensesMngrLocal{
    
    @EJB
    private ExpensesEntityMngrLocal expensesEntityMngr;
    @EJB
    private FarmingSeasonEntityMngrLocal seasonEntityMngr;
    @EJB
    private FarmSectionEntityMngrLocal sectionEntityMngr;

    @Override
    public Expenses addExpense(Expenses expense) {
        return expensesEntityMngr.create(expense);
    }

    @Override
    public Expenses updateExpense(Expenses expense) {
      return expensesEntityMngr.update(expense);
    }

    @Override
    public void deleteExpense(String expenseId) {
        expensesEntityMngr.delete(expenseId);
    
    }

    @Override
    public void deleteExpenses(List<String> expenseIds) {
        for(String expenseId : expenseIds){
            deleteExpense(expenseId);
        }
    }

    @Override
    public Expenses getExpense(String expenseId) {
        return expensesEntityMngr.get(expenseId);
    }

    @Override
    public List<Expenses> getExpenses() {
        return expensesEntityMngr.getAll();
    }
    
    @Override
    public List<Expenses> getExpenses(String sectionId, Date date) {
        FarmingSeason season = seasonEntityMngr.get(sectionId, date);
        Date from = season.getFarmingSeasonPK().getStartDate();
        Date to = season.getEndDate();
        
        return getExpenses(from, to);
        
    }

    @Override
    public List<Expenses> getExpenses(List<String> expenseIds) {
        List<Expenses> expenses = new ArrayList<Expenses>();
        
        for(String expenseId : expenseIds){
        
            Expenses result = expensesEntityMngr.get(expenseId);
            if(result != null){
                expenses.add(result);
            }
        }
        if(expenses.isEmpty()) return null;
        
        return expenses;
    }

    @Override
    public List<Expenses> getExpenses(Date from, Date to) {
        return expensesEntityMngr.getExpensesBetween(from, to);
    }

    @Override
    public List<Expenses> getExpenses(String sectionId, Date date, String typeId) {
        FarmingSeason season = seasonEntityMngr.get(sectionId, date);
        Date from = season.getFarmingSeasonPK().getStartDate();
        Date to = season.getEndDate();
        
        return getExpenses(from, to, typeId);
        
    }

    @Override
    public List<Expenses> getExpenses(int month, int year) {
        Date from = new Date(year, month, 1);
        Date to = new Date(year, month, MaxDay.getMaxDay(month));
        return getExpenses(from, to);
    }

    @Override
    public List<Expenses> getExpenses(int month, int year, String typeId) {
        Date from = new Date(year, month, 1);
        Date to = new Date(year, month, MaxDay.getMaxDay(month));
        return getExpenses(from, to, typeId);
    }

    @Override
    public List<Expenses> getExpenses(Date from, Date to, String typeId) {
        return expensesEntityMngr.getExpensesByExpenseTypeBetween(from, to, typeId);
    }

    @Override
    public List<Expenses> getExpensesBySection(String sectionId) {

        return expensesEntityMngr.getExpensesBySection(sectionId);
    }

    @Override
    public List<Expenses> getExpensesBySection(String sectionId, String typeId) {
        
        return expensesEntityMngr.getExpensesBySection(sectionId, typeId);
    }

    @Override
    public List<Expenses> getExpensesBySection(String sectionId, int month, int year) {
        
        Date from = new Date(year, month, 1);
        Date to = new Date(year, month, MaxDay.getMaxDay(month));
        
        return getExpensesBySection(sectionId, from, to);
    }

    @Override
    public List<Expenses> getExpensesBySection(String sectionId, int month, int year, String typeId) {
        
        Date from = new Date(year, month, 1);
        Date to = new Date(year, month, MaxDay.getMaxDay(month));
        return getExpensesBySection(sectionId, from, to, typeId);
        
    }

    @Override
    public List<Expenses> getExpensesBySection(String sectionId, Date from, Date to) {
        return expensesEntityMngr.getExpensesBySectionAndBetween(sectionId, from, to);
    }

    @Override
    public List<Expenses> getExpensesBySection(String sectionId, Date from, Date to, String typeId) {
        return expensesEntityMngr.getExpensesBySectionExpenseTypeAndBetween(sectionId, from, to, typeId);
    }

    @Override
    public double getTotalExpenses(String sectionId, Date date) {
        
        FarmingSeason season = seasonEntityMngr.get(sectionId, date);
        Date from = season.getFarmingSeasonPK().getStartDate();
        Date to  = season.getEndDate();
        
        return getTotalExpenses(from, to);
    }

    @Override
    public double getTotalExpenses(String sectionId, Date date, String typeId) {
        FarmingSeason season = seasonEntityMngr.get(sectionId, date);
        Date from = season.getFarmingSeasonPK().getStartDate();
        Date to  = season.getEndDate();
        
        return getTotalExpenses(from, to, typeId);
    }

    @Override
    public double getTotalExpenses(int month, int year) {
        Date from = new Date(year, month, 1);
        Date to = new Date(year, month, MaxDay.getMaxDay(month));
        
        return getTotalExpenses(from, to);
    }

    @Override
    public double getTotalExpenses(int month, int year, String typeId) {
        Date from = new Date(year, month, 1);
        Date to = new Date(year, month, MaxDay.getMaxDay(month));
        
        return getTotalExpenses(from, to, typeId);
    }

    @Override
    public double getTotalExpenses(Date from, Date to) {
        List<Expenses> result;
       result = expensesEntityMngr.getExpensesBetween(from, to);
       return getTotalExpenses(result);
    }

    @Override
    public double getTotalExpenses(Date from, Date to, String typeId) {
        List<Expenses> result;
        result = expensesEntityMngr.getExpensesByExpenseTypeBetween(from, to, typeId);
        return getTotalExpenses(result);
    }

    @Override
    public double getTotalExpensesBySection(String sectionId) {
        return getTotalExpenses(getExpensesBySection(sectionId));
    }

    @Override
    public double getTotalExpensesBySection(String sectionId, String typeId) {
        
        
        return getTotalExpenses(getExpensesBySection(sectionId,typeId));
    }

    @Override
    public double getTotalExpensesBySection(String sectionId, int month, int year) {
        
        Date from = new Date(year, month, 1);
        Date to = new Date(year, month, MaxDay.getMaxDay(month));
        
        return getTotalExpensesBySection(sectionId, from, to);
        
    }

    @Override
    public double getTotalExpensesBySection(String sectionId, int month, int year, String typeId) {
        
        Date from = new Date(year, month, 1);
        Date to = new Date(year, month, MaxDay.getMaxDay(month));
        
        return getTotalExpensesBySection(sectionId, from, to, typeId);
    }

    @Override
    public double getTotalExpensesBySection(String sectionId, Date from, Date to) {
        
        List<Expenses> result;
        result = expensesEntityMngr.getExpensesBySection(sectionId, from, to);
        return getTotalExpenses(result);
    }

    @Override
    public double getTotalExpensesBySection(String sectionId, Date from, Date to, String typeId) {
        
       List<Expenses> result;
       result = expensesEntityMngr.getExpensesBySection(sectionId, from, to, typeId);
       return getTotalExpenses(result);
    }

    @Override
    public double getTotalExpenses(List<Expenses> expenses) {
        double totalAmount = 0.0f;
        
        for(Expenses e: expenses){
            totalAmount += e.getAmount();
        }
        
        return totalAmount;
    }

    @Override
    public List<Expenses> getExpensesByExpenseType(String typeId) {
        return expensesEntityMngr.getExpensesByExpenseType(typeId);
    }

    
    
}

    