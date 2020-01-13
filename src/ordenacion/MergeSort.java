package ordenacion;

import java.util.ArrayList;

import usuariosAdmins.Usuario;

/**
 * Esta clase contiene el MergeSort para ordenar los usuarios por valor o puntos
 */
public class MergeSort 
{
	/**
	 * Esta clase implementa el diseño recursivo del mergesort
	 * @param arrayUsuarios arraylist con los usuarios
	 * @param izq indice del primer elemento
	 * @param der indice del ultimo elemento
	 * @param aux variabe auxiliar para decidir si ordena por punto o valor de los usuarios
	 */
	public static void mergesort(ArrayList <Usuario> arrayUsuarios,int izq, int der, int aux)
	{
	    if (izq<der)
	    {
            int m=(izq+der)/2;
            mergesort(arrayUsuarios, izq, m,aux);
            mergesort(arrayUsuarios, m+1, der,aux);
            if(aux ==1) {
				merge(arrayUsuarios, izq, m, der);
			}
            else {
            	mergeValor(arrayUsuarios, izq, m, der);
			}
	    }
	}

	/**
	 * Materializa el cambio de posiciones para que quede ordenado por puntos
	 * @param arrayUsuarios arraylist de los usuarios a ordenar por puntos
	 * @param izq indice del primer elemento
	 * @param m indice medio
	 * @param der indice del ultimo elemento
	 */
	public static void merge(ArrayList <Usuario> arrayUsuarios, int izq, int m, int der)
	{
	   int i, j, k;
	   Usuario [] B = new Usuario [arrayUsuarios.size()]; //array auxiliar
	   
	   for (i=izq; i<=der; i++)     //copia ambas mitades en el array auxiliar
	   {
		   B[i]=arrayUsuarios.get(i);
	   }
       
	   i=izq; j=m+1; k=izq;
             
	   while (i<=m && j<=der)       //copia el siguiente elemento m�s grande
	   {
		   if (B[i].getPuntos() > B[j].getPuntos())
		   {
			   arrayUsuarios.set(k++, B[i++]);
		   }
		   
		   else
		   {
			   arrayUsuarios.set(k++, B[j++]);
		   }
	   }
		 
	   while (i<=m)                //copia los elementos que quedan de laprimera mitad (si los hay)
	   {
		   arrayUsuarios.set(k++, B[i++]);
	   }
	}

	/**
	 * Materializa el cambio de posiciones para que quede ordenado por valor
	 * @param arrayUsuarios arraylist de los usuarios a ordenar por valor
	 * @param izq indice del primer elemento
	 * @param m indice medio
	 * @param der indice del ultimo elemento
	 */
	public static void mergeValor(ArrayList <Usuario> arrayUsuarios, int izq, int m, int der)
	{
		int i, j, k;
		Usuario [] B = new Usuario [arrayUsuarios.size()]; //array auxiliar

		for (i=izq; i<=der; i++)     //copia ambas mitades en el array auxiliar
		{
			B[i]=arrayUsuarios.get(i);
		}

		i=izq; j=m+1; k=izq;

		while (i<=m && j<=der)       //copia el siguiente elemento m�s grande
		{
			if (B[i].getValorEquipo() > B[j].getValorEquipo())
			{
				arrayUsuarios.set(k++, B[i++]);
			}

			else
			{
				arrayUsuarios.set(k++, B[j++]);
			}
		}

		while (i<=m)                //copia los elementos que quedan de laprimera mitad (si los hay)
		{
			arrayUsuarios.set(k++, B[i++]);
		}
	}

}
