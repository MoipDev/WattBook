package com.example.wattbook.Dto;

import com.example.wattbook.Enums.Genero;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Data
@Getter
@Setter
public class LibroLeerDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private Genero generos;
    private Date fechaPublicacion;
    private String imagen;
    private Long autorId;
}