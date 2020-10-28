package ia.segundoparcial;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import org.junit.jupiter.api.Test;

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
  public void cadenasFueraDeRangoDeFilasRetornaNull() {
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
  public void cadenasFueraDeRangoDeColumnasRetornaNull() {
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
}
