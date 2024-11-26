package org.example;

import java.util.LinkedList;
import java.util.List;

public class LinkedListConPredecir extends LinkedList {

    public LinkedListConPredecir() {
        super();
    }

    public List<String> predecirPalabra(String prefijo) {
        List<String> palabrasCoincidentes = new LinkedList<>();
        for(Object obj : this){
            String palabra = (String) obj;
            if(palabra.startsWith(prefijo)){
                palabrasCoincidentes.add(palabra);
            }
        }
        return palabrasCoincidentes;
    }
}
