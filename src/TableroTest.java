import org.junit.Test;
import static org.junit.Assert.*;

public class TableroTest {
  @Test
  public void inicioEstaVacio() {
    Tablero t = new Tablero();
    
    assertTrue(t.estaVacio());
  }

  @Test
  public void obtenerCeldaRegresaVaciaAlInicio() {
    Tablero t = new Tablero();

    assertEquals(Celda.Vacio, t.obtener(0, 0));
  }

  @Test
  public void obtenerFueraLimitesLanzaExcepcion() {
    Tablero t = new Tablero();

    assertThrows(IllegalArgumentException.class, 
                 () -> t.obtener(10, 10));
  }

  @Test
  public void obtenerFueraLimitesLanzaExcepcion2() {
    Tablero t = new Tablero();

    assertThrows(IllegalArgumentException.class, 
                 () -> t.obtener(6, 7));
  }

  @Test
  public void tirarEnColumna0DeberiaAgregarCeldaEnFondoColumna0() {
    Tablero t = new Tablero();

    t.tirar(0);

    assertEquals(Celda.Jugador1, t.obtener(5, 0));
  }

  @Test
  public void tirarEnColumna0DosVecesDeberiaAgregarDosCeldasDiferenteJugador() {
    // Arrange = Preparar
    Tablero t = new Tablero();

    // Act = Actuar
    t.tirar(0);
    t.tirar(0);

    // Assert = Verificar
    assertEquals(Celda.Jugador1, t.obtener(5, 0));
    assertEquals(Celda.Jugador2, t.obtener(4, 0));
  }

  @Test
  public void noEstaVacioDespuesDeTirar() {
    Tablero t = new Tablero();

    t.tirar(0);

    assertFalse(t.estaVacio());
  }

  @Test
  public void tirarEnColumnaDiferente0DeberiaNoEstarVacio() {      
    Tablero t = new Tablero();

    t.tirar(2);

    assertFalse(t.estaVacio());
  }

  @Test
  public void inicioTodasColumnasEstanVacias() {
    Tablero t = new Tablero();
    
    for (int i = 0; i < Tablero.COLUMNAS; i++)
      assertTrue(t.estaVaciaColumna(i));
  }
}
