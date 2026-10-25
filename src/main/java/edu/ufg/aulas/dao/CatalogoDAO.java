/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package edu.ufg.aulas.dao;

/**
 *
 * @author Jorge Parra
 */

import edu.ufg.aulas.entidades.EstadoAula;
import edu.ufg.aulas.entidades.TipoAula;
import edu.ufg.aulas.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/** Consultas que llenan los 3 ComboBox del formulario desde la base de datos. */
public class CatalogoDAO {

    public List<Integer> listarPlantas() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("select p.numero from Planta p order by p.numero", Integer.class).list();
        }
    }

    public List<TipoAula> listarTipos() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("from TipoAula t order by t.id", TipoAula.class).list();
        }
    }

    public List<EstadoAula> listarEstados() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("from EstadoAula e order by e.id", EstadoAula.class).list();
        }
    }
}
