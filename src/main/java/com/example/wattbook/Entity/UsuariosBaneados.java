package com.example.wattbook.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.time.*;

@Getter
@Setter
@Data
@Entity
@Table(name = "usuarios_baneados")
public class UsuariosBaneados implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuarioId;

    @Column(name = "fecha_baneo", nullable = false)
    private LocalDate fechaBaneo;

    @Column(name = "motivo_baneo", nullable = false)
    private String motivoBaneo;

}
