package ar.edu.uns.cs.ed.tdas.tdaarbol;
import java.util.Iterator;

import ar.edu.uns.cs.ed.tdas.excepciones.BoundaryViolationException;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyTreeException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidOperationException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdalista.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

public class Arbol<E> implements Tree<E> {
    //atributos de instancia
    protected int size;
    protected TNodo<E> raiz;
    public Arbol(){
        raiz=null;
        size=0;
    }
    public boolean isEmpty(){
        return raiz==null;
    }
    public Position<E> root(){
        if(isEmpty()){
            throw new EmptyTreeException("Arbol vacio");
        }
        return raiz;
    }
    public boolean isExternal(Position<E> v){
        TNodo<E> nodo=checkPosition(v);
        return nodo.getHijos().isEmpty();// veo la validez de esa posicion , la asigno a un nodo y devuelvo si en ese nodo la lista de hijos esta vacia
    }
    public Position<E> addFirstChild(Position<E> p, E e){
        if (isEmpty()) {
            throw new InvalidPositionException("El árbol está vacío");
        }
        TNodo<E> nodo=checkPosition(p);
        TNodo<E> nuevo=new TNodo<E>(e, nodo);//creo un nodo con el elemento y con la pos  como padre
        nodo.getHijos().addFirst(nuevo);//nodo.getHijos() es la lista de hijos  del padre y le añado al principio el nuevo nodo
        size++;
        return nuevo;
    }
    /**
	 * Agrega un nodo con rótulo e como último hijo de un nodo dado.
	 * @param e Rótulo del nuevo nodo.
	 * @param p Posición del nodo padre.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o el árbol está vacío.
	 */
    public Position<E> addLastChild(Position<E> p, E e){
        if (isEmpty()) {
            throw new InvalidPositionException("El árbol está vacío");
        }
        TNodo<E> pos=checkPosition(p);
        TNodo<E> nuevo=new TNodo<E>(e,pos);
        pos.getHijos().addLast(nuevo);
        size++;
        return nuevo;
    }
    public Position<E> addBefore(Position<E> p, Position<E> rb, E e){
        if (isEmpty()) {
            throw new InvalidPositionException("el arbol esta vacio");}
        TNodo<E> n=checkPosition(p);
        TNodo<E> hd=checkPosition(rb);
        if(hd.getPadre()!=n){
            throw new InvalidPositionException("p no es padre de rb");
        }
        TNodo<E> nuevo=new TNodo<E>(e,n);//nodo a añadir con el elemento y el nodo padre (p)
        PositionList<TNodo<E>> hijos=n.getHijos();//lista de hijos del padre en posicion p
        Position<TNodo<E>> posicionDeRb = null;
        for(Position<TNodo<E>> hijo:hijos.positions()){
            if(hijo.element()==hd){
                posicionDeRb=hijo;
                break;
            }
        }
        if(posicionDeRb==null){
            throw new InvalidPositionException("p no es padre de rb");
        }
        hijos.addBefore(posicionDeRb, nuevo); // inserto el nodo nuevo delante de rb
        size++;
        return nuevo;
    }
	public Position<E> addAfter (Position<E> p, Position<E> lb, E e){
        if(isEmpty()){
            throw new InvalidPositionException("el arbol esta vacio");
        }
        TNodo<E> pos=checkPosition(p);
        TNodo<E> hi=checkPosition(lb);
        if(hi.getPadre()!=pos){
            throw new InvalidPositionException("p no es padre de lb");
        }
        TNodo<E> nuevo=new TNodo<E>(e,pos);
        PositionList<TNodo<E>> hijos=pos.getHijos();
        Position<TNodo<E>> posHI=null;
        for( Position<TNodo<E>> hijo : hijos.positions()){
            if(hijo.element()==hi){
                posHI=hijo;
                break;
            }
        }
        if(posHI==null){
            throw new InvalidPositionException("lb no se encontró en la lista de hijos");
        }
        hijos.addAfter(posHI, nuevo);
        size++;
        return nuevo;
    }
    public void removeExternalNode(Position<E> p){//elimina la hoja de esa posicion
        if(isEmpty()){
            throw new InvalidPositionException("Arbol vacio");
        }
        TNodo<E> pos=checkPosition(p);
        if(!pos.getHijos().isEmpty()){//veo si el nodo p tiene hijos
            throw new InvalidPositionException("p no es una hoja");
        }
        if(pos==raiz){
            raiz=null;
            size=0;
            pos.setElemento(null);
            return;
        }
        TNodo<E> padre=pos.getPadre();
        PositionList<TNodo<E>> hijosDePadre=padre.getHijos();
        Position<TNodo<E>> posel=null;
        for(Position<TNodo<E>> poshijos: hijosDePadre.positions()){
            if(poshijos.element()==pos){
                posel=poshijos;
                break;
            }
        }
        if(posel==null){
            throw new InvalidPositionException("p no aparece en la lista de hijos de su padre");
        }
        hijosDePadre.remove(posel);
        pos.setElemento(null);
        size--;
    }
    public void removeInternalNode (Position<E> p){
          if(isEmpty()){
            throw new InvalidPositionException("Arbol vacio");
        }
        TNodo<E> pos=checkPosition(p);
        if (pos.getHijos().isEmpty()) { 
        throw new InvalidPositionException("El nodo pasado por parámetro es una hoja, no un nodo interno");}
        if(pos==raiz){
            if(raiz.getHijos().size()!=1 ){// veo si el nodo es raiz y tiene un solo hijo
                throw new InvalidPositionException("la posicion no es valida");
            }
            TNodo<E> unicoHijo=raiz.getHijos().first().element();
            raiz=unicoHijo;
            unicoHijo.setPadre(null);
        }
        else{
            TNodo<E> padre=pos.getPadre();
            PositionList<TNodo<E>> hermanos= padre.getHijos();
           
            Position<TNodo<E>> posnodoenH=null;
            for(Position<TNodo<E>> posnodo: hermanos.positions()){
                if(posnodo.element()==pos){
                    posnodoenH=posnodo;
                    break;
                }
            }
            if(posnodoenH==null){
                throw new InvalidPositionException("p no aparece en la lista de hijos de su padre");
            }
            for(TNodo<E> nodoHijo : pos.getHijos()){
                nodoHijo.setPadre(padre);
                hermanos.addBefore(posnodoenH,nodoHijo);
            }
            hermanos.remove(posnodoenH);
        }
            pos.setElemento(null);
            pos.setPadre(null);
            size--;
    }
	
    public Iterable<Position<E>> children(Position<E> v){
        TNodo<E> p=checkPosition(v);
        PositionList<Position<E>> lista=new ListaDoblementeEnlazada<>();
        PositionList<TNodo<E>> hijos=p.getHijos();
        for(TNodo<E> hijo: hijos){
            lista.addLast(hijo);
        }
        return lista;
    }
    public Position<E> parent(Position<E> v){
        TNodo<E> n=checkPosition(v);
        if(v==root()){
            throw new BoundaryViolationException("el nodo raiz no tiene padre");
        }
        return n.getPadre();
    }
    public boolean isInternal(Position<E> v){
        TNodo<E> n=checkPosition(v);
        return (!n.getHijos().isEmpty());
        //return !isExternal(n);
    }
    
    public void createRoot(E element){
        if(!isEmpty()){
            throw new InvalidOperationException("el arbol ya tiene raiz"); 
        }
        raiz=new TNodo<E>(element,null);
        size++;
    }
    public Iterator<E> iterator(){
        ListaDoblementeEnlazada<E> l=new ListaDoblementeEnlazada<>();
        if(!isEmpty()){
            preOrden(raiz,l);
        }
        return l.iterator();
    }
    private void preOrden(TNodo<E> nodoActual, ListaDoblementeEnlazada<E> lista){
        lista.addLast(nodoActual.element());
        for(TNodo<E> hijo: nodoActual.getHijos()){
            preOrden(hijo, lista);
        }
    }

	public Iterable<Position<E>> positions(){
        ListaDoblementeEnlazada<Position<E>> listaPosiciones=new ListaDoblementeEnlazada<>();
        if(!isEmpty()){
            preOrdenPos(raiz, listaPosiciones);
        }
        return listaPosiciones;
    }
    private void preOrdenPos(TNodo<E> nodo, ListaDoblementeEnlazada<Position<E>> lista){
        lista.addLast(nodo);
        for(TNodo<E> hijo: nodo.getHijos()){
            preOrdenPos(hijo, lista);
        }
    }
    public int size(){
        return size;
    }
    public boolean isRoot(Position<E> v){
        TNodo<E> p=checkPosition(v);
        return p.getPadre()==null;
    }
	public E replace(Position<E> v, E e){
        TNodo<E> p=checkPosition(v);
        E elementoviejo=p.element();
        p.setElemento(e);
        return elementoviejo;
    }
	public void removeNode (Position<E> p){
      TNodo<E> pos=checkPosition(p);
      if(isInternal(pos))
        removeInternalNode(pos);
      else
        removeExternalNode(pos);
    }
    /* 
    Este método deberá eliminar del árbol receptor del mensaje a la posición p siempre que p sea el último hijo
    (de izq a der) de su padre. La raíz no se considera último hijo, en este caso el método deberá lanzar
    InvalidOperationException. Si la posición p es inválida el método deberá lanzar
    InvalidPositionException*/
    public void eliminarUltimoHijo(Position<E> p){
        TNodo<E> pos=checkPosition(p);
        if(pos==raiz){
            throw new InvalidOperationException("la raiz no se considera ultimo hijo");
        }
        TNodo<E> padre=pos.getPadre();
        PositionList<TNodo<E>> hermanos=padre.getHijos();
        TNodo<E> ultimo=hermanos.last().element();
        if (pos != ultimo) {
        throw new InvalidPositionException("El nodo no es el último hijo de su padre");
        }
        removeNode(pos);
    }
    





















    public TNodo<E> checkPosition(Position<E> p){
        try{
            if(p==null){
                throw new InvalidPositionException("la posicion no puede ser nula");
            }
            if(p.element()==null){
                throw new InvalidPositionException(" el elemento fue eliminado");
            }
            return(TNodo<E>)p;
        }
        catch(ClassCastException c){
            throw new InvalidPositionException("no es un nodo de la lista");
        }
    }
}
