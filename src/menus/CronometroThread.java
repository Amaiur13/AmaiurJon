package menus;

import basesDeDatos.SelectData;
import ficherosYbd.LecturaEscrituraFichero;
import jugadores.Jugador;
import usuariosAdmins.UsuariosYadmins;

import javax.swing.*;
import java.util.ArrayList;

public class CronometroThread extends Thread
{
    JLabel eti;

    public CronometroThread (JLabel cronometro)
    {
        this.eti = cronometro;
    }

    public void run ()
    {
        try
        {

            while ((VentanaCronometro.hora !=0) || (VentanaCronometro.minuto !=0) || (VentanaCronometro.segundo !=0))
            {
                Thread.sleep(500);
                ejecutarHiloCronometro();
            }

            JOptionPane.showMessageDialog(null, "Se ha acabado el tiempo de subasta.");

            ArrayList<UsuariosYadmins> arrayUsuarios = SelectData.selectAllUsers();
            ArrayList<Jugador> arrayJugadores = SelectData.selectAllPlayers(arrayUsuarios);
            ArrayList<Jugador> arrayMarket = LecturaEscrituraFichero.leerFicheroMercado(arrayJugadores);

            MenuAdministrador.compararPujas(arrayMarket, arrayUsuarios, arrayJugadores);
        }

        catch (Exception e)
        {
            System.out.println("Excepcion en el hilo " + e.getMessage());
        }
    }

    private void ejecutarHiloCronometro()
    {

        if (VentanaCronometro.segundo == 0)
        {
            VentanaCronometro.segundo = 59;
            VentanaCronometro.minuto--;
        }

        else
        {
            VentanaCronometro.segundo--;
        }



        String txtHora = "", txtMin = "", txtSeg = "";

        if (VentanaCronometro.segundo<10)
        {
            txtSeg = "0" + VentanaCronometro.segundo;
        }
        else
        {
            txtSeg = ""+ VentanaCronometro.segundo;
        }

        if (VentanaCronometro.minuto<10)
        {
            txtMin = "0" + VentanaCronometro.minuto;
        }
        else
        {
            txtMin = ""+ VentanaCronometro.minuto;
        }

        if (VentanaCronometro.hora<10)
        {
            txtHora = "0" + VentanaCronometro.hora;
        }
        else
        {
            txtHora = ""+ VentanaCronometro.hora;
        }

        String reloj = txtHora + ":" + txtMin + ":" + txtSeg;
        eti.setText(reloj);
    }

}
