/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.occ.ues.ingenieria.prn335.bolsatrabajoprn335.bm20019parcial.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import java.io.Serializable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.occ.ues.ingenieria.prn335.bolsatrabajoprn335.bm20019parcial.entity.Persona;

/**
 *
 * @author adalberto
 */
@Stateless
@LocalBean
public class PersonaBean extends AbstractDataAccess<Persona> implements Serializable {
    
    public PersonaBean(){
        
    }
    
   // EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BolsaPU");
    //@PersistenceContext(unitName="bolsa_trabajo")
    public EntityManager em;
   // Persona persona;
    
    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<Persona> getClase() {
        return Persona.class;
    }
   
}
