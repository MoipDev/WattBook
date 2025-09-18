package com.example.wattbook.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class VerificacionDTO {
    private String codigoVerificacion;
    private String username;


    public VerificacionDTO(String codigoVerificacion, String username) {
        this.codigoVerificacion = codigoVerificacion;
        this.username = username;
    }


}
