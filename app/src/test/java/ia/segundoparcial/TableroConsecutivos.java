package ia.segundoparcial;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

public class TableroConsecutivos {

  private static Stream<Arguments> generarTablerosDe2() {
    // spotless:off
    return Stream.of(
      Arguments.of(
        new String[] {
          "       ",
          "       ",
          "       ",
          "       ",
          "       ",
          "       ",
        }, 0, 0
      )
    );
    // spotless:on
  }

  @ParameterizedTest
  @MethodSource("generarTablerosDe2")
  public void debeTenerConsecutivosDe2Jugador1(
      String[] filas, int esperadoJugador1, int esperadoJugador2) {
    Optional<Tablero> resultado = Tablero.deArreglo(filas);

    assertTrue(resultado.isPresent());
    Tablero tablero = resultado.get();

    assertEquals(esperadoJugador1, tablero.obtenerConsecutivos(2, Celda.Jugador1));
    assertEquals(esperadoJugador2, tablero.obtenerConsecutivos(2, Celda.Jugador2));
  }
}
