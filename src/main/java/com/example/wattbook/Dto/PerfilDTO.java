package com.example.wattbook.Dto;


import com.example.wattbook.Enums.Genero;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class PerfilDTO {

    private Long id;
    private String nombre;
    private String apellidos;
    private String descripcion;
    private String imagen;
    private Genero generos;
    private String email;


}
