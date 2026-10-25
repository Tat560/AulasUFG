/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package edu.ufg.aulas.util;

/**
 *
 * @author Walter Valladares
 */

import edu.ufg.aulas.entidades.EstadoAula;
import edu.ufg.aulas.entidades.Planta;
import edu.ufg.aulas.entidades.TipoAula;
import edu.ufg.aulas.entidades.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;

/** Inserta los catalogos y el usuario administrador la primera vez que corre la app. */
public class DatosIniciales {

    public static final String ADMIN_CORREO = "admin@ufg.edu.sv";
    public static final String ADMIN_CLAVE = "admin123";

    private DatosIniciales() {
    }

    public static void cargar() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = s.beginTransaction();
            try {
                if (contar(s, "Usuario") == 0) {
                    s.persist(new Usuario(ADMIN_CORREO, HashUtil.hash(ADMIN_CLAVE)));
                }
                if (contar(s, "TipoAula") == 0) {
                    for (String n : new String[]{"Normal", "Centro de cómputo", "Laboratorio", "Especial"}) {
                        s.persist(new TipoAula(n));
                    }
                }
                if (contar(s, "EstadoAula") == 0) {
                    for (String n : new String[]{"Asignada", "Asignada temporalmente", "En mantenimiento", "Disponible"}) {
                        s.persist(new EstadoAula(n));
                    }
                }
                if (contar(s, "Planta") == 0) {
                    for (int i = 1; i <= 5; i++) {
                        s.persist(new Planta(i));
                    }
                }
                tx.commit();
            } catch (RuntimeException e) {
                tx.rollback();
                throw e;
            }
        }
    }

    private static long contar(Session s, String entidad) {
        return s.createQuery("select count(e) from " + entidad + " e", Long.class).getSingleResult();
    }
}
