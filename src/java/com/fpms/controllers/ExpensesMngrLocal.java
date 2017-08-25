/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpms.controllers;

import com.fpms.persistence.entities.Expenses;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author aabello
 */
@Local
public interface ExpensesMngrLocal {
    
    Expenses addExpense(Expenses expense);
    
    Expenses updateExpense(Expenses expense);
    
    void deleteExpense(String expenseId);
    
    void deleteExpenses(List<String> expenseIds);
    
    Expenses getExpense(String expenseId);
    
    List<Expenses> getExpenses();
    
    List<Expenses> getExpenses(String sectionId, Date date);
    
    List<Expenses> getExpenses(List<String> expenseIds);
    
    List<Expenses> getExpenses(Date from, Date to);
    
    List<Expenses> getExpenses(String sectionId, Date date, String typeId);
    
    List<Expenses> getExpenses(int month, int year);
    
    List<Expenses> getExpenses(int month, int year, String typeId);
    
    List<Expenses> getExpenses(Date from, Date to, String typeId);
    
    List<Expenses> getExpensesBySection(String sectionId);
    
    List<Expenses> getExpensesBySection(String sectionId, 
            String typeId);
    
    List<Expenses> getExpensesBySection(String sectionId, int month, int year);
    
    List<Expenses> getExpensesBySection(String sectionId, int month, int year, 
            String typeId);
    
    List<Expenses> getExpensesBySection(String sectionId, Date from, Date to);
    
    List<Expenses> getExpensesBySection(String sectionId, Date from, 
            Date to, String typeId);
    
    List<Expenses> getExpensesByExpenseType(String typeId);
    
    double getTotalExpenses(String sectionId, Date date);
    
    double getTotalExpenses(String sectionId, Date date, String typeId);
    
    double getTotalExpenses(int month, int year);
    
    double getTotalExpenses(int month, int year, String typeId);
    
    double getTotalExpenses(Date from, Date to);
    
    double getTotalExpenses(Date from, Date to, String typeId);
    
    double getTotalExpensesBySection(String sectionId);
    
    double getTotalExpensesBySection(String sectionId, 
            String typeId);
    
    double getTotalExpensesBySection(String sectionId, int month, int year);
    
    double getTotalExpensesBySection(String sectionId, int month, int year, 
            String typeId);
    
    double getTotalExpensesBySection(String sectionId, Date from, Date to);
    
    double getTotalExpensesBySection(String sectionId, Date from, 
            Date to, String typeId);
    
    double getTotalExpenses(List<Expenses> expenses);
    
    
    
    
}
