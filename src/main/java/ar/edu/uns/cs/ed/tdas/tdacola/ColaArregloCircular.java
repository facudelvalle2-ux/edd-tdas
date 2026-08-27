package ar.edu.uns.cs.ed.tdas.tdacola;

import ar.edu.uns.cs.ed.tdas.excepciones.EmptyQueueException;

public class ColaArregloCircular<E> implements Queue<E>{
    //atributos de instancia
    protected E [] dato;
    protected int f;
    protected int r;

    //constructor
    public ColaArregloCircular(){
        r=0;
        f=0;
        dato=(E[]) new Object[11];
    }
    public void enqueue(E item){
        if(size()==dato.length-1){
            E[] arregloAumentado= (E[]) new Object[dato.length*2];
            for(int i=0; i<size();i++){
                arregloAumentado[i]=dato[(f+i) % dato.length];
            }
            
           
            r=size();
            f=0;
            dato=arregloAumentado;
        } 
        dato[r]=item;
        r=(r+1) % dato.length;
    }
    public int size(){
        return (dato.length-f+r) % dato.length;
    }
    public E dequeue(){
        if(isEmpty()){
            throw new EmptyQueueException("la cola esta vacia");
        }
       E temp=dato[f];
       dato[f]=null;
       f=(f+1) % dato.length;
       return temp;
    }
    public boolean isEmpty(){
        return f==r;
    }
    public E front(){
        if(isEmpty()){
            throw new EmptyQueueException("la cola esta vacia");
        }
        return dato[f];
    }


    
}
