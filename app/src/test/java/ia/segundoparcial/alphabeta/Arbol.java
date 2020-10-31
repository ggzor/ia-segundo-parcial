package ia.segundoparcial.alphabeta;

import java.util.ArrayList;

/**
 * Clase para generar casos de prueba para el algoritmo de poda alpha-beta el movimiento está
 * representado por el índice en el que aparece el nodo
 */
public class Arbol implements Estado<Integer> {
  private Integer indice;
  private Integer valor;
  private ArrayList<Estado<Integer>> hijos;

  public Arbol(Integer indice, Integer valor, ArrayList<Estado<Integer>> hijos) {
    this.indice = indice;
    this.valor = valor;
    this.hijos = hijos;
  }

  @Override
  public ArrayList<Estado<Integer>> expandir() {
    return hijos;
  }

  @Override
  public Integer generadoPor() {
    return indice;
  }

  @Override
  public boolean esTerminal() {
    return hijos == null || hijos.isEmpty();
  }

  @Override
  public double f() {
    visitados.add(valor);
    return valor != null ? valor : 0.0;
  }

  // Las funciones N permiten generar un árbol con nodos hijos
  // y nodos padre
  public static Arbol N(int valor) {
    return new Arbol(null, valor, null);
  }

  public static Arbol N(Arbol... nodos) {
    ArrayList<Estado<Integer>> lista = new ArrayList<>();
    for (int i = 0; i < nodos.length; i++) {
      nodos[i].indice = i;
      lista.add(nodos[i]);
    }
    return new Arbol(null, null, lista);
  }

  public static Arbol N(int valor, Arbol... nodos) {
    Arbol arbol = N(nodos);
    arbol.valor = valor;
    return arbol;
  }

  private static final ArrayList<Integer> visitados = new ArrayList<>();
  // Limpia los resultados de los nodos
  public static void limpiar() {
    visitados.clear();
  }

  public static ArrayList<Integer> obtenerVisitados() {
    return visitados;
  }
}
