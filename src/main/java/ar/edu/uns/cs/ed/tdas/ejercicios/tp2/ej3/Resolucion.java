package ar.edu.uns.cs.ed.tdas.ejercicios.tp2.ej3;
import java.util.Stack;

public class Resolucion {
    public <E> Stack<E> intercalarPilas(Stack<E> p1, Stack<E> p2){
        Stack<E> pilaIntercalada = new Stack<>();
        while(!p1.isEmpty() && !p2.isEmpty()){
           E elemento= p1.pop();
            pilaIntercalada.push(elemento);
           E elemento2= p2.pop();
           pilaIntercalada.push(elemento2);
        }
        while(!p1.isEmpty()){
            pilaIntercalada.push(p1.pop());
        }
        while(!p2.isEmpty()){
            pilaIntercalada.push(p2.pop());
        }
        return pilaIntercalada;
    }
}
