#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ${package}.aulas.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author Tatia
 */
public class LoginController {
    
    @FXML private TextField txtCorreo;
    @FXML private PasswordField txtClave;
    @FXML private Label lblMensaje;

    @FXML
    private void ingresar() {
        // TODO (Alex): validar contra la BD con Hibernate
    }
    
}
