
import java.util.Scanner;

public class LabTexto {
    public static void main(String[] args) {
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
                        int caracteres = frase.replace(" ", "").length();
                        System.out.println("Caracteres: " + caracteres);

                        //Cuenta palabras
                        int palabras = 0;
                        String fraseLimpia = frase.trim();
                        boolean dentroPalabra = false;

                        for (int i = 0; i < fraseLimpia.length(); i++) {
                            if (fraseLimpia.charAt(i) != ' ' && !dentroPalabra) {
                                palabras++;
                                dentroPalabra = true;
                            } else if (fraseLimpia.charAt(i) == ' ') {
                                dentroPalabra = false;
                            }
                        }
                        System.out.println("Palabras: " + palabras);

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
                            if (c >= 'A' && c <= 'Z') {
                                mayusculas++;
                            }
                            //Cuenta los dígitos
                            if (c >= '0' && c <= '9') {
                                digitos++;
                            }
                        }
                        System.out.println("Vocales: " + vocales + " | Mayúsculas: " + mayusculas + " | Dígitos: " + digitos );

                        //Comprueba si es un palíndromo
                        String fraseMinusSinEspacio = frase.toLowerCase().replace(" ", "");

                        boolean esPalindromo = true;

                        for (int i = 0; i < fraseMinusSinEspacio.length() / 2; i++) {
                            if (fraseMinusSinEspacio.charAt(i) !=
                                    fraseMinusSinEspacio.charAt(fraseMinusSinEspacio.length() - 1 - i)) {
                                esPalindromo = false;
                                break;
                            }
                        }

                        if (esPalindromo) {
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

                        //Se ajusta para que esté entre 0 y 25 siempre
                        desplazamiento = ((desplazamiento % NUM_LETRAS) + NUM_LETRAS) % NUM_LETRAS;
                        String cifrado = "";
                        for (int i = 0; i < texto.length(); i++) {
                            char c = texto.charAt(i);

                            if (c >= 'A' && c <= 'Z') {
                                int posicion = c - 'A';
                                int nuevaPosicion = (posicion + desplazamiento) % NUM_LETRAS;
                                c = (char) ('A' + nuevaPosicion);
                            } else if (c >= 'a' && c <= 'z') {
                                int posicion = c - 'a';
                                // Se usa % 26 para volver al principio del alfabeto si nos pasamos de la Z
                                int nuevaPosicion = (posicion + desplazamiento) % NUM_LETRAS;
                                c = (char) ('a' + nuevaPosicion);
                            }
                            cifrado += c;
                        }

                        System.out.println("Cifrado: " + cifrado);
                        break;

                    case 3: // Opción 3 — Validar contraseña
                        System.out.println("Usuario: ");
                        String usuario = scanner.nextLine();
                        System.out.println("Contraseña: ");
                        String contrasena =  scanner.nextLine();

                        //Comprueba si cumple la longitud mínima
                        boolean longitudCumplido = contrasena.length() >= MIN_LONG;
                        //Comprueba si contiene al menos una mayúscula y  un dígito
                        boolean mayusCumplido = false;
                        boolean digitoCumplido = false;
                        for (int i = 0; i < contrasena.length(); i++) {
                            char c = contrasena.charAt(i);
                            if (c >= 'A' && c <= 'Z') {
                                mayusCumplido = true;
                            }
                            if (c >= '0' && c <= '9') {
                                digitoCumplido = true;
                            }
                            if (mayusCumplido && digitoCumplido) {
                                break;
                            }
                        }
                        //Comprueba que NO contiene el usuario, sin distinguir mayúsculas y minúsculas
                        boolean noContieneUsuario = contrasena.toLowerCase().indexOf(usuario.toLowerCase()) == -1;

                        System.out.println((longitudCumplido ? "[OK] " : "[--] ") + "Al menos 8 caracteres");
                        System.out.println((mayusCumplido ? "[OK] " : "[--] ") + "Una mayúscula");
                        System.out.println((digitoCumplido ? "[OK] " : "[--] ") + "Un dígito");
                        System.out.println((noContieneUsuario ? "[OK] " : "[--] ") + "No contiene el usuario");

                        // Cuenta reglas cumplidas
                        int reglasCumplidas = 0;
                        if (longitudCumplido) reglasCumplidas++;
                        if (mayusCumplido) reglasCumplidas++;
                        if (digitoCumplido) reglasCumplidas++;
                        if (noContieneUsuario) reglasCumplidas++;

                        String nivel = (reglasCumplidas == 4) ? "FUERTE"
                                : (reglasCumplidas >= 2) ? "MEDIA" : "DÉBIL";

                        System.out.println("Nivel: " + nivel + " (" + reglasCumplidas + "/4)");
                        break;

                    case 0:
                        System.out.println("Saliendo del programa...");
                        break;

                    default:
                        System.out.println("La opción " + opcion + " no es válida.");
                }

            } catch (NumberFormatException e) {
                System.out.println("La opción \"" + opcionTexto + "\" no es válida.");
            }
        } while(opcion != 0);
        scanner.close();
    }
}
