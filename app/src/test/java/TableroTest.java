import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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

    assertThrows(IllegalArgumentException.class, () -> t.obtener(10, 10));
  }

  @Test
  public void obtenerFueraLimitesLanzaExcepcion2() {
    Tablero t = new Tablero();

    assertThrows(IllegalArgumentException.class, () -> t.obtener(6, 7));
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

    for (int i = 0; i < Tablero.COLUMNAS; i++) assertTrue(t.estaVaciaColumna(i));
  }

  @Test
  public void despuesTirarUnaColumnaDebeNoEstarVaciaYLasDemasVacias() {
    Tablero t = new Tablero();

    t.tirar(0);

    assertFalse(t.estaVaciaColumna(0));
    for (int i = 1; i < Tablero.COLUMNAS; i++) assertTrue(t.estaVaciaColumna(i));
  }

  @Test
  public void despuesTirarDosVecesMismoJugadorEnMismaColumnaDebenSerDelMismoJugador() {
    Tablero t = new Tablero();

    t.tirar(0);
    t.tirar(2);
    t.tirar(0);

    assertEquals(Celda.Jugador1, t.obtener(Tablero.FILAS - 1, 0));
    assertEquals(Celda.Jugador1, t.obtener(Tablero.FILAS - 2, 0));
  }

  @Test
  public void despuesLlenarUnaColumnaNoSePuedeTirarMismaColumna() {
    Tablero t = new Tablero();

    t.tirar(0);
    t.tirar(0);
    t.tirar(0);
    t.tirar(0);
    t.tirar(0);
    t.tirar(0);

    assertFalse(t.tirar(0));
  }
}
