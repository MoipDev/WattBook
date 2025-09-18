package com.example.wattbook.Repository;

import com.example.wattbook.Entity.Libros;
import com.example.wattbook.Entity.LibrosFavoritos;
import com.example.wattbook.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibrosFavoritosRepository extends JpaRepository<LibrosFavoritos, Long> {
    List<LibrosFavoritos> findByUsuarioId(Usuario usuarioId);
    void deleteByUsuarioIdAndLibroId(Usuario usuarioId, Libros libroId);
}