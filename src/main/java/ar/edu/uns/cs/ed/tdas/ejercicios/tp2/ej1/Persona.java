package ar.edu.uns.cs.ed.tdas.ejercicios.tp2.ej1;
import java.util.Stack;


public class Persona {
    //atributos de instancia
    protected String nombre;
    protected int edad;
    protected int peso;
    //constructor
    public Persona(String nom, int edad, int peso){
        nombre=nom;
        this.edad=edad;
        this.peso=peso;
    }
    //getters
    public String getNombre(){
        return nombre;
    }
    public int getDni(){
        return edad;
    }
    public int getPeso(){
        return peso;
    }
    //setters
    public void setNombre(String nom){
        nombre=nom;
    }
    public void setDni(int edad){
        this.edad=edad;
    }
    public void setPeso(int peso){
        this.peso=peso;
    }
    public Persona []  invertir(Persona [] A){
        Persona [] a=new Persona[A.length];
        Stack<Persona> pila= new Stack<>();
        for(int i=0; i<A.length; i++){
            pila.push(A[i]);
        }
        for(int k=0;k<A.length;k++){
            a[k]=pila.pop();
        }
        return a;
    }
    public String toString() {
        return nombre + " edad: " + edad  +  " años  " + " peso: " + peso;
    }

    
}
