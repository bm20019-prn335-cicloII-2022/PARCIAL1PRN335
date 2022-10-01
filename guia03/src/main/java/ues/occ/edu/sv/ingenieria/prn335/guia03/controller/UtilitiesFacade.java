/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ues.occ.edu.sv.ingenieria.prn335.guia03.controller;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import ues.occ.edu.sv.ingenieria.prn335.bolsaTrabajoData.entity.Persona;

/**
 *
 * @author adalberto
 */
@Stateless
@LocalBean
public class UtilitiesFacade {

    private EntityManager entityManager;
    private EntityManagerFactory emf;
    
 /*  @PersistenceContext(unitName = "bolsaPU")
    EntityManager em;
   @PersistenceUnit
   EntityManagerFactory emf;*/
    
   public List<Persona> findPersonaTipoDocumento(String idPersona) {
       this.emf = Persistence.createEntityManagerFactory("bolsaPU");
       this.entityManager =this.emf.createEntityManager();
       
       
      // EntityManager em=emf.createEntityManager();
      //SELECT t FROM Persona t WHERE t.idPersona=\"1\"
        Query qe = entityManager.createNamedQuery("find Persona by id");
        qe.setParameter("id","1");
        return qe.getResultList();
    }

    protected TypedQuery generarConsultaBase(EntityManager em) {

        if (em != null) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery(Persona.class);
            Root<Persona> raiz = cq.from(Persona.class);
            cq.select(raiz);
            return em.createQuery(cq);
        }
        throw new IllegalArgumentException();
    }
}
