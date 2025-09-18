package com.example.wattbook.Controller;


import com.example.wattbook.Dto.ChatMensajesDTO;
import com.example.wattbook.Entity.Chat;
import com.example.wattbook.Entity.ChatMensajes;
import com.example.wattbook.Service.ChatMensajesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chatmensaje")
public class ChatMensajesController {

    @Autowired
    private ChatMensajesService chatMensajesService;

    @PostMapping("/crear")
    public ChatMensajes crearMensaje(@RequestBody ChatMensajesDTO chatMensajesDTO) {
        return chatMensajesService.crearMensaje(chatMensajesDTO);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarMensaje(@PathVariable Long id) {
        chatMensajesService.eliminarMensaje(id);
    }

    @GetMapping("/ver/{chatId}")
    public List<ChatMensajes> verMensajesDeChat(@PathVariable Chat chatId) {
        return chatMensajesService.verMensajesDeChat(chatId);
    }

    @GetMapping("/chat/{chatId}")
    public List<ChatMensajesDTO> obtenerMensajesDeChat(@PathVariable Long chatId) {
        return chatMensajesService.obtenerMensajesDeChat(chatId);
    }

}