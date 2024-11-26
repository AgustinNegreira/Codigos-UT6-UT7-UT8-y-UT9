import java.util.ArrayList;
import java.util.Collection;

import org.example.TArista;
import org.example.TGrafoNoDirigido;
import org.example.TVertice;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GrafoNoDirigido_Junit5 {

private TGrafoNoDirigido grafo;
private Collection<TVertice> vertices;
private Collection<TArista> aristas;


@BeforeEach
public void setUp() {
    vertices = new ArrayList<>();
    aristas = new ArrayList<>();
    vertices.add(new TVertice("A"));
    vertices.add(new TVertice("B"));
    vertices.add(new TVertice("C"));
    vertices.add(new TVertice("D"));
    grafo = new TGrafoNoDirigido(vertices, aristas);
}
@AfterEach
public void tearDown() {
    grafo = null;
    vertices = null;
    aristas = null;
}

@Test
public void testInsertarVertice() {
    TGrafoNoDirigido grafoLocal = new TGrafoNoDirigido(new ArrayList<>(), new ArrayList<>());
    assertTrue(grafoLocal.insertarVertice(new TVertice("A")), "El vértice A debería insertarse exitosamente.");
    assertTrue(grafoLocal.existeVertice("A"), "El vértice A debería existir en el grafo.");
}


@Test
public void testInsertarArista() {
    grafo.getVertices().clear();
    TVertice verticeA = new TVertice("A");
    TVertice verticeB = new TVertice("B");
    assertTrue(grafo.insertarVertice(verticeA), "El vértice A debería insertarse exitosamente.");
    assertTrue(grafo.insertarVertice(verticeB), "El vértice B debería insertarse exitosamente.");
    assertTrue(grafo.insertarArista(new TArista("A", "B", 1.0)), "La arista A-B debería insertarse exitosamente.");
    assertTrue(grafo.existeArista("A", "B"), "La arista A-B debería existir en el grafo.");
    assertTrue(grafo.existeArista("B", "A"), "La arista B-A debería existir en el grafo.");
}

@Test
public void testPrim() {
    grafo.getVertices().clear();
    grafo.getLasAristas().clear();
    assertTrue(grafo.insertarVertice(new TVertice<>("A")), "El vértice A debería insertarse exitosamente.");
    assertTrue(grafo.insertarVertice(new TVertice<>("B")), "El vértice B debería insertarse exitosamente.");
    assertTrue(grafo.insertarVertice(new TVertice<>("C")), "El vértice H debería insertarse exitosamente.");
    assertTrue(grafo.insertarVertice(new TVertice<>("D")), "El vértice I debería insertarse exitosamente.");
    assertTrue(grafo.insertarArista(new TArista("A", "B", 1.0)), "La arista A-B debería insertarse exitosamente.");
    assertTrue(grafo.insertarArista(new TArista("A", "C", 2.0)), "La arista A-H debería insertarse exitosamente.");
    assertTrue(grafo.insertarArista(new TArista("A", "D", 3.0)), "La arista A-I debería insertarse exitosamente.");
    assertTrue(grafo.insertarArista(new TArista("B", "C", 4.0)), "La arista B-H debería insertarse exitosamente.");
    assertTrue(grafo.insertarArista(new TArista("B", "D", 5.0)), "La arista B-I debería insertarse exitosamente.");
    assertTrue(grafo.insertarArista(new TArista("C", "D", 6.0)), "La arista H-I debería insertarse exitosamente.");
    TGrafoNoDirigido arbolPrim = new TGrafoNoDirigido(grafo.getVertices().values(), new ArrayList<>());
    assertTrue(arbolPrim.existeVertice("A"), "El vértice A debería existir en el árbol de expansión mínima.");
}

@Test
public void testKruskal() {
    grafo.getVertices().clear();
    grafo.getLasAristas().clear();
    assertTrue(grafo.insertarVertice(new TVertice<>("A")), "El vértice A debería insertarse exitosamente.");
    assertTrue(grafo.insertarVertice(new TVertice<>("B")), "El vértice B debería insertarse exitosamente.");
    assertTrue(grafo.insertarVertice(new TVertice<>("C")), "El vértice C debería insertarse exitosamente.");
    assertTrue(grafo.insertarArista(new TArista("A", "B", 1.0)), "La arista A-B debería insertarse exitosamente.");
    assertTrue(grafo.insertarArista(new TArista("A", "C", 2.0)), "La arista A-C debería insertarse exitosamente.");
    assertTrue(grafo.insertarArista(new TArista("B", "C", 4.0)), "La arista B-C debería insertarse exitosamente.");
    TGrafoNoDirigido arbolKruskal = grafo.Kruskal();
    assertTrue(arbolKruskal.existeVertice("A"), "El vértice A debería existir en el árbol de expansión mínima.");
    assertTrue(arbolKruskal.existeVertice("B"), "El vértice B debería existir en el árbol de expansión mínima.");
    assertTrue(arbolKruskal.existeVertice("C"), "El vértice C debería existir en el árbol de expansión mínima.");
    assertFalse(arbolKruskal.existeVertice("D"), "El vértice D no debería existir en el árbol de expansión mínima.");
}

@Test
public void testBea() {
    grafo.getVertices().clear();
    grafo.getLasAristas().clear();
    assertTrue(grafo.insertarVertice(new TVertice<>("A")), "El vértice A debería insertarse exitosamente.");
    assertTrue(grafo.insertarVertice(new TVertice<>("B")), "El vértice B debería insertarse exitosamente.");
    assertTrue(grafo.insertarVertice(new TVertice<>("C")), "El vértice C debería insertarse exitosamente.");
    assertTrue(grafo.insertarArista(new TArista("A", "B", 1.0)), "La arista A-B debería insertarse exitosamente.");
    assertTrue(grafo.insertarArista(new TArista("A", "C", 2.0)), "La arista A-C debería insertarse exitosamente.");
    assertTrue(grafo.insertarArista(new TArista("B", "C", 4.0)), "La arista B-C debería insertarse exitosamente.");
    Collection<TVertice> recorrido = grafo.bea();
    assertTrue(recorrido.size() == 3, "El recorrido debería tener 3 vértices.");
}

@Test
public void testEsConexo(){
    grafo.getVertices().clear();
    grafo.getLasAristas().clear();
    assertTrue(grafo.insertarVertice(new TVertice<>("A")), "El vértice A debería insertarse exitosamente.");
    assertTrue(grafo.insertarVertice(new TVertice<>("B")), "El vértice B debería insertarse exitosamente.");
    assertTrue(grafo.insertarVertice(new TVertice<>("C")), "El vértice C debería insertarse exitosamente.");
    assertTrue(grafo.insertarArista(new TArista("A", "B", 1.0)), "La arista A-B debería insertarse exitosamente.");
    assertTrue(grafo.insertarArista(new TArista("A", "C", 2.0)), "La arista A-C debería insertarse exitosamente.");
    assertTrue(grafo.insertarArista(new TArista("B", "C", 4.0)), "La arista B-C debería insertarse exitosamente.");
    assertTrue(grafo.esConexo(), "El grafo debería ser conexo.");
}
}