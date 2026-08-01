package com.prueba.juan_manuel_loaiza_garcia.repositories;

import com.prueba.juan_manuel_loaiza_garcia.models.Ejemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EjemplarRepository extends JpaRepository<Ejemplar, Long> {
    
    List<Ejemplar> findByLibroIsbnAndEstado(String isbn, String estado);
    
}