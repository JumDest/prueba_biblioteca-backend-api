package com.prueba.juan_manuel_loaiza_garcia.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "ejemplares")
public class Ejemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String estado; 

    @ManyToOne
    @JoinColumn(name = "libro_id", nullable = false)
    @JsonIgnore
    private Libro libro;

    // --- CONSTRUCTOR ---
    public Ejemplar() {
        this.estado = "DISPONIBLE"; 
    }

    
    public Long getId() { 
        return id; 
    }
    
    public void setId(Long id) { 
        this.id = id; 
    }

    public String getEstado() { 
        return estado; 
    }
    
    public void setEstado(String estado) { 
        this.estado = estado; 
    }

    public Libro getLibro() { 
        return libro; 
    }
    
    public void setLibro(Libro libro) { 
        this.libro = libro; 
    }
}