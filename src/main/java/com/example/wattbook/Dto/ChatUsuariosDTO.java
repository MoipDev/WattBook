package com.example.wattbook.Dto;

import com.example.wattbook.Entity.Chat;
import com.example.wattbook.Entity.Usuario;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class ChatUsuariosDTO {
    private Long id;
    private Long chatId;
    private Long usuarioId;
}
