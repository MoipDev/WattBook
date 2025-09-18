package com.example.wattbook.Dto;

import com.example.wattbook.Enums.Genero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LibroGeneroDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private String generos;
    private String imagen;
    private Long autorId;
    private Long likes;
    private Long dislikes;
    private String username;


    public LibroGeneroDto() {
    }


    public LibroGeneroDto(Long id, String nombre, String descripcion, Genero generos, String imagen, Long autorId, String username, Long likes, Long dislikes) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.generos = generos.name();
        this.imagen = imagen;
        this.autorId = autorId;
        this.likes = likes;
        this.dislikes = dislikes;
        this.username = username;
    }

   }