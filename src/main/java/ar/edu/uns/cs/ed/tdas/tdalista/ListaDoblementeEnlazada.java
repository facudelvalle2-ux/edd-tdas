package ar.edu.uns.cs.ed.tdas.tdalista;

import java.util.Iterator;


import ar.edu.uns.cs.ed.tdas.excepciones.BoundaryViolationException;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyListException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;

public class ListaDoblementeEnlazada<E> implements PositionList<E> {
    //atributos de instancia
    private int tamaño ;
    private NodoD<E> head;
    private NodoD<E> tail;
    //constructor
    public ListaDoblementeEnlazada(){
        tamaño=0;
        head=new NodoD<E>(null,null,null);
        tail=new NodoD<E>(null,null,null);
        head.setSiguiente(tail);
        tail.setAnterior(head);
    }
    public int size(){
        return tamaño;
    }
    public boolean isEmpty(){
        return tamaño==0;
    }
    public E set(Position<E> p, E elemen){
        NodoD<E> nodo=checkPosition(p);
        E elementoviejo=nodo.element();
        nodo.setElem(elemen);
        return elementoviejo;
    }
    public void addFirst(E elem){
        NodoD<E> anterior= head;
        NodoD<E> siguiente=head.getSiguiente();
        NodoD<E> nuevoNodo=new NodoD<E>(elem, siguiente, anterior);
        siguiente.setAnterior(nuevoNodo);
        anterior.setSiguiente(nuevoNodo);
        tamaño++;
    }
    public void addLast(E elem){
        NodoD<E> siguiente= tail;
        NodoD<E> anterior=tail.getAnterior();
        NodoD<E> nuevoNodo=new NodoD<E>(elem, siguiente, anterior);
        anterior.setSiguiente(nuevoNodo);
        siguiente.setAnterior(nuevoNodo);
        tamaño++;
    }


    public Position<E> prev(Position<E> p){
        NodoD<E> nodo=checkPosition(p);
        if(nodo.getAnterior()==head){
            throw new BoundaryViolationException("Prev: la primera posicion no tiene anterior");
        }
        return nodo.getAnterior();
    }
    public Position<E> next(Position<E> p){
        NodoD<E> nodo=checkPosition(p);
        if(nodo.getSiguiente()==tail){
            throw new BoundaryViolationException("Next:la ultima posicion no tiene siguiente");
        }
        return nodo.getSiguiente();
    }

    public void addAfter(Position<E> p, E item){
        NodoD<E> nodo=checkPosition(p);
        NodoD<E> nsiguiente=nodo.getSiguiente();
        NodoD<E> nuevo= new NodoD<E>(item,nsiguiente,nodo);
        nsiguiente.setAnterior(nuevo);
        nodo.setSiguiente(nuevo);
        tamaño++;
    }

    public void addBefore(Position<E> p, E item){
        NodoD<E> nodo=checkPosition(p);
        NodoD<E> nanterior=nodo.getAnterior();
        NodoD<E> nuevo= new NodoD<E>(item,nodo,nanterior);
        nanterior.setSiguiente(nuevo);
        nodo.setAnterior(nuevo);
        tamaño++;
    }
    public Position<E> first() {
        if (isEmpty()) {
            throw new EmptyListException("La lista está vacía");
        }
        return head.getSiguiente();
    }

    public Position<E> last(){
        if(isEmpty()){
            throw new EmptyListException("La lista esta vacia");
        }
        return tail.getAnterior();

    }

    public E remove(Position<E> p){
        NodoD<E> nodo=checkPosition(p);
        E removi=nodo.element();
        NodoD<E> anterior=nodo.getAnterior();
        NodoD<E> siguiente=nodo.getSiguiente();
        anterior.setSiguiente(siguiente);
        siguiente.setAnterior(anterior);
        nodo.setElem(null);
        nodo.setAnterior(null);
        nodo.setSiguiente(null);
        tamaño--;
        return removi;

    }
    public Iterator<E> iterator(){
        return new ElementIterator<>(this);
    }
    public Iterable<Position<E>> positions(){
        NodoD<E> cursor=head.getSiguiente();
        PositionList<Position<E>> l=new ListaDoblementeEnlazada<>();
        while(cursor!=tail){
            l.addLast(cursor);
            cursor=cursor.getSiguiente();
        }
        return l;
        
    }

    public void ejercicio2(E e1, E e2){
        if(this.isEmpty()){
            NodoD<E> primero=new NodoD<>(e2,null,head);
            NodoD<E> anteult=new NodoD<>(e1, tail, primero);
            primero.setSiguiente(anteult);
            head.setSiguiente(primero);
            tail.setAnterior(anteult);
        }
        NodoD<E> primero=head.getSiguiente();
        NodoD<E> segundo=primero.getSiguiente();
        NodoD<E> nuevo=new NodoD<>(e1, segundo, primero);
        primero.setSiguiente(nuevo);
        segundo.setAnterior(nuevo);
        tamaño++;
        NodoD<E> ultimo= tail.getAnterior();
        NodoD<E> antUlt= ultimo.getAnterior();
        NodoD<E> nanteul=new NodoD<>(e2, ultimo, antUlt);
        ultimo.setAnterior(nanteul);
        antUlt.setSiguiente(nanteul);
        
    }
    public boolean ej3Pertenece(PositionList<E> l, E e1){
        if(!l.isEmpty()){
            for(E item:l){
                if(item.equals(e1))return true;
        }
    }
    return false;}
   /*  public boolean Pertenece(PositionList<E> l, E e1){
        if(!l.isEmpty()){
            Iterator<E> it=l.iterator();
            while(it.hasNext()){
                E item=it.next();
                if(item.equals(e1))
                    return true;
        }
    }
    return false;}/* */ 

    public int ej3b(PositionList<E> l, E e1){
        int contador=0;
        if(!l.isEmpty()){
            for(E item:l){
                if(item.equals(e1)) contador++;
            }
        }
        return contador;
    }

    public boolean ej3c(PositionList<E> l, E x, int n){
        int contador=0;
        if(!l.isEmpty()){
            for(E item:l){
                if(x.equals(item))
                    contador++;
                if(contador==n) return true;//si hay n cumple n o mas
            }
        }
        return contador>=n;
    }
    //con iterador y metodos implementados
    public PositionList<E> ej4(PositionList<E> l){
        PositionList<E> lista=new ListaDoblementeEnlazada<>();
        for(E item:l){
            lista.addLast(item);
            lista.addLast(item);
        }
        return lista;
    }
    //sin iterador ni metodos ya echos
    /*public PositionList<E> ej4(PositionList<E> l){
        PositonList<E> lista=new ListaDoblementeEnlazada();
        Iterator<E> it= l.iterator();
        while(it.hasNext()){
            E item=it.next();
            NodoD<E> siguiente1=lista.tail;
            NodoD<E> anterior1=lista.tail.getAnterior();
            NodoD<E> nuevo1=new Nodo<E>(item,siguiente1,anterior1);
            anterior1.setSiguiente(nuevo1);
            siguiente1.setAnterior(nuevo1);
            lista.tamaño++;

            NodoD<E> siguiente2=lista.tail;
            NodoD<E> anterior2=lista.tail.getAnterior();
            NodoD<E> nuevo2=new Nodo<E>(item,siguiente2,anterior2);
            anterior2.setSiguiente(nuevo2);
            siguiente2.setAnterior(nuevo2);
            lista.tamaño++;
        }
            return lista;
    } */

    /*
    public PositionList<E> ej4_conPosiciones(PositionList<E> l) {
    ListaDobleEnlazada<E> lista= new ListaDobleEnlazada<E>();
    if (!l.isEmpty()) {
        Position<E> cursor = l.first();
        boolean termine = false;
        while (!termine) {
            E item = cursor.element();
            lista.addLast(item);
            lista.addLast(item);
            
            if (cursor == l.last()) {
                termine = true; 
            } else {
                cursor = l.next(cursor); 
            }
        }
    }
    return lista;
} */


   // public Iterable<Character> interseccionEj5(PositionList<Character> l1, PositionList<Character> l2){//preguntar, no me sale :(
        
    public PositionList<E> ej6a(PositionList<E> l1,PositionList<E> l2){
        PositionList<E> lista= new ListaDoblementeEnlazada<>();
        Iterator<E> it1=l1.iterator();
        Iterator<E> it2=l2.iterator();
        while(it1.hasNext() && it2.hasNext()){
            lista.addLast(it1.next());
            lista.addLast(it2.next());
        }
        while(it1.hasNext()){
            lista.addLast(it1.next());
        }
        while(it2.hasNext()){
            lista.addLast(it2.next());
        }
        return lista;
    }

    

    private NodoD<E> checkPosition( Position<E> p ) {
    try {
    if( p == null ) 
        throw new InvalidPositionException("la posicion es nula");
    if (p.element() == null)
        throw new InvalidPositionException("p eliminada previamente");
    return (NodoD<E>) p;
    } catch( ClassCastException e ) { 
    throw new InvalidPositionException("p no es un nodo de lista");
    }
    } 
    
}
