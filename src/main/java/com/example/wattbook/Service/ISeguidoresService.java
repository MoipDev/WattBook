package com.example.wattbook.Service;

import com.example.wattbook.Dto.SeguidorDTO;
import java.util.List;

public interface ISeguidoresService {
    List<Long> getSeguidoresIds(Long usuarioId);
    void deleteById(Long seguidorId, Long usuarioId);
    SeguidorDTO addSeguidor(SeguidorDTO seguidorDTO);
    List<SeguidorDTO> findByUsuarioId(Long usuarioId);
    List<SeguidorDTO> findBySeguidorId(Long seguidorId); // Método invertido
}