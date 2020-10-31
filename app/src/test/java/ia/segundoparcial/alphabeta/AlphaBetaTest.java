package ia.segundoparcial.alphabeta;

import static ia.segundoparcial.alphabeta.Arbol.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import ia.segundoparcial.utils.Par;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AlphaBetaTest {
  @BeforeEach
  public void limpiarRegistroArbol() {
    Arbol.limpiar();
  }

  @Test
  public void ejemploProfesor() {
    Arbol inicial =
        // spotless:off
      N(
        N(
          N(
            N(
              N(16), N(15)),
            N(
              N(14), N(13))
          ),
          N(
            N(
              N(12), N(11)),
            N(
              N(10), N(9))
          )
        ),
        N(
          N(
            N(
              N(8), N(7)),
            N(
              N(6), N(5))
          ),
          N(
            N(
              N(4), N(3)),
            N(
              N(2), N(1))
          )
        )
      );
      // spotless:on

    Par<Double, Integer> resultado = AlphaBeta.calcular(inicial, 4);
    assertEquals(11.0, resultado.primero, 0.001);
    // Tomó el nodo en el índice 0 (el de la izquierda)
    assertEquals(0, resultado.segundo);

    List<Integer> esperado = Arrays.asList(16, 15, 14, 12, 11, 10, 8, 6);
    assertIterableEquals(esperado, Arbol.obtenerVisitados());
  }
}
