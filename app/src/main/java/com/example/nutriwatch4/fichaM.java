package com.example.nutriwatch4;

public class fichaM{
    private String idM;
    private String comidasC;
    private String enfermedades;
    private String caloriasC;
    private String medicamentos;
    private String telefono;
    private String Nutriologo;
    private String nNutriologo;

    public fichaM(String idM, String comidasC,String enfermedades,String caloriasC, String medicamentos, String telefono, String Nutriologo, String nNutriologo){
        this.idM=idM;
        this.comidasC=comidasC;
        this.enfermedades=enfermedades;
        this.caloriasC=caloriasC;
        this.medicamentos=medicamentos;
        this.telefono=telefono;
        this.Nutriologo=Nutriologo;
        this.nNutriologo=nNutriologo;
    }
//GET
    public String getIdM() {
        return idM;
    }

    public String getEnfermedades() {
        return enfermedades;
    }

    public String getComidasC() {
        return comidasC;
    }

    public String getCaloriasC() {
        return caloriasC;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public String getnNutriologo() {
        return nNutriologo;
    }

    public String getNutriologo() {
        return Nutriologo;
    }

    public String getTelefono() {
        return telefono;
    }
    //SET

    public void setEnfermedades(String enfermedades) {
        this.enfermedades = enfermedades;
    }

    public void setCaloriasC(String caloriasC) {
        this.caloriasC = caloriasC;
    }

    public void setComidasC(String comidasC) {
        this.comidasC = comidasC;
    }

    public void setIdM(String idM) {
        this.idM = idM;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public void setnNutriologo(String nNutriologo) {
        this.nNutriologo = nNutriologo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setNutriologo(String nutriologo) {
        Nutriologo = nutriologo;
    }
}
