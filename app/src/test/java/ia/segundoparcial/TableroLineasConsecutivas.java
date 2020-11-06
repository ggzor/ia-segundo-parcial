package ia.segundoparcial;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TableroLineasConsecutivas {
  private static Stream<Arguments> tablerosDe2Consecutivos() {
    // spotless:off
    return Stream.of(
      Arguments.of(
        new String[] {
          /*0*/ "       ",
          /*1*/ "       ",
          /*2*/ "       ",
          /*3*/ "       ",
          /*4*/ "       ",
          /*5*/ "11     ",
               /*0123456*/
        }, new int[][][] {
          // Horizontales
          {{5,0},{5,1}} 
        }
      ),
      Arguments.of(
        new String[] {
          /*0*/ "       ",
          /*1*/ "       ",
          /*2*/ "       ",
          /*3*/ "       ",
          /*4*/ "       ",
          /*5*/ " 112   ",
               /*0123456*/
        }, new int[][][] {
          // Horizontales
          {{5,1},{5,2}} 
        }
      ),
      Arguments.of(
        new String[] {
          /*0*/ "       ",
          /*1*/ "       ",
          /*2*/ "       ",
          /*3*/ "       ",
          /*4*/ "       ",
          /*5*/ " 11    ",
               /*0123456*/
        }, new int[][][] {
          // Horizontales
          {{5,1},{5,2}} 
        }
      ),
      Arguments.of(
        new String[] {
          /*0*/ "       ",
          /*1*/ "       ",
          /*2*/ "       ",
          /*3*/ "       ",
          /*4*/ "       ",
          /*5*/ "112    ",
               /*0123456*/
        }, new int[][][] {}
      ),
      Arguments.of(
        new String[] {
          /*0*/ "       ",
          /*1*/ "       ",
          /*2*/ "       ",
          /*3*/ "       ",
          /*4*/ "       ",
          /*5*/ " 22    ",
               /*0123456*/
        }, new int[][][] {}
      ),
      Arguments.of(
        new String[] {
          /*0*/ "       ",
          /*1*/ "       ",
          /*2*/ "       ",
          /*3*/ "       ",
          /*4*/ "       ",
          /*5*/ " 111   ",
               /*0123456*/
        }, new int[][][] {}
      ),
      Arguments.of(
        new String[] {
          /*0*/ "       ",
          /*1*/ "       ",
          /*2*/ "       ",
          /*3*/ "       ",
          /*4*/ " 1     ",
          /*5*/ " 1     ",
               /*0123456*/
        }, new int[][][] {
          {{5, 1}, {4, 1}}
        }
      ),
      Arguments.of(
        new String[] {
          /*0*/ "       ",
          /*1*/ "       ",
          /*2*/ "       ",
          /*3*/ " 2     ",
          /*4*/ " 1     ",
          /*5*/ " 1     ",
               /*0123456*/
        }, new int[][][] {}
      ),
      Arguments.of(
        new String[] {
          /*0*/ "       ",
          /*1*/ "       ",
          /*2*/ "       ",
          /*3*/ "       ",
          /*4*/ "  1    ",
          /*5*/ " 12    ",
               /*0123456*/
        }, new int[][][] {
          {{5, 1}, {4, 2}}
        }
      ),
      Arguments.of(
        new String[] {
          /*0*/ "       ",
          /*1*/ "       ",
          /*2*/ "       ",
          /*3*/ "       ",
          /*4*/ "      1",
          /*5*/ "     12",
               /*0123456*/
        }, new int[][][] {}
      ),
      Arguments.of(
        new String[] {
          /*0*/ "       ",
          /*1*/ "       ",
          /*2*/ "       ",
          /*3*/ "       ",
          /*4*/ "      1",
          /*5*/ "    1 2",
               /*0123456*/
        }, new int[][][] {}
      ),
      Arguments.of(
        new String[] {
          /*0*/ "       ",
          /*1*/ "       ",
          /*2*/ "       ",
          /*3*/ "       ",
          /*4*/ "   1   ",
          /*5*/ "   21  ",
               /*0123456*/
        }, new int[][][] {
          {{5, 4}, {4, 3}}
        }
      ),
      Arguments.of(
        new String[] {
          /*0*/ "       ",
          /*1*/ "       ",
          /*2*/ "       ",
          /*3*/ "       ",
          /*4*/ "1      ",
          /*5*/ "21     ",
               /*0123456*/
        }, new int[][][] {}
      )
    );
    // spotless:on
  }

  @ParameterizedTest
  @MethodSource("tablerosDe2Consecutivos")
  public void debeTenerConsecutivosDe2Jugador1(String[] filas, int[][][] esperadoJugador1) {
    Optional<Tablero> resultado = Tablero.deArreglo(filas);

    assertTrue(resultado.isPresent());
    Tablero tablero = resultado.get();

    assertArrayEquals(esperadoJugador1, tablero.obtenerLineasExtensibles(2, Celda.Jugador1));
  }

  @Test
  public void conteoTotal() {
    Optional<Tablero> resultado =
        Tablero.deArreglo(
            // spotless:off
            new String[] {
              /*0*/ "       ",
              /*1*/ "       ",
              /*2*/ "       ",
              /*3*/ "       ",
              /*4*/ "1111111",
              /*5*/ "1111111",
                   /*0123456*/
            }
            // spotless:on
            );
    assertTrue(resultado.isPresent());
    Tablero tablero = resultado.get();

    assertEquals(17, tablero.obtenerLineasExtensibles(2, Celda.Jugador1).length);
  }
}
