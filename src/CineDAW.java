
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Programa principal CineDAW.
 * @author Thanh Hang Nguyen Luu
 */
public class CineDAW {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Constantes de la sala
        final int FILAS = 6;
        final int BUTACAS_POR_FILA = 8;

        // Estados de las butacas
        final char LIBRE = '-';
        final char OCUPADA = 'X';

        // Precios en céntimos
        final int PRECIO_INFANTIL = 500;
        final int PRECIO_SENIOR = 600;
        final int PRECIO_GENERAL = 850;

        // Descuentos y suplementos
        final int SUPLEMENTO_FIN_SEMANA = 150;
        final int DESCUENTO_MIERCOLES = 30;

        // Crear la sala con todas las butacas libres
        String sala = "";
        for (int i = 0; i < FILAS * BUTACAS_POR_FILA; i++) {
            sala += LIBRE;
        }

        int opcion = -1;
        do {
            System.out.println("""
                ===== CineDAW ===== 
                1. Ver sala 
                2. Comprar entrada 
                3. Estadísticas 
                0. Salir 
                Elige una opción:
                """);

            try {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.println("     P A N T A L L A");
                        System.out.println("   1 2 3 4   5 6 7 8");

                        for (char fila = 'A'; fila <= 'F'; fila++) {
                            System.out.print(fila + "  ");

                            for (int butaca = 1; butaca <= BUTACAS_POR_FILA; butaca++) {
                                int posicion = (fila - 'A') * BUTACAS_POR_FILA + (butaca - 1);

                                System.out.print(sala.charAt(posicion) + " ");

                                if (butaca == 4) {
                                    System.out.print("  ");
                                }
                            }
                            System.out.println();
                        }
                        System.out.println("(- libre, X ocupada)");
                        break;

                    case 2:

                        break;

                    case 3:

                        break;

                    case 0:
                        System.out.println("¡Hasta pronto!");
                        break;

                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            } catch (InputMismatchException e)  {
                System.out.println("Opción no válida.");
                scanner.nextLine();
            }
        } while (opcion != 0);

        scanner.close();

    }
}
