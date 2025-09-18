package com.example.wattbook.Dto;

import com.example.wattbook.Entity.Chat;
import com.example.wattbook.Entity.Usuario;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Data
@Builder
@Getter
@Setter
public class ChatMensajesDTO {
    private Long id;
    private Date fecha;
    private String mensaje;
    private Long chatId;
    private Long usuarioId;
    private String imagen;

}
