/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpms.controllers;

import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author aabello
 */
@Local
public interface ProfitMngrLocal {
    
    
    double getTotalProfitBySection(String sectionId);
    
    double getTotalProfitBySeason(String sectionId, Date date);
    
    double getTotalProfitByDate(Date from, Date to);
    
    double getTotalProfitByYear(int Month, int Year);
    
    double getTotalProfit();
    
    byte[] getProfitReport();
    
    
}
