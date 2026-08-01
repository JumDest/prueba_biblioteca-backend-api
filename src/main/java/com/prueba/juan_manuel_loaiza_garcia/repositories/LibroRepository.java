package com.prueba.juan_manuel_loaiza_garcia.repositories;

import com.prueba.juan_manuel_loaiza_garcia.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByIsbn(String isbn);
}