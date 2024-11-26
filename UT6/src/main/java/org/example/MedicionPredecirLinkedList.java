package org.example;

import java.util.LinkedList;
import java.util.List;

public class MedicionPredecirLinkedList extends Medible{

    private LinkedListConPredecir linkedList;

    public MedicionPredecirLinkedList(LinkedListConPredecir linkedList) {
        this.linkedList = linkedList;
    }

    @Override
    public void ejecutar(Object... params) {
        int repeticion = (int) params[0];
        String[] palabras = (String[]) params[1];
        for(int i = 0; i < repeticion; i++){
            for(String palabra : palabras){
                linkedList.predecirPalabra(palabra);
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.linkedList;
    }

}
