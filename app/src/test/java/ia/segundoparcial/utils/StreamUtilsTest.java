package ia.segundoparcial.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

public class StreamUtilsTest {
  @Test
  public void recorridoDiagonalHorizontalEsCorrecto() {
    Integer[][] matriz =
        new Integer[][] {
          // spotless:off
        { 36, 37, 38, 39, 40, 41, 42},
        { 29, 30, 31, 32, 33, 34, 35},
        { 22, 23, 24, 25, 26, 27, 28},
        { 15, 16, 17, 18, 19, 20, 21},
        {  8,  9, 10, 11, 12, 13, 14},
        {  1,  2,  3,  4,  5,  6,  7}
        // spotless:on
        };

    assertArrayEquals(
        IntStream.range(1, 42 + 1).toArray(),
        StreamUtils.recorrerHorizontal(matriz)
            .flatMapToInt(s -> s.mapToInt(p -> p.segundo))
            .toArray());
  }

  @Test
  public void recorridoVerticalEsCorrecto() {
    Integer[][] matriz =
        // spotless:off
      new Integer[][] {
      { 6, 12, 18, 24, 30, 36, 42},
      { 5, 11, 17, 23, 29, 35, 41},
      { 4, 10, 16, 22, 28, 34, 40},
      { 3,  9, 15, 21, 27, 33, 39},
      { 2,  8, 14, 20, 26, 32, 38},
      { 1,  7, 13, 19, 25, 31, 37} 
      // spotless:on
        };

    assertArrayEquals(
        IntStream.range(1, 42 + 1).toArray(),
        StreamUtils.recorrerVertical(matriz)
            .flatMapToInt(s -> s.mapToInt(p -> p.segundo))
            .toArray());
  }

  @Test
  public void recorridoDiagonalIDEsCorrecto() {
    Integer[][] matriz =
        new Integer[][] {
          // spotless:off
          {42, 41, 39, 36, 32, 27, 21}, 
          {40, 38, 35, 31, 26, 20, 15}, 
          {37, 34, 30, 25, 19, 14, 10}, 
          {33, 29, 24, 18, 13, 9 , 6 },
          {28, 23, 17, 12, 8 , 5 , 3 },
          {22, 16, 11, 7 , 4 , 2 , 1 },
      // spotless:on
        };

    assertArrayEquals(
        IntStream.range(1, 42 + 1).toArray(),
        StreamUtils.recorrerDiagonalID(matriz)
            .flatMapToInt(s -> s.mapToInt(p -> p.segundo))
            .toArray());
  }

  @Test
  public void recorridoDiagonalDIEsCorrecto() {
    Integer[][] matriz =
        new Integer[][] {
          // spotless:off
      { 27, 32, 36, 39, 41, 42 }, 
      { 21, 26, 31, 35, 38, 40 }, 
      { 15, 20, 25, 30, 34, 37 }, 
      { 10, 14, 19, 24, 29, 33 }, 
      { 6 , 9 , 13, 18, 23, 28 }, 
      { 3 , 5 , 8 , 12, 17, 22 }, 
      { 1 , 2 , 4 , 7 , 11, 16 }, 
      // spotless:on
        };

    assertArrayEquals(
        IntStream.range(1, 42 + 1).toArray(),
        StreamUtils.recorrerDiagonalDI(matriz)
            .flatMapToInt(s -> s.mapToInt(p -> p.segundo))
            .toArray());
  }
}
