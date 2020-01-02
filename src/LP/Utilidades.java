package LP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/** Esta clase define todas las utilidades de lectura de la aplicacion
 *
 */
public class Utilidades {

	private static Random generadorDeEnteros = new Random(System.currentTimeMillis());

	/** Constructor vacio de la clase Utilidades
	 *
	 */
	public Utilidades(){
		
	}

	/** Permite introducir por teclado en la consola un caracter realizando
	 las comprobaciones pertienentes.
	 *
	 * @return Devuelve el caracter introducio por el teclado
	 */
	public static char leerCadena(){
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		Integer entero = null;
		boolean err = true;
		String cadena="";
		do
		{
			try {
				cadena = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			err=false;
			
		}while(err);
			return cadena.toCharArray()[0];
	}

	/** Permite introducir por teclado en la consola una cadena de caracteres realizando
	 las comprobaciones pertienentes.
	 *
	 * @return Devuelve el String introducido por el teclado
	 */
	public static String leerTexto(){
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		Integer entero = null;
		boolean err = true;
		String cadena="";
		do
		{
			try {
				cadena = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			err=false;
			
		}while(err);
			return cadena;
	}

	/** Permite introducir por teclado en la consola un número entero realizando
	 las comprobaciones pertienentes.
	 *
	 * @return El numero entero introducido por teclado
	 */
	public static int leerEntero()
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		Integer entero = null;
		boolean error = true;
		do
		{
			try
			{
				String cadena = br.readLine();
				entero = new Integer(cadena);
				error = false;
			}
			catch(NumberFormatException nfe)
			{
				System.out.println("No tecleó un número entero-Repetir");
			}
			catch (Exception e)
			{
				System.out.println("Error de entrada-Repetir ");
			}
		}
		while(error);
		return entero.intValue();
	}

	/** Permite introducir por teclado en la consola un número real realizando
	 las comprobaciones pertienentes.
	 @return Devuelve el número real que se haya introducido por teclado.
	 */
	public static double leerReal()
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		Double real = null;
		boolean error = true;
		do
		{
			try
			{
				String cadena = br.readLine();
				real = new Double(cadena);
				error = false;
			}
			catch(NumberFormatException nfe)
			{
				System.out.println("No tecleó un número real-Repetir ");
			}
			catch (Exception e)
			{
				System.out.println("Error de entrada-Repetir ");
			}
		}
		while(error);
		return real.doubleValue();
	}
}
