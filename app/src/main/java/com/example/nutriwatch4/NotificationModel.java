package com.example.nutriwatch4;

public class NotificationModel {
    private String titulo;
    private String mensaje;
    private long fecha;

    public NotificationModel() {}

    public NotificationModel(String titulo, String mensaje, long fecha) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

    public String getTitulo() { return titulo; }
    public String getMensaje() { return mensaje; }
    public long getFecha() { return fecha; }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}

