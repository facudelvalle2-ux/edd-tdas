package ar.edu.uns.cs.ed.tdas.ejercicios.tp6;
import java.util.Iterator;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyTreeException;
import ar.edu.uns.cs.ed.tdas.tdaarbol.Arbol;
import ar.edu.uns.cs.ed.tdas.tdaarbol.TNodo;
import ar.edu.uns.cs.ed.tdas.tdaarbol.Tree;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdalista.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

public class ej6 { /*Dado un árbol de enteros a y un entero n, escriba un método que determine si n pertenece al árbol a. Para
resolver este método utilice el iterador del árbol */
public boolean pertenece(Tree<Integer> a, int n){
    if(a.isEmpty()){
        throw new EmptyTreeException("ta vacio");
    }
    Iterator<Integer> it=a.iterator();
    while (it.hasNext()){
        Integer actual= it.next();
        if(actual.equals(n)){
            return true;
        }
    }
    return false;
}
    
}
