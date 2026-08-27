package ar.edu.uns.cs.ed.tdas.ejercicios.tp2.ej3;
import java.util.Stack;

public class TesterPilas {

    public static void main(String[] args) {
        // Creamos una instancia de tu clase (asegurate de que se llame Resolucion)
        Resolucion res = new Resolucion();

        // 1. Armamos la Pila 1 (tamaño 3)
        Stack<String> pila1 = new Stack<>();
        pila1.push("A1");
        pila1.push("B1");
        pila1.push("C1"); // C1 queda arriba de todo

        // 2. Armamos la Pila 2 (tamaño 5, más grande a propósito)
        Stack<String> pila2 = new Stack<>();
        pila2.push("V2");
        pila2.push("W2");
        pila2.push("X2");
        pila2.push("Y2");
        pila2.push("Z2"); // Z2 queda arriba de todo

        System.out.println("--- INTERCALANDO PILAS ---");
        System.out.println("Pila 1 tiene " + pila1.size() + " elementos.");
        System.out.println("Pila 2 tiene " + pila2.size() + " elementos.\n");

        // 3. Llamamos a tu método mágico
        Stack<String> resultado = res.intercalarPilas(pila1, pila2);

        // 4. Verificamos que las pilas originales hayan quedado vacías como pedía el TP
        System.out.println("¿Pila 1 quedó vacía? " + pila1.isEmpty());
        System.out.println("¿Pila 2 quedó vacía? " + pila2.isEmpty());
        System.out.println("Tamaño de la pila resultado: " + resultado.size() + "\n");

        // 5. Imprimimos el resultado vaciando la pila nueva para ver el orden
        System.out.println("--- CONTENIDO DE LA PILA RESULTADO (de arriba hacia abajo) ---");
        while (!resultado.isEmpty()) {
            System.out.println(resultado.pop());
        }
    }
}
