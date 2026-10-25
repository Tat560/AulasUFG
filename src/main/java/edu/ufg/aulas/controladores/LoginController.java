/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package edu.ufg.aulas.controladores;

/**
 *
 * @author Tatiana Ascencio
 */

import edu.ufg.aulas.App;
import edu.ufg.aulas.dao.UsuarioDAO;
import edu.ufg.aulas.entidades.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField txtCorreo;
    @FXML private PasswordField txtClave;
    @FXML private Label lblError;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    private void ingresar() {
        String correo = txtCorreo.getText().trim();
        String clave = txtClave.getText();

        if (correo.isEmpty() || clave.isEmpty()) {
            lblError.setText("Ingresa tu correo y tu clave.");
            return;
        }

        try {
            Usuario usuario = usuarioDAO.autenticar(correo, clave);
            if (usuario == null) {
                lblError.setText("Correo o clave incorrectos.");
                return;
            }
            App.usuarioActual = usuario;
            App.cambiarVista("Home", "Aulas UFG - Registro de aulas");
        } catch (Exception e) {
            lblError.setText("Error de conexión con la base de datos.");
            e.printStackTrace();
        }
    }
}
