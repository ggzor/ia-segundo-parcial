package ia.segundoparcial.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

public class StreamUtils {
  public static <A> Stream<List<A>> agruparConsecutivos(Stream<A> stream, BiPredicate<A, A> pred) {
    ArrayList<List<A>> resultado = new ArrayList<>();

    stream.forEach(
        a -> {
          if (resultado.isEmpty()) {
            List<A> lista = new ArrayList<>();
            lista.add(a);
            resultado.add(lista);
          } else {
            List<A> ultima = resultado.get(resultado.size() - 1);

            if (pred.test(ultima.get(ultima.size() - 1), a)) {
              ultima.add(a);
            } else {
              List<A> lista = new ArrayList<>();
              lista.add(a);
              resultado.add(lista);
            }
          }
        });

    return resultado.stream();
  }

  public static <A> Stream<Stream<Par<int[], A>>> recorrerHorizontal(A[][] arreglo) {
    ArrayList<ArrayList<Par<int[], A>>> resultado = new ArrayList<>();

    for (int y = arreglo.length - 1; y >= 0; y--) {
      ArrayList<Par<int[], A>> nueva = new ArrayList<>();
      resultado.add(nueva);

      for (int x = 0; x < arreglo[0].length; x++) {
        nueva.add(Par.de(new int[] {y, x}, arreglo[y][x]));
      }
    }

    return resultado.stream().map(l -> l.stream());
  }

  public static <A> Stream<Stream<Par<int[], A>>> recorrerVertical(A[][] arreglo) {
    ArrayList<ArrayList<Par<int[], A>>> resultado = new ArrayList<>();

    for (int x = 0; x < arreglo[0].length; x++) {
      ArrayList<Par<int[], A>> nueva = new ArrayList<>();
      resultado.add(nueva);

      for (int y = arreglo.length - 1; y >= 0; y--) {
        nueva.add(Par.de(new int[] {y, x}, arreglo[y][x]));
      }
    }

    return resultado.stream().map(l -> l.stream());
  }

  public static <A> Stream<Stream<Par<int[], A>>> recorrerDiagonalID(A[][] arreglo) {
    ArrayList<ArrayList<Par<int[], A>>> resultado = new ArrayList<>();

    for (int c = arreglo[0].length - 1; c >= 0; c--) {
      ArrayList<Par<int[], A>> nueva = new ArrayList<>();
      resultado.add(nueva);

      for (int y = arreglo.length - 1, x = c; x < arreglo[0].length && y >= 0; y--, x++) {
        nueva.add(Par.de(new int[] {y, x}, arreglo[y][x]));
      }
    }

    for (int f = arreglo.length - 2; f >= 0; f--) {
      ArrayList<Par<int[], A>> nueva = new ArrayList<>();
      resultado.add(nueva);

      for (int x = 0, y = f; x < arreglo[0].length && y >= 0; y--, x++) {
        nueva.add(Par.de(new int[] {y, x}, arreglo[y][x]));
      }
    }

    return resultado.stream().map(l -> l.stream());
  }

  public static <A> Stream<Stream<Par<int[], A>>> recorrerDiagonalDI(A[][] arreglo) {
    ArrayList<ArrayList<Par<int[], A>>> resultado = new ArrayList<>();

    for (int c = 0; c < arreglo[0].length; c++) {
      ArrayList<Par<int[], A>> nueva = new ArrayList<>();
      resultado.add(nueva);

      for (int y = arreglo.length - 1, x = c; x >= 0 && y >= 0; y--, x--) {
        nueva.add(Par.de(new int[] {y, x}, arreglo[y][x]));
      }
    }

    for (int f = arreglo.length - 2; f >= 0; f--) {
      ArrayList<Par<int[], A>> nueva = new ArrayList<>();
      resultado.add(nueva);

      for (int x = arreglo[0].length - 1, y = f; x >= 0 && y >= 0; y--, x--) {
        nueva.add(Par.de(new int[] {y, x}, arreglo[y][x]));
      }
    }

    return resultado.stream().map(l -> l.stream());
  }
}
