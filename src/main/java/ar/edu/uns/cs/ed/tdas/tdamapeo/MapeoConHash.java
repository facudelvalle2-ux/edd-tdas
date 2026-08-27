package ar.edu.uns.cs.ed.tdas.tdamapeo;
import ar.edu.uns.cs.ed.tdas.Entry;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidKeyException;
import ar.edu.uns.cs.ed.tdas.tdadiccionario.Entrada;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdalista.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

public class MapeoConHash<K,V> implements Map<K,V> {
    protected int n;//cant elementos
    protected int N;//tamaño arreglo
    protected float factorDeCarga;
    protected ListaDoblementeEnlazada<Entry<K,V>> [] arreglo;
    public MapeoConHash(){
        N=11; //nro primo
        n=0;
        factorDeCarga=(float)n/N;
        arreglo=(ListaDoblementeEnlazada<Entry<K,V>>[]) new ListaDoblementeEnlazada[N];
        for(int i=0; i<N; i++){
            arreglo[i]=new ListaDoblementeEnlazada<Entry<K,V>>();
        }
    }
    public int size(){
        return n;
    }
    public boolean isEmpty(){
        return n==0;
    }
    public Iterable<Entry<K,V>> entries(){
        ListaDoblementeEnlazada<Entry<K,V>> TodaslasEntradas= new ListaDoblementeEnlazada<>();

        for(int i=0; i<N;i++){                      //N
            for(Entry<K,V> e: arreglo[i]){          //xi pasos, donde i es bucket actual, entonces xi es la cantidad de elementos en la lista del bucket actual 
                TodaslasEntradas.addLast(e);        //c2
            }
        }

        return TodaslasEntradas;
    }
    public V get(K key){
        if(key==null){
            throw new InvalidKeyException("la key es invalida");
        }
        int indice=Math.abs(key.hashCode()%N);
        ListaDoblementeEnlazada<Entry<K,V>> bucket=arreglo[indice];
        for(Entry<K,V> e:bucket){
            if(e.getKey().equals(key)){
                return e.getValue();
            }
        }
        return  null;
    }
    public V put(K key, V value){
        if(key==null){
            throw new InvalidKeyException("la key es invalida");
        }
        int indice=Math.abs(key.hashCode()%N);
        ListaDoblementeEnlazada<Entry<K,V>> bucket=arreglo[indice];
        V valorviejo=null;
        for(Position<Entry<K,V>> p: bucket.positions()){
            if(p.element().getKey().equals(key)){
                valorviejo=p.element().getValue();
                bucket.set(p,new Entrada<K,V>(key,value));
                return valorviejo;
            }
        }
        bucket.addLast(new Entrada<K,V>(key, value));
        n++;
        factorDeCarga=(float)n/N;
        if(factorDeCarga>0.9f){
            rehash();
        }
        return null;
    }
    public V remove(K key){
        if(key==null){
            throw new InvalidKeyException("la key es invalida");
        }
        int indice=Math.abs(key.hashCode()%N);
        ListaDoblementeEnlazada<Entry<K,V>> bucket=arreglo[indice];
        V valorBorrado=null;
        for(Position<Entry<K,V>> p: bucket.positions()){
            if(p.element().getKey().equals(key)){
                valorBorrado=p.element().getValue();
                bucket.remove(p);
                n--;
                return valorBorrado;
            }
        }
        return null;
    }
    public Iterable<K> keys(){
        ListaDoblementeEnlazada<K> listakeys=new ListaDoblementeEnlazada<K>();
        for(int i=0;i<N;i++){
            for(Entry<K,V> e:arreglo[i]){
                listakeys.addLast(e.getKey());
            }
        }
        return listakeys;
    }
    public Iterable<V> values(){
        ListaDoblementeEnlazada<V> listavalores=new ListaDoblementeEnlazada<V>();
        for(int i=0;i<N;i++){
            for(Entry<K,V> e: arreglo[i]){
                listavalores.addLast(e.getValue());
            }
        }
        return listavalores;
    }
    private void rehash(){
        ListaDoblementeEnlazada<Entry<K,V>> [] arregloviejo=arreglo;
        int tamañoviejo=N;
        N=N*2;
        arreglo=(ListaDoblementeEnlazada<Entry<K,V>> []) new ListaDoblementeEnlazada[N];
        for(int i=0;i<N;i++){
            arreglo[i]=new ListaDoblementeEnlazada<>();
        }
        for(int i=0; i<tamañoviejo;i++){
            for(Entry<K,V> e: arregloviejo[i]){
                int indice=Math.abs(e.getKey().hashCode()%N);
                arreglo[indice].addLast(e);
            }
        }
    }


    }
    

