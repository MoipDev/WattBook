package com.example.wattbook.Service;

import com.example.wattbook.Dto.ChatUsuariosDTO;
import com.example.wattbook.Entity.Chat;
import com.example.wattbook.Entity.ChatUsuarios;
import com.example.wattbook.Entity.Usuario;
import com.example.wattbook.Repository.ChatRepository;
import com.example.wattbook.Repository.ChatUsuariosRepository;
import com.example.wattbook.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatUsuariosService {

    @Autowired
    private ChatUsuariosRepository chatUsuariosRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ChatRepository chatRepository;

    public ChatUsuarios agregarUsuarioAlChat(ChatUsuariosDTO chatUsuariosDTO) {
        Usuario usuario = usuarioRepository.findById(chatUsuariosDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Chat chat = chatRepository.findById(chatUsuariosDTO.getChatId())
                .orElseThrow(() -> new RuntimeException("Chat no encontrado"));

        if (chatUsuariosRepository.existsByChatIdAndUsuarioId(chat, usuario)) {
            throw new RuntimeException("El usuario ya está en el chat");
        }

        ChatUsuarios chatUsuarios = new ChatUsuarios();
        chatUsuarios.setUsuarioId(usuario);
        chatUsuarios.setChatId(chat);

        return chatUsuariosRepository.save(chatUsuarios);
    }

    public void eliminarUsuarioDelChat(Long chatUsuarioId) {
        chatUsuariosRepository.deleteById(chatUsuarioId);
    }

    public List<ChatUsuarios> obtenerUsuariosEnChat(Long chatId) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new RuntimeException("Chat no encontrado"));
        return chatUsuariosRepository.findByChatId(chat);
    }

    public List<ChatUsuarios> obtenerChatsDeUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return chatUsuariosRepository.findByUsuarioId(usuario);
    }
}
