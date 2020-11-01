package ia.segundoparcial.alphabeta;

import static ia.segundoparcial.alphabeta.Arbol.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import ia.segundoparcial.utils.Par;
import java.util.*;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class AlphaBetaTest {
  @BeforeEach
  public void limpiarRegistroArbol() {
    Arbol.limpiar();
  }

  private static Stream<Arguments> ejemplosAlphaBeta() {
    return Stream.of(
        // spotless:off
      Arguments.of(
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
        ), 4, 11.0, 0, Arrays.asList(16, 15, 14, 12, 11, 10, 8, 6)
      ),
      Arguments.of(
        N(
          N(
            N(N(7), N(6)),
            N(8),
            N(N(10), N(12))
          ),
          N(
            N(12),
            N(N(13), N(16), N(15))
          ),
          N(
            N(17),
            N(N(20), N(22))
          )
        ), 4, 17.0, 2, Arrays.asList(7, 6, 8, 10, 12, 13, 17, 20)
      ),
      Arguments.of(
        N(
          N(
            N(N(2), N(7)),
            N(N(8), N(5)),
            N(N(11), N(4))
          ),
          N(
            N(N(6), N(3), N(1))
          ),
          N(
            N(N(9), N(8)),
            N(N(6), N(12), N(15))
          )
        ), 4, 9.0, 2, Arrays.asList(2, 7, 8, 11, 6, 3, 1, 9, 8, 6, 12)
      ),
      Arguments.of(
        N(
          N(
            N(N(2), N(7)),
            N(N(8), N(5)),
            N(N(11), N(4))
          ),
          N(
            N(N(6), N(3), N(1))
          ),
          N(
            N(N(2), N(5)),
            N(N(6), N(12), N(15))
          )
        ), 4, 7.0, 0, Arrays.asList(2, 7, 8, 11, 6, 3, 1, 2, 5)
      )

      // spotless:on
        );
  }

  @ParameterizedTest
  @MethodSource("ejemplosAlphaBeta")
  public void ejemplos(
      Arbol arbol, int profundidaMax, double f, int movimiento, List<Integer> visitados) {
    Par<Double, Integer> resultado = AlphaBeta.calcular(arbol, profundidaMax);
    assertEquals(f, resultado.primero, 0.001);
    // Tomó el nodo en el índice 0 (el de la izquierda)
    assertEquals(movimiento, resultado.segundo);
    assertIterableEquals(visitados, Arbol.obtenerVisitados());
  }
}
