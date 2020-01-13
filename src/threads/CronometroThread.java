package threads;

import basesDeDatos.SelectData;
import ficheros.LecturaEscrituraFichero;
import jugadoresPujaAlineacion.Jugador;
import jugadoresPujaAlineacion.Puja;
import menus.MenuAdministrador;
import usuariosAdmins.UsuariosYadmins;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Clase que contiene la logica del thread de las subastas, hace que el temporizador decrezca segundo a segundo
 */
public class CronometroThread extends Thread
{
    JLabel eti;
    VentanaCronometro ventCron;
    Puja puja;

    /**
     * Constructor de la clase
     * @param cronometro el label que contiene el temporizador
     * @param ventanaCron la ventana del temporizador
     * @param puja la puja la cual se esta subastando
     */
    public CronometroThread (JLabel cronometro, VentanaCronometro ventanaCron, Puja puja)
    {
        this.eti = cronometro;
        this.ventCron = ventanaCron;
        this.puja = puja;
    }

    /**
     * Metodo run que ejecuta el hilo
     */
    public void run ()
    {
        try
        {

            while ((ventCron.hora !=0) || (ventCron.minuto !=0) || (ventCron.segundo !=0))
            {
                Thread.sleep(300);
                ejecutarHiloCronometro();
            }



            JOptionPane.showMessageDialog(null, "Se ha acabado el tiempo de subasta.");
            ventCron.dispose();

            ArrayList<UsuariosYadmins> arrayUsuarios = SelectData.selectAllUsers();
            ArrayList<Jugador> arrayJugadores = SelectData.selectAllPlayers(arrayUsuarios);
            ArrayList<Jugador> arrayMarket = LecturaEscrituraFichero.leerFicheroMercado(arrayJugadores);

            MenuAdministrador.compararPujasAutomaticas(arrayMarket, arrayUsuarios, arrayJugadores, puja);
        }

        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Excepcion en el hilo " + e.getMessage());
        }
    }

    /**
     * Metodo que se encarga de que el temporizador funcione, que baje un segundo cada segundo
     */
    private  void ejecutarHiloCronometro()
    {

        if (ventCron.segundo == 0)
        {
            ventCron.segundo = 59;
            ventCron.minuto--;
        }

        else
        {
            ventCron.segundo--;
        }



        String txtHora = "", txtMin = "", txtSeg = "";

        if (ventCron.segundo<10)
        {
            txtSeg = "0" + ventCron.segundo;
        }
        else
        {
            txtSeg = ""+ ventCron.segundo;
        }

        if (ventCron.minuto<10)
        {
            txtMin = "0" + ventCron.minuto;
        }
        else
        {
            txtMin = ""+ ventCron.minuto;
        }

        if (ventCron.hora<10)
        {
            txtHora = "0" + ventCron.hora;
        }
        else
        {
            txtHora = ""+ ventCron.hora;
        }

        String reloj = txtHora + ":" + txtMin + ":" + txtSeg;
        eti.setText(reloj);
    }

}
