/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpms.controllers;

import com.fpms.persistence.entities.ExpenseType;
import com.fpms.persistence.entities.FarmInfo;
import com.fpms.persistence.entities.FarmSection;
import com.fpms.persistence.entities.FarmingSeason;
import com.fpms.persistence.entities.FarmingSeasonPK;
import com.fpms.persistence.entities.ProductOtherUnits;
import com.fpms.persistence.entities.ProductOtherUnitsPK;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author aabello
 */
@Local
public interface FpmsConfigMngrLocal {
    
    //Section
    FarmSection addSection(FarmSection section);
    
    FarmSection updateSection(FarmSection section);
    
    void deleteSection(String sectionId);
    
    void deleteSections(List<String> sectionIds);
    
    FarmSection getSection(String sectionId);
    
    List<FarmSection> getSections();
    
    //Season
    FarmingSeason addSeason(FarmingSeason season);
    
    FarmingSeason updateSeason(FarmingSeason season);
    
    void deleteSeason(String sectionId, Date date);
    
    void deleteSeasons(List<FarmingSeasonPK> seasonPKs);
    
    FarmingSeason getSeason(String sectionId, Date date);
    
    List<FarmingSeason> getSeasons();
    
    //ExpenseType
    ExpenseType addExpenseType(ExpenseType type);
    
    ExpenseType updateExpenseType(ExpenseType type);
    
    void deleteExpenseType(String typeId);
    
    void deleteExpenseTypes(List<String> typeIds);
    
    ExpenseType getExpenseType(String typeId);
    
    List<ExpenseType> getExpenseTypes();
    
    //ProductOtherUnits
    ProductOtherUnits addOtherUnit(ProductOtherUnits otherUnits);
    
    ProductOtherUnits updateOtherUnit(ProductOtherUnits otherUnits);
    
    void deleteOtherUnit(String productId, String sectionId, String unitName);
    
    void deleteOtherUnits(List<ProductOtherUnitsPK> otherUnitsPK);
    
    ProductOtherUnits getOtherUnit(String productId, String sectionId, String unitName);
    
    List<ProductOtherUnits> getOtherUnits();
    
        FarmInfo addFarm(FarmInfo farmInfo);
    
    FarmInfo updateFarm(FarmInfo farmInfo);
    
    void deleteFarm(String farmId);
    
    void deleteFarms(List<String> farmIds);
    
    FarmInfo getFarm(String farmId);
    
    List<FarmInfo> getFarms();
    
    
    
    
    
}
