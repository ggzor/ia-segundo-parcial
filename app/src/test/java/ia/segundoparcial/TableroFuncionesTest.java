package ia.segundoparcial;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TableroFuncionesTest {
  @Test
  public void cadenasVaciasGeneranTableroVacio() {
    // spotless:off
    Optional<Tablero> t = Tablero.deArreglo(new String[] {
      "       ",
      "       ",
      "       ",
      "       ",
      "       ",
      "       ",
    });
    // spotless:on

    assertTrue(t.isPresent());
    assertTrue(t.get().estaVacio());
  }

  @Test
  public void cadenasFueraDeRangoDeFilasNoSeInstancia() {
    // spotless:off
    Optional<Tablero> t = Tablero.deArreglo(new String[] {
        "       ",
        "       ",
        "       ",
        "       ",
        "       ",
    });
    // spotless:on

    assertFalse(t.isPresent());
  }

  @Test
  public void cadenasFueraDeRangoDeColumnaaNoSeInstancia() {
    // spotless:off
    Optional<Tablero> t = Tablero.deArreglo(new String[] {
      "       ",
      "       ",
      "       ",
      "       ",
      "       ",
      "    ",
    });
    // spotless:on

    assertFalse(t.isPresent());
  }

  @Test
  public void cadenasConSimbolosNoPermitidosNoSeCrea() {
    // spotless:off
    Optional<Tablero> t = Tablero.deArreglo(new String[] {
      "       ",
      "       ",
      "       ",
      "       ",
      "       ",
      "  1234 ",
    });
    // spotless:on

    assertFalse(t.isPresent());
  }

  @Test
  public void piezasEnColumnasNoCumplenReglasNoSeCreaTablero() {
    // spotless:off
    Optional<Tablero> t = Tablero.deArreglo(new String[] {
      "     1 ",
      "  1  1 ",
      "  1  2 ",
      "     1 ",
      "  1  1 ",
      "       ",
    });
    // spotless:on

    assertFalse(t.isPresent());
  }

  @Test
  public void tableroCorrectoNoEsVacio() {
    // spotless:off
    Optional<Tablero> t = Tablero.deArreglo(new String[] {
      "       ",
      "       ",
      "       ",
      "2      ",
      "1 2  2 ",
      "1 1  1 ",
    });
    // spotless:on

    assertTrue(t.isPresent());
    assertFalse(t.get().estaVacio());
  }

  private static Stream<Arguments> columnasDisponiblesArgumentos() {
    return Stream.of(
        // spotless:off
      Arguments.of(
        new String[] {
          "       ",
          "       ",
          "       ",
          "       ",
          "       ",
          "       ",
        }, new int[] { 0, 1, 2, 3, 4, 5, 6 }
      )
      // spotless:on
        );
  }

  @ParameterizedTest
  @MethodSource("columnasDisponiblesArgumentos")
  public void columnasDisponiblesDeVacioRegresaTodosIndices(String[] arreglo, int[] esperado) {
    Optional<Tablero> t = Tablero.deArreglo(arreglo);

    assertTrue(t.isPresent());

    Tablero tablero = t.get();
    assertArrayEquals(esperado, tablero.obtenerColumnasDisponibles());
  }
}
