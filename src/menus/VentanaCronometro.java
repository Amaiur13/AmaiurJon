package menus;

import javax.swing.*;
import java.awt.*;

public class VentanaCronometro extends JFrame
{
    private JLabel label;
    static int hora=0, minuto=2, segundo=0;

    public VentanaCronometro ()
    {
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
        label.setBounds(47,11,137,45);
        getContentPane().add(label);


        iniciarHiloCronometro();
    }

    public void iniciarHiloCronometro()
    {
        CronometroThread hilo = new CronometroThread(label);
        hilo.start();
    }

}
