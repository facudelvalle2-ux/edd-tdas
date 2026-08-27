package ar.edu.uns.cs.ed.tdas.tdaarbol;

import ar.edu.uns.cs.ed.tdas.tdalista.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdalista.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

public class TNodo<E> implements Position<E> {
    //atributos de instancia
    protected E elemento;
    protected TNodo<E> padre;
    protected PositionList<TNodo<E>> listahijos;
    //constructor
    public TNodo(E elem , TNodo<E> padre){
        elemento=elem;
        this.padre=padre;
        listahijos=new ListaDoblementeEnlazada<>();
    }
    public TNodo(E elem){
        elemento=elem;
        padre=null;
    }
    public E element(){
        return elemento;
    }
    public PositionList<TNodo<E>> getHijos(){
        return listahijos;
    }
    public void setElemento(E elem){
        elemento=elem;
    }
    public TNodo<E> getPadre(){
        return padre;
    }
    public void setPadre(TNodo<E> padre){
        this.padre=padre;
    }

}
