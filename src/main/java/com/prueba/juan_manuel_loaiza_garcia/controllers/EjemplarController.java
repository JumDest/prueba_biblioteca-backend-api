package com.prueba.juan_manuel_loaiza_garcia.controllers;

import com.prueba.juan_manuel_loaiza_garcia.models.Ejemplar;
import com.prueba.juan_manuel_loaiza_garcia.models.Libro;
import com.prueba.juan_manuel_loaiza_garcia.repositories.EjemplarRepository;
import com.prueba.juan_manuel_loaiza_garcia.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ejemplares")
@CrossOrigin(origins = "*")
public class EjemplarController {

    @Autowired
    private EjemplarRepository ejemplarRepository;

    @Autowired
    private LibroRepository libroRepository;

    // Crear 1 o N ejemplares para un libro
    @PostMapping("/libro/{libroId}")
    public ResponseEntity<?> crearEjemplares(@PathVariable Long libroId, @RequestParam(defaultValue = "1") int cantidad) {
        Libro libro = libroRepository.findById(libroId).orElse(null);
        if (libro == null) {
            return ResponseEntity.badRequest().body("El libro no existe.");
        }

        for (int i = 0; i < cantidad; i++) {
            Ejemplar ejemplar = new Ejemplar();
            ejemplar.setEstado("DISPONIBLE");
            ejemplar.setLibro(libro);
            ejemplarRepository.save(ejemplar);
        }

        return ResponseEntity.ok("Se crearon " + cantidad + " ejemplares exitosamente.");
    }

    // Listar todos los ejemplares
    @GetMapping
    public List<Ejemplar> listarEjemplares() {
        return ejemplarRepository.findAll();
    }

    // =========================================================
    // NUEVO MÉTODO: ELIMINAR EJEMPLAR POR ID
    // =========================================================
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEjemplar(@PathVariable Long id) {
        Ejemplar ejemplar = ejemplarRepository.findById(id).orElse(null);
        
        if (ejemplar == null) {
            return ResponseEntity.notFound().build();
        }

        // Validación en Backend para evitar eliminar ejemplares en préstamo
        if ("PRESTADO".equalsIgnoreCase(ejemplar.getEstado())) {
            return ResponseEntity.badRequest().body("No se puede eliminar un ejemplar que se encuentra actualmente PRESTADO.");
        }

        ejemplarRepository.delete(ejemplar);
        return ResponseEntity.ok("Ejemplar eliminado exitosamente.");
    }
}