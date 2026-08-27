package ar.edu.uns.cs.ed.tdas.tdalista;

public class NodoD<E> implements Position<E>{
    private  E elem;
    private NodoD<E> anterior;
    private NodoD<E> siguiente;

    public NodoD(E elemento, NodoD<E> sig, NodoD<E> ant ){
        elem=elemento;
        siguiente=sig;
        anterior=ant;
    }
    
    public E element(){
        return elem;
    }
    public void setElem(E element){
        elem=element;
    }
    public NodoD<E> getSiguiente(){
        return siguiente;
    }
    public NodoD<E> getAnterior(){
        return anterior;
    }
    public void setAnterior(NodoD<E> ant){
        anterior=ant;
    }
    public void setSiguiente(NodoD<E> proximo){
        siguiente=proximo;
    }

    



    
}
