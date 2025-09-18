package com.example.wattbook.Controller;

import com.example.wattbook.Dto.SeguidorDTO;
import com.example.wattbook.Dto.UsuarioDTO;
import com.example.wattbook.Service.ISeguidoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://wattbook.onrender.com")
@RestController
@RequestMapping("/seguidores")
public class SeguidoresController {

    @Autowired
    private ISeguidoresService seguidoresService;

    @GetMapping("/listaSeguidores/{usuarioId}")
    public ResponseEntity<List<Long>> listaSeguidores(@PathVariable Long usuarioId) {
        List<Long> seguidoresIds = seguidoresService.getSeguidoresIds(usuarioId);
        return ResponseEntity.ok(seguidoresIds);
    }

    @DeleteMapping("/eliminarSeguidor")
    public ResponseEntity<String> deleteOneSeguidor(@RequestBody SeguidorDTO eliminarSeguidorDTO) {
        Long seguidorId = eliminarSeguidorDTO.getSeguidorId();
        Long usuarioId = eliminarSeguidorDTO.getUserId();

        seguidoresService.deleteById(seguidorId, usuarioId);
        return ResponseEntity.ok("Follower with ID " + seguidorId + " for user with ID " + usuarioId + " has been deleted.");
    }

    @PostMapping("/anyadirSeguidor")
    public ResponseEntity<SeguidorDTO> addSeguidor(@RequestBody SeguidorDTO seguidorDTO) {
        if (seguidorDTO.getUserId() == null || seguidorDTO.getSeguidorId() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        SeguidorDTO nuevoSeguidor = seguidoresService.addSeguidor(seguidorDTO);
        return ResponseEntity.ok(nuevoSeguidor);
    }

    @GetMapping("/tusSeguidos/{seguidorId}")
    public ResponseEntity<List<SeguidorDTO>> getUsuariosPorSeguidorId(@PathVariable Long seguidorId) {
        List<SeguidorDTO> usuarios = seguidoresService.findBySeguidorId(seguidorId);
        return ResponseEntity.ok(usuarios);
    }
}