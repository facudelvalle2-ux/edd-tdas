package ar.edu.uns.cs.ed.tdas.ejercicios.tp1;

public class TesterConjunto {
    public static void main (String [] args){
        Conjunto<String> Conjunto1 = new ConjuntoArreglo<>(4);
        System.out.println("¿Esta vacío? " + Conjunto1.isEmpty() + " deberia imprimir true");
        System.out.println("Tamaño inicial: " + Conjunto1.size() + " deberia imprimir 0"); 
        Conjunto1.put("juan");
        Conjunto1.put("tomy");
        Conjunto1.put("leo");
        System.out.println("Tamaño despues de insertar es de:  " +  Conjunto1.size() + "  deberia imprimir 3");
        System.out.println("veo si leo pertenece al conjunto " + Conjunto1.pertenece("leo") + " deberia dar true ");
        System.out.println("veo si agus pertenece al conjunto " + Conjunto1.pertenece("agus") + " deberia dar false ");
        Conjunto<String> Conjunto2= new ConjuntoArreglo<>(3);
        Conjunto2.put("agus");
        Conjunto2.put("mica");
        Conjunto2.put("leo");
        Conjunto<String> ConjuntoInterseccion=Conjunto1.interseccion(Conjunto2);
        System.out.println("leo pertenece a la interseccion? " +  ConjuntoInterseccion.pertenece("leo")  + " deberia dar true ");
        System.out.println("tomy pertenece a la interseccion? " +  ConjuntoInterseccion .pertenece("tomy")  + " deberia dar false ");





    }
    
}
