/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpms.controllers;

import com.fpms.controllers.test.common.ServiceLocator;
import com.fpms.controllers.util.MD5;
import com.fpms.persistence.entities.User;
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
public class UserMngrTest {
       
    @EJB
    private static UserMngrLocal instance;
    
    public UserMngrTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception{
        instance = new ServiceLocator().lookup(UserMngrLocal.class);
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
     * Test of changePassword method, of class UserMngr.
     */
    @Test
    public void testChangePassword() throws Exception {
        
        System.out.println("changePassword");
        String userId = "abello7";
        String newPassword = "mariscas";
        String comfirmPassword = "mariscas";
        
        instance.changePassword(userId, newPassword, comfirmPassword);
        assertEquals(MD5.hash(comfirmPassword), instance.getUser(userId).getPassword());
        
    }
    
    /**
     * Test of changePassword method, of class UserMngr.
     */
//    @Test
    public void testChangePasswordMismatch() throws Exception {
        System.out.println("changePassword");
        String userId = "abello7";
        String newPassword = "mariscas";
        String comfirmPassword = "mariscaz";
        
        instance.changePassword(userId, newPassword, comfirmPassword);
        assertEquals(MD5.hash(comfirmPassword), instance.getUser(userId).getPassword());
        
    }
    
    /**
     * Test of changePassword method, of class UserMngr.
     */
//    @Test
    public void testChangePasswordLessThanEight() throws Exception {
        System.out.println("changePassword");
        String userId = "abello7";
        String newPassword = "maris";
        String comfirmPassword = "maris";
        
        instance.changePassword(userId, newPassword, comfirmPassword);
        assertEquals(MD5.hash(comfirmPassword), instance.getUser(userId).getPassword());
        
    }

    /**
     * Test of getUser method, of class UserMngr.
     */
//    @Test
    public void testGetUser() throws Exception {
        System.out.println("getUser");
        String userId = "abello7";
        
        User expResult = new User(userId);
        User result = instance.getUser(userId);
        assertEquals(expResult, result);
        
    }
}