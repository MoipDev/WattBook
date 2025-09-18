package com.example.wattbook.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@Data
@Entity
@Table(name = "comentarios")
public class Comentarios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuarioId;

    @ManyToOne
    @JoinColumn(name = "libro_id", nullable = false)
    private Libros libroId;

    @Column(name = "comentario", nullable = false)
    private String comentario;

    @Column(name = "fecha")
    private Date fecha;


    @PrePersist
    protected void onCreate() {
        if (this.fecha == null) {
            this.fecha = Date.valueOf(LocalDate.now());
        }
    }

}
