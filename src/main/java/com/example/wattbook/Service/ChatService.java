package com.example.wattbook.Service;

import com.example.wattbook.Dto.ChatDTO;
import com.example.wattbook.Entity.Chat;
import com.example.wattbook.Entity.Libros;
import com.example.wattbook.Repository.ChatRepository;
import com.example.wattbook.Repository.LibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private LibrosRepository librosRepository;

    public Chat crearChat(ChatDTO chatDTO) {
        Libros libro = librosRepository.findById(chatDTO.getLibroId())
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        Chat chat = new Chat();
        chat.setNombre(chatDTO.getNombre());
        chat.setDescripcion(chatDTO.getDescripcion());
        chat.setImagen(chatDTO.getImagen());
        chat.setLibroId(libro);

        return chatRepository.save(chat);
    }

    public List<Chat> obtenerChatsPorUsuario(Long usuarioId) {
        return chatRepository.findChatsByUsuario(usuarioId);
    }

    public ChatDTO obtenerChatPorId(Long chatId) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new RuntimeException("Chat no encontrado"));

        return ChatDTO.builder()
                .id(chat.getId())
                .nombre(chat.getNombre())
                .descripcion(chat.getDescripcion())
                .imagen(chat.getImagen())
                .libroId(chat.getLibroId().getId())
                .build();
    }

}
