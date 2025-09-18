package com.example.wattbook.Service;

import com.example.wattbook.Dto.ComentarioDto;
import com.example.wattbook.Entity.Comentarios;
import com.example.wattbook.Entity.Libros;
import com.example.wattbook.Entity.Perfil;
import com.example.wattbook.Entity.Usuario;
import com.example.wattbook.Repository.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.wattbook.Repository.ComentariosRepository;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ComentariosService implements IComentariosService {

    @Autowired
    private ComentariosRepository comentariosRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private LibrosRepository librosRepository;
    @Autowired
    private PerfilRepository perfilRepository;

    public List<ComentarioDto> getAllComentarios() {
        return comentariosRepository.findAll().stream().map(this::convDto).collect(Collectors.toList());
    }
    @Override
    public void deleteById(Long id) {
        comentariosRepository.deleteById(id);
    }

    public void agregarComentario(ComentarioDto comentarioDto) {
        Usuario usuario = usuarioRepository.findById(comentarioDto.getUsuarioId()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Libros libro = librosRepository.findById(comentarioDto.getLibroId()).orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        Comentarios comentario = new Comentarios();
        comentario.setUsuarioId(usuario);
        comentario.setLibroId(libro);
        comentario.setComentario(comentarioDto.getComentario());
        comentario.setFecha(new java.sql.Date(comentarioDto.getFecha().getTime()));

        comentariosRepository.save(comentario);

    }
    public List<ComentarioDto> obtenerComentariosPorLibro(Long libroId) {
        List<Comentarios> comentarios = comentariosRepository.findByLibroId_Id(libroId);
        return comentarios.stream().map(this::convDto).collect(Collectors.toList());
    }

    private ComentarioDto convDto(Comentarios comentario) {
        ComentarioDto comentarioDto = new ComentarioDto();
        comentarioDto.setUsername(comentario.getUsuarioId().getUsername());
        comentarioDto.setLibroId(comentario.getLibroId().getId());
        comentarioDto.setComentario(comentario.getComentario());
        comentarioDto.setFecha(comentario.getFecha());
        Perfil perfil = perfilRepository.findByUsuario_Id(comentario.getUsuarioId().getId());
        comentarioDto.setImagen(perfil != null ? perfil.getImagen() : null);
        return comentarioDto;
    }

    public long contarComentariosPorLibro(Long libroId) {
        return comentariosRepository.countByLibroId(libroId);
    }

}