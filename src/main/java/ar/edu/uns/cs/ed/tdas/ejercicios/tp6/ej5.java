package ar.edu.uns.cs.ed.tdas.ejercicios.tp6;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyTreeException;
import ar.edu.uns.cs.ed.tdas.tdaarbol.Arbol;
import ar.edu.uns.cs.ed.tdas.tdaarbol.TNodo;
import ar.edu.uns.cs.ed.tdas.tdaarbol.Tree;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdalista.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;


public class ej5<E>{
    /*Escriba un método tal que dado un árbol genérico a y un elemento e, elimine de a todas las apariciones de e.
Compare los elementos por equivalencia. El método debe retornar la cantidad de eliminaciones realizadas. */
    public int cantEenA(Tree<E> a, E elem){
        PositionList<Position<E>> cantEl=new ListaDoblementeEnlazada<>();//c1
        if(a.isEmpty()){//c2
            return 0;//c3
        }
        buscoElmino(a,a.root(),elem,cantEl);//c4
        for(Position<E> el: cantEl){//O(N)
            a.removeNode(el);//O(N)
        }
        return cantEl.size();//c5
    }
    private void buscoElmino(Tree<E> a, Position<E> p, E elem, PositionList<Position<E>> cantEl){
        if(p.element().equals(elem)){//c6
            cantEl.addLast(p);//O(1)
        }
        for(Position<E> nodo: a.children(p)){//O(N)
            buscoElmino(a, nodo, elem, cantEl);//c5
        }
            //deberia ser O(N*N) N cuadrado
    }
}
