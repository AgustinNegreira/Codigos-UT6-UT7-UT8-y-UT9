import org.example.TArista;
import org.example.TGrafoDirigido;
import org.example.TVertice;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for implemented methods.
 */public class GrafoDirigido_Junit5 {

    private TGrafoDirigido grafo;
    private Collection<TVertice> vertices;
    private Collection<TArista> aristas;

    /**
     * Configuración inicial de los recursos para las pruebas.
     */
    @BeforeEach
    public void setUp() {
        // Inicializar las colecciones de vértices y aristas para las pruebas
        vertices = new ArrayList<>();
        aristas = new ArrayList<>();

        // Crear vértices y agregarlos a la colección
        vertices.add(new TVertice("A"));
        vertices.add(new TVertice("B"));
        vertices.add(new TVertice("C"));
        vertices.add(new TVertice("D"));

        // Inicializar el grafo con los vértices y sin aristas al principio
        grafo = new TGrafoDirigido(vertices, aristas);
    }

    /**
     * Liberación de recursos después de cada prueba.
     */
    @AfterEach
    public void tearDown() {
        // Limpiar los recursos después de cada prueba
        grafo = null;
        vertices = null;
        aristas = null;
    }

    /**
     * Prueba de insertarVertice.
     */
    @Test
    public void testInsertarVertice() {
        assertTrue(grafo.insertarVertice(new TVertice("E")), "El vértice E debería insertarse exitosamente.");
        assertTrue(grafo.existeVertice("E"), "El vértice E debería existir en el grafo.");
    }

    /**
     * Prueba de insertarArista.
     */
    @Test
    public void testInsertarArista() {
        TArista aristaAB = new TArista("A", "B", 10);
        assertTrue(grafo.insertarArista(aristaAB), "La arista A->B debería insertarse exitosamente.");
        assertTrue(grafo.existeArista("A", "B"), "La arista A->B debería existir en el grafo.");
    }

    /**
     * Prueba de eliminarArista.
     */
    @Test
    public void testEliminarArista() {
        grafo.insertarArista(new TArista("A", "B", 10));
        assertTrue(grafo.eliminarArista("A", "B"), "La arista A->B debería eliminarse exitosamente.");
        assertFalse(grafo.existeArista("A", "B"), "La arista A->B no debería existir en el grafo después de eliminarla.");
    }

    /**
     * Prueba de floyd.
     */
    @Test
    public void testFloyd() {
        grafo.insertarArista(new TArista("A", "B", 5));
        grafo.insertarArista(new TArista("B", "C", 10));
        grafo.insertarArista(new TArista("C", "D", 3));
        
        // Ejecutamos el algoritmo de Floyd
        Double[][] distancias = grafo.floyd();
        double delta = 0.0001; // Margen de error aceptable para comparación de doubles
    
        // Validamos las distancias mínimas entre los vértices según el grafo
        assertEquals(0.0, distancias[0][0], delta, "La distancia de A a A debería ser 0.");
        assertEquals(5.0, distancias[0][1], delta, "La distancia de A a B debería ser 5.");
        assertEquals(15.0, distancias[0][2], delta, "La distancia de A a C debería ser 15.");
        assertEquals(18.0, distancias[0][3], delta, "La distancia de A a D debería ser 18.");
    }
    
//hay que preguntar si asi los test estan bien ordenados y hechos o tenemos que cambiar cosas porque fueron muchos y complicados. Quizas si hay que cambiarles algo para el parcial hay que verlo.
    /**
     * Prueba de centroDelGrafo.
     */
    @Test
    public void testCentroDelGrafo() {
        grafo.insertarArista(new TArista("A", "B", 5));
        grafo.insertarArista(new TArista("B", "C", 10));
        grafo.insertarArista(new TArista("C", "A", 8));

        Comparable centro = grafo.centroDelGrafo();

        assertEquals("B", centro, "El centro del grafo debería ser B basado en la mínima excentricidad.");
    }

    /**
     * Test para el método warshall, verificando la matriz de alcanzabilidad.
     */
    @Test
    public void testWarshall() {
        grafo.insertarArista(new TArista("A", "B", 1));
        grafo.insertarArista(new TArista("B", "C", 1));
        grafo.insertarArista(new TArista("C", "D", 1));

        boolean[][] alcanzabilidad = grafo.warshall();

        // Verificamos que el camino existe entre A y D (transitivamente)
        assertTrue(alcanzabilidad[0][3], "Debería ser alcanzable de A a D a través de otros vértices");
    }

    /**
     * Test para el método obtenerExcentricidad, verificando que calcula correctamente.
     */
    @Test
    public void testObtenerExcentricidad() {
        grafo.insertarArista(new TArista("A", "B", 4));
        grafo.insertarArista(new TArista("B", "C", 3));
        grafo.insertarArista(new TArista("C", "D", 1));
        grafo.insertarArista(new TArista("D", "A", 2));

        Comparable excentricidadA = grafo.obtenerExcentricidad("A");

        assertEquals(7.0, excentricidadA, "La excentricidad del vértice A debería ser 7");
    }

    /**
     * Test para el método desvisitarVertices, asegurando que marca todos los vértices como no visitados.
     */
    @Test
    public void testDesvisitarVertices() {
        grafo.insertarArista(new TArista("A", "B", 1));
        TVertice verticeA = grafo.getVertices().get("A");

        // Simulamos visitar el vértice A
        verticeA.setVisitado(true);
        grafo.desvisitarVertices();

        // Verificamos que el vértice A esté no visitado nuevamente
        assertFalse(verticeA.getVisitado(), "El vértice A debería estar marcado como no visitado después de desvisitarVertices()");
    }
}