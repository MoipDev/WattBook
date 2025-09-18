package com.example.wattbook.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibroCreacionDTO {
    private String nombre;
    private String descripcion;
    private String imagen;
    private Long autorId;
    private String generos;
    private Date fechaPublicacion;

    // Getters y setters
}
