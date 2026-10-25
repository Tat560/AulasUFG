/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package edu.ufg.aulas;

/**
 *
 * @author Tatiana Ascencio
 */

import edu.ufg.aulas.entidades.Usuario;
import edu.ufg.aulas.util.DatosIniciales;
import edu.ufg.aulas.util.HibernateUtil;
import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class App extends Application {

    private static Stage stage;

    /** Usuario que inicio sesion (null si no hay sesion). */
    public static Usuario usuarioActual;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        try {
            DatosIniciales.cargar();
        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR,
                    "No se pudo conectar a la base de datos.\nVerifica que MySQL esté encendido y que "
                    + "usuario/clave en hibernate.cfg.xml sean correctos.\n\nDetalle: " + e.getMessage());
            alerta.showAndWait();
            Platform.exit();
            return;
        }
        cambiarVista("Login", "Aulas UFG - Iniciar sesión");
        stage.show();
    }

    public static void cambiarVista(String nombre, String titulo) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/vistas/" + nombre + ".fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.setTitle(titulo);
        stage.centerOnScreen();
    }

    @Override
    public void stop() {
        HibernateUtil.cerrar();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
