package com.example.wattbook.Controller;

import com.example.wattbook.Dto.ChatUsuariosDTO;
import com.example.wattbook.Entity.ChatUsuarios;
import com.example.wattbook.Service.ChatUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RestController
@RequestMapping("/chatusuario")
public class ChatUsuariosController {

    @Autowired
    private ChatUsuariosService chatUsuariosService;

    @PostMapping("/agregar")
    public ChatUsuarios agregarUsuario(@RequestBody ChatUsuariosDTO chatUsuariosDTO) {
        return chatUsuariosService.agregarUsuarioAlChat(chatUsuariosDTO);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        chatUsuariosService.eliminarUsuarioDelChat(id);
    }

    @GetMapping("/usuarios/{chatId}")
    public List<ChatUsuarios> obtenerUsuariosEnChat(@PathVariable Long chatId) {
        return chatUsuariosService.obtenerUsuariosEnChat(chatId);
    }

    @GetMapping("/chats/{usuarioId}")
    public List<ChatUsuarios> obtenerChatsDeUsuario(@PathVariable Long usuarioId) {
        return chatUsuariosService.obtenerChatsDeUsuario(usuarioId);
    }
}
