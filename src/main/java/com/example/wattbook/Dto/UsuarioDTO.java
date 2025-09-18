package com.example.wattbook.Dto;

import com.example.wattbook.Enums.Genero;
import com.example.wattbook.Enums.Rol;
import lombok.*;

@Data
@Getter
@Setter
public class UsuarioDTO {
    private Long id;
    private Rol rol;
    private String username;
    private String password;

    UsuarioDTO() {
    }

    public UsuarioDTO(Long id, Rol rol, String username, String password) {
        this.id = id;
        this.rol = rol;
        this.username = username;
        this.password = password;
    }
}
