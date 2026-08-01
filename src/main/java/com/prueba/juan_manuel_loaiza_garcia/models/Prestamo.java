package com.prueba.juan_manuel_loaiza_garcia.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "prestamos")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    // Con @JsonIgnoreProperties evitamos que al traer el usuario intente traer los préstamos de ese usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonIgnoreProperties({"prestamos", "hibernateLazyInitializer", "handler"})
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "libro_id", nullable = false)
    @JsonIgnoreProperties({"ejemplares", "prestamos", "hibernateLazyInitializer", "handler"})
    private Libro libro;
    
    @ManyToOne
    @JoinColumn(name = "ejemplar_id", nullable = false)
    @JsonIgnoreProperties({"libro", "prestamos", "hibernateLazyInitializer", "handler"})
    private Ejemplar ejemplar;

    @Column(nullable = false)
    private String estadoPrestamo;
}