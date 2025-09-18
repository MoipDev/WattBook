package com.example.wattbook.Service;

import com.example.wattbook.Entity.Libros;
import com.example.wattbook.Repository.LibrosRepository;
import com.example.wattbook.Repository.VotosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class VotosService {
    @Autowired
    private VotosRepository votoRepository;
    @Autowired
    private LibrosRepository libroRepository;

    public List<Libros> obtenerTop4LibrosConMasLikes() {
        Pageable top4 = PageRequest.of(0, 4);
        List<Object[]> resultados = votoRepository.findTop4LibrosConMasLikes(top4);

        return resultados.stream()
                .map(obj -> libroRepository.findById((Long) obj[0]).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
