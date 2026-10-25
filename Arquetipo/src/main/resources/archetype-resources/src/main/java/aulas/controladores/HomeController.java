#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ${package}.aulas.controladores;


import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 *
 * @author Tatia
 */
public class HomeController {
    
    @FXML private TextField txtNombre, txtEdificio, txtCapacidad, txtResponsable;
    @FXML private ComboBox<String> cmbPlanta, cmbTipoAula, cmbEstado;

    @FXML private void guardar() { /* TODO (Alex) */ }
    @FXML private void limpiar() { /* TODO (Alex) */ }
    @FXML private void cerrarSesion() { /* TODO (Alex) */ }
    
}
