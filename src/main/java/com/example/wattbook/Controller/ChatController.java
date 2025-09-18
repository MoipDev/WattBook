package com.example.wattbook.Controller;

import com.example.wattbook.Dto.ChatDTO;
import com.example.wattbook.Entity.Chat;
import com.example.wattbook.Service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/crear")
    public Chat crearChat(@RequestBody ChatDTO chatDTO) {
        return chatService.crearChat(chatDTO);
    }

    @GetMapping("/listar/{usuarioId}")
    public List<Chat> obtenerChatsPorUsuario(@PathVariable Long usuarioId) {
        return chatService.obtenerChatsPorUsuario(usuarioId);
    }

    @GetMapping("/{chatId}")
    public ChatDTO obtenerChatPorId(@PathVariable Long chatId) {
        return chatService.obtenerChatPorId(chatId);
    }
}