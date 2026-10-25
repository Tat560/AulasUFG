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
import edu.ufg.aulas.dao.AulaDAO;
import edu.ufg.aulas.dao.CatalogoDAO;
import edu.ufg.aulas.entidades.Aula;
import edu.ufg.aulas.entidades.EstadoAula;
import edu.ufg.aulas.entidades.TipoAula;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HomeController {

    @FXML private Label lblUsuario;
    @FXML private TextField txtNombre;
    @FXML private TextField txtEdificio;
    @FXML private ComboBox<Integer> cmbPlanta;
    @FXML private TextField txtCapacidad;
    @FXML private ComboBox<TipoAula> cmbTipoAula;
    @FXML private TextField txtResponsable;
    @FXML private ComboBox<EstadoAula> cmbEstado;

    private final CatalogoDAO catalogoDAO = new CatalogoDAO();
    private final AulaDAO aulaDAO = new AulaDAO();

    /** Se ejecuta automaticamente al cargar Home.fxml. */
    @FXML
    private void initialize() {
        if (App.usuarioActual != null) {
            lblUsuario.setText("Sesión: " + App.usuarioActual.getCorreo());
        }
        try {
            cmbPlanta.setItems(FXCollections.observableArrayList(catalogoDAO.listarPlantas()));
            cmbTipoAula.setItems(FXCollections.observableArrayList(catalogoDAO.listarTipos()));
            cmbEstado.setItems(FXCollections.observableArrayList(catalogoDAO.listarEstados()));
        } catch (Exception e) {
            mostrar(Alert.AlertType.ERROR, "No se pudieron cargar los catálogos desde la base de datos.");
            e.printStackTrace();
        }
    }

    @FXML
    private void guardar() {
        String nombre = txtNombre.getText().trim();
        String edificio = txtEdificio.getText().trim();
        String capacidadTxt = txtCapacidad.getText().trim();
        String responsable = txtResponsable.getText().trim();
        Integer planta = cmbPlanta.getValue();
        TipoAula tipo = cmbTipoAula.getValue();
        EstadoAula estado = cmbEstado.getValue();

        // Validacion 1: ningun campo vacio
        if (nombre.isEmpty() || edificio.isEmpty() || capacidadTxt.isEmpty() || responsable.isEmpty()
                || planta == null || tipo == null || estado == null) {
            mostrar(Alert.AlertType.WARNING, "Todos los campos son obligatorios.");
            return;
        }

        // Validacion 2: planta y capacidad enteros > 0
        int capacidad;
        try {
            capacidad = Integer.parseInt(capacidadTxt);
        } catch (NumberFormatException e) {
            mostrar(Alert.AlertType.WARNING, "La capacidad debe ser un número entero.");
            return;
        }
        if (planta <= 0 || capacidad <= 0) {
            mostrar(Alert.AlertType.WARNING, "Planta y capacidad deben ser enteros mayores que 0.");
            return;
        }

        try {
            // Validacion 3: no repetir nombre en el mismo edificio
            if (aulaDAO.existe(nombre, edificio)) {
                mostrar(Alert.AlertType.WARNING,
                        "Ya existe un aula \"" + nombre + "\" en el edificio \"" + edificio + "\".");
                return;
            }

            Aula aula = new Aula();
            aula.setNombre(nombre);
            aula.setEdificio(edificio);
            aula.setPlanta(planta);
            aula.setCapacidad(capacidad);
            aula.setTipoAula(tipo);
            aula.setResponsable(responsable);
            aula.setEstado(estado);
            aulaDAO.guardar(aula);

            mostrar(Alert.AlertType.INFORMATION, "Aula guardada correctamente.");
            limpiar();
        } catch (Exception e) {
            mostrar(Alert.AlertType.ERROR, "No se pudo guardar el aula: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void limpiar() {
        txtNombre.clear();
        txtEdificio.clear();
        txtCapacidad.clear();
        txtResponsable.clear();
        cmbPlanta.getSelectionModel().clearSelection();
        cmbTipoAula.getSelectionModel().clearSelection();
        cmbEstado.getSelectionModel().clearSelection();
    }

    @FXML
    private void cerrarSesion() {
        try {
            App.usuarioActual = null;
            App.cambiarVista("Login", "Aulas UFG - Iniciar sesión");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrar(Alert.AlertType tipo, String mensaje) {
        Alert alerta = new Alert(tipo, mensaje);
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }
}
