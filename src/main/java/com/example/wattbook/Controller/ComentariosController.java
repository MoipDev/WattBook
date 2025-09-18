package com.example.wattbook.Controller;

import com.example.wattbook.Service.IComentariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/comentarios")
public class ComentariosController {

    @Autowired
    private IComentariosService comentariosService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComentario(@PathVariable Long id) {
        comentariosService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}