
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
        int entradasVendidas = 0;
        int recaudacion = 0;

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
                    case 1: //Opción 1 — Ver sala
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
                        System.out.println("(- libre, X ocupada)\n");
                        break;

                    case 2: //Opción 2 — Comprar entrada
                        if (sala.indexOf(LIBRE) == -1) {
                            System.out.println("La sala está completa.");
                            break;
                        }

                        // Elige la fila
                        char fila = ' ';
                        while (fila < 'A' || fila > 'F') {
                            System.out.print("Fila (A-F): ");
                            String filaString = scanner.nextLine().trim().toUpperCase();

                            if (filaString.length() == 1) {
                                fila = filaString.charAt(0);
                            } else {
                                fila = ' ';
                            }

                            if (fila < 'A' || fila > 'F') {
                                System.out.println("Fila no válida. Debe ser una letra entre A y F.");
                            }
                        }

                        // Elige la butaca
                        int butaca = 0;
                        do {
                            try {
                                System.out.print("Butaca (1-8): ");
                                butaca = scanner.nextInt();
                                scanner.nextLine();

                                if (butaca < 1 || butaca > 8) {
                                    System.out.println("Butaca no válida. Debe estar entre 1 y 8.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Debes introducir un número entero.");
                                scanner.nextLine();
                                butaca = 0;
                            }
                        } while (butaca < 1 || butaca > 8);

                        // Comprueba si está ocupada
                        int posicion = (fila - 'A') * BUTACAS_POR_FILA + (butaca - 1);

                        if (sala.charAt(posicion) == OCUPADA) {
                            System.out.println("Esta butaca ya está ocupada.");

                            //sugiere la primera butaca libre de esa misma fila
                            int inicioFila = (fila - 'A') * BUTACAS_POR_FILA;
                            int libre = sala.indexOf(LIBRE, inicioFila);
                            if (libre != -1 && libre < inicioFila + BUTACAS_POR_FILA) {
                                System.out.println("Te sugerimos la butaca " +
                                        (libre - inicioFila + 1));
                            } else {
                                System.out.println("No quedan butacas libres en esta fila.");
                            }
                            break;
                        }

                        // Añade su edad
                        int edad = -1;
                        do {
                            try {
                                System.out.print("Edad del espectador: ");
                                edad = scanner.nextInt();
                                scanner.nextLine();
                                if (edad < 0 || edad > 120) {
                                    System.out.println("Edad no válida. Debe estar entre 0 y 120.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Debes introducir una edad válida.");
                                scanner.nextLine();
                                edad = -1;
                            }
                        } while (edad < 0 || edad > 120);

                        // Calcula el precio según la edad
                        int precio;
                        String tarifa;
                        if (edad < 12) {
                            precio = PRECIO_INFANTIL;
                            tarifa = "infantil";
                        } else if (edad >= 65) {
                            precio = PRECIO_SENIOR;
                            tarifa = "senior";
                        } else {
                            precio = PRECIO_GENERAL;
                            tarifa = "general";
                        }

                        // Elige el día de la semana
                        int dia = 0;
                        do {
                            try {
                                System.out.print("Día de la semana (1=lunes ... 7=domingo): ");
                                dia = scanner.nextInt();
                                scanner.nextLine();

                                if (dia < 1 || dia > 7) {
                                    System.out.println("Día no válido. Debe estar entre 1 y 7.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Debes introducir un número entero.");
                                scanner.nextLine();
                                dia = 0;
                            }
                        } while (dia < 1 || dia > 7);

                        // Aplica descuentos o suplementos
                        String mensajeDia = "";

                        switch (dia) {
                            case 1:
                            case 2:
                            case 4:
                            case 5:
                                mensajeDia = "Día laborable: precio normal.";
                                break;

                            case 3:
                                precio = (int) Math.round(precio * (100 - DESCUENTO_MIERCOLES) / 100.0);
                                mensajeDia = "Miércoles, día del espectador: -30 %";
                                break;

                            case 6:
                            case 7:
                                precio += SUPLEMENTO_FIN_SEMANA;
                                mensajeDia = "Fin de semana: +1,50 €";
                                break;
                        }

                        // Muestra la tarifa y el precio
                        System.out.println("Tarifa: " + tarifa);
                        System.out.println(mensajeDia);

                        System.out.println("Total a pagar: " +
                                (precio / 100) + "," +
                                (precio % 100 < 10 ? "0" : "") +
                                (precio % 100) + " €");

                        // Marca la butaca comprada
                        sala = sala.substring(0, posicion) + OCUPADA + sala.substring(posicion + 1);
                        entradasVendidas++;
                        recaudacion += precio;

                        System.out.println("Entrada vendida: fila " + fila + ", butaca " + butaca + ".");
                        break;

                    case 3: //Opción 3 — Estadísticas
                        // Muestra las entradas vendidas con ternario
                        System.out.println("Entradas vendidas: " +
                                (entradasVendidas == 1 ? "1 entrada" : entradasVendidas + " entradas"));

                        // Muestra la recaudación total
                        System.out.println("Recaudación: " +
                                (recaudacion / 100) + "," +
                                (recaudacion % 100 < 10 ? "0" : "") +
                                (recaudacion % 100) + " €");

                        // Cuenta las butacas ocupadas y usa continue para saltar las butacas libres
                        int butacasOcupadas = 0;
                        for (int i = 0; i < sala.length(); i++) {
                            if (sala.charAt(i) == LIBRE) {
                                continue;
                            }
                            butacasOcupadas++;
                        }

                        // Calcula las butacas libres
                        int butacasLibres = FILAS * BUTACAS_POR_FILA - butacasOcupadas;
                        System.out.println("Butacas ocupadas: " + butacasOcupadas +
                                " | libres: " + butacasLibres);

                        // Calcula el porcentaje de ocupación
                        double ocupacion = (double) butacasOcupadas /
                                (FILAS * BUTACAS_POR_FILA) * 100;
                        ocupacion = Math.round(ocupacion * 100.0) / 100.0; //Redondea a 2 decimales

                        System.out.println("Ocupación: " + String.format("%.2f", ocupacion) + " %"); //Muestra siempre dos decimales
                        break;

                    case 0: //Opción 0 — Salir
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
