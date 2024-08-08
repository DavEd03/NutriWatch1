package com.example.nutriwatch4;

public class variables {
    private String nombre;
    private String correo;

    private String edad;
    private String Ciudad;

    public variables(String nombre, String correo, String edad, String Ciudad){
        this.nombre=nombre;
        this.correo=correo;
        this.edad= edad;
        this.Ciudad= Ciudad;

    }
    //GET- Method

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getEdad() {
        return edad;
    }

    public String getCiudad() {
        return Ciudad;
    }
    //SET -METHOD

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public void setEdad(String edad) {
        this.edad = edad;
    }

    public void setCiudad(String ciudad) {
        this.Ciudad = ciudad;
    }
}
