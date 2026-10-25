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
@Table(name = "aula",
        uniqueConstraints = @UniqueConstraint(name = "uk_aula_nombre_edificio",
                columnNames = {"nombre", "edificio"}))
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String edificio;

    @Column(nullable = false)
    private int planta;

    @Column(nullable = false)
    private int capacidad;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_aula_id", nullable = false)
    private TipoAula tipoAula;

    @Column(nullable = false, length = 100)
    private String responsable;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "estado_id", nullable = false)
    private EstadoAula estado;

    public Aula() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEdificio() { return edificio; }
    public void setEdificio(String edificio) { this.edificio = edificio; }
    public int getPlanta() { return planta; }
    public void setPlanta(int planta) { this.planta = planta; }
    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
    public TipoAula getTipoAula() { return tipoAula; }
    public void setTipoAula(TipoAula tipoAula) { this.tipoAula = tipoAula; }
    public String getResponsable() { return responsable; }
    public void setResponsable(String responsable) { this.responsable = responsable; }
    public EstadoAula getEstado() { return estado; }
    public void setEstado(EstadoAula estado) { this.estado = estado; }
}
