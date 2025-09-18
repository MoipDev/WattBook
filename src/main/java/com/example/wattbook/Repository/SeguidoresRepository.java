package com.example.wattbook.Repository;

import com.example.wattbook.Dto.UsuarioDTO;
import com.example.wattbook.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.wattbook.Entity.Seguidores;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeguidoresRepository extends JpaRepository<Seguidores, Long> {
    void deleteBySeguidorIdAndUsuarioId(Long seguidorId, Long usuarioId);
    void deleteByUsuarioIdAndSeguidorId(Long usuarioId, Long seguidorId);
    List<Seguidores> findByUsuarioId(Long usuarioId);
    List<Seguidores> findBySeguidorId(Long seguidorId); // Método invertido
}