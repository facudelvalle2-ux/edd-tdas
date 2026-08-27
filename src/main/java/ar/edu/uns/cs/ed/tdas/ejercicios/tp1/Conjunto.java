package ar.edu.uns.cs.ed.tdas.ejercicios.tp1;

public interface Conjunto<E> {
    public int size();
    public int capacity();
    public boolean isEmpty();
    public E get(int i);
    public void put(E element);
    public boolean pertenece(E elem);
    public Conjunto<E> interseccion(Conjunto<E> c);

    

}
