package ia.segundoparcial.alphabeta;

import java.util.ArrayList;

// Interfaz para representar un node del árbol alphabeta
public interface Estado<M> {

  // Obtiene los siguientes nodos de este estado
  ArrayList<Estado<M>> expandir();

  // Obtiene el movimiento que llevó a este estado
  M generadoPor();

  // Determina si este nodo es terminal, es decir que no puede
  // seguir siendo expandido
  boolean esTerminal();

  // Obtiene el valor de evaluación de la función para este nodo
  double f();
}
