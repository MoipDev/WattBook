package com.example.wattbook.Repository;

import com.example.wattbook.Entity.Votos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface VotosRepository extends JpaRepository<Votos, Long>, JpaSpecificationExecutor<Votos> {
    Optional<Votos> findByLibroId_IdAndUsuarioId_Id(Long libroId, Long usuarioId);

    @Query("SELECT v.libroId.id, COUNT(v.id) as likes FROM Votos v WHERE v.tipoVoto = true GROUP BY v.libroId.id ORDER BY likes DESC")
    List<Object[]> findTop4LibrosConMasLikes(Pageable pageable);
}
