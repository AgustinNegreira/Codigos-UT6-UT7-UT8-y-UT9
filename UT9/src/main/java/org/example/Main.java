package org.example;

import java.sql.SQLOutput;

public class Main {

    public static void main(String[] args) {
        TClasificador clasif = new TClasificador();
        GeneradorDatosGenericos gdg = new GeneradorDatosGenericos();

        // Datos a clasificar
        int[][] conjuntosDatos = {
                gdg.generarDatosAleatorios(),
                gdg.generarDatosAscendentes(),
                gdg.generarDatosDescendentes()
        };

        String[] nombresConjuntos = { "Aleatorio", "Ascendente", "Descendente" };
        String[] nombresMetodos = {
                "Insertion Sort",
                "Shell Sort",
                "Bubble Sort",
                "Counting Sort",
                "Heap Sort",
                "Quick Sort",
                "Selection Sort",
                "Bin Sort/Bucket Sort",
                "Radix Sort"
        };

        // Probar todos los algoritmos
        for (int metodo = 1; metodo <= 9; metodo++) {
            System.out.println("\nMétodo de clasificación: " + nombresMetodos[metodo-1]);
            System.out.print("\n");

            for (int i = 0; i < conjuntosDatos.length; i++) {
                System.out.println("Conjunto: " + nombresConjuntos[i]);

                int[] resultado = clasif.clasificar(conjuntosDatos[i].clone(), metodo);

                for (int num : resultado) {
                    System.out.print(num + " ");
                }
                System.out.println("\n¿Ordenado?: " + TClasificador.estaOrdenado(resultado));
                System.out.print("\n");
            }
        }
    }
}