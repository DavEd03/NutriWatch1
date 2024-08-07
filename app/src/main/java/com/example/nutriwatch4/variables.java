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
    private String talla;

    public variables(String nombre, String correo, String edad, String Ciudad, String peso,String imc, String estatura, String enfermedades, String talla ){
        this.nombre=nombre;
        this.correo=correo;
        this.edad= edad;
        this.Ciudad= Ciudad;
        this.imc=imc;
        this.estatura=estatura;
        this.peso=peso;
        this.enfermedades=enfermedades;
        this.talla=talla;

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

    public String getPeso(){return peso;}

    public String getImc(){return imc;}

    public String getEnfermedades() {
        return enfermedades;
    }

    public String getEstatura() {
        return estatura;
    }

    public String getTalla() {
        return talla;
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

    public void setEnfermedades(String enfermedades) {
        this.enfermedades = enfermedades;
    }

    public void setEstatura(String estatura) {
        this.estatura = estatura;
    }

    public void setImc(String imc) {
        this.imc = imc;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }
}
