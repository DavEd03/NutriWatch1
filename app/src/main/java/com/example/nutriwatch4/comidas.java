package com.example.nutriwatch4;

public class comidas {
    private String almuerzo;
    private String colacion1;
    private String comida;
    private String colacion2;
    private String cena;
    private int total;

    public comidas(String almuerzo, String colacion1, String comida,String colacion2,String cena, int total){
       this.almuerzo=almuerzo;
       this.colacion1=colacion1;
       this.comida=comida;
       this.colacion2=colacion2;
       this.cena= cena;
       this.total=total;
    }
    //SET
    public String getAlmuerzo() {
        return almuerzo;
    }

    public String getColacion1() {
        return colacion1;
    }

    public String getComida() {
        return comida;
    }

    public String getColacion2() {
        return colacion2;
    }

    public String getCena() {
        return cena;
    }

    public int getTotal() {
        return total;
    }
    //SET

    public void setAlmuerzo(String almuerzo) {
        this.almuerzo = almuerzo;
    }

    public void setColacion1(String colacion1) {
        this.colacion1 = colacion1;
    }

    public void setComida(String comida) {
        this.comida = comida;
    }

    public void setColacion2(String colacion2) {
        this.colacion2 = colacion2;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
