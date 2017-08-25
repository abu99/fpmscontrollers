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
import com.fpms.persistence.mngrs.ExpenseTypeEntityMngrLocal;
import com.fpms.persistence.mngrs.FarmInfoEntityMngrLocal;
import com.fpms.persistence.mngrs.FarmSectionEntityMngrLocal;
import com.fpms.persistence.mngrs.FarmingSeasonEntityMngrLocal;
import com.fpms.persistence.mngrs.ProductOtherUnitsEntityMngrLocal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author aabello
 */
@Stateless
public class FpmsConfigMngr implements FpmsConfigMngrLocal{

    @EJB
    private FarmSectionEntityMngrLocal sectionEntityMngr;
    @EJB
    private FarmingSeasonEntityMngrLocal seasonEntityMngr;
    @EJB
    private ExpenseTypeEntityMngrLocal expenseTypeEntityMngr;
    @EJB
    private ProductOtherUnitsEntityMngrLocal otherUnitsEntityMngr;
    @EJB
    private FarmInfoEntityMngrLocal farmInfoEntityMngr;
    
    @Override
    public FarmSection addSection(FarmSection section) {
        return sectionEntityMngr.create(section);
    }

    @Override
    public FarmSection updateSection(FarmSection section) {
        return sectionEntityMngr.update(section);
    }

    @Override
    public void deleteSection(String sectionId) {
        sectionEntityMngr.delete(sectionId);
    }

    @Override
    public void deleteSections(List<String> sectionIds) {
        for(String sectionId : sectionIds){
            deleteSection(sectionId);
        }
    }

    @Override
    public FarmSection getSection(String sectionId) {
        return sectionEntityMngr.get(sectionId);
    }

    @Override
    public List<FarmSection> getSections() {
        return sectionEntityMngr.getAll();
    }

    @Override
    public FarmingSeason addSeason(FarmingSeason season) {
        return seasonEntityMngr.create(season);
    }

    @Override
    public FarmingSeason updateSeason(FarmingSeason season) {
        return seasonEntityMngr.update(season);
    }

    @Override
    public void deleteSeason(String sectionId, Date date) {
        seasonEntityMngr.delete(sectionId, date);
    }

    @Override
    public void deleteSeasons(List<FarmingSeasonPK> seasonPKs) {
        for(FarmingSeasonPK seasonPK: seasonPKs){

            deleteSeason(seasonPK.getSectionId(), seasonPK.getStartDate());
        }
    }

    @Override
    public FarmingSeason getSeason(String sectionId, Date date) {
        return seasonEntityMngr.get(sectionId, date);
    }

    @Override
    public List<FarmingSeason> getSeasons() {
        return seasonEntityMngr.getAll();
    }

    @Override
    public ExpenseType addExpenseType(ExpenseType type) {
        return expenseTypeEntityMngr.create(type);
    }

    @Override
    public ExpenseType updateExpenseType(ExpenseType type) {
        return expenseTypeEntityMngr.update(type);
    }

    @Override
    public void deleteExpenseType(String typeId) {
        expenseTypeEntityMngr.delete(typeId);
    }

    @Override
    public void deleteExpenseTypes(List<String> typeIds) {
        for(String typeId : typeIds){
            deleteExpenseType(typeId);
        }
    }

    @Override
    public ExpenseType getExpenseType(String typeId) {
        return expenseTypeEntityMngr.get(typeId);
    }

    @Override
    public List<ExpenseType> getExpenseTypes() {
        return expenseTypeEntityMngr.getAll();
    }

    @Override
    public ProductOtherUnits addOtherUnit(ProductOtherUnits otherUnits) {
        return otherUnitsEntityMngr.create(otherUnits);
    }

    @Override
    public ProductOtherUnits updateOtherUnit(ProductOtherUnits otherUnits) {
        return otherUnitsEntityMngr.update(otherUnits);
    }

    @Override
    public void deleteOtherUnit(String productId, String sectionId, 
        String unitName) {
        otherUnitsEntityMngr.delete(productId, sectionId, unitName);
    }

    @Override
    public void deleteOtherUnits(List<ProductOtherUnitsPK> otherUnitsPK) {
        for(ProductOtherUnitsPK unitsPK : otherUnitsPK){
            
            deleteOtherUnit(unitsPK.getProductId(), unitsPK.getSectionId(), unitsPK.getUnitName());
        }
    }

    @Override
    public ProductOtherUnits getOtherUnit(String productId, String sectionId, 
        String unitName) {
        return otherUnitsEntityMngr.get(productId, sectionId, unitName);
    }

    @Override
    public List<ProductOtherUnits> getOtherUnits() {
        return otherUnitsEntityMngr.getAll();
    }
    
    @Override
    public FarmInfo addFarm(FarmInfo farmInfo) {
        return farmInfoEntityMngr.create(farmInfo);
    }

    @Override
    public FarmInfo updateFarm(FarmInfo farmInfo) {
        return farmInfoEntityMngr.update(farmInfo);
    }

    @Override
    public void deleteFarm(String farmId) {
        farmInfoEntityMngr.delete(farmId);
    }

    @Override
    public void deleteFarms(List<String> farmIds) {
        
        for(String s : farmIds){
            deleteFarm(s);
        }
    }

    @Override
    public FarmInfo getFarm(String farmId) {
        return farmInfoEntityMngr.get(farmId);
    }

    @Override
    public List<FarmInfo> getFarms() {
        return farmInfoEntityMngr.getAll();
    }
    
    
    
}
