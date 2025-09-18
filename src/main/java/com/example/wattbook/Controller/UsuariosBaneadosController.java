package com.example.wattbook.Controller;

import com.example.wattbook.Dto.BaneoDTO;
import com.example.wattbook.Entity.Usuario;
import com.example.wattbook.Entity.UsuariosBaneados;
import com.example.wattbook.Service.UsuarioService;
import com.example.wattbook.Service.UsuariosBaneadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/baneados")
public class UsuariosBaneadosController {

    @Autowired
    private UsuariosBaneadosService usuariosBaneadosService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/banearUsuario")
    public ResponseEntity<Void> banearUsuario(@RequestBody BaneoDTO baneoDTO) {
        usuariosBaneadosService.banearUsuario(baneoDTO.getUsuarioId(), baneoDTO.getMotivoBan());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/eliminarBaneo/{usuarioId}")
    public ResponseEntity<Void> eliminarBaneo(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioService.findById(usuarioId);
        if (usuario != null) {
            usuariosBaneadosService.eliminarBaneo(usuario);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getUsuariosBaneados")
    public ResponseEntity<List<UsuariosBaneados>> getUsuariosBaneados() {
        List<UsuariosBaneados> usuariosBaneados = usuariosBaneadosService.getAllUsuariosBaneados();
        return ResponseEntity.ok(usuariosBaneados);
    }

    @GetMapping("/getUsuarioBaneado/{usuarioId}")
    public ResponseEntity<UsuariosBaneados> getUsuarioBaneado(@PathVariable Usuario usuarioId) {
        Optional<UsuariosBaneados> usuarioBaneado = usuariosBaneadosService.getUsuarioBaneadoByUsuarioId(usuarioId);
        return usuarioBaneado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}