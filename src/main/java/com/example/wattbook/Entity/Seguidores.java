package com.example.wattbook.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import java.io.Serializable;

@Getter
@Setter
@Data
@Entity
@Table(name = "seguidores")
public class Seguidores implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "seguidor_id", nullable = false)
    private Long seguidorId;
}
