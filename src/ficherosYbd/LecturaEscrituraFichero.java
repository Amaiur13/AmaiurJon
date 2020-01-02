package ficherosYbd;

import jugadores.Jugador;

import java.io.*;
import java.util.ArrayList;

/**
 * Clase que contiene metodos de lectura y escritura del fichero mercado
 */
public class LecturaEscrituraFichero
{
    /**
     * Metodo para leer el fichero mercado
     * @param arrayJugadores todos los jugadores en arraylist tipo jugador
     * @return devuelve arraylist de tipo jugador con todos los jugadores en venta en el mercado
     */
    public static ArrayList<Jugador> leerFicheroMercado(ArrayList<Jugador> arrayJugadores) {
        ArrayList<Jugador> arrayMercado = new ArrayList<>();
        File aFile = new File("./src/ficherosYbd/Mercado.txt");

        try {
            FileReader fr = new FileReader(aFile);
            BufferedReader br = new BufferedReader(fr);
            String linea = br.readLine();

            while (linea != null) {
                String[] dividir = linea.split(";");

                for (int i = 0; i < dividir.length - 1; i++) {
                    for (int j = 0; j < arrayJugadores.size(); j++) {
                        if (arrayJugadores.get(j).getNombre().equals(dividir[i])) {
                            arrayMercado.add(arrayJugadores.get(j));
                            break;
                        }
                    }
                }
                linea = br.readLine();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return arrayMercado;

    }

    /**
     * Metodo que lee la ultima variable del fichero mercado
     * @return boolean reset, true --> hay que actualizar mercado, false --> no actualizar
     */
    public static boolean reset() {
        Boolean reset = false;

        File aFile = new File("./src/ficherosYbd/Mercado.txt");

        try {
            FileReader fr = new FileReader(aFile);
            BufferedReader br = new BufferedReader(fr);
            String linea = br.readLine();

            while (linea != null)
            {
                String[] dividir = linea.split(";");
                if (dividir[19].equals("true")) {
                    reset = true;
                }

                linea = br.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reset;
    }


    /**
     * Escribe en el fichero mercado los nuevos jugadores aleatorios
     * @param arrayMercado arraylist tipo Jugador con los jugadores a escribir
     * @param nuevaJornada 1--> reset=true, 0--> reset=false
     */
    public static void escribirFichero(ArrayList<Jugador> arrayMercado, int nuevaJornada)
    {
        String path = "./src/ficherosYbd/Mercado.txt";
        File aFile=new File(path);

        try
        {
            FileWriter fw = new FileWriter(path, false);
            BufferedWriter bw = new BufferedWriter(fw);

                for (int i=0; i<arrayMercado.size(); i++)
                {
                    bw.write(arrayMercado.get(i).getNombre()+";");
                }
                int resta = 19-arrayMercado.size();
                for(int j=0; j<resta;j++)
                {
                    bw.write(";");
                }
                if(nuevaJornada==0) {
                    bw.write("false");
                }
                else
                {
                    bw.write("true");
                }

            bw.flush();
            fw.flush();
            fw.close();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
