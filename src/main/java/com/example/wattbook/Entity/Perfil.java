package com.example.wattbook.Entity;

import com.example.wattbook.Enums.Genero;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Entity
@Table(name = "perfil")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Perfil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "email", unique = true)
    private String email;

    @Column(columnDefinition = "TEXT", name = "imagen")
    private String imagen;


    @Enumerated(EnumType.STRING)
    @Column(name = "generos",nullable = false)
    private Genero generos;


    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private Usuario usuario;



    @Override
    public String toString() {
        return "Perfil{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", email='" + email + '\'' +
                ", imagen='" + imagen + '\'' +
                ", generos=" + generos +
                '}';
    }


}
