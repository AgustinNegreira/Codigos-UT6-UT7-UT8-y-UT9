package org.example;

import java.util.HashMap;

public class MedicionPredecirHashMap extends Medible{

    private HashMapConPredecir hashMap;

    public MedicionPredecirHashMap(HashMapConPredecir hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public void ejecutar(Object... params) {
        int repeticion = (int) params[0];
        String[] palabras = (String[]) params[1];
        for(int i = 0; i < repeticion; i++){
            for(String palabra : palabras){
                hashMap.predecirPalabra(palabra);
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.hashMap;
    }

}
