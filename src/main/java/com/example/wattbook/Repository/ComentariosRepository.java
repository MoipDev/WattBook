package com.example.wattbook.Repository;

import com.example.wattbook.Entity.Comentarios;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.wattbook.Entity.Comentarios;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComentariosRepository extends JpaRepository<Comentarios, Long> {
    List<Comentarios> findByLibroId_Id(Long libroId);
    @Query("SELECT COUNT(c) FROM Comentarios c WHERE c.libroId.id = :libroId")
    long countByLibroId(@Param("libroId") Long libroId);
}