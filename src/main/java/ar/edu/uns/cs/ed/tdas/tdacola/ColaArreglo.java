package ar.edu.uns.cs.ed.tdas.tdacola;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyQueueException;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyStackException;

public class ColaArreglo<E> implements Queue<E> {
    //atributos de instancia
    protected E [] dato;
    protected int f;
    protected int r;
    //constructor
    public ColaArreglo(){
        dato=(E[])new Object[11];
        f=0;
        r=0;
    }
    // servicios
    public int size(){
        return r-f;
    }
    public void enqueue(E element){
        if(r==dato.length){
            E [] aux=(E[])new Object[dato.length*2];
            for(int i=0; i<size();i++){
                aux[i]=dato[f+i];
            }
            dato=aux;
            r=size();
            f=0;
        }
        dato[r]=element;
        r++;
    }
    public boolean isEmpty(){
        return f==r;
    }
    public E dequeue(){
        if(isEmpty()){
            throw new EmptyQueueException("la cola esta vacia");
        }
        E elemento=dato[f];
        dato[f]=null;
        f++;
        return elemento;
    }
    public E front(){
        if(isEmpty()){
            throw new EmptyQueueException("la cola esta vacia");
        }
        return dato[f];
    }

}
