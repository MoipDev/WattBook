package com.example.wattbook.Controller;

import com.example.wattbook.Dto.LibrosFavoritosDTO;
import com.example.wattbook.Service.ILibrosFavoritosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/libros-favoritos")
public class LibrosFavoritosController {

    @Autowired
    private ILibrosFavoritosService librosFavoritosService;

    @GetMapping("/listaLibros")
    public ResponseEntity<List<LibrosFavoritosDTO>> getLibrosFavoritos() {
        List<LibrosFavoritosDTO> librosFavoritos = librosFavoritosService.findAll();
        return ResponseEntity.ok(librosFavoritos);
    }

    @DeleteMapping("/eliminarLibroFav")
    public ResponseEntity<Void> deleteOneLibroFavorito(@RequestBody LibrosFavoritosDTO libroFavoritoDTO) {
        if (libroFavoritoDTO.getUserId() == null || libroFavoritoDTO.getLibroId() == null) {
            return ResponseEntity.badRequest().build();
        }
        librosFavoritosService.deleteByUserIdAndLibroId(libroFavoritoDTO.getUserId(), libroFavoritoDTO.getLibroId());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/anyadirLibroFavorito")
    public ResponseEntity<LibrosFavoritosDTO> addLibroFavorito(@RequestBody LibrosFavoritosDTO libroFavoritoDTO) {
        if (libroFavoritoDTO.getUserId() == null || libroFavoritoDTO.getLibroId() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        LibrosFavoritosDTO nuevoLibroFavorito = librosFavoritosService.addLibroFavorito(libroFavoritoDTO);
        return ResponseEntity.ok(nuevoLibroFavorito);
    }

    @GetMapping("/yourFaves/{usuarioId}")
    public ResponseEntity<List<LibrosFavoritosDTO>> getLibrosFavoritosByUsuarioId(@PathVariable Long usuarioId) {
        List<LibrosFavoritosDTO> librosFavoritos = librosFavoritosService.findByUsuarioId(usuarioId);
        return ResponseEntity.ok(librosFavoritos);
    }
}