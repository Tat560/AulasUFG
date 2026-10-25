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

/** Catalogo de niveles (plantas) disponibles. Alimenta el ComboBox de planta. */
@Entity
@Table(name = "planta")
public class Planta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private int numero;

    public Planta() {
    }

    public Planta(int numero) {
        this.numero = numero;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    @Override
    public String toString() {
        return String.valueOf(numero);
    }
}
