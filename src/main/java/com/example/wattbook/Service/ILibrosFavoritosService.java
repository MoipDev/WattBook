package com.example.wattbook.Service;

import com.example.wattbook.Dto.LibrosFavoritosDTO;
import java.util.List;

public interface ILibrosFavoritosService {
    List<LibrosFavoritosDTO> findAll();
    void deleteById(Long id);
    LibrosFavoritosDTO addLibroFavorito(LibrosFavoritosDTO libroFavoritoDTO);
    List<LibrosFavoritosDTO> findByUsuarioId(Long usuarioId);
    void deleteByUserIdAndLibroId(Long userId, Long libroId);
}