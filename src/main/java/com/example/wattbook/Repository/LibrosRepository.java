package com.example.wattbook.Repository;

import com.example.wattbook.Dto.LibroDTO;
import com.example.wattbook.Entity.Libros;
import com.example.wattbook.Enums.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LibrosRepository extends JpaRepository<Libros, Long>, JpaSpecificationExecutor<Libros> {

    @Query("SELECT new com.example.wattbook.Dto.LibroDTO(" +
            "l.id, l.nombre, l.descripcion, l.generos, l.imagen, l.autorId.id,a.username, " +
            "SUM(CASE WHEN v.tipoVoto = true THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN v.tipoVoto = false THEN 1 ELSE 0 END)) " +
            "FROM Libros l LEFT JOIN Votos v ON l.id = v.libroId.id " +
            "LEFT JOIN l.autorId a " +
            "GROUP BY l.id, l.nombre, l.descripcion, l.generos, l.imagen, l.autorId.id,a.username " +
            "ORDER BY SUM(CASE WHEN v.tipoVoto = true THEN 1 ELSE 0 END ) DESC")
    List<LibroDTO> obtenerLibrosYVotos();



    @Query("SELECT new com.example.wattbook.Dto.LibroDTO(" +
            "l.id, l.nombre, l.descripcion, l.generos, l.imagen, l.autorId.id,a.username, " +
            "SUM(CASE WHEN v.tipoVoto = true THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN v.tipoVoto = false THEN 1 ELSE 0 END)) " +
            "FROM Libros l LEFT JOIN Votos v ON l.id = v.libroId.id " +
            "LEFT JOIN l.autorId a " +
            "WHERE l.autorId.id = :idAutor" +
            " GROUP BY l.id, l.nombre, l.descripcion, l.generos, l.imagen, l.autorId.id,a.username " +
            "ORDER BY SUM(CASE WHEN v.tipoVoto = true THEN 1 ELSE 0 END ) DESC")
    List<LibroDTO> obtenerLibrosYVotosporidauthor(@Param("idAutor")Long idAutor);

    List<Libros> findByGeneros(Genero genero);


}