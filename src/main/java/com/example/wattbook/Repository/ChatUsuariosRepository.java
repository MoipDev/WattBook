package com.example.wattbook.Repository;

import com.example.wattbook.Entity.Chat;
import com.example.wattbook.Entity.ChatUsuarios;
import com.example.wattbook.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface ChatUsuariosRepository extends JpaRepository<ChatUsuarios, Long>, JpaSpecificationExecutor<ChatUsuarios> {

    List<ChatUsuarios> findByUsuarioId(Usuario usuarioId);

    List<ChatUsuarios> findByChatId(Chat chatId);

    boolean existsByChatIdAndUsuarioId(Chat chatId, Usuario usuarioId);
}
