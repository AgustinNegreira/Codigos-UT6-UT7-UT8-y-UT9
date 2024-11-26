package org.example;

import java.util.*;

public class Main2 {

    private static final int REPETICIONES = 100;

    public static void main(String[] args){
        TArbolTrie trie = new TArbolTrie();
        LinkedListConPredecir linkedList = new LinkedListConPredecir();
        HashMapConPredecir hashMap = new HashMapConPredecir();

        String[] palabrasclave = ManejadorArchivosGenerico.leerArchivo("D:\\UCU 4to semestre\\AED\\UT6\\TA3\\alumnos\\src\\listado-general_desordenado.txt");
        String[] palabrasBuscar = ManejadorArchivosGenerico.leerArchivo("D:\\UCU 4to semestre\\AED\\UT6\\TA3\\alumnos\\src\\listado-general_palabrasBuscar.txt");

        for (String p : palabrasclave) {
            trie.insertar(p);
            linkedList.add(p);
            hashMap.put(p, p);
        }

        Medible[] medibles = new Medible[3];
        int i = 0;
        medibles[i++] = new MedicionPredecirLinkedList(linkedList);
        medibles[i++] = new MedicionPredecirTrie(trie);
        medibles[i++] = new MedicionPredecirHashMap(hashMap);
        Medicion mi;
        i = 0;
        Object[] params = {REPETICIONES, palabrasBuscar};
        String[] lineas = new String[4];
        lineas[i++] = "algoritmo,tiempo,memoria";
        for (Medible m: medibles){
            mi= m.medir(params);
            mi.print();
            lineas[i++] = mi.getTexto()+","+mi.getTiempoEjecucion().toString()+", "+mi.getMemoria().toString();
        }

        ManejadorArchivosGenerico.escribirArchivo("salida2.csv",lineas);
    }
}