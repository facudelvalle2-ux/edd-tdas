package ar.edu.uns.cs.ed.tdas.ejercicios.tp1;

public class ConjuntoArreglo<E> implements Conjunto<E> {
    //atributos de instancia
    private E[] elementos;
    private int cant;
    //constructor 
    public ConjuntoArreglo(int cantidad){
        elementos=(E[])new Object[cantidad];
        cant=0;
    }
    //servicios
    public int size(){
        return cant;}
    public int capacity(){
        return elementos.length;
    }
    public boolean isEmpty(){
        return cant==0;
    }
    public E get(int i){
        return elementos[i];
    }
    public void put(E elem){
        elementos[cant]=elem;
        cant++;
    }
    public boolean pertenece(E elem){
        boolean pertenece=false;
        for(int i=0;i<cant && !pertenece;i++){
            if(elementos[i].equals(elem))
                pertenece=true;
        }
        return pertenece;
    }
    public Conjunto<E> interseccion(Conjunto<E> c){
        Conjunto<E> resultado= new ConjuntoArreglo<>(capacity());
        for(int i=0; i<cant;i++){
            E elemActual=elementos[i];
            if(c.pertenece(elemActual))
                resultado.put(elemActual);
        }
        return resultado;
    }
    public boolean perteneceR(E elem){
        //solucion recursiva
        return perteneceRec(elem,cant);
    }
    private boolean perteneceRec(E elem, int cant){
        if(cant==0)
            return false;
        else
            return(elem.equals(elementos[cant-1]) || perteneceRec(elem, cant-1));
    }




    
}
