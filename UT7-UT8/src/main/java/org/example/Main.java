package org.example;

import java.util.List;
import java.util.Map;

public class Main
{
    public static void main(String[] args)
    {
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("D:\\UCU 4to semestre\\AED\\Codigos-UT6-UT7-UT8-y-UT9\\UT7-UT8\\src\\main\\java\\org\\example\\aeropuertos.txt","D:\\UCU 4to semestre\\AED\\Codigos-UT6-UT7-UT8-y-UT9\\UT7-UT8\\src\\main\\java\\org\\example\\conexiones (1).txt", false, TGrafoDirigido.class);

        // Obtener las etiquetas de los vértices en orden y guardarlas en un array
        Object[] etiquetasArray = gd.getEtiquetasOrdenado();

        // Crear y mostrar la matriz de costos inicial (costo de las conexiones entre aeropuertos)
        Double[][] matrizCostos = UtilGrafos.obtenerMatrizCostos(gd.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matrizCostos, gd.getVertices(), "Matriz de Costos Inicial");

        // Aplicar el algoritmo de Floyd para encontrar caminos mínimos y obtener la matriz resultante
        Double[][] matrizFloyd = gd.floyd();
        UtilGrafos.imprimirMatrizMejorado(matrizFloyd, gd.getVertices(), "Matriz luego de aplicar FLOYD");

        // Calcular y mostrar la excentricidad de cada vértice (la mayor distancia mínima a otros vértices)
        for (Object etiqueta : etiquetasArray) {
            System.out.println("Excentricidad de " + etiqueta + " : " + gd.obtenerExcentricidad((Comparable) etiqueta));
        }

        // Determinar y mostrar el centro del grafo, el vértice con menor excentricidad
        System.out.println("\nCentro del grafo: " + gd.centroDelGrafo());


        // aplicando los métodos extras

        // Aplicar el algoritmo de Dijkstra desde el vértice "Asuncion"
        Map<Comparable, Double> distanciasDesdeA = gd.dijkstra("Asuncion");
        System.out.println("\nDistancias mínimas desde Asuncion (Dijkstra):");
        distanciasDesdeA.forEach((k, v) -> System.out.println("A -> " + k + " : " + v));

        // Recuperar el camino mínimo de "A" a "C" usando Dijkstra
        List<Comparable> caminoMinimoAC = gd.recuperarCaminoDijkstra("Asuncion", "Curitiba");
        System.out.println("\nCamino mínimo de Asuncion a Curitiba:");
        System.out.println(caminoMinimoAC);

        // Realizar la clasificación topológica del grafo (útil para grafos acíclicos dirigidos)
        List<Comparable> ordenTopologico = gd.clasificacionTopologica();
        System.out.println("\nOrden Topológico del Grafo:");
        System.out.println(ordenTopologico);

        // Detectar si hay ciclos en el grafo
        boolean tieneCiclos = gd.detectarCiclos();
        System.out.println("\n¿El grafo tiene ciclos? " + (tieneCiclos ? "Sí" : "No"));

        // Realizar una búsqueda en amplitud (BFS) desde el vértice "A"
        List<Comparable> recorridoBFS = gd.bfs("Asuncion");
        System.out.println("\nRecorrido BFS desde Asuncion:");
        System.out.println(recorridoBFS);

        System.out.println("\n");
        System.out.println("Grafo no dirigido:");
        System.out.println("\n");

        // GRAFO NO DIRIGIDO

        TGrafoNoDirigido gnd = (TGrafoNoDirigido) UtilGrafos.cargarGrafo("D:\\UCU 4to semestre\\AED\\Codigos-UT6-UT7-UT8-y-UT9\\UT7-UT8\\src\\main\\java\\org\\example\\verticesBEA.txt","D:\\UCU 4to semestre\\AED\\Codigos-UT6-UT7-UT8-y-UT9\\UT7-UT8\\src\\main\\java\\org\\example\\aristasBEA.txt",
                false, TGrafoNoDirigido.class);
        gnd = gnd.Kruskal();
        Double[][] gndMatriz = UtilGrafos.obtenerMatrizCostos(gnd.getVertices());
        UtilGrafos.imprimirMatrizMejorado(gndMatriz, gnd.getVertices(), "Matriz luego de KRUSKAL");

        TGrafoNoDirigido gnd2 = (TGrafoNoDirigido) UtilGrafos.cargarGrafo("D:\\UCU 4to semestre\\AED\\Codigos-UT6-UT7-UT8-y-UT9\\UT7-UT8\\src\\main\\java\\org\\example\\verticesBEA.txt","D:\\UCU 4to semestre\\AED\\Codigos-UT6-UT7-UT8-y-UT9\\UT7-UT8\\src\\main\\java\\org\\example\\aristasBEA.txt",
                false, TGrafoNoDirigido.class);
        gnd2 = gnd2.Prim();
        Double[][] gnd2Matriz = UtilGrafos.obtenerMatrizCostos(gnd.getVertices());
        UtilGrafos.imprimirMatrizMejorado(gnd2Matriz, gnd.getVertices(), "Matriz luego de PRIM");
    }
}