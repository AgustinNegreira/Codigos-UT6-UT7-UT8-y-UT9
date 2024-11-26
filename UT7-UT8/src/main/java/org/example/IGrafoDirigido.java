package org.example;

import java.util.Map;

public interface IGrafoDirigido {
    /**
     * Metodo encargado de eliminar una arista dada por un origen y destino. En
     * caso de no existir la arista, retorna falso. En caso de que las etiquetas
     * sean invalidas (no existe el vertice origen o el destino), retorna falso.
     */
    boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino);


    public boolean eliminarVertice(Comparable nombreVertice);

    /**
     * Metodo encargado de verificar la existencia de una arista. Las etiquetas
     * pasadas por parametro deben ser validas (o sea, los vértices origen y
     * destino deben existir en el grafo).
     *
     * @return True si existe la arista, false en caso contrario
     */
    boolean existeArista(Comparable etiquetaOrigen, Comparable etiquetaDestino);

    /**
     * Metodo encargado de verificar la existencia de un vertice dentro del
     * grafo.-
     *
     * La etiqueta especificada como parametro debe ser valida.
     *
     * @param unaEtiqueta Etiqueta del vertice a buscar.-
     * @return True si existe el vertice con la etiqueta indicada, false en caso
     * contrario
     */
    boolean existeVertice(Comparable unaEtiqueta);


    boolean insertarArista(TArista arista);


    boolean insertarVertice(TVertice vertice);

    public Map<Comparable, TVertice> getVertices();

    public void desvisitarVertices();
    
}