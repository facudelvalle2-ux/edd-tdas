package ar.edu.uns.cs.ed.tdas.ejercicios.tp6;
import ar.edu.uns.cs.ed.tdas.tdaarbol.Arbol;
import ar.edu.uns.cs.ed.tdas.tdaarbol.TNodo;
import ar.edu.uns.cs.ed.tdas.tdaarbol.Tree;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdalista.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.tdamapeo.Map;
import ar.edu.uns.cs.ed.tdas.tdamapeo.MapeoConHash;

public class ej4 {
    /*Ejercicio 4: Dado un árbol a de Strings y un String s, programe un método tal que retorne un Iterable con las
posiciones del árbol en las que aparece el String s. Para resolver este problema implemente un recorrido en
postorden.*/
public Iterable<Position<String>> repeticiones(Tree<String> t, String s){
    PositionList<Position<String>>lista=new ListaDoblementeEnlazada<>();
    if(!t.isEmpty()){
        PostOrden(t,t.root(),s,lista);}
    return lista;
}
private void PostOrden(Tree<String> a, Position<String> p, String s, PositionList<Position<String>> lista){
    for(Position<String> hijo : a.children(p)){
        PostOrden(a, hijo, s, lista);
    }
    String palabra=p.element();
    if(palabra.equals(s)){
        lista.addLast(p);
    }
}
    
}
