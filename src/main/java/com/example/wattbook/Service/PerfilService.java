package com.example.wattbook.Service;


import com.example.wattbook.Repository.PerfilRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import com.example.wattbook.Entity.Perfil;

@Service
@AllArgsConstructor
@Validated
public class PerfilService implements IPerfilService {
    @Autowired
    private PerfilRepository perfilRepository;
    public Perfil guardarPerfil(Perfil perfil){
        return perfilRepository.save(perfil);
    }
}
