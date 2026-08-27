package ar.edu.uns.cs.ed.tdas.tdacola;
import java.util.EmptyStackException;
import java.util.Stack;

public class ColaConPila<E> implements Queue<E> {
    //atributos de instancia
    protected Stack<E> pila;
    protected Stack<E> pilAUX;
    //constructor
    public ColaConPila(){
        pila=new Stack<E>();
        pilAUX=new Stack<>();
    }
    public int size(){
        return pila.size();
    }
    public boolean isEmpty(){
        return pila.isEmpty();
    }
    public void enqueue(E element){
        while(!pila.isEmpty()){
            pilAUX.push(pila.pop());
        }
        pila.push(element);
        while(!pilAUX.isEmpty()){
            pila.push(pilAUX.pop());
        }

    }
    public E dequeue(){
        if(pila.isEmpty())
            throw new ar.edu.uns.cs.ed.tdas.excepciones.EmptyQueueException("la cola esta vacia");
        return pila.pop();
    }
    public E front(){
        if(pila.isEmpty())
            throw new ar.edu.uns.cs.ed.tdas.excepciones.EmptyQueueException("la cola esta vacia");
        return pila.peek();
        

    }
    
}
