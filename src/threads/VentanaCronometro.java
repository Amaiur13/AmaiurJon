package threads;

import jugadoresPujaAlineacion.Puja;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que contiene la ventana o el frame del temporizador de las subastas
 */
public class VentanaCronometro extends JFrame
{
    private JLabel label;
    int hora=0, minuto=2, segundo=0;
    Puja pujaRealizada;
    private JLabel infoJugador;
    static int contadorHilos = 0;

    /**
     * Constructor de la clase
     * @param puja
     */
    public VentanaCronometro (Puja puja)
    {
        this.pujaRealizada = puja;
        inicializar();
    }

    /**
     * Inicializa compenentes del frame
     */
    private void inicializar()
    {
        setTitle("Subasta " + pujaRealizada.getJugador().getNombre());
        setSize(328,120);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        label = new JLabel("00:00:00");
        label.setFont((new Font("Traditional Arabic", Font.PLAIN, 30)));
        label.setBounds(57,22,137,45);
        getContentPane().add(label);

        String info = pujaRealizada.getJugador().getNombre();
        infoJugador = new JLabel(info);
        infoJugador.setFont((new Font("Traditional Arabic", Font.BOLD, 15)));
        infoJugador.setForeground(Color.blue);
        infoJugador.setBounds(10,1, 130, 45);
        getContentPane().add(infoJugador);


        iniciarHiloCronometro();
    }

    /**
     * Inicia el hilo de la subasta
     */
    public void iniciarHiloCronometro()
    {
        /*CronometroThread [] arrayHilos = new CronometroThread[10];

        arrayHilos[contadorHilos] = new CronometroThread(label, VentanaCronometro.this, pujaRealizada);
        arrayHilos[contadorHilos].start();
        contadorHilos++;*/


       CronometroThread hilo = new CronometroThread(label, VentanaCronometro.this, pujaRealizada);
       hilo.start();
    }

}
