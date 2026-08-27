package ar.edu.uns.cs.ed.tdas.ejercicios.tp6;
import ar.edu.uns.cs.ed.tdas.tdaarbol.Arbol;
import ar.edu.uns.cs.ed.tdas.tdaarbol.TNodo;
import ar.edu.uns.cs.ed.tdas.tdaarbol.Tree;
import ar.edu.uns.cs.ed.tdas.tdalista.Position;
import ar.edu.uns.cs.ed.tdas.tdamapeo.Map;
import ar.edu.uns.cs.ed.tdas.tdamapeo.MapeoConHash;

public class ej3 {
    /*Programe un método con la siguiente signatura: public Map<Character, Integer>
cantidadRepeticiones(Tree<Character> t). Este método deberá retornar un mapeo con cada uno de los
caracteres del árbol y la cantidad de veces que aparece cada carácter en el árbol. Resuelva este problema utilizando
un recorrido en preorden.*/
public Map<Character, Integer>cantidadRepeticiones(Tree<Character> t){
    Map<Character,Integer> mapa=new MapeoConHash<>();
    if(!t.isEmpty()){
        preOrdenRepeticiones(t,t.root(),mapa);
    }
    return mapa;}

private void preOrdenRepeticiones(Tree<Character> t, Position<Character> p, Map<Character,Integer> m){
    Character letra=p.element();
    int cant=m.get(letra);
    if(cant==0){
        m.put(letra, 1);
    }
    else{
        m.put(letra,cant+1);
    }
    for(Position<Character> hijo: t.children(p)){
        preOrdenRepeticiones(t, hijo, m);
    }
}

}

