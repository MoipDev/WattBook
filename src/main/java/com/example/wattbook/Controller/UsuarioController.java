package com.example.wattbook.Controller;

import com.example.wattbook.Dto.UsuarioDTO;
import com.example.wattbook.Dto.VerificacionDTO;
import com.example.wattbook.Entity.Perfil;
import com.example.wattbook.Entity.Usuario;
import com.example.wattbook.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}/perfil")
    public ResponseEntity<Perfil> obtenerPerfilUsuario(@PathVariable Long id) {
        Perfil perfil = usuarioService.obtenerPerfil(id);
        System.out.println(perfil);
        return ResponseEntity.ok(perfil);
    }

    @GetMapping("/{authorId}/username")
    public ResponseEntity<String> getUsername(@PathVariable Long authorId) {
        String username = usuarioService.getUsernameById(authorId);
        return ResponseEntity.ok(username);
    }


    @GetMapping("/{id}/mensaje")
    public ResponseEntity<String> obtenerUsername(@PathVariable Long id) {
        String username = usuarioService.obtenerUsernamePorId(id);
        if (username != null) {
            return ResponseEntity.ok(username);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/rol/{id}")
    public ResponseEntity<String> obtenerRolPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario.getRol().name());
    }


    @PostMapping("/comprobarVerificacion/{id}")
    public ResponseEntity<Boolean> comprobarVerificacion(@PathVariable Long id) {
        boolean verificado = usuarioService.comprobarVerificaddo(id);
        return ResponseEntity.ok(verificado);
    }


    @GetMapping("/allPerfiles")
    public ResponseEntity<List<UsuarioDTO>> getAllPerfiles() {
        List<UsuarioDTO> perfiles = usuarioService.getAllPerfiles();
        return ResponseEntity.ok(perfiles);
    }

    @PostMapping("/verificar-codigo")
    public ResponseEntity<String> verificarCodigo(@RequestBody VerificacionDTO verificacionDTO) {
        boolean verificado = usuarioService.verificarCodigo(verificacionDTO);
        if (verificado) {
            return ResponseEntity.ok("Correo verificado exitosamente");
        } else {
            return ResponseEntity.status(400).body("Código de verificación incorrecto");
        }
    }


}
