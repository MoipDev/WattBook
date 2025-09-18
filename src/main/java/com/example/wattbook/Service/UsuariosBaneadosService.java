package com.example.wattbook.Service;

import com.example.wattbook.Entity.UsuariosBaneados;
import com.example.wattbook.Entity.Usuario;
import com.example.wattbook.Repository.UsuarioRepository;
import com.example.wattbook.Repository.UsuariosBaneadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UsuariosBaneadosService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuariosBaneadosRepository usuariosBaneadosRepository;

    @Transactional
    public void banearUsuario(Long usuarioId, String motivoBaneo) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            UsuariosBaneados usuarioBaneado = new UsuariosBaneados();
            usuarioBaneado.setUsuarioId(usuario);
            usuarioBaneado.setFechaBaneo(LocalDate.now());
            usuarioBaneado.setMotivoBaneo(motivoBaneo);
            usuariosBaneadosRepository.save(usuarioBaneado);
        } else {
            System.out.printf("Usuario no encontrado con ID: " + usuarioId);
        }
    }

    @Transactional
    public void eliminarBaneo(Usuario usuario) {
        usuariosBaneadosRepository.deleteByUsuarioId(usuario);
    }

    public List<UsuariosBaneados> getAllUsuariosBaneados() {
        return usuariosBaneadosRepository.findAll();
    }

    public Optional<UsuariosBaneados> getUsuarioBaneadoByUsuarioId(Usuario usuarioId) {
        return usuariosBaneadosRepository.findByUsuarioId(usuarioId);
    }
}