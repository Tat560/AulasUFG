package edu.ufg.aulas.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Clase de conexión a la base de datos.
 * Crea una única SessionFactory (patrón Singleton) a partir de hibernate.cfg.xml
 * y la comparte con el resto de la aplicación.
 *
 * Uso desde los controladores:
 * <pre>
 * try (Session session = HibernateUtil.getSessionFactory().openSession()) {
 *     // consultas, session.persist(...), etc.
 * }
 * </pre>
 *
 * @author Jorge
 */
public final class HibernateUtil {

    private static SessionFactory sessionFactory;

    private HibernateUtil() {
        // Clase de utilidad: no se instancia
    }

    /**
     * Devuelve la SessionFactory, creándola la primera vez que se pide.
     */
    public static synchronized SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = construirSessionFactory();
        }
        return sessionFactory;
    }

    private static SessionFactory construirSessionFactory() {
        try {
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

            // Permite usar otros datos de conexión sin modificar el XML ni subir claves a GitHub
            sobrescribirSiExiste(configuration, "DB_URL", "hibernate.connection.url");
            sobrescribirSiExiste(configuration, "DB_USER", "hibernate.connection.username");
            sobrescribirSiExiste(configuration, "DB_PASSWORD", "hibernate.connection.password");
            sobrescribirSiExiste(configuration, "DB_DIALECT", "hibernate.dialect");

            return configuration.buildSessionFactory();
        } catch (Exception ex) {
            System.err.println("Error al crear la SessionFactory: " + ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static void sobrescribirSiExiste(Configuration configuration, String variable, String propiedad) {
        String valor = System.getenv(variable);
        if (valor != null && !valor.isBlank()) {
            configuration.setProperty(propiedad, valor);
        }
    }

    /**
     * Comprueba que la base de datos responde ejecutando "SELECT 1".
     *
     * @return true si la conexión funciona
     */
    public static boolean probarConexion() {
        try (Session session = getSessionFactory().openSession()) {
            Integer resultado = session.createNativeQuery("SELECT 1", Integer.class).getSingleResult();
            return resultado != null && resultado == 1;
        } catch (Exception ex) {
            System.err.println("No se pudo conectar a la base de datos: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Cierra la SessionFactory. Llamar al salir de la aplicación.
     */
    public static synchronized void cerrar() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
        sessionFactory = null;
    }

    /**
     * Prueba rápida de la conexión:
     * mvn compile exec:java -Dexec.mainClass=edu.ufg.aulas.util.HibernateUtil
     */
    public static void main(String[] args) {
        boolean ok = probarConexion();
        System.out.println(ok
                ? "Conexión a la base de datos aulas_ufg: OK"
                : "Conexión a la base de datos aulas_ufg: FALLÓ");
        cerrar();
        System.exit(ok ? 0 : 1);
    }
}
