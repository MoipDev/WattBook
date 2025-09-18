package com.example.wattbook.Repository;

import com.example.wattbook.Entity.LibrosLeyendo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LibrosLeyendoRepository extends JpaRepository<LibrosLeyendo, Long>, JpaSpecificationExecutor<LibrosLeyendo> {

}