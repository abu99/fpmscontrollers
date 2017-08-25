/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpms.controllers;

import com.fpms.controllers.test.common.ServiceLocator;
import com.fpms.persistence.entities.ExpenseType;
import com.fpms.persistence.entities.FarmSection;
import com.fpms.persistence.entities.FarmingSeason;
import com.fpms.persistence.entities.FarmingSeasonPK;
import com.fpms.persistence.entities.Product;
import com.fpms.persistence.entities.ProductOtherUnits;
import com.fpms.persistence.entities.ProductOtherUnitsPK;
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
public class FpmsConfigMngrTest {
    @EJB
    private static FpmsConfigMngrLocal instance;
    
    public FpmsConfigMngrTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        
        instance = new ServiceLocator().lookup(FpmsConfigMngrLocal.class);
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
     * Test of addSection method, of class FpmsConfigMngr.
     */
    @Test
    public void testAddSection() throws Exception {
        System.out.println("addSection");
        FarmSection section = new FarmSection("SEC09", "Ploughing section");
        FarmSection expResult = new FarmSection("SEC09");
        FarmSection result = instance.addSection(section);
        assertEquals(expResult, result);
        
        instance.deleteSection("SEC09");
        
    }

    /**
     * Test of updateSection method, of class FpmsConfigMngr.
     */
    @Test
    public void testUpdateSection() throws Exception {
        System.out.println("updateSection");
        FarmSection section = new FarmSection("SEC09", "Ploughing section");
        instance.addSection(section);
        String expResult = "milling section";
        section.setSectionName(expResult);
        FarmSection result = instance.updateSection(section);
        assertEquals(expResult, result.getSectionName());
        
        
        
    }

    /**
     * Test of deleteSection method, of class FpmsConfigMngr.
     */
    @Test
    public void testDeleteSection() throws Exception {
        System.out.println("deleteSection");
        String sectionId = "SEC09";
        instance.deleteSection(sectionId);
        FarmSection result = instance.getSection(sectionId);
        assertNull(result);
    }

    /**
     * Test of deleteSections method, of class FpmsConfigMngr.
     */
    @Test
    public void testDeleteSections() throws Exception {
        System.out.println("deleteSections");
        FarmSection section1 = new FarmSection("SEC05", "vegetable section");
        FarmSection section2 = new FarmSection("SEC06", "garden section");
        instance.addSection(section1);
        instance.addSection(section2);
        List<String> sectionIds = new ArrayList<String>();
        sectionIds.add("SEC05");
        sectionIds.add("SEC06");
        instance.deleteSections(sectionIds);
    }

    /**
     * Test of getSection method, of class FpmsConfigMngr.
     */
    @Test
    public void testGetSection() throws Exception {
        System.out.println("getSection");
        FarmSection section = new FarmSection("SEC04", "Ploughing section");
        instance.addSection(section);
        String sectionId = "SEC04";
        
        FarmSection expResult = new FarmSection(sectionId);
        FarmSection result = instance.getSection(sectionId);
        assertEquals(expResult, result);
        
        instance.deleteSection(sectionId);
        
    }

    /**
     * Test of getSections method, of class FpmsConfigMngr.
     */
    @Test
    public void testGetSections() throws Exception {
        
        System.out.println("getSections");
        
        FarmSection section = new FarmSection("SEC04", "Ploughing section");
        instance.addSection(section);
        FarmSection section1 = new FarmSection("SEC07", "packaging section");
        instance.addSection(section1);
        int expResult = 4;
        List result = instance.getSections();
        assertEquals(expResult, result.size());
        
        instance.deleteSection("SEC04");
        instance.deleteSection("SEC07");
        
    }

    /**
     * Test of addSeason method, of class FpmsConfigMngr.
     */
    @Test
    public void testAddSeason() throws Exception {
        System.out.println("addSeason");
        FarmSection section = new FarmSection("SEC04", "Ploughing section");
        instance.addSection(section);
        FarmingSeasonPK seasonPK = new FarmingSeasonPK("SEC04", new Date(2013-1900, 5, 16));
        FarmingSeason season = new FarmingSeason(seasonPK, new Date(2013-1900, 5, 20));
        
        FarmingSeason expResult = new FarmingSeason(seasonPK);
        FarmingSeason result = instance.addSeason(season);
        assertEquals(expResult, result);
        
        instance.deleteSeason("SEC04", new Date(2013-1900, 5, 16));
        instance.deleteSection("SEC04");
    }

    /**
     * Test of updateSeason method, of class FpmsConfigMngr.
     */
    @Test
    public void testUpdateSeason() throws Exception {
        System.out.println("updateSeason");
        FarmSection section = new FarmSection("SEC04", "Ploughing section");
        instance.addSection(section);
        FarmingSeasonPK seasonPK = new FarmingSeasonPK("SEC04", new Date(2013-1900, 5, 16));
        FarmingSeason season = new FarmingSeason(seasonPK, 
                new Date(2013-1900, 5, 16));
        instance.addSeason(season);
        Date expResult = new Date(2013-1900, 5, 30);
        season.setEndDate(expResult);
        FarmingSeason result = instance.updateSeason(season);
        assertEquals(expResult, result.getEndDate());
        
        instance.deleteSeason("SEC04", new Date(2013-1900, 5, 16));
        instance.deleteSection("SEC04");
        
    }

    /**
     * Test of deleteSeason method, of class FpmsConfigMngr.
     */
    @Test
    public void testDeleteSeason() throws Exception {
        System.out.println("deleteSeason");
        FarmSection section = new FarmSection("SEC04", "Ploughing section");
        instance.addSection(section);
        FarmingSeasonPK seasonPK = new FarmingSeasonPK("SEC04", new Date(2013-1900, 5, 16));
        FarmingSeason season = new FarmingSeason(seasonPK, 
                new Date(2013-1900, 5, 20));
        instance.addSeason(season);
        
        instance.deleteSeason("SEC04", new Date(2013-1900, 5, 16));
        FarmingSeason result = instance.getSeason("SEC04", new Date(2013-1900, 5, 16));
        assertNull(result);
        
        instance.deleteSection("SEC04");
        
    }

    /**
     * Test of deleteSeasons method, of class FpmsConfigMngr.
     */
    @Test
    public void testDeleteSeasons() throws Exception {
        System.out.println("deleteSeasons");
        FarmSection section1 = new FarmSection("SEC04", "Ploughing section");
        instance.addSection(section1);
        FarmSection section2 = new FarmSection("SEC07", "milling section");
        instance.addSection(section2);
        FarmingSeasonPK seasonPK1 = new FarmingSeasonPK("SEC04", new Date(2013-1900, 4, 16));
        FarmingSeasonPK seasonPK2 = new FarmingSeasonPK("SEC07", new Date(2013-1900, 3, 16));
        FarmingSeason season = new FarmingSeason(seasonPK1, new Date(2013-1900, 5, 20));
        FarmingSeason season2 = new FarmingSeason(seasonPK2, new Date(2013-1900, 7, 12));
        instance.addSeason(season);
        instance.addSeason(season2);
        List<FarmingSeasonPK> seasonPKs = new ArrayList<FarmingSeasonPK>();
        seasonPKs.add(seasonPK1);
        seasonPKs.add(seasonPK2);
        
        instance.deleteSeasons(seasonPKs);
        
        instance.deleteSection("SEC04");
        instance.deleteSection("SEC07");
        
    }

    /**
     * Test of getSeason method, of class FpmsConfigMngr.
     */
    @Test
    public void testGetSeason() throws Exception {
        System.out.println("getSeason");
        FarmSection section = new FarmSection("SEC04", "Ploughing section");
        instance.addSection(section);
        FarmingSeasonPK seasonPK = new FarmingSeasonPK("SEC04", new Date(2013-1900, 5, 16));
        FarmingSeason season = new FarmingSeason(seasonPK, new Date(2013-1900, 5, 20));
        instance.addSeason(season);
        FarmingSeason expResult = new FarmingSeason(seasonPK);
        FarmingSeason result = instance.getSeason("SEC04", new Date(2013-1900, 5, 16));
        assertEquals(expResult, result);
        instance.deleteSeason("SEC04", new Date(2013-1900, 5, 16));
        instance.deleteSection("SEC04");
    }

    /**
     * Test of getSeasons method, of class FpmsConfigMngr.
     */
    //@Test
    public void testGetSeasons() throws Exception {
        System.out.println("getSeasons");
                FarmSection section1 = new FarmSection("SEC04", "Ploughing section");
        instance.addSection(section1);
        FarmSection section2 = new FarmSection("SEC07", "milling section");
        instance.addSection(section2);
        FarmingSeasonPK seasonPK1 = new FarmingSeasonPK("SEC04", new Date(2013-1900, 4, 16));
        FarmingSeasonPK seasonPK2 = new FarmingSeasonPK("SEC07", new Date(2013-1900, 3, 16));
        FarmingSeason season = new FarmingSeason(seasonPK1, new Date(2013-1900, 5, 20));
        FarmingSeason season2 = new FarmingSeason(seasonPK2, new Date(2013-1900, 7, 12));
        instance.addSeason(season);
        instance.addSeason(season2);
        int expResult = 2;
        List result = instance.getSeasons();
        assertEquals(expResult, result.size());
        
        instance.deleteSeason("SEC04", new Date(2013-1900, 4, 16));
        instance.deleteSeason("SEC07", new Date(2013-1900, 3, 16));
        
        instance.deleteSection("SEC04");
        instance.deleteSection("SEC07");
        
    }

    /**
     * Test of addExpenseType method, of class FpmsConfigMngr.
     */
    @Test
    public void testAddExpenseType() throws Exception {
        System.out.println("addExpenseType");
        ExpenseType type = new ExpenseType("ext1", "salary");
        
        ExpenseType expResult =new ExpenseType("ext1");
        ExpenseType result = instance.addExpenseType(type);
        assertEquals(expResult, result);
        
        instance.deleteExpenseType("ext1");
        
    }

    /**
     * Test of updateExpenseType method, of class FpmsConfigMngr.
     */
    @Test
    public void testUpdateExpenseType() throws Exception {
        System.out.println("updateExpenseType");
        ExpenseType type = new ExpenseType("ext1", "salary");
        instance.addExpenseType(type);
        String expResult = "wages";
        type.setTypeName(expResult);
        ExpenseType result = instance.updateExpenseType(type);
        assertEquals(expResult, result.getTypeName());
        
        instance.deleteExpenseType("ext1");
        
    }

    /**
     * Test of deleteExpenseType method, of class FpmsConfigMngr.
     */
    @Test
    public void testDeleteExpenseType() throws Exception {
        System.out.println("deleteExpenseType");
        ExpenseType type = new ExpenseType("ext1", "salary");
        instance.addExpenseType(type);
        String typeId = "ext1";
        
        instance.deleteExpenseType(typeId);
        ExpenseType result = instance.getExpenseType(typeId);
        assertNull(result);
    }

    /**
     * Test of deleteExpenseTypes method, of class FpmsConfigMngr.
     */
    @Test
    public void testDeleteExpenseTypes() throws Exception {
        System.out.println("deleteExpenseTypes");
        ExpenseType type1 = new ExpenseType("ext2", "salary");
        ExpenseType type2 = new ExpenseType("ext3", "salary");
        instance.addExpenseType(type1);
        instance.addExpenseType(type2);
        List<String> typeIds = new ArrayList<String>();
        typeIds.add("ext2");
        typeIds.add("ext3");
        
        instance.deleteExpenseTypes(typeIds);
        
       
        
    }

    /**
     * Test of getExpenseType method, of class FpmsConfigMngr.
     */
    @Test
    public void testGetExpenseType() throws Exception {
        System.out.println("getExpenseType");
        ExpenseType type2 = new ExpenseType("ext4", "salary");
        instance.addExpenseType(type2);
        String typeId = "ext4";
        
        ExpenseType expResult = new ExpenseType(typeId);
        ExpenseType result = instance.getExpenseType(typeId);
        assertEquals(expResult, result);
        
        instance.deleteExpenseType(typeId);
    }

    /**
     * Test of getEcxpenseTypes method, of class FpmsConfigMngr.
     */
    @Test
    public void testGetExpenseTypes() throws Exception {
        System.out.println("getExpenseTypes");
        
        ExpenseType type1 = new ExpenseType("ext4", "salary");
        ExpenseType type2 = new ExpenseType("ext5", "salary");
        instance.addExpenseType(type1);
        instance.addExpenseType(type2);
        int expResult = 4;
        List result = instance.getExpenseTypes();
        assertEquals(expResult, result.size());
        
        instance.deleteExpenseType("ext4");
        instance.deleteExpenseType("ext5");
        
    }

    /**
     * Test of addOtherUnit method, of class FpmsConfigMngr.
     */
    @Test
    public void testAddOtherUnit() throws Exception {
        System.out.println("addOtherUnit");
        ProductOtherUnitsPK otherUnitsPK = new ProductOtherUnitsPK("PD01", "SC01", "Herds");
        ProductOtherUnits otherUnits = new ProductOtherUnits(otherUnitsPK, 100);
        otherUnits.setFarmSection(new FarmSection("SC01"));
        otherUnits.setProduct(new Product("PD01"));
        ProductOtherUnits expResult = new ProductOtherUnits(otherUnitsPK);
        ProductOtherUnits result = instance.addOtherUnit(otherUnits);
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of updateOtherUnit method, of class FpmsConfigMngr.
     */
    @Test
    public void testUpdateOtherUnit() throws Exception {
        System.out.println("updateOtherUnit");
        ProductOtherUnitsPK otherUnitsPK = new ProductOtherUnitsPK("PD01", "SC01", "Herds");
        ProductOtherUnits otherUnits = new ProductOtherUnits(otherUnitsPK, 100);
        otherUnits.setFarmSection(new FarmSection("SC01"));
        otherUnits.setProduct(new Product("PD01"));
        
        int expResult = 1000;
        otherUnits.setCount(expResult);
        ProductOtherUnits result = instance.updateOtherUnit(otherUnits);
        assertEquals(expResult, result.getCount());

    }

    /**
     * Test of deleteOtherUnit method, of class FpmsConfigMngr.
     */
    @Test
    public void testDeleteOtherUnit() throws Exception {
        System.out.println("deleteOtherUnit");
        ProductOtherUnitsPK otherUnitsPK = new ProductOtherUnitsPK("PD01", "SC01", "Herds");
        otherUnitsPK.setProductId("PD01");
        otherUnitsPK.setSectionId("SC01");
        instance.deleteOtherUnit("PD01", "SC01", "Herds");
        
    }

    /**
     * Test of deleteOtherUnits method, of class FpmsConfigMngr.
     */
    @Test
    public void testDeleteOtherUnits() throws Exception {
        System.out.println("deleteOtherUnits");
        List<ProductOtherUnitsPK> otherUnitsPKs =new ArrayList<ProductOtherUnitsPK>();
        ProductOtherUnitsPK otherUnitsPK1 = new ProductOtherUnitsPK("PD01", "SC01", "Herd");
        ProductOtherUnits otherUnits1 = new ProductOtherUnits(otherUnitsPK1, 100);
        ProductOtherUnitsPK otherUnitsPK2 = new ProductOtherUnitsPK("PD02", "SC02", "Carton");
        ProductOtherUnits otherUnits2 = new ProductOtherUnits(otherUnitsPK2, 24);
        instance.addOtherUnit(otherUnits1);
        instance.addOtherUnit(otherUnits2);
        otherUnitsPKs.add(otherUnitsPK1);
        otherUnitsPKs.add(otherUnitsPK2);
        instance.deleteOtherUnits(otherUnitsPKs);
        
    }

    /**
     * Test of getOtherUnit method, of class FpmsConfigMngr.
     */
    @Test
    public void testGetOtherUnit() throws Exception {
        System.out.println("getOtherUnit");
        ProductOtherUnitsPK otherUNitsPK = new ProductOtherUnitsPK("PD02", "SC02", "Dozen");

        ProductOtherUnits expResult = new ProductOtherUnits(otherUNitsPK);
        ProductOtherUnits result = instance.getOtherUnit("PD02", "SC02", "Dozen");
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getOtherUnits method, of class FpmsConfigMngr.
     */
    @Test
    public void testGetOtherUnits() throws Exception {
        System.out.println("getOtherUnits");
        
        int expResult = 2;
        List result = instance.getOtherUnits();
        assertEquals(expResult, result.size());
        
    }
}