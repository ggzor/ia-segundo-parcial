package ia.segundoparcial;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
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
          "1111111",
          "1111111",
        }, 17, 0
      ),
      Arguments.of(
        new String[] {
          "       ",
          "      1",
          "      1",
          " 2  1 2",
          "1121212",
          "1122211",
        }, 5, 2
      ),
      Arguments.of(
        new String[] {
          "       ",
          "       ",
          "       ",
          "  1   2",
          "1212212",
          "2121221",
        }, 4, 5
      ),
      Arguments.of(
        new String[] {
          "       ",
          "       ",
          "       ",
          "1     2",
          "1212212",
          "2121121",
        }, 4, 4
      ),
      Arguments.of(
        new String[] {
          "       ",
          " 1     ",
          "121   1",
          "212   1",
          "1122 22",
          "1122 11",
        }, 4, 6
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

  @Test
  public void ganarJuegoV() {
    // spotless:off
    Optional<Tablero> t = Tablero.deArreglo(new String[] {
      "       ",
      "       ",
      "1      ",
      "1 2    ",
      "1 2  2 ",
      "1 12 1 ",
    });
    // spotless:on

    assertTrue(t.isPresent());
    assertTrue(t.get().esGanador(Celda.Jugador1));
  }

  @Test
  public void ganarJuegoH() {
    // spotless:off
    Optional<Tablero> t = Tablero.deArreglo(new String[] {
      "       ",
      "       ",
      "       ",
      "1222211",
      "1221121",
      "1212211",
    });
    // spotless:on

    assertTrue(t.isPresent());
    assertTrue(t.get().esGanador(Celda.Jugador2));
  }

  @Test
  public void ganarJuegoD1() {
    // spotless:off
    Optional<Tablero> t = Tablero.deArreglo(new String[] {
      "       ",
      "       ",
      "2      ",
      "1222211",
      "1221121",
      "1212211",
    });
    // spotless:on

    assertTrue(t.isPresent());
    assertTrue(t.get().esGanador(Celda.Jugador2));
  }

  @Test
  public void ganarJuegoD2() {
    // spotless:off
    Optional<Tablero> t = Tablero.deArreglo(new String[] {
      "   1   ",
      " 211   ",
      "2112   ",
      "1222211",
      "1221121",
      "1212211",
    });
    // spotless:on

    assertTrue(t.isPresent());
    assertTrue(t.get().esGanador(Celda.Jugador1));
  }

  @Test
  public void ganarJuegoJ2PiezasMayorA4Vertical() {
    // spotless:off
    Optional<Tablero> t = Tablero.deArreglo(new String[] {
      "       ",
      " 211  1",
      "2112  1",
      "1222121",
      "1221121",
      "1211211",
    });
    // spotless:on

    assertTrue(t.isPresent());
    assertTrue(t.get().esGanador(Celda.Jugador1));
  }

  @Test
  public void ganarJuegoJ1PiezasMayorA4Horizontal() {
    // spotless:off
    Optional<Tablero> t = Tablero.deArreglo(new String[] {
      "       ",
      "       ",
      "       ",
      "  2    ",
      " 22   1",
      "2211111",
    });
    // spotless:on

    assertTrue(t.isPresent());
    assertTrue(t.get().esGanador(Celda.Jugador1));
  }

  @Test
  public void ganarJuegoJ1PiezasMayorA4Diagonal() {
    // spotless:off
    Optional<Tablero> t = Tablero.deArreglo(new String[] {
      "1      ",
      "21     ",
      "211    ",
      "1221   ",
      "21121 1",
      "2212211",
    });
    // spotless:on

    assertTrue(t.isPresent());
    assertTrue(t.get().esGanador(Celda.Jugador1));
  }
}
