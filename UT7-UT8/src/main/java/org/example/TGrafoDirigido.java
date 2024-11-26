package org.example;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.*; //Import generico, nos ahorra tiempo

public class TGrafoDirigido implements IGrafoDirigido {

    private Map<Comparable, TVertice> vertices; // vertices del grafo.-

    public TGrafoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        this.vertices = new HashMap<>();
        for (TVertice vertice : vertices) {
            insertarVertice(vertice.getEtiqueta());
        }
        for (TArista arista : aristas) {
            insertarArista(arista);
        }
    }

    /**
     * Metodo encargado de eliminar una arista dada por un origen y destino. En
     * caso de no existir la adyacencia, retorna falso. En caso de que las
     * etiquetas sean invalidas, retorna falso.
     *
     */
    @Override
    public boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino) {
        if ((nomVerticeOrigen != null) && (nomVerticeDestino != null)) {
            TVertice vertOrigen = buscarVertice(nomVerticeOrigen);
            if (vertOrigen != null) {
                return vertOrigen.eliminarAdyacencia(nomVerticeDestino);
            }
        }
        return false;
    }

    @Override
    public boolean eliminarVertice(Comparable nombreVertice) {
        if (nombreVertice != null) {
            getVertices().remove(nombreVertice);
            return getVertices().containsKey(nombreVertice);
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de una arista. Las etiquetas
     * pasadas por par�metro deben ser v�lidas.
     *
     * @return True si existe la adyacencia, false en caso contrario
     */
    @Override
    public boolean existeArista(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TVertice vertOrigen = buscarVertice(etiquetaOrigen);
        TVertice vertDestino = buscarVertice(etiquetaDestino);
        if ((vertOrigen != null) && (vertDestino != null)) {
            return vertOrigen.buscarAdyacencia(vertDestino) != null;
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de un vertice dentro del
     * grafo.-
     *
     * La etiqueta especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del vertice a buscar.-
     * @return True si existe el vertice con la etiqueta indicada, false en caso
     * contrario
     */
    @Override
    public boolean existeVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta) != null;
    }

    /**
     * Metodo encargado de insertar una arista en el grafo (con un cierto
     * costo), dado su vertice origen y destino.- Para que la arista sea valida,
     * se deben cumplir los siguientes casos: 1) Las etiquetas pasadas por
     * parametros son v�lidas.- 2) Los vertices (origen y destino) existen
     * dentro del grafo.- 3) No es posible ingresar una arista ya existente
     * (miso origen y mismo destino, aunque el costo sea diferente).- 4) El
     * costo debe ser mayor que 0.
     *
     * @return True si se pudo insertar la adyacencia, false en caso contrario
     */
    @Override
    public boolean insertarArista(TArista arista) {
        boolean tempbool = false;
        if ((arista.getEtiquetaOrigen() != null) && (arista.getEtiquetaDestino() != null)) {
            TVertice vertOrigen = buscarVertice(arista.getEtiquetaOrigen());
            TVertice vertDestino = buscarVertice(arista.getEtiquetaDestino());
            tempbool = (vertOrigen != null) && (vertDestino != null);
            if (tempbool) {
                // getLasAristas().add(arista);
                return vertOrigen.insertarAdyacencia(arista.getCosto(), vertDestino);
            }

        }
        return false;
    }

    /**
     * Metodo encargado de insertar un vertice en el grafo.
     *
     * No pueden ingresarse vertices con la misma etiqueta. La etiqueta
     * especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del vertice a ingresar.
     * @return True si se pudo insertar el vertice, false en caso contrario
     */
    public boolean insertarVertice(Comparable unaEtiqueta) {
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            TVertice vert = new TVertice(unaEtiqueta);
            getVertices().put(unaEtiqueta, vert);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    @Override
    public boolean insertarVertice(TVertice vertice) {
        // Agrega un vértice al grafo si no está ya. Devuelve true si lo agregó, false si no.
        Comparable unaEtiqueta = vertice.getEtiqueta();
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            getVertices().put(unaEtiqueta, vertice);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    @Override
    public Map<Comparable, TVertice> getVertices() {
        return vertices;
    }

    public Comparable centroDelGrafo() {
        Iterator<TVertice> it = vertices.values().iterator();
        Comparable[] excentricidades = new Comparable[vertices.size()];
        Comparable centro = Double.MAX_VALUE;
        Comparable etiqueta_centro = null;
        int i = 0;
        while(it.hasNext()){
            Comparable a = excentricidades[i];
            Comparable etiqueta_vertice = it.next().getEtiqueta();

            a = this.obtenerExcentricidad(etiqueta_vertice);
            if(a.compareTo(centro) == -1){
                centro = a;
                etiqueta_centro = etiqueta_vertice;
            }
        }
        return etiqueta_centro+" (" + centro.toString().trim()+")";
    }


    public Double[][] floyd() {
        // Calcula el centro del grafo, es decir, el vértice con la menor distancia máxima a otros vértices.
        int numVertices = vertices.size();
        Double[][] distancias = new Double[numVertices][numVertices];
        Integer[][] predecesores = new Integer[numVertices][numVertices];
        TreeMap<Comparable, TVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        Object[] etiquetas = mapOrdenado.keySet().toArray();

    // Inicializa las matrices de distancias y predecesores
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (i == j) {
                    distancias[i][j] = 0.0;
                    predecesores[i][j] = null;
                } else if (existeArista((Comparable) etiquetas[i], (Comparable) etiquetas[j])) {
                    TAdyacencia arista = buscarVertice((Comparable) etiquetas[i]).buscarAdyacencia(buscarVertice((Comparable) etiquetas[j]));
                    if (arista != null) {
                        distancias[i][j] = arista.getCosto();
                        predecesores[i][j] = i;
                    } else {
                        distancias[i][j] = Double.POSITIVE_INFINITY;
                        predecesores[i][j] = null;
                    }
                } else {
                    distancias[i][j] = Double.POSITIVE_INFINITY;
                    predecesores[i][j] = null;
                }
            }
        }
   // Actualiza las distancias usando cada vértice como intermedio
        for (int k = 0; k < numVertices; k++) {
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    if (distancias[i][k] + distancias[k][j] < distancias[i][j]) {
                        distancias[i][j] = distancias[i][k] + distancias[k][j];
                        predecesores[i][j] = predecesores[k][j];
                    }
                }
            }
        }

        return distancias;
    }

    public Comparable obtenerExcentricidad(Comparable etiquetaVertice) {
    // Calcula la excentricidad de un vértice, o sea, la máxima distancia mínima a cualquier otro vértice.
        Double[][] distancias = floyd();
        TreeMap<Comparable, TVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        Object[] etiquetas = mapOrdenado.keySet().toArray();
        int indiceVertice = Arrays.asList(etiquetas).indexOf(etiquetaVertice);
        if(indiceVertice == -1) throw new IllegalArgumentException("Vertice inexistente");
        double maxDistancia = 0.0;
        for(int i = 0; i < distancias.length; i++){
            if(i != indiceVertice && distancias[indiceVertice][i] > maxDistancia){
                maxDistancia = distancias[indiceVertice][i];
            }
        }
        return maxDistancia;
    }

    public boolean[][] warshall() {
    // Aplica el algoritmo de Warshall para determinar si hay caminos entre todos los pares de vértices.
        int numVertices = vertices.size();
        boolean[][] matrizAlcanzabilidad = new boolean[numVertices][numVertices];
        TreeMap<Comparable, TVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        Object[] etiquetas = mapOrdenado.keySet().toArray();

    // Inicializa la matriz de alcanzabilidad
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                matrizAlcanzabilidad[i][j] = existeArista((Comparable) etiquetas[i], (Comparable) etiquetas[j]);
            }
        }

    // Aplica Warshall para actualizar la matriz
        for (int k = 0; k < numVertices; k++) {
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    matrizAlcanzabilidad[i][j] = matrizAlcanzabilidad[i][j] ||
                                                  (matrizAlcanzabilidad[i][k] && matrizAlcanzabilidad[k][j]);
                }
            }
        }
        return matrizAlcanzabilidad;
    }

    @Override
    public void desvisitarVertices() {
        // Marca todos los vértices como no visitados.
        for (TVertice vertice : vertices.values()) {
            vertice.setVisitado(false);
        }
    }

    /**
     * Metodo encargado de verificar buscar un vertice dentro del grafo.-
     *
     * La etiqueta especificada como parametro debe ser valida.
     *
     * @param unaEtiqueta Etiqueta del vertice a buscar.-
     * @return El vertice encontrado. En caso de no existir, retorna nulo.
     */
    public TVertice buscarVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta);
    }

    public Object[] getEtiquetasOrdenado() {
        // Devuelve las etiquetas de los vértices en orden (ordenadas alfabéticamente).
        TreeMap<Comparable, TVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        return mapOrdenado.keySet().toArray();
    }

//METODOS EXTRAS POR LAS DUDAS, estan en el pdf de clase y trabajamos por separado pero los unimos aca


    /**
     * Aplica el algoritmo de Dijkstra para encontrar las distancias más cortas desde el origen
     * a todos los demás vértices. Devuelve un mapa con la distancia mínima desde el origen
     * a cada vértice.
     */
    public Map<Comparable, Double> dijkstra(Comparable etiquetaOrigen) {
        // Verificar que el vértice origen exista en el grafo
        TVertice verticeOrigen = vertices.get(etiquetaOrigen);
        if (verticeOrigen == null) {
            throw new IllegalArgumentException("El vértice de origen no existe: " + etiquetaOrigen);
        }

        // Inicializamos las estructuras
        Map<Comparable, Double> distancias = new HashMap<>();
        Map<Comparable, TVertice> predecesores = new HashMap<>();
        PriorityQueue<TVertice> colaPrioridad = new PriorityQueue<>(Comparator.comparingDouble(v -> distancias.getOrDefault(v.getEtiqueta(), Double.POSITIVE_INFINITY)));

        // Inicializamos todas las distancias como infinito
        for (Comparable etiqueta : vertices.keySet()) {
            distancias.put(etiqueta, Double.POSITIVE_INFINITY);
        }
        distancias.put(etiquetaOrigen, 0.0); // La distancia al origen es 0

        // Agregar el vértice origen a la cola
        if (distancias.get(verticeOrigen.getEtiqueta()) != null) {
            colaPrioridad.add(verticeOrigen);
        }

        while (!colaPrioridad.isEmpty()) {
            TVertice actual = colaPrioridad.poll();

            // Verificamos si `actual` es nulo antes de continuar
            if (actual == null) continue;

            // Iterar sobre los adyacentes utilizando casting
            for (Object obj : actual.getAdyacentes()) {
                TAdyacencia adyacencia = (TAdyacencia) obj;
                if (adyacencia == null) continue;

                TVertice destino = adyacencia.getDestino();
                double nuevaDistancia = distancias.get(actual.getEtiqueta()) + adyacencia.getCosto();

                // Si encontramos un camino más corto, actualizamos la distancia
                if (nuevaDistancia < distancias.get(destino.getEtiqueta())) {
                    distancias.put(destino.getEtiqueta(), nuevaDistancia);
                    predecesores.put(destino.getEtiqueta(), actual);

                    // Añadimos el destino a la cola de prioridad si tiene una distancia válida
                    if (distancias.get(destino.getEtiqueta()) != null) {
                        colaPrioridad.add(destino);
                    }
                }
            }
        }

        return distancias;
    }

    /**
     * Recupera el camino mínimo desde el origen al destino usando Dijkstra.
     * Devuelve una lista de etiquetas de vértices que representan el camino mínimo.
     */
    public List<Comparable> recuperarCaminoDijkstra(Comparable origen, Comparable destino) {
        Map<Comparable, Comparable> predecesores = new HashMap<>();
        dijkstraConCamino(origen, predecesores);

        LinkedList<Comparable> camino = new LinkedList<>();
        Comparable actual = destino;

        // Construimos el camino desde el destino hacia el origen
        while (actual != null && !actual.equals(origen)) {
            camino.addFirst(actual);
            actual = predecesores.get(actual);
        }

        // Si no se alcanzó el origen, significa que no hay un camino
        if (actual == null) {
            return Collections.emptyList();
        } else {
            camino.addFirst(origen);
            return camino;
        }
    }

    private void dijkstraConCamino(Comparable origen, Map<Comparable, Comparable> predecesores) {
        Map<Comparable, Double> distancias = new HashMap<>();
        PriorityQueue<TVertice> colaPrioridad = new PriorityQueue<>(Comparator.comparingDouble(v -> distancias.getOrDefault(v.getEtiqueta(), Double.POSITIVE_INFINITY)));

        // Inicialización de distancias
        for (Comparable etiqueta : vertices.keySet()) {
            distancias.put(etiqueta, Double.POSITIVE_INFINITY);
            predecesores.put(etiqueta, null);
        }
        distancias.put(origen, 0.0);

        TVertice verticeOrigen = vertices.get(origen);
        if (verticeOrigen == null) {
            throw new IllegalArgumentException("El vértice de origen no existe: " + origen);
        }

        // Agregar el vértice origen a la cola
        colaPrioridad.add(verticeOrigen);

        while (!colaPrioridad.isEmpty()) {
            TVertice actual = colaPrioridad.poll();

            // Si actual es nulo, continuamos
            if (actual == null) continue;

            // Iteramos sobre sus adyacentes
            for (Object obj : actual.getAdyacentes()) {
                TAdyacencia adyacencia = (TAdyacencia) obj;
                TVertice destino = adyacencia.getDestino();

                double nuevaDistancia = distancias.get(actual.getEtiqueta()) + adyacencia.getCosto();

                // Si encontramos un camino más corto al destino, actualizamos
                if (nuevaDistancia < distancias.get(destino.getEtiqueta())) {
                    distancias.put(destino.getEtiqueta(), nuevaDistancia);
                    predecesores.put(destino.getEtiqueta(), actual.getEtiqueta());

                    // Agregamos a la cola o actualizamos su prioridad
                    colaPrioridad.remove(destino);
                    colaPrioridad.add(destino);
                }
            }
        }
    }

    /**
     * Realiza una clasificación topológica del grafo dirigido acíclico usando DFS.
     * Devuelve una lista con los vértices en orden topológico.
     */
    public List<Comparable> clasificacionTopologica() {
        Stack<Comparable> stack = new Stack<>();
        Set<Comparable> visitados = new HashSet<>();

        for (Comparable etiqueta : vertices.keySet()) {
            if (!visitados.contains(etiqueta)) {
                dfsClasificacion(etiqueta, visitados, stack);
            }
        }

        List<Comparable> ordenTopologico = new ArrayList<>();
        while (!stack.isEmpty()) {
            ordenTopologico.add(stack.pop());
        }
        return ordenTopologico;
    }

    private void dfsClasificacion(Comparable etiqueta, Set<Comparable> visitados, Stack<Comparable> stack) {
        visitados.add(etiqueta);
        TVertice vertice = vertices.get(etiqueta);

        // Usamos buscarAdyacencia para iterar sobre los adyacentes
        for (Comparable etiquetaAdyacente : vertices.keySet()) {
            TAdyacencia adyacencia = vertice.buscarAdyacencia(etiquetaAdyacente);

            if (adyacencia != null) {
                Comparable destinoEtiqueta = adyacencia.getDestino().getEtiqueta();
                if (!visitados.contains(destinoEtiqueta)) {
                    dfsClasificacion(destinoEtiqueta, visitados, stack);
                }
            }
        }

        stack.push(etiqueta);
    }

    /**
     * Detecta si existe un ciclo en el grafo dirigido usando DFS.
     * Devuelve true si hay un ciclo, de lo contrario, false.
     */
    public boolean detectarCiclos() {
        Set<Comparable> visitados = new HashSet<>();
        Set<Comparable> stackRecursion = new HashSet<>();

        for (Comparable etiqueta : vertices.keySet()) {
            if (detectarCiclosDFS(etiqueta, visitados, stackRecursion)) {
                return true;
            }
        }
        return false;
    }

    private boolean detectarCiclosDFS(Comparable etiqueta, Set<Comparable> visitados, Set<Comparable> stackRecursion) {
        if (stackRecursion.contains(etiqueta)) {
            return true;  // Se detectó un ciclo
        }
        if (visitados.contains(etiqueta)) {
            return false;  // El vértice ya ha sido procesado, no hay ciclo
        }

        visitados.add(etiqueta);
        stackRecursion.add(etiqueta);

        TVertice vertice = vertices.get(etiqueta);

        // Usamos buscarAdyacencia para iterar sobre los adyacentes
        for (Comparable etiquetaAdyacente : vertices.keySet()) {
            TAdyacencia adyacencia = vertice.buscarAdyacencia(etiquetaAdyacente);

            if (adyacencia != null) {
                // Si encontramos un ciclo, retornamos true
                if (detectarCiclosDFS(adyacencia.getDestino().getEtiqueta(), visitados, stackRecursion)) {
                    return true;
                }
            }
        }

        stackRecursion.remove(etiqueta);  // Se quita el vértice del stack de recursión
        return false;
    }


    /**
     * Realiza una búsqueda en amplitud (BFS) desde un vértice origen.
     * Devuelve una lista con los vértices en el orden de visita de BFS.
     */
    public List<Comparable> bfs(Comparable etiquetaOrigen) {
        List<Comparable> resultado = new ArrayList<>();
        Queue<TVertice> cola = new LinkedList<>();
        Set<Comparable> visitados = new HashSet<>();

        TVertice verticeOrigen = vertices.get(etiquetaOrigen);
        if (verticeOrigen == null) {
            return resultado;
        }

        cola.add(verticeOrigen);
        visitados.add(etiquetaOrigen);

        while (!cola.isEmpty()) {
            TVertice actual = cola.poll();
            resultado.add(actual.getEtiqueta());

            // Usamos buscarAdyacencia para obtener los adyacentes
            for (Comparable etiquetaAdyacente : vertices.keySet()) {
                TAdyacencia adyacencia = actual.buscarAdyacencia(etiquetaAdyacente);

                if (adyacencia != null) {
                    TVertice destino = adyacencia.getDestino();
                    if (!visitados.contains(destino.getEtiqueta())) {
                        visitados.add(destino.getEtiqueta());
                        cola.add(destino);
                    }
                }
            }
        }
        return resultado;
    }
}