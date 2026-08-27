package ar.edu.uns.cs.ed.tdas.ejercicios.tp2.ej4;
import java.util.Queue;
import java.util.LinkedList;

public class Resolucion4 {
    public int mayor(Queue<Integer> q){
        Queue<Integer> ColaAux=new LinkedList<>();
        int max=Integer.MIN_VALUE;
        while(!q.isEmpty()){
            int elem = q.remove();
            if(max<elem){
                max=elem;
            }
            ColaAux.add(elem);
        }
        while(!ColaAux.isEmpty()){
            q.add(ColaAux.remove());
        }
        return max;
    }
}
