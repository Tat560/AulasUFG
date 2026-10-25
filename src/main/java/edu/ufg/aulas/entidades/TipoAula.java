/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package edu.ufg.aulas.entidades;

/**
 *
 * @author Maryorie Villalta
 */

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_aula")
public class TipoAula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 50)
    private String nombre;

    public TipoAula() {
    }

    public TipoAula(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    /** El ComboBox muestra este texto. */
    @Override
    public String toString() {
        return nombre;
    }
}
