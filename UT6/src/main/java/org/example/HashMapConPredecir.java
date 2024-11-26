package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HashMapConPredecir extends HashMap {

    public HashMapConPredecir() {
        super();
    }

    public List<String> predecirPalabra(String prefijo) {
        List<String> palabrasCoincidentes = new ArrayList<String>();
        for(Object obj : this.values()) {
            String palabra = (String) obj;
            if(palabra.startsWith(prefijo)){
                palabrasCoincidentes.add(palabra);
            }
        }
        return palabrasCoincidentes;
    }
}
