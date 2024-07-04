package com.example.nutriwatch4;

public class variables {
    private String nombre;
    private String apellido;
    private String correo;
    private String fecnac;
    private String pais;

    public variables(String nombre, String apellido,String correo, String fecnac,String pais){
        this.nombre=nombre;
        this.apellido=apellido;
        this.correo=correo;
        this.fecnac=fecnac;
        this.pais=pais;

    }
    //GET

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getFecnac() {
        return fecnac;
    }

    public String getPais() {
        return pais;
    }
    //SET

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setFecnac(String fecnac) {
        this.fecnac = fecnac;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
