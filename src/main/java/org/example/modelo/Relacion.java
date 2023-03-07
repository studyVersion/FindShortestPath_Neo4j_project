package org.example.modelo;

public class Relacion {
    private Ciudad ciudadUno;
    private Ciudad ciudadDos;
    private double distancia;

    public Relacion(Ciudad ciudadUno, Ciudad ciudadDos, double distancia) {
        this.ciudadUno = ciudadUno;
        this.ciudadDos = ciudadDos;
        this.distancia = distancia;
    }

    public Ciudad getCiudadUno() {
        return ciudadUno;
    }

    public void setCiudadUno(Ciudad ciudadUno) {
        this.ciudadUno = ciudadUno;
    }

    public Ciudad getCiudadDos() {
        return ciudadDos;
    }

    public void setCiudadDos(Ciudad ciudadDos) {
        this.ciudadDos = ciudadDos;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    @Override
    public String toString() {
        return "Relacion{" +
                "ciudadUno=" + ciudadUno +
                ", ciudadDos=" + ciudadDos +
                ", distancia=" + distancia +
                '}';
    }
}
