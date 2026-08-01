package com.prueba.juan_manuel_loaiza_garcia.repositories;

import com.prueba.juan_manuel_loaiza_garcia.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}