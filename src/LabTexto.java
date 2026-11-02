import java.util.InputMismatchException;
import java.util.Scanner;

public class LabTexto {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final int MIN_LONG = 8;
        final int NUM_LETRAS = 26;

        int opcion = -1;
        do {
            System.out.println("""
                    ===== LabTexto ===== 
                    1. Analizar frase 
                    2. Cifrado César 
                    3. Validar contraseña 
                    0. Salir 
                    Elige una opción:
                    """);
            String opcionTexto = scanner.nextLine();
            try {
                opcion = Integer.parseInt(opcionTexto);

                switch (opcion) {
                    case 1: //Opción 1 — Analizar frase
                        System.out.println("Escribe una frase: ");
                        String frase = scanner.nextLine();

                        //Cuenta caracteres
                        int caracteres = frase.length();
                        System.out.println("Caracteres: " + caracteres);

                        //Cuenta palabras
                        int palabra = 0;
                        String fraseLimpio = frase.trim();
                        boolean dentroPalabra = false;

                        for (int i = 0; i < frase.length(); i++) {
                            if (fraseLimpio.charAt(i) != ' ' && !dentroPalabra) {
                                palabra++;
                                dentroPalabra = true;
                            } else if (fraseLimpio.charAt(i) == ' ') {
                                dentroPalabra = false;
                            }
                        }
                        System.out.println("Palabras: " + palabra);

                        //Cuenta vocales, mayúsculas, dígitos
                        int vocales = 0;
                        int mayusculas = 0;
                        int digitos = 0;
                        for (int i = 0; i < frase.length(); i++) {
                            char c = frase.charAt(i);
                            char letra = Character.toLowerCase(c);

                            //Cuenta los vocales
                            if (letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u') {
                                vocales++;
                            }
                            //Cuenta las mayúsculas
                            if (c >= 'A' && c <= "Z") {
                                mayusculas++;
                            }
                            //Cuenta los dígitos
                            if (c >= 0 && c <= 9) {
                                digitos++;
                            }
                        }
                        System.out.println("Vocales: " + vocales + " | Mayúsculas: " + mayusculas + " | Dígitos: " + digitos );

                        //Comprueba si es un palíndromo
                        String fraseMinus = frase.toLowerCase().trim();
                        String fraseSinEspacio = "";
                        for (int i = 0; i < fraseMinus.length(); i++) {
                            if (fraseMinus.charAt(i) != ' ') {
                                fraseSinEspacio += fraseMinus.charAt(i);
                            }
                        }

                        String fraseInvertida = "";
                        for (int i = fraseSinEspacio.length() -1; i >= 0; i--) {
                            fraseInvertida += fraseSinEspacio.charAt(i);
                        }

                        if (fraseInvertida.equals(fraseSinEspacio)) {
                            System.out.println("Es un palíndromo.");
                        } else {
                            System.out.println(" No es un palíndromo");
                        }
                        break;

                    case 2: //Opción 2 — Cifrado César
                        System.out.println("Texto: ");
                        String texto = scanner.nextLine();

                        int desplazamiento = 0;
                        boolean valido = false;
                        do {
                            System.out.println("Desplazamiento: ");
                            try {
                                desplazamiento = Integer.parseInt(scanner.nextLine());
                                valido = true;
                            } catch (NumberFormatException e){
                                System.out.println("El desplazamiento debe ser un número entero.");
                            }
                        }while (!valido);

                        //Se suma 26 para obtener un número positivo
                        desplazamiento = ((desplazamiento % 26) + 26) % 26;
                        String cifrado = "";
                        for (int i = 0; i < texto.length(); i++) {
                            char c = texto.charAt(i);

                            if (c >= 'A' && c <= 'Z') {
                                int posicion = c - 'A';
                                int nuevaPosicion = (posicion + desplazamiento) % 26;
                                c = (char) ('A' + nuevaPosicion);
                            } else if (c >= 'a' && c <= 'z') {
                                int posicion = c - 'a';
                                // Se usa % 26 para volver al principio del alfabeto si nos pasamos de la Z
                                int nuevaPosicion = (posicion + desplazamiento) % 26;
                                c = (char) ('a' + nuevaPosicion);
                            }
                            cifrado += c;
                        }

                        System.out.println("Cifrado: " + cifrado);
                        break;

                }


            } catch (NumberFormatException e) {
                System.out.println("La opción \"" + opcionTexto + "\" no es válida.");
            }
        } while(opcion != 0);
        scanner.close();
    }
}
