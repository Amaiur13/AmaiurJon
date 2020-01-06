package menus;

import jugadores.Puja;

import javax.swing.*;
import java.awt.*;

public class VentanaCronometro extends JFrame
{
    private JLabel label;
    static int hora=0, minuto=2, segundo=0;
    Puja pujaRealizada;
    private JLabel infoJugador;

    public VentanaCronometro (Puja puja)
    {
        this.pujaRealizada = puja;
        inicializar();
    }

    private void inicializar()
    {
        setSize(228,120);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        label = new JLabel("00:00:00");
        label.setFont((new Font("Traditional Arabic", Font.PLAIN, 30)));
        label.setBounds(47,18,137,45);
        getContentPane().add(label);

        String info = pujaRealizada.getJugador().getNombre();
        infoJugador = new JLabel(info);
        infoJugador.setFont((new Font("Traditional Arabic", Font.PLAIN, 15)));
        infoJugador.setBackground(Color.blue);
        infoJugador.setBounds(1,1, 130, 45);
        getContentPane().add(infoJugador);


        iniciarHiloCronometro();
    }

    public void iniciarHiloCronometro()
    {
        CronometroThread hilo = new CronometroThread(label);
        hilo.start();
    }

}
