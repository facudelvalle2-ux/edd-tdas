package ar.edu.uns.cs.ed.tdas.ejercicios.tp2.ej2;
import java.util.LinkedList;
import java.util.Queue;
public class ResolucionEj2 {
    public Queue<Integer> Colaimpares(Queue<Integer> q){
        Queue<Integer> colimp=new LinkedList<>();
        while(!q.isEmpty()){
            int elemento=q.remove();// o poll() nose si debo usar uno en especifico
            if(elemento%2!=0){
                colimp.add(elemento);
            }

        }
        return colimp;
    }
    
}
