package com.example.wattbook.Controller;

import com.example.wattbook.Dto.LoginDTO;
import com.example.wattbook.Dto.RegistroDTO;
import com.example.wattbook.Dto.RespuestaDTO;
import com.example.wattbook.Entity.Usuario;
import com.example.wattbook.Service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "https://frontend-932v.onrender.com")
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private UsuarioService service;


    @PostMapping("/registro/perfil")
    public Usuario registro(@RequestBody RegistroDTO registroDTO){
        return service.registrarUsuario(registroDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<RespuestaDTO> registro(@RequestBody LoginDTO dto){
        return service.login(dto);
    }


}