package com.prueba.juan_manuel_loaiza_garcia.services;

import com.prueba.juan_manuel_loaiza_garcia.models.Ejemplar;
import com.prueba.juan_manuel_loaiza_garcia.models.Prestamo;
import com.prueba.juan_manuel_loaiza_garcia.repositories.EjemplarRepository;
import com.prueba.juan_manuel_loaiza_garcia.repositories.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private EjemplarRepository ejemplarRepository;

    // Registrar un préstamo
    public Prestamo registrarPrestamo(Prestamo prestamo) {
        // 1. Restricción: máximo 1 préstamo activo por usuario
        long prestamosActivos = prestamoRepository.countByUsuarioIdAndEstadoPrestamo(
                prestamo.getUsuario().getId(), "ACTIVO");
        
        if (prestamosActivos >= 1) {
            throw new RuntimeException("El usuario ya tiene un préstamo activo.");
        }

        // 2. Restricción de Fechas: Máximo 30 días de préstamo
        LocalDate hoy = LocalDate.now();
        if (prestamo.getFechaDevolucion().isBefore(hoy)) {
            throw new RuntimeException("La fecha de devolución no puede ser anterior a hoy.");
        }
        if (prestamo.getFechaDevolucion().isAfter(hoy.plusDays(30))) {
            throw new RuntimeException("El tiempo máximo de préstamo es de 30 días.");
        }

        // 3. Obtener ejemplar y marcarlo como PRESTADO
        Ejemplar ejemplar = ejemplarRepository.findById(prestamo.getEjemplar().getId())
                .orElseThrow(() -> new RuntimeException("El ejemplar especificado no existe."));
        
        ejemplar.setEstado("PRESTADO");
        ejemplarRepository.save(ejemplar);

        // ---------------------------------------------------------------------
        // LÍNEA CLAVE AÑADIDA: Vincula el Libro del ejemplar al Préstamo
        // Esto evita que libro_id sea null al realizar el INSERT en la BD
        // ---------------------------------------------------------------------
        prestamo.setLibro(ejemplar.getLibro());

        prestamo.setEjemplar(ejemplar);
        prestamo.setEstadoPrestamo("ACTIVO");
        return prestamoRepository.save(prestamo);
    }

    // Método: Devolver Préstamo
    public Prestamo devolverPrestamo(Long id) {
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El préstamo no existe."));

        prestamo.setEstadoPrestamo("DEVUELTO");

        // Liberar el ejemplar asignado
        Ejemplar ejemplar = prestamo.getEjemplar();
        if (ejemplar != null) {
            ejemplar.setEstado("DISPONIBLE");
            ejemplarRepository.save(ejemplar);
        }

        return prestamoRepository.save(prestamo);
    }

    public List<Prestamo> listarTodos() {
        List<Prestamo> prestamos = prestamoRepository.findAll();
        return actualizarEstadosDinamicos(prestamos);
    }

    public List<Prestamo> listarPorUsuario(Long usuarioId) {
        List<Prestamo> prestamos = prestamoRepository.findByUsuarioId(usuarioId);
        return actualizarEstadosDinamicos(prestamos);
    }

    public List<Prestamo> listarPorLibro(Long libroId) {
        List<Prestamo> prestamos = prestamoRepository.findByEjemplarLibroId(libroId);
        return actualizarEstadosDinamicos(prestamos);
    }

    private List<Prestamo> actualizarEstadosDinamicos(List<Prestamo> prestamos) {
        LocalDate hoy = LocalDate.now();
        for (Prestamo p : prestamos) {
            if ("ACTIVO".equals(p.getEstadoPrestamo())) {
                if (hoy.isAfter(p.getFechaDevolucion())) {
                    p.setEstadoPrestamo("VENCIDO");
                    prestamoRepository.save(p);
                }
            }
        }
        return prestamos;
    }
}