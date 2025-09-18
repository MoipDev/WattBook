package com.example.wattbook.Service;

import com.example.wattbook.Dto.LibrosFavoritosDTO;
import com.example.wattbook.Entity.Libros;
import com.example.wattbook.Entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.wattbook.Repository.LibrosFavoritosRepository;
import com.example.wattbook.Entity.LibrosFavoritos;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibrosFavoritosService implements ILibrosFavoritosService {

    @Autowired
    private LibrosFavoritosRepository librosFavoritosRepository;

    @Override
    public List<LibrosFavoritosDTO> findAll() {
        return librosFavoritosRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        librosFavoritosRepository.deleteById(id);
    }

    @Override
    public LibrosFavoritosDTO addLibroFavorito(LibrosFavoritosDTO libroFavoritoDTO) {
        LibrosFavoritos libroFavorito = new LibrosFavoritos();
        Usuario usuario = new Usuario();
        usuario.setId(libroFavoritoDTO.getUserId());
        libroFavorito.setUsuarioId(usuario);

        Libros libro = new Libros();
        libro.setId(libroFavoritoDTO.getLibroId());
        libroFavorito.setLibroId(libro);

        return convertToDTO(librosFavoritosRepository.save(libroFavorito));
    }

    @Override
    public List<LibrosFavoritosDTO> findByUsuarioId(Long usuarioId) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        return librosFavoritosRepository.findByUsuarioId(usuario).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteByUserIdAndLibroId(Long userId, Long libroId) {
        Usuario usuario = new Usuario();
        usuario.setId(userId);
        Libros libro = new Libros();
        libro.setId(libroId);
        librosFavoritosRepository.deleteByUsuarioIdAndLibroId(usuario, libro);
    }

    private LibrosFavoritosDTO convertToDTO(LibrosFavoritos libroFavorito) {
        LibrosFavoritosDTO dto = new LibrosFavoritosDTO();
        dto.setUserId(libroFavorito.getUsuarioId().getId());
        dto.setLibroId(libroFavorito.getLibroId().getId());
        return dto;
    }
}