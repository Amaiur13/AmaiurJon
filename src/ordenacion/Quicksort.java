package ordenacion;

import java.util.ArrayList;

import jugadoresPujaAlineacion.Jugador;

public class Quicksort 
{
	public static void quicksort(ArrayList <Jugador> arrayJugadores, int izq, int der) 
	{
		  int pivote=arrayJugadores.get(izq).getValor(); // tomamos primer elemento como pivote
		  int i=izq; // i realiza la b�squeda de izquierda a derecha
		  int j=der; // j realiza la b�squeda de derecha a izquierda
		  Jugador aux;
		 
		  while(i<j)
		  {            // mientras no se crucen las b�squedas
		     while(arrayJugadores.get(i).getValor() <= pivote && i<j)
		     {
		    	 i++;// busca elemento mayor que pivote y guarda su indice en i  A[i] seria.
		     }
		     
		     while(arrayJugadores.get(j).getValor() > pivote)
		     {
		    	 j--;         // busca elemento menor que pivote y guarda su indice en j A[j] seria.
		     }
		     
		     if (i<j) 
		     {                      // si no se han cruzado     si el elemento mayor (i) estuviese mas a la izquierda, lo intercambiamos con el elemento de j.                 
		         aux= arrayJugadores.get(i);                  // los intercambia
		         arrayJugadores.set(i, arrayJugadores.get(j));  //A[i]=A[j]
		         arrayJugadores.set(j, aux);
		     }
		   }
		   Jugador aux1 = arrayJugadores.get(izq);
		   arrayJugadores.set(izq, arrayJugadores.get(j));    // A[izq]=A[j]  // se coloca el pivote en su lugar de forma que tendremos
		   arrayJugadores.set(j, aux1);   //A[j]=pivote;   // los menores a su izquierda y los mayores a su derecha
		   
		   if(izq<j-1)
		      quicksort(arrayJugadores,izq,j-1); // ordenamos subarray izquierdo desde 0 hasta el anterior del pivote
		   
		   if(j+1 <der)
		      quicksort(arrayJugadores,j+1,der); // ordenamos subarray derecho desde el siguiente al pivote hasta el ultimo
		   
		}
}
