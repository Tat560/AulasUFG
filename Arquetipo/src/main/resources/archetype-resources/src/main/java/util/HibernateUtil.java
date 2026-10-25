#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ${package}.util;

import java.io.FileDescriptor;
import java.io.FileOutputStream;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author jorge
 */
public final class HibernateUtil {

    private static SessionFactory sessionFactory;

    private HibernateUtil() {
    }

    public static synchronized SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = construirSessionFactory();
        }
        return sessionFactory;
    }

    private static SessionFactory construirSessionFactory() {
        try {
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Exception ex) {
            System.err.println("Error al crear la SessionFactory: " + ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static boolean probarConexion() {
        try (Session sesion = getSessionFactory().openSession()) {
            Integer resultado = sesion.createNativeQuery("SELECT 1", Integer.class).getSingleResult();
            return resultado != null && resultado == 1;
        } catch (Exception ex) {
            System.err.println("No se pudo conectar a la base de datos: " + ex.getMessage());
            return false;
        }
    }

    public static synchronized void cerrar() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
        sessionFactory = null;
    }

    public static void main(String[] args) {
        PrintStream salida = new PrintStream(new FileOutputStream(FileDescriptor.out), true, StandardCharsets.UTF_8);
        boolean ok = probarConexion();
        salida.println(ok
                ? "Conexión a la base de datos aulas_ufg: OK"
                : "Conexión a la base de datos aulas_ufg: FALLÓ");
        cerrar();
        System.exit(ok ? 0 : 1);
    }
}