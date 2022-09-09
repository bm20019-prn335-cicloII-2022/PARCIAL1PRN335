/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.occ.ues.ingenieria.prn335.bolsatrabajoprn335.bm20019parcial.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import sv.edu.occ.ues.ingenieria.prn335.bolsatrabajoprn335.bm20019parcial.entity.Persona;

/**
 *
 * @author adalberto
 * @param <T>
 */
public abstract class AbstractDataAccess<T> {

    public AbstractDataAccess() {
           clase = getClase();
    }

    public abstract EntityManager getEntityManager();
     public abstract Class<T> getClase();
     Class<T> clase;

   // public Class<T> type;

 //   public abstract boolean getType(Class<T> type) {
   //     this.type = type;
   //     return true;
   // }

    public void crear(T registro) throws IllegalArgumentException, IllegalStateException {
        if (registro != null) {
            EntityManager em = null;
            try {
                em = getEntityManager();
            } catch (Exception exc) {

            }
            if (em != null) {
                em.persist(registro);
                return;
            } else {
                throw new IllegalStateException("El Entity es nulo");
            }
        }
    }

    public T findById(final Object id) throws IllegalArgumentException, IllegalStateException {
        if (id != null) {
            EntityManager em = null;
            try {
                em = getEntityManager();
            } catch (Exception ex) {
            }
            if (em != null) {
                clase = getClase();
                return em.find(clase, id);
            }
            throw new IllegalStateException("EntityManager Null");
        }
        throw new IllegalArgumentException("El Id es nulo, para FINDByID");
    }

    public List<T> findAll() {
        EntityManager em = null;
        try {
            em = this.getEntityManager();
        } catch (Exception exc) {
        }
        if (em != null) {
            TypedQuery tq = this.generarConsultaBase(em);
            List result = tq.getResultList();
            if (result != null) {
                return result;
            }
            return Collections.EMPTY_LIST;
        }
        return Collections.EMPTY_LIST;
       // throw new IllegalStateException("El entity manager es nulo");
    }

    public List<T> findRange(int fist, int pageSize) {
        if (fist >= 0 && pageSize >= 0) {
            EntityManager em = null;
            try {
                em = getEntityManager();
            } catch (Exception ex) {
            }
            if (em != null) {
                clase = getClase();
                List<T> rango = new ArrayList<T>();
                
                for (int i = fist; i < pageSize; i++) {
                    T encontrado = em.find(clase, i);
                    rango.add(encontrado);
                }
                return rango; //(T)em.find((Class<T>)clase, id);
            }
        }
        return new ArrayList<T>();
      //  throw new IllegalArgumentException("El Id es nulo");
    }

    public void eliminar(T registro) throws IllegalArgumentException, IllegalStateException {
        if (registro != null) {
            EntityManager em = null;
            try {
                em = getEntityManager();
            } catch (Exception exc) {

            }
            if (em != null) {
                em.remove(registro);
                return;
            } else {
                throw new IllegalStateException("El Entity es nulo");
            }
        }
    }

    public T modificar(T registro) throws IllegalArgumentException, IllegalStateException {
        if (registro != null) {
            //return registro;
            EntityManager em = null;
            try {
                em = getEntityManager();
            } catch (Exception ex) {
            }
            if (em != null) {
                em.merge(registro);
                return registro;
            } else {
                throw new IllegalStateException("Entity Nulo");
            }
        }
        throw new IllegalArgumentException("El registro dado es nulo");
    }

    public Long contar() throws IllegalArgumentException {
        EntityManager em = null;
        try {
            em = this.getEntityManager();
        } catch (Exception ex) {

        }
        if (em != null) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            cq.select(cb.count(cq.from(clase)));
            return em.createQuery(cq).getSingleResult();
        }
        throw new IllegalStateException("El Entity es nulo");
    }

    public TypedQuery generarConsultaBase(EntityManager em) {
        return TypedQuery.class.cast(em);
    }
}
