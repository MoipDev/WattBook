package com.example.wattbook.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RespuestaDTO {
    public Integer estado;  // Estado HTTP, por ejemplo 200, 400, 403, etc.
    private String token;    // El token de acceso si es necesario
    private String mensaje;  // Mensaje de respuesta
    private Object cuerpo;   // Datos adicionales que se pueden incluir en la respuesta

    // Si tienes algún DTO adicional como 'SeguidoresDTO' puedes mantenerlo aquí, pero asegúrate de que sea público también
    @Data
    @Builder
    public static class SeguidoresDTO {
        private Long usuarioId;
        private Long seguidorId;

        // Getters y Setters generados por Lombok con la anotación @Data
    }
}
