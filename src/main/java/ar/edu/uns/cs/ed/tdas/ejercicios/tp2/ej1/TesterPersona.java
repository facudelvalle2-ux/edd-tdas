package ar.edu.uns.cs.ed.tdas.ejercicios.tp2.ej1;

public class TesterPersona {
    public static void main(String[] args) {
        Persona f=new Persona("facu", 20, 60);
        Persona l=new Persona("leo", 21, 80);
        Persona a=new Persona("agus", 22, 70);
        Persona [] miArreglo={f,l,a};
        for(int i=0; i<3;i++){
            System.out.println("la persona en la posicion " + i + " es:  " + miArreglo[i].toString());
        }
        Persona [] arregloInvertido=f.invertir(miArreglo);
        System.out.println("ahora en sentido inverso de la pila: ");

        for(int i=0; i<3;i++){
            System.out.println("la persona en la posicion " + i + " es: " + arregloInvertido[i].toString());
        }
    }
    
}
