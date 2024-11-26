package org.example;

public class TAdyacencia implements IAdyacencia {

    private double costo;
    private TVertice destino;
    private Comparable etiqueta;

    public TAdyacencia(double costo, TVertice destino) {
        this.etiqueta = destino.getEtiqueta();
        this.costo = costo;
        this.destino = destino;
    }

    public double getCosto() {
        return costo;
    }

    public TVertice getDestino() {
        return destino;
    }

    public Comparable getEtiqueta() {
        return etiqueta;
    }
}
