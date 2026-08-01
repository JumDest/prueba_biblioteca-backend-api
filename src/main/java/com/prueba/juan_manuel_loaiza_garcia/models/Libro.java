package com.prueba.juan_manuel_loaiza_garcia.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    
    @Column(unique = true, nullable = false)
    private String isbn;
    
    private String edicion;
    private LocalDate fechaPublicacion;
    private String autor;

    // Relación One-to-Many con Préstamo exigida en la prueba
    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Prestamo> prestamos;
    
    // Relación One-to-Many con Ejemplar (nuestra estrategia Senior)
    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("libro")
    private List<Ejemplar> ejemplares;
}