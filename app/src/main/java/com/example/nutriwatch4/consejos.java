package com.example.nutriwatch4;

public class consejos {
    public String titulo;
    public String descripcion;

    // Constructor vacío necesario para Firebase
    public consejos() {

    }

    // Constructor con parámetros
    public consejos(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
