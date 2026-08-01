package com.prueba.juan_manuel_loaiza_garcia.repositories;

import com.prueba.juan_manuel_loaiza_garcia.models.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    // Requerimientos: Listar préstamos por usuario y por libro[cite: 1]
    List<Prestamo> findByUsuarioId(Long usuarioId);
    List<Prestamo> findByEjemplarLibroId(Long libroId);
    
    // Para validar que un usuario no tenga más de un ejemplar con préstamo activo[cite: 1]
    long countByUsuarioIdAndEstadoPrestamo(Long usuarioId, String estadoPrestamo);
}