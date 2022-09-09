/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package sv.edu.occ.ues.ingenieria.prn335.bolsatrabajoprn335.bm20019parcial.control;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.FlushModeType;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.metamodel.Metamodel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import sv.edu.occ.ues.ingenieria.prn335.bolsatrabajoprn335.bm20019parcial.entity.Persona;

/**
 *
 * @author adalberto
 */
public class PersonaBeanTest {
protected List<Persona> findAllResult;

    protected EntityManager emNullGenerator;

    public PersonaBean prepararCutParaFind() {
        EntityManager em = Mockito.mock(EntityManager.class);
        TypedQuery tq = Mockito.mock(TypedQuery.class);
        Mockito.when(tq.getResultList()).thenReturn(findAllResult);

        PersonaBean cut = new PersonaBean();
        PersonaBean espia = Mockito.spy(cut);
        Mockito.doReturn(tq).when(espia).generarConsultaBase(em);

        espia.em = em;
        return espia;
    }

    public PersonaBeanTest() {
        findAllResult = new ArrayList<>();
        findAllResult.add(new Persona(1l));
        findAllResult.add(new Persona(2l));
        findAllResult.add(new Persona(3l));
    }

    /**
     * Test of getEntityManager method, of class PersonaBean.
     */
    @Test
    public void testGetEntityManager() {
        PersonaBean cut = new PersonaBean();
        EntityManager mockEm = Mockito.mock(EntityManager.class);
        cut.em = mockEm;
        EntityManager resultado = cut.getEntityManager();
        assertEquals(resultado, mockEm);
    }

    @Test
    public void testCrear() {
        PersonaBean cut = new PersonaBean();
        EntityManager mockEm = Mockito.mock(EntityManager.class);
        cut.em = mockEm;

        Persona registro = new Persona();
        registro.setNombres("Chepe");
        registro.setApellidos("Diablo");
        registro.setFechaNacimiento(new Date());
        cut.crear(registro);

        try {
            cut.crear(null);
        } catch (IllegalArgumentException ex) {

        }

        cut.em = null;
        try {
            cut.crear(registro);
        } catch (IllegalStateException ex) {

        }

        cut.em = emNullGenerator;

        try {
            cut.crear(registro);
        } catch (IllegalStateException ex) {

        }

    }

    @Test
    public void testFindById() throws Exception {
        System.out.println("findById");
        Long id = 1l;
        EntityManager mockEM = Mockito.mock(EntityManager.class);
        PersonaBean cut = new PersonaBean();
        Persona expResult = new Persona(1l);
        Mockito.when(mockEM.find(Persona.class, id)).thenReturn(expResult);
        assertThrows(IllegalStateException.class, () -> {cut.findById(id);});
        assertThrows(IllegalArgumentException.class, () -> {cut.findById(null);});

        cut.em = mockEM;
        Persona encontrado = cut.findById(id);

        assertEquals(expResult, encontrado);

        PersonaBean spy = Mockito.spy(PersonaBean.class);
        spy.em = mockEM;
        spy.findById(id);
        Mockito.when(spy.getEntityManager()).thenThrow(NullPointerException.class);
        Mockito.verify(spy, Mockito.times(1)).getEntityManager();

        PersonaBean otroCut = new PersonaBean();
        otroCut.em = emNullGenerator;

        assertThrows(IllegalStateException.class, () -> {otroCut.findById(id);});
    }

    @Test
    public void testFindRange() throws Exception {
        System.out.println("findRange");
        int first = 0;
        int pageSize = 3;
        PersonaBean cut = this.prepararCutParaFind();
        List<Persona> result = cut.findRange(first, pageSize);
        assertEquals(result.size(),this.findAllResult.size());
        cut.em = null;
        result = cut.findRange(0, 1000);
        assertTrue(result.isEmpty());       
    }
    
     @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        PersonaBean cut = this.prepararCutParaFind();
        List<Persona> resultado = cut.findAll();
        assertEquals(resultado.size(), findAllResult.size());
        cut.em = null;
        assertTrue(cut.findAll().isEmpty());
    }

    
    @Test
    public void testEliminar() throws Exception {
        System.out.println("eliminar");
        EntityManager em = Mockito.mock(EntityManager.class);
        PersonaBean cut = new PersonaBean();
        cut.em = em;
        Persona eliminado = new Persona(1l);
        cut.eliminar(eliminado);
        Mockito.verify(em, Mockito.times(1)).remove(ArgumentMatchers.any());
        try {
            cut.eliminar(null);
          //  fail("el parametro era nulo");
        } catch (IllegalArgumentException ex) {

        }
        try {
            cut.em = null;
            cut.eliminar(eliminado);
         //   fail("el entitymanager era nulo");
        } catch (IllegalStateException ex) {

        }
    }
    
    @Test
    public void testModificar() throws Exception {
        System.out.println("modificar");

        EntityManager em = Mockito.mock(EntityManager.class);
        PersonaBean cut = new PersonaBean();
        cut.em = em;
        cut.modificar(new Persona(1l));
        Mockito.verify(em, Mockito.times(1)).merge(ArgumentMatchers.any());
        try {
            cut.modificar(null);
         //   fail("el parametro era nulo");
        } catch (IllegalArgumentException ex) {

        }
        try {
            cut.em = null;
            cut.modificar(new Persona(1l));
            //fail("el entitymanager era nulo");
        } catch (IllegalStateException ex) {

        }
    }
    
     @Test
    public void testContar() throws Exception {
        System.out.println("contar");
        EntityManager em = Mockito.mock(EntityManager.class);
        CriteriaBuilder cb = Mockito.mock(CriteriaBuilder.class);
        CriteriaQuery cq = Mockito.mock(CriteriaQuery.class);
        TypedQuery tq = Mockito.mock(TypedQuery.class);
        Mockito.when(cb.createQuery(Long.class)).thenReturn(cq);
        Mockito.when(em.getCriteriaBuilder()).thenReturn(cb);
        Mockito.when(em.createQuery(cq)).thenReturn(tq);
        Mockito.when(tq.getSingleResult()).thenReturn(Long.valueOf(1l));
        PersonaBean cut = new PersonaBean();
        cut.em = em;
        Long resultado = cut.contar();
        assertEquals(resultado, Long.valueOf(1));
        try {
            cut.em = null;
            cut.contar();
            //fail("El EntityManager era nulo");
        } catch (Exception ex) {

        }
    }
}
