import java.util.Scanner;

public class MultiplosTres {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduce un número N: ");
        int n = entrada.nextInt();
        int suma = 0, cantidad = 0;
        // Cambio 1: se utiliza <= para incluir también N.
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0) {
                suma += i; cantidad++;
            }
        }
        System.out.println("Múltiplos de 3 entre 1 y " + n + ": " + cantidad);
        // Cambio 2: comprobamos que haya múltiplos antes de dividir.
        if (cantidad > 0) {
            // Cambio 3: convertimos suma a double para obtener decimales.
            double media = (double) suma / cantidad;
            System.out.println("Suma: " + suma + " | Media: " + media);
        } else {
            // Cambio 4: mostramos un mensaje si no hay múltiplos.
            System.out.println("No hay múltiplos de 3. No se puede calcular la media.");
        }

        entrada.close();
    }
}
