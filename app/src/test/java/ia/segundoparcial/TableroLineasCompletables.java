package ia.segundoparcial;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TableroLineasCompletables {
  private static Stream<Arguments> tablerosLineasCompletables() {
    // spotless:off
    return Stream.of(
      Arguments.of(
        new String[] {
          /*0*/ "       ",
          /*1*/ "       ",
          /*2*/ "       ",
          /*3*/ "       ",
          /*4*/ "       ",
          /*5*/ "       ",
               /*0123456*/
        }, 0
      ),
      Arguments.of(
        new String[] {
          /*0*/ "       ",
          /*1*/ "       ",
          /*2*/ "       ",
          /*3*/ "       ",
          /*4*/ "       ",
          /*5*/ "   11 1",
               /*0123456*/
        }, 1
      ),
      Arguments.of(
        new String[] {
          /*0*/ "       ",
          /*1*/ "       ",
          /*2*/ "       ",
          /*3*/ "       ",
          /*4*/ "       ",
          /*5*/ "   1121",
               /*0123456*/
        }, 0
      ),
      Arguments.of(
        new String[] {
          /*0*/ "       ",
          /*1*/ "       ",
          /*2*/ "       ",
          /*3*/ "       ",
          /*4*/ "       ",
          /*5*/ " 1 11 1",
               /*0123456*/
        }, 1
      ),
      Arguments.of(
        new String[] {
          /*0*/ "       ",
          /*1*/ "       ",
          /*2*/ "       ",
          /*3*/ "       ",
          /*4*/ "       ",
          /*5*/ " 111 11",
               /*0123456*/
        }, 1
      ),
      Arguments.of(
        new String[] {
          /*0*/ "       ",
          /*1*/ "       ",
          /*2*/ "       ",
          /*3*/ "       ",
          /*4*/ "       ",
          /*5*/ "   1 1 ",
               /*0123456*/
        }, 0
      ),
      Arguments.of(
        new String[] {
          /*0*/ "       ",
          /*1*/ "       ",
          /*2*/ "     1 ",
          /*3*/ "     2 ",
          /*4*/ "   122 ",
          /*5*/ "  1222 ",
               /*0123456*/
        }, 1
      ),
      Arguments.of(
        new String[] {
          /*0*/ "       ",
          /*1*/ "       ",
          /*2*/ "    1  ",
          /*3*/ "    2  ",
          /*4*/ "  1 2  ",
          /*5*/ " 12 2  ",
               /*0123456*/
        }, 0
      ),
      Arguments.of(
        new String[] {
          /*0*/ "       ",
          /*1*/ "      1",
          /*2*/ "     12",
          /*3*/ "     22",
          /*4*/ "   1222",
          /*5*/ "  12222",
               /*0123456*/
        }, 1
      ),
      Arguments.of(
        new String[] {
          /*0*/ "       ",
          /*1*/ "       ",
          /*2*/ "   1   ",
          /*3*/ "   2   ",
          /*4*/ "   221 ",
          /*5*/ "   2221",
               /*0123456*/
        }, 1
      )
    );
    // spotless:on
  }

  public static int f(String s) {
    System.out.println(s);
    return 0;
  }

  @ParameterizedTest
  @MethodSource("tablerosLineasCompletables")
  public void debeTenerLineasCompletables(String[] filas, int esperado) {
    Optional<Tablero> resultado = Tablero.deArreglo(filas);

    f("Hola");

    Function<String, Integer> g = TableroLineasCompletables::f;
    g.apply("Mundo");

    assertTrue(resultado.isPresent());
    Tablero tablero = resultado.get();

    assertEquals(esperado, tablero.obtenerLineasCompletables(Celda.Jugador1));
  }
}
