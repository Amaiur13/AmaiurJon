package ordenacion;

import java.util.ArrayList;

import usuariosAdmins.Usuario;

public class MergeSort 
{
	
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
