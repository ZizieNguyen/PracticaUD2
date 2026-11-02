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
                    case 1:
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
                        for (int i = fraseSinEspacio.length(); i < 0; i--) {
                            fraseInvertida += fraseSinEspacio.charAt(i);
                        }

                        if (fraseInvertida == fraseSinEspacio) {
                            System.out.println("Es un palíndromo.");
                        } else {
                            System.out.println(" No es un palíndromo");
                        }

                }


            } catch (NumberFormatException e) {
                System.out.println("La opción \"" + opcionTexto + "\" no es válida.");
            }
        } while(opcion != 0);
        scanner.close();
    }
}
