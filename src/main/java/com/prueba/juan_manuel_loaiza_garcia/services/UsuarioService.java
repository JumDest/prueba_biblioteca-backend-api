package com.prueba.juan_manuel_loaiza_garcia.services;

import com.prueba.juan_manuel_loaiza_garcia.models.Usuario;
import com.prueba.juan_manuel_loaiza_garcia.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Crear un usuario
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Listar todos los usuarios[cite: 1]
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Consultar un usuario por id[cite: 1]
    public Optional<Usuario> consultarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    // Actualizar un usuario[cite: 1]
    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNombre(usuarioActualizado.getNombre());
            usuario.setApellido(usuarioActualizado.getApellido());
            usuario.setEmail(usuarioActualizado.getEmail());
            usuario.setFechaNacimiento(usuarioActualizado.getFechaNacimiento());
            return usuarioRepository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // Eliminar un usuario[cite: 1]
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}