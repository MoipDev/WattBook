package com.example.wattbook.Dto;

import com.example.wattbook.Enums.Genero;
import lombok.Data;


@Data
public class RegistroDTO {

    private Long id;
    private String nombre;
    private String apellidos;
    private String descripcion;
    private String imagen;
    private String generos;
    private String email;


    private String username;
    private String password;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getGeneros() {
        return generos;
    }

    public void setGeneros(String generos) {
        this.generos = generos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RegistroDTO(Long id, String nombre, String apellidos, String descripcion, String imagen, Genero generos, String email, String username, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.generos = generos.name();
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
