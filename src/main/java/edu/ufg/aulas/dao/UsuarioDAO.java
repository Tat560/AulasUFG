/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package edu.ufg.aulas.dao;

/**
 *
 * @author Jorge Parra
 */

import edu.ufg.aulas.entidades.Usuario;
import edu.ufg.aulas.util.HashUtil;
import edu.ufg.aulas.util.HibernateUtil;
import org.hibernate.Session;

public class UsuarioDAO {

    /** Devuelve el usuario si correo y clave (hasheada) coinciden; si no, null. */
    public Usuario autenticar(String correo, String clavePlana) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery(
                            "from Usuario u where u.correo = :correo and u.clave = :clave", Usuario.class)
                    .setParameter("correo", correo)
                    .setParameter("clave", HashUtil.hash(clavePlana))
                    .uniqueResult();
        }
    }
}
