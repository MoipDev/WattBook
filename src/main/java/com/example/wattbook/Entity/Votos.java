package com.example.wattbook.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Data
@Entity
@Table(name = "votos")
public class Votos implements Serializable {

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

    @Column(name = "tipo_voto")
    private Boolean tipoVoto;


    public Boolean getTipoVoto() {
        return tipoVoto;
    }

    public void setTipoVoto(Boolean tipoVoto) {
        this.tipoVoto = tipoVoto;
    }

    public Libros getLibroId() {
        return libroId;
    }

    public void setLibroId(Libros libroId) {
        this.libroId = libroId;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }
}
