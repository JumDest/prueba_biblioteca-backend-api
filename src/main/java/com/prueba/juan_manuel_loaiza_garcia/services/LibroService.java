package com.prueba.juan_manuel_loaiza_garcia.services;

import com.prueba.juan_manuel_loaiza_garcia.models.Libro;
import com.prueba.juan_manuel_loaiza_garcia.models.Ejemplar;
import com.prueba.juan_manuel_loaiza_garcia.repositories.LibroRepository;
import com.prueba.juan_manuel_loaiza_garcia.repositories.EjemplarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;
    
    @Autowired
    private EjemplarRepository ejemplarRepository;

    public Libro crearLibro(Libro libro) {
        return libroRepository.save(libro); // Crear libro
    }

    public List<Libro> listarLibros() {
        return libroRepository.findAll(); // Listar libros
    }

    public Optional<Libro> consultarPorId(Long id) {
        return libroRepository.findById(id); // Consultar libro por id
    }

    public Libro actualizarLibro(Long id, Libro libroActualizado) { // Actualizar libro[cite: 1]
        return libroRepository.findById(id).map(libro -> {
            libro.setTitulo(libroActualizado.getTitulo());
            libro.setIsbn(libroActualizado.getIsbn());
            libro.setEdicion(libroActualizado.getEdicion());
            libro.setFechaPublicacion(libroActualizado.getFechaPublicacion());
            libro.setAutor(libroActualizado.getAutor());
            return libroRepository.save(libro);
        }).orElseThrow(() -> new RuntimeException("Libro no encontrado"));
    }

    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id); // Eliminar libro[cite: 1]
    }

    // Listar ejemplares disponibles por ISBN
    public List<Ejemplar> listarEjemplaresDisponiblesPorIsbn(String isbn) {
        return ejemplarRepository.findByLibroIsbnAndEstado(isbn, "DISPONIBLE");
    }
}