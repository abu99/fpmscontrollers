/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpms.controllers;

import com.fpms.controllers.test.common.ServiceLocator;
import com.fpms.persistence.entities.ExpenseType;
import com.fpms.persistence.entities.Expenses;
import com.fpms.persistence.entities.FarmSection;
import com.fpms.persistence.entities.FarmingSeasonPK;
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
public class ExpensesMngrTest {
    
    @EJB
    private static ExpensesMngrLocal instance;
    
    public ExpensesMngrTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception{
        instance  = new ServiceLocator().lookup(ExpensesMngrLocal.class);
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
     * Test of addExpense method, of class ExpenseMngr.
     */
    @Test
    public void testAddExpense() throws Exception {
        System.out.println("addExpense");
        Expenses expense = new Expenses("EP01", 2000, new Date(2013-1900, 9, 10),
                "Fuel for generator");
        expense.setSectionId(new FarmSection("SC01"));
        expense.setTypeId(new ExpenseType("ET01"));
        
        
        Expenses expResult = new Expenses("EP01");
        Expenses result = instance.addExpense(expense);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of updateExpense method, of class ExpenseMngr.
     */
    @Test
    public void testUpdateExpense() throws Exception {
        System.out.println("updateExpense");
        Expenses expense = new Expenses("EP01", 2000, new Date(2013-1900, 9, 10),
                "Fuel for generator");
        expense.setSectionId(new FarmSection("SC01"));
        expense.setTypeId(new ExpenseType("ET01"));
        String remarks = "Fuel for tractor";
        expense.setRemarks(remarks);
        Expenses result = instance.updateExpense(expense);
        assertEquals(remarks, result.getRemarks());
        
    }

    /**
     * Test of deleteExpense method, of class ExpenseMngr.
     */
    @Test
    public void testDeleteExpense() throws Exception {
        System.out.println("deleteExpense");
        String expenseId = "EP01";
        
        
        instance.deleteExpense(expenseId);
        
    }

    /**
     * Test of deleteExpenses method, of class ExpenseMngr.
     */
    @Test
    public void testDeleteExpenses() throws Exception {
        System.out.println("deleteExpenses");
        Expenses expense1 = new Expenses("EP01", 2000, new Date(2013-1900, 9, 10),
                "Fuel for generator");
        expense1.setSectionId(new FarmSection("SC01"));
        expense1.setTypeId(new ExpenseType("ET01"));
        instance.addExpense(expense1);
        Expenses expense2 = new Expenses("EP02", 3000, new Date(2013-1900, 9, 11),
                "chicken food");
        expense2.setSectionId(new FarmSection("SC02"));
        expense2.setTypeId(new ExpenseType("ET02"));
        instance.addExpense(expense2);
        List<String> expenseIds = new ArrayList<String>();
        expenseIds.add("EP01");
        expenseIds.add("EP02");
        instance.deleteExpenses(expenseIds);
        
    }

    /**
     * Test of getExpense method, of class ExpenseMngr.
     */
    @Test
    public void testGetExpense() throws Exception {
        System.out.println("getExpense");
        String expenseId = "EP03";
        
        Expenses expResult = new Expenses(expenseId);
        Expenses result = instance.getExpense(expenseId);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getExpenses method, of class ExpenseMngr.
     */
    @Test
    public void testGetExpenses_FarmingSeasonPK() throws Exception {
        System.out.println("getExpenses");
        FarmingSeasonPK seasonPK = new FarmingSeasonPK("SC01", new Date(2013-1900, 0, 1));
        
        
        int expResult = 2;
        List result = instance.getExpenses("SC01", new Date(2013-1900, 0, 1));
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getExpenses method, of class ExpenseMngr.
     */
    @Test
    public void testGetExpenses_List() throws Exception {
        System.out.println("getExpenses");
        List<String> expenseIds = new ArrayList<String>();
        
        expenseIds.add("EP03");
        expenseIds.add("EP04");
        int expResult = 2;
        List result = instance.getExpenses(expenseIds);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getExpenses method, of class ExpenseMngr.
     */
    @Test
    public void testGetExpenses_Date_Date() throws Exception {
        System.out.println("getExpenses");
        Date from = new Date(2013-1900, 7, 1);
        Date to = new Date(2013-1900, 10, 1);
        
        int expResult = 2;
        List result = instance.getExpenses(from, to);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getExpenses method, of class ExpenseMngr.
     */
    @Test
    public void testGetExpenses_FarmingSeasonPK_String() throws Exception {
        System.out.println("getExpenses");
        FarmingSeasonPK seasonPK = new FarmingSeasonPK("SC01", new Date(2013-1900,0,1));
        String typeId = "ET01";
        
        int expResult = 1;
        List result = instance.getExpenses("SC01", new Date(2013-1900,0,1), typeId);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getExpenses method, of class ExpenseMngr.
     */
    @Test
    public void testGetExpenses_int_int() throws Exception {
        System.out.println("getExpenses");
        int month = 8;
        int year = 2013-1900;
        
        int expResult = 2;
        List result = instance.getExpenses(month, year);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getExpenses method, of class ExpenseMngr.
     */
    @Test
    public void testGetExpenses_3args_1() throws Exception {
        System.out.println("getExpenses");
        int month = 8;
        int year = 2013-1900;
        String typeId = "ET01";
        
        int expResult = 1;
        List result = instance.getExpenses(month, year, typeId);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getExpenses method, of class ExpenseMngr.
     */
    @Test
    public void testGetExpenses_3args_2() throws Exception {
        System.out.println("getExpenses");
        Date from = new Date(2013-1900, 8, 1);
        Date to = new Date(2013-1900, 10, 1);
        String typeId = "ET02";
        
        int expResult = 1;
        List result = instance.getExpenses(from, to, typeId);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getExpensesBySection method, of class ExpenseMngr.
     */
    @Test
    public void testGetExpensesBySection_FarmingSeasonPK_String() throws Exception {
        System.out.println("getExpensesBySection");
        
        String sectionId = "SC02";
        
        int expResult = 1;
        List result = instance.getExpensesBySection(sectionId);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getExpensesBySection method, of class ExpenseMngr.
     */
    @Test
    public void testGetExpensesBySection_3args_1() throws Exception {
        System.out.println("getExpensesBySection");
        
        String sectionId = "SC01";
        String typeId = "ET01";
        
        int expResult = 1;
        List result = instance.getExpensesBySection(sectionId, typeId);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getExpensesBySection method, of class ExpenseMngr.
     */
    @Test
    public void testGetExpensesBySection_3args_2() throws Exception {
        System.out.println("getExpensesBySection");
        String sectionId = "SC02";
        int month = 8;
        int year = 2013-1900;
        
        int expResult = 1;
        List result = instance.getExpensesBySection(sectionId, month, year);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getExpensesBySection method, of class ExpenseMngr.
     */
    @Test
    public void testGetExpensesBySection_4args_1() throws Exception {
        System.out.println("getExpensesBySection");
        String sectionId = "SC01";
        int month = 8;
        int year = 2013-1900;
        String typeId = "ET01";
        
        int expResult = 1;
        List result = instance.getExpensesBySection(sectionId, month, year, typeId);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getExpensesBySection method, of class ExpenseMngr.
     */
    @Test
    public void testGetExpensesBySection_3args_3() throws Exception {
        System.out.println("getExpensesBySection");
        String sectionId = "SC02";
        Date from = new Date(2013-1900, 8, 1);
        Date to = new Date(2013-1900, 10, 1);
        
        int expResult = 1;
        List result = instance.getExpensesBySection(sectionId, from, to);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getExpensesBySection method, of class ExpenseMngr.
     */
    @Test
    public void testGetExpensesBySection_4args_2() throws Exception {
        System.out.println("getExpensesBySection");
        String sectionId = "SC01";
        Date from = new Date(2013-1900, 8, 1);
        Date to = new Date(2013-1900, 10, 1);
        String typeId = "ET01";
        
        int expResult = 1;
        List result = instance.getExpensesBySection(sectionId, from, to, typeId);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getTotalExpenses method, of class ExpenseMngr.
     */
    @Test
    public void testGetTotalExpenses_FarmingSeasonPK() throws Exception {
        System.out.println("getTotalExpenses");
        FarmingSeasonPK seasonPK = new FarmingSeasonPK("SC01", new Date(2013-1900, 0, 1));
        
        double expResult = 15000.0;
        double result = instance.getTotalExpenses("SC01", new Date(2013-1900, 0, 1));
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of getTotalExpenses method, of class ExpenseMngr.
     */
    @Test
    public void testGetTotalExpenses_FarmingSeasonPK_String() throws Exception {
        System.out.println("getTotalExpenses");
        FarmingSeasonPK seasonPK = new FarmingSeasonPK("SC01", new Date(2013-1900, 0, 1));
        String typeId = "ET01";
        
        double expResult = 5000.0;
        double result = instance.getTotalExpenses("SC01", new Date(2013-1900, 0, 1), typeId);
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of getTotalExpenses method, of class ExpenseMngr.
     */
    @Test
    public void testGetTotalExpenses_int_int() throws Exception {
        System.out.println("getTotalExpenses");
        int month = 8;
        int year = 2013-1900;
        
        double expResult = 15000.0;
        double result = instance.getTotalExpenses(month, year);
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of getTotalExpenses method, of class ExpenseMngr.
     */
    @Test
    public void testGetTotalExpenses_3args_1() throws Exception {
        System.out.println("getTotalExpenses");
        int month = 8;
        int year = 2013-1900;
        String typeId = "ET02";
        
        double expResult = 10000.0;
        double result = instance.getTotalExpenses(month, year, typeId);
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of getTotalExpenses method, of class ExpenseMngr.
     */
    @Test
    public void testGetTotalExpenses_Date_Date() throws Exception {
        System.out.println("getTotalExpenses");
        Date from = new Date(2013-1900, 8, 1);
        Date to = new Date(2013-1900, 8, 30);
        
        double expResult = 15000.0;
        double result = instance.getTotalExpenses(from, to);
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of getTotalExpenses method, of class ExpenseMngr.
     */
    @Test
    public void testGetTotalExpenses_3args_2() throws Exception {
        System.out.println("getTotalExpenses");
        Date from = new Date(2013-1900, 8, 1);
        Date to = new Date(2013-1900, 8, 30);
        String typeId = "ET01";
        
        double expResult = 5000.0;
        double result = instance.getTotalExpenses(from, to, typeId);
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of getTotalExpensesBySection method, of class ExpenseMngr.
     */
    @Test
    public void testGetTotalExpensesBySection_FarmingSeasonPK_String() throws Exception {
        System.out.println("getTotalExpensesBySection");
        FarmingSeasonPK seasonPK = new FarmingSeasonPK("SC01", new Date(2013-1900, 9, 12));
        String sectionId = "SC01";
        
        double expResult = 5000.0;
        double result = instance.getTotalExpensesBySection( sectionId);
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of getTotalExpensesBySection method, of class ExpenseMngr.
     */
    @Test
    public void testGetTotalExpensesBySection_3args_1() throws Exception {
        System.out.println("getTotalExpensesBySection");
        FarmingSeasonPK seasonPK = new FarmingSeasonPK("SC01", new Date(2013-1900, 8, 12));
        String sectionId = "SC01";
        String typeId = "ET01";
        
        double expResult = 5000.0;
        double result = instance.getTotalExpensesBySection(sectionId, typeId);
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of getTotalExpensesBySection method, of class ExpenseMngr.
     */
    @Test
    public void testGetTotalExpensesBySection_3args_2() throws Exception {
        System.out.println("getTotalExpensesBySection");
        String sectionId = "SC01";
        int month = 8;
        int year = 2013-1900;
        
        double expResult = 5000.0;
        double result = instance.getTotalExpensesBySection(sectionId, month, year);
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of getTotalExpensesBySection method, of class ExpenseMngr.
     */
    @Test
    public void testGetTotalExpensesBySection_4args_1() throws Exception {
        System.out.println("getTotalExpensesBySection");
        String sectionId = "SC01";
        int month = 8;
        int year = 2013-1900;
        String typeId = "ET01";
        
        double expResult = 5000.0;
        double result = instance.getTotalExpensesBySection(sectionId, month, year, typeId);
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of getTotalExpensesBySection method, of class ExpenseMngr.
     */
    @Test
    public void testGetTotalExpensesBySection_3args_3() throws Exception {
        System.out.println("getTotalExpensesBySection");
        String sectionId = "SC01";
        Date from = new Date(2013-1900, 8, 1);
        Date to = new Date(2013-1900, 8, 30);
        
        double expResult = 5000.0;
        double result = instance.getTotalExpensesBySection(sectionId, from, to);
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of getTotalExpensesBySection method, of class ExpenseMngr.
     */
    @Test
    public void testGetTotalExpensesBySection_4args_2() throws Exception {
        System.out.println("getTotalExpensesBySection");
        String sectionId = "SC01";
        Date from = new Date(2013-1900, 8, 1);
        Date to = new Date(2013-1900, 8, 30);
        String typeId = "ET01";
        
        double expResult = 5000.0;
        double result = instance.getTotalExpensesBySection(sectionId, from, to, typeId);
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of getTotalExpenses method, of class ExpenseMngr.
     */
    @Test
    public void testGetTotalExpenses_List() throws Exception {
        System.out.println("getTotalExpenses");
        int month = 8;
        int year = 2013-1900;
        List<Expenses> expenses = instance.getExpenses(month, year);
        
        double expResult = 15000.0;
        double result = instance.getTotalExpenses(expenses);
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of getExpenses method, of class ExpensesMngr.
     */
    @Test
    public void testGetExpenses_0args() throws Exception {
        System.out.println("getExpenses");
        
        int expResult = 2;
        List result = instance.getExpenses();
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getExpenses method, of class ExpensesMngr.
     */
    @Test
    public void testGetExpenses_String_Date() throws Exception {
        System.out.println("getExpenses");
        String sectionId = "SC01";
        Date date = new Date(2013,8,11);
        
        int expResult = 1;
        List result = instance.getExpenses(sectionId, date);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getExpenses method, of class ExpensesMngr.
     */
    @Test
    public void testGetExpenses_3args_3() throws Exception {
        System.out.println("getExpenses");
        Date from = new Date(2013-1900, 8, 1);
        Date to = new Date(2013-1900, 8, 30);
        String typeId = "ET01";
        
        int expResult = 1;
        List result = instance.getExpenses(from, to, typeId);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getExpensesBySection method, of class ExpensesMngr.
     */
    @Test
    public void testGetExpensesBySection_String() throws Exception {
        System.out.println("getExpensesBySection");
        String sectionId = "SC01";
        
        int expResult = 1;
        List result = instance.getExpensesBySection(sectionId);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getExpensesBySection method, of class ExpensesMngr.
     */
    @Test
    public void testGetExpensesBySection_String_String() throws Exception {
        System.out.println("getExpensesBySection");
        String sectionId = "SC01";
        String typeId = "ET01";
        
        int expResult = 1;
        List result = instance.getExpensesBySection(sectionId, typeId);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getTotalExpenses method, of class ExpensesMngr.
     */
    @Test
    public void testGetTotalExpenses_String_Date() throws Exception {
        System.out.println("getTotalExpenses");
        String sectionId = "SC01";
        Date date = new Date(2013-1900, 8, 12);
        
        double expResult = 5000.0;
        double result = instance.getTotalExpenses(sectionId, date);
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of getTotalExpenses method, of class ExpensesMngr.
     */
    @Test
    public void testGetTotalExpenses_3args_3() throws Exception {
        System.out.println("getTotalExpenses");
        Date from = new Date(2013-1900, 8, 1);
        Date to = new Date(2013-1900, 8, 30);
        String typeId = "ET01";
        
        double expResult = 5000.0;
        double result = instance.getTotalExpenses(from, to, typeId);
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of getTotalExpensesBySection method, of class ExpensesMngr.
     */
    @Test
    public void testGetTotalExpensesBySection_String() throws Exception {
        System.out.println("getTotalExpensesBySection");
        String sectionId = "SC01";
        
        double expResult = 5000.0;
        double result = instance.getTotalExpensesBySection(sectionId);
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of getTotalExpensesBySection method, of class ExpensesMngr.
     */
    @Test
    public void testGetTotalExpensesBySection_String_String() throws Exception {
        System.out.println("getTotalExpensesBySection");
        String sectionId = "SC01";
        String typeId = "ET01";
        
        double expResult = 5000.0;
        double result = instance.getTotalExpensesBySection(sectionId, typeId);
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of getExpensesByExpenseType method, of class ExpensesMngr.
     */
    @Test
    public void testGetExpensesByExpenseType() throws Exception {
        System.out.println("getExpensesByExpenseType");
        String typeId = "ET01";
        
        int expResult = 1;
        List result = instance.getExpensesByExpenseType(typeId);
        assertEquals(expResult, result.size());
        
    }
}