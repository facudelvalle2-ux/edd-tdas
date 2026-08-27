package ar.edu.uns.cs.ed.tdas.tdalista;
import java.util.*;

public class ElementIterator<E> implements Iterator<E> {
    protected PositionList<E> lista;
    protected Position<E> cursor;
    
    public ElementIterator (PositionList <E> l ) {
        lista = l; 
        if (lista.isEmpty()) cursor = null;
        else
            cursor=lista.first();
        }
    public boolean hasNext(){   
        return cursor!=null;
    }
    public E next(){
        if(cursor==null)
            throw new NoSuchElementException("iterador de lista : no hay siguiente");
        E resultado=cursor.element();
        cursor=(cursor==lista.last())?null:lista.next(cursor);
        return resultado;
    }

}
