/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package edu.ufg.aulas.dao;

/**
 *
 * @author Jorge Parra
 */

import edu.ufg.aulas.entidades.Aula;
import edu.ufg.aulas.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AulaDAO {

    /** True si ya existe un aula con ese nombre en ese edificio. */
    public boolean existe(String nombre, String edificio) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Long total = s.createQuery(
                            "select count(a) from Aula a where lower(a.nombre) = :n and lower(a.edificio) = :e",
                            Long.class)
                    .setParameter("n", nombre.toLowerCase())
                    .setParameter("e", edificio.toLowerCase())
                    .getSingleResult();
            return total > 0;
        }
    }

    public void guardar(Aula aula) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = s.beginTransaction();
            try {
                s.persist(aula);
                tx.commit();
            } catch (RuntimeException e) {
                tx.rollback();
                throw e;
            }
        }
    }
}
