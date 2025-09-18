package com.example.wattbook.Repository;

import com.example.wattbook.Entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long>, JpaSpecificationExecutor<Chat> {
    @Query("SELECT c FROM Chat c JOIN c.chatUsuarios cu JOIN cu.usuarioId u WHERE u.id = :usuarioId")
    List<Chat> findChatsByUsuario(Long usuarioId);

}