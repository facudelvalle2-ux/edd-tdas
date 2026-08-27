package ar.edu.uns.cs.ed.tdas.tdapila;

import ar.edu.uns.cs.ed.tdas.excepciones.EmptyStackException;

public class PilaArreglo<E> implements Stack<E> {
    //atributos de instancia
    protected int tamaño;
    protected E [] dato;
    //constructor
    @SuppressWarnings("unchecked")
    public PilaArreglo(){
        tamaño=0;
        dato=(E[]) new Object[11];
    }
    public int size(){
        return tamaño;
    }
    public boolean isEmpty(){
        return size()==0;
    }
    public void push(E element){
        if(size()==dato.length){
            E [] aux= (E[]) new Object[dato.length *2];
            for(int i=0; i<dato.length; i ++){
                aux[i]=dato[i];
            }
            dato=aux;}

        dato[tamaño]=element;
        tamaño++;
        
    }
    public E top(){
        if(isEmpty()){
            throw new EmptyStackException("la pila esta vacia");
        }
        return dato[tamaño-1];
    }
    
    public E pop() {
        E resultado=top();
        dato[tamaño-1]=null;
        tamaño--;
        return resultado;
    }



    
}
