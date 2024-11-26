package org.example;

import java.util.ArrayList;

public class MedicionBuscarArrayList extends Medible{

    private ArrayList arrayList;

    public MedicionBuscarArrayList(ArrayList arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public void ejecutar(Object... params) {
        int repeticion = (int) params[0];
        String[] palabras = (String[]) params[1];
        for(int i = 0; i < repeticion; i++){
            for(String palabra : palabras){
                arrayList.contains(palabra);
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.arrayList;
    }
}