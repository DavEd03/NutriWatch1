package com.example.nutriwatch4;

public class variables {
    private String nombre;
    private String correo;
    private String edad;
    private String Ciudad;
    private String peso;
    private String imc;
    private String estatura;
    private String enfermedades;

    public variables(String nombre, String correo, String edad, String Ciudad, String peso,String imc, String estatura, String enfermedades ){
        this.nombre=nombre;
        this.correo=correo;
        this.edad= edad;
        this.Ciudad= Ciudad;
        this.imc=imc;
        this.estatura=estatura;
        this.peso=peso;
        this.enfermedades=enfermedades;

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
