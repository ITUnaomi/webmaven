/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import projgit.Personne;

/**
 *
 * @author ITU
 */
public class FonctionIT {
    
    public FonctionIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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
     * Test of debutWeek method, of class Fonction.
     */
    @Test
    public void testDebutWeek() throws Exception {
        System.out.println("debutWeek");
        String date = "16/01/2017";
        Fonction instance = new Fonction();
        boolean expResult = true;
        boolean result = instance.debutWeek(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of testHeure method, of class Fonction.
     */
    @Test
    public void testTestHeure() {
        System.out.println("testHeure");
        String heure = "09:00 PM";
        Fonction instance = new Fonction();
        String expResult = "21:00";
        String result = instance.testHeure(heure);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of Insert method, of class Fonction.
     */
    @Test
    public void testInsert() {
        System.out.println("Insert");
        Personne emp = new Personne();
        emp.setIdPersonne("1");
        emp.setNom("Naomi");
        emp.setSexe("F");
        emp.setDateNaissance("29/10/1998");
        Fonction instance = new Fonction();
        boolean expResult = true;
        boolean result = emp.insert();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of WeekEnd method, of class Fonction.
     */
    @Test
    public void testWeekEnd() throws Exception {
        System.out.println("WeekEnd");
        String date = "17/01/2017";
        Fonction instance = new Fonction();
        boolean expResult = false;
        boolean result = instance.WeekEnd(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
