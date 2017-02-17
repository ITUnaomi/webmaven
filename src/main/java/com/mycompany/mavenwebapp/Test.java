/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenwebapp;

import projgit.Personne;

/**
 *
 * @author ITU
 */
public class Test {
    public static void main(String[] arg){
        Personne emp = new Personne();
        emp.setIdPersonne("1");
        emp.setNom("Naomi");
        emp.setSexe("F");
        emp.setDateNaissance("29/10/1998");
        emp.insert();
    }
}
