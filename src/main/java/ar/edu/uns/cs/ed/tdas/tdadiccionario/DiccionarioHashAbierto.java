package ar.edu.uns.cs.ed.tdas.tdadiccionario;

import ar.edu.uns.cs.ed.tdas.tdalista.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdalista.Position;
import ar.edu.uns.cs.ed.tdas.Entry;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidEntryException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidKeyException;

public class DiccionarioHashAbierto<K,V> implements Dictionary<K,V> {
    //atributos de instancia
    protected ListaDoblementeEnlazada<Entry<K,V>> [] arreglo;
    protected int N; //tamaño del arreglo
    protected int n; //cant elementos
    protected float factorDeCarga;
    //constructor
    public DiccionarioHashAbierto(){
        N=11; //nro primo
        n=0;
        factorDeCarga=n/N;
        arreglo=(ListaDoblementeEnlazada<Entry<K,V>>[]) new ListaDoblementeEnlazada[N];
        for(int i=0; i<N; i++){
            arreglo[i]=new ListaDoblementeEnlazada<Entry<K,V>>();
        }
    }
    public Entry<K,V> insert(K key, V value){
        if(key==null){
            throw new InvalidKeyException("La Clave no puede ser nula");
        }
        Entry<K,V> e=new Entrada<K,V>(key,value);
        int codigoHash=key.hashCode();
        int indice=Math.abs(codigoHash%N);
        arreglo[indice].addLast(e);
        n++;
        factorDeCarga=(float)n/N;
        if(factorDeCarga>0.9f){
            reHash();
        }
        return e;
    }
    public Entry<K,V> remove( Entry<K,V> e ){
        if(e==null){
            throw new InvalidEntryException("La entrada no puede ser nula");
        }
        int codigoHash=e.getKey().hashCode();
        int indice=Math.abs(codigoHash%N);
        ListaDoblementeEnlazada<Entry<K,V>> bucket=arreglo[indice];
        for(Position<Entry<K,V>> p : bucket.positions()){
            if(p.element()==e){
                bucket.remove(p);
                n--;
                return e;
            }
        }
        throw new InvalidEntryException("la entrada no pertenece al diccionario"); 
    }
    public int size(){
        return n;
    }
    public boolean isEmpty(){
        return n==0;
    }
    public Entry<K,V> find(K key){
        if(key==null){
            throw new InvalidKeyException("La clave no puede ser nula");
        }
        int indice=Math.abs(key.hashCode()%N);
        ListaDoblementeEnlazada<Entry<K,V>> bucket=arreglo[indice];
        for(Entry<K,V> e: bucket){
            if(e.getKey().equals(key)){
                return e;
            }
        }
        return null;
    }
    public Iterable<Entry<K,V>> findAll(K key){
    if (key == null) {
        throw new InvalidKeyException("La clave no puede ser nula");}
        ListaDoblementeEnlazada<Entry<K,V>> resultado= new ListaDoblementeEnlazada<>();
        int indice=Math.abs(key.hashCode()%N);
        ListaDoblementeEnlazada<Entry<K,V>> bucket=arreglo[indice];
        for(Entry<K,V> e: bucket){
            if(e.getKey().equals(key)){
                resultado.addLast(e);
            }
        }
        return resultado;
    }
    public Iterable<Entry<K,V>> entries(){
        ListaDoblementeEnlazada<Entry<K,V>> TodaslasEntradas= new ListaDoblementeEnlazada<>();
        for(int i=0; i<N;i++){
            for(Entry<K,V> e: arreglo[i]){
                TodaslasEntradas.addLast(e);
            }
        }
        return TodaslasEntradas;
    }
    Iterable<Entry<K,V>> eliminarTodas(K key,V value){
        if(key==null){
            throw new InvalidKeyException("la key es invalida");
        }
        int indice=Math.abs(key.hashCode()%N);
        ListaDoblementeEnlazada<Entry<K,V>> bucket=arreglo[indice];
        ListaDoblementeEnlazada<Entry<K,V>> listaEliminadas=new ListaDoblementeEnlazada<>();
        ListaDoblementeEnlazada<Position<Entry<K,V>>> posicionesABorrar = new ListaDoblementeEnlazada<>();
        for(Position<Entry<K,V>> p :bucket.positions() ){
            if(p.element().getKey().equals(key)&& p.element().getValue().equals(value)){
            listaEliminadas.addLast(p.element());
            posicionesABorrar.addLast(p);
            }
        }
        for(Position<Entry<K,V>> p: posicionesABorrar){
            bucket.remove(p);
            n--;
            factorDeCarga=(float)n/N;
        }
        return listaEliminadas;
    }
    private void reHash(){
        int tamañoviejo=N;
        ListaDoblementeEnlazada<Entry<K,V>> [] arregloviejo=arreglo;
        N=N*2;
        arreglo=(ListaDoblementeEnlazada<Entry<K,V>> [])new ListaDoblementeEnlazada[N];
        for(int i=0;i<N;i++){
            arreglo[i]=new ListaDoblementeEnlazada<>();
        }
        for(int i=0; i<tamañoviejo;i++){
            for(Entry<K,V> e:arregloviejo[i]){
                int indice=(e.getKey().hashCode()%N);
                arreglo[indice].addLast(e);
            }
        }

    }
}
