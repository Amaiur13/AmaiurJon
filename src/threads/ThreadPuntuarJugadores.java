package threads;

import basesDeDatos.GestorBD;
import basesDeDatos.SelectData;
import jugadoresPujaAlineacion.Alineacion;
import jugadoresPujaAlineacion.Jugador;
import usuariosAdmins.Usuario;
import usuariosAdmins.UsuariosYadmins;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ThreadPuntuarJugadores implements Runnable
{
    ArrayList <UsuariosYadmins> arrayUsuarios;
    ArrayList <Jugador> arrayJugadores;
    JLabel clasificacion;

    public ThreadPuntuarJugadores (ArrayList <UsuariosYadmins> arrayUsuarios, ArrayList <Jugador> arrayJugadores)
    {
        this.arrayJugadores = arrayJugadores;
        this.arrayUsuarios = arrayUsuarios;
    }
    @Override
    public void run()
    {
        puntuarJugadores();
        Stream <Usuario> strUser = ordenarUsuariosPorPuntuacion();
        List <Usuario> listUser = strUser.collect(Collectors.toList());

        String label = "";

        for (int i=0; i<listUser.size(); i++)
        {
            label+=listUser.get(i).getUser() + "  " + listUser.get(i).getPuntos() + " PUNTOS\n       ";
        }


        JFrame frame = new JFrame("CLASIFICACION");
        frame.setSize(500,150);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        clasificacion = new JLabel();
        clasificacion.setText(label);
        clasificacion.setFont((new Font("Traditional Arabic", Font.BOLD, 15)));
        clasificacion.setBounds(25,1,475,140);
        frame.getContentPane().add(clasificacion);

        frame.setVisible(true);
    }

    private void puntuarJugadores ()
    {
        arrayUsuarios = SelectData.selectAllUsers();
        ArrayList<Alineacion> arrayAlineaciones = SelectData.selectAllAlineaciones(arrayUsuarios, arrayJugadores);

        GestorBD gestor = new GestorBD("Comunio.db");
        gestor.createLink();
        int puntosJugador = 0;
        int puntosTotalesJornada = 0;


        for (int i=0; i<arrayAlineaciones.size(); i++)
        {
            puntosJugador = SelectData.jugadorYsusPuntosTotales("A.portero", arrayAlineaciones.get(i).getEntrenador().getUser());
            puntosTotalesJornada += puntosJugador;
            puntosJugador = SelectData.jugadorYsusPuntosTotales("A.defensa1", arrayAlineaciones.get(i).getEntrenador().getUser());
            puntosTotalesJornada += puntosJugador;
            puntosJugador = SelectData.jugadorYsusPuntosTotales("A.defensa2", arrayAlineaciones.get(i).getEntrenador().getUser());
            puntosTotalesJornada += puntosJugador;
            puntosJugador = SelectData.jugadorYsusPuntosTotales("A.defensa3", arrayAlineaciones.get(i).getEntrenador().getUser());
            puntosTotalesJornada += puntosJugador;
            puntosJugador = SelectData.jugadorYsusPuntosTotales("A.defensa4", arrayAlineaciones.get(i).getEntrenador().getUser());
            puntosTotalesJornada += puntosJugador;
            puntosJugador = SelectData.jugadorYsusPuntosTotales("A.defensa5", arrayAlineaciones.get(i).getEntrenador().getUser());
            puntosTotalesJornada += puntosJugador;
            puntosJugador = SelectData.jugadorYsusPuntosTotales("A.mediocentro1", arrayAlineaciones.get(i).getEntrenador().getUser());
            puntosTotalesJornada += puntosJugador;
            puntosJugador = SelectData.jugadorYsusPuntosTotales("A.mediocentro2", arrayAlineaciones.get(i).getEntrenador().getUser());
            puntosTotalesJornada += puntosJugador;
            puntosJugador = SelectData.jugadorYsusPuntosTotales("A.mediocentro3", arrayAlineaciones.get(i).getEntrenador().getUser());
            puntosTotalesJornada += puntosJugador;
            puntosJugador = SelectData.jugadorYsusPuntosTotales("A.mediocentro4", arrayAlineaciones.get(i).getEntrenador().getUser());
            puntosTotalesJornada += puntosJugador;
            puntosJugador = SelectData.jugadorYsusPuntosTotales("A.mediocentro5", arrayAlineaciones.get(i).getEntrenador().getUser());
            puntosTotalesJornada += puntosJugador;
            puntosJugador = SelectData.jugadorYsusPuntosTotales("A.delantero1", arrayAlineaciones.get(i).getEntrenador().getUser());
            puntosTotalesJornada += puntosJugador;
            puntosJugador = SelectData.jugadorYsusPuntosTotales("A.delantero2", arrayAlineaciones.get(i).getEntrenador().getUser());
            puntosTotalesJornada += puntosJugador;
            puntosJugador = SelectData.jugadorYsusPuntosTotales("A.delantero3", arrayAlineaciones.get(i).getEntrenador().getUser());
            puntosTotalesJornada += puntosJugador;


            int puntosTotalesUsuario = arrayAlineaciones.get(i).getEntrenador().getPuntos() + puntosTotalesJornada;
            gestor.updatePuntuacionTotalUsers(puntosTotalesUsuario, arrayAlineaciones.get(i).getEntrenador().getUser());
        }

        for (Jugador a: arrayJugadores)
        {
            gestor.updateEstadisticasa0trasJornada(a.getNombre());
        }
        gestor.closeLink();
    }

    private Stream<Usuario> ordenarUsuariosPorPuntuacion ()
    {
        this.arrayUsuarios = SelectData.selectAllUsers();

        Stream<Usuario> listUsuarios = arrayUsuarios.stream().filter(a -> (a instanceof Usuario) && !(a.getUser().equals("comunio"))).map(a -> (Usuario)a);
        Stream<Usuario> streamUsuariosOrdendos = listUsuarios.sorted(Comparator.comparingInt(Usuario::getPuntos).reversed());

        return streamUsuariosOrdendos;
    }
}
