/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ues.occ.edu.sv.ingenieria.prn335.guia03.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ues.occ.edu.sv.ingenieria.prn335.bolsaTrabajoData.entity.Persona;

/**
 *
 * @author adalberto
 */
public class UtilitiesFacadeTest {
    
    public UtilitiesFacadeTest() {
    }

    /**
     * Test of findPersonaTipoDocumento method, of class UtilitiesFacade.
     */
    @Test
    public void testFindPersonaTipoDocumento() {
        System.out.println("findPersonaTipoDocumento");
        String idPersona = "1";
        UtilitiesFacade instance = new UtilitiesFacade();
        List<Persona> pList = instance.findPersonaTipoDocumento(idPersona);
        for (int i = 0; i < pList.size(); i++) {
            System.out.println(pList.get(i));
        }
        //List<Persona> expResult = null;
        //List<Persona> result = instance.findPersonaTipoDocumento(idPersona);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}
