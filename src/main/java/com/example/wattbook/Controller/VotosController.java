package com.example.wattbook.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.wattbook.Entity.Libros;
import com.example.wattbook.Service.VotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/votos")
public class VotosController {
    @Autowired
    private VotosService votoService;

    @GetMapping("/top4")
    public ResponseEntity<List<Libros>> getTop4LibrosConMasLikes() {
        return ResponseEntity.ok(votoService.obtenerTop4LibrosConMasLikes());
    }
}
