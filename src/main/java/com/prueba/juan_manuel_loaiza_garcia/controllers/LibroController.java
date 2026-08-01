package com.prueba.juan_manuel_loaiza_garcia.controllers;

import com.prueba.juan_manuel_loaiza_garcia.models.Libro;
import com.prueba.juan_manuel_loaiza_garcia.models.Ejemplar;
import com.prueba.juan_manuel_loaiza_garcia.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
@CrossOrigin(origins = "*")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        return ResponseEntity.ok(libroService.crearLibro(libro));
    }

    @GetMapping
    public ResponseEntity<List<Libro>> listarLibros() {
        return ResponseEntity.ok(libroService.listarLibros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> consultarPorId(@PathVariable Long id) {
        return libroService.consultarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @RequestBody Libro libro) {
        return ResponseEntity.ok(libroService.actualizarLibro(id, libro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        libroService.eliminarLibro(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ejemplares/disponibles/{isbn}")
    public ResponseEntity<List<Ejemplar>> disponiblesPorIsbn(@PathVariable String isbn) {
        return ResponseEntity.ok(libroService.listarEjemplaresDisponiblesPorIsbn(isbn));
    }
}