package com.example.wattbook.Controller;

import com.example.wattbook.Dto.ComentarioDto;
import com.example.wattbook.Service.ComentariosService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "https://frontend-932v.onrender.com")
@Controller
@RequestMapping("/comentarios")
@RequiredArgsConstructor
public class ComentarioController {
    private final ComentariosService comentariosService;

    @PostMapping
    public ResponseEntity<Map<String, String>> agregarComentario(@RequestBody ComentarioDto comentarioDto) {
        comentariosService.agregarComentario(comentarioDto);

        return ResponseEntity.ok(Collections.singletonMap("mensaje", "Comentario agregado"));
    }


    @GetMapping("/libro/{libroId}")
    public ResponseEntity<List<ComentarioDto>> obtenerComentariosPorLibro(@PathVariable Long libroId) {
        List<ComentarioDto> comentarios = comentariosService.obtenerComentariosPorLibro(libroId);
        return ResponseEntity.ok(comentarios);
    }

    @GetMapping("/cont/{libroId}")
    public ResponseEntity<Long> contarComentarios(@PathVariable Long libroId) {
        long count = comentariosService.contarComentariosPorLibro(libroId);
        return ResponseEntity.ok(count);
    }



}
