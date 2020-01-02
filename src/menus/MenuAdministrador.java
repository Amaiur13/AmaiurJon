package menus;

import LP.Utilidades;
import basesDeDatos.GestorBD;
import basesDeDatos.SelectData;
import ficherosYbd.LecturaEscrituraFichero;
import jugadores.*;
import main.Login;
import usuariosAdmins.Usuario;
import usuariosAdmins.UsuariosYadmins;

import java.util.ArrayList;

/** Esta clase define el menu del administrador
 *
 */
public class MenuAdministrador
{
    /** Este metodo define el menu del administrador
     *
     * @param arrayUsuarios ArrayList donde se encuentran los datos de todos los usuarios
     * @param arrayJugadores ArrayList donde se encuentran todos los jugadores de la aplicacion, con sus respectivos datos
     * @param arrayMarket ArrayList donde se encuentran aquellos jugadores que estan en el mercado
     */
    public  static void menuAdministrador (ArrayList<UsuariosYadmins> arrayUsuarios, ArrayList <Jugador> arrayJugadores, ArrayList <Jugador> arrayMarket)
    {
        System.out.println("Bienvenido a Comunio, ¿Qué desea?\n" +
                "1.- Resetear mercado y comparar pujas\n" +
                "2.- Puntuar jugadores\n" +
                "3.- Establecer puntuacion de los usuarios tras puntuar jornada\n" +
                "4.- Cerrar sesión\n");

        System.out.println("Seleccione la opcion deseada:");

        int a= Utilidades.leerEntero();

        switch (a)
        {
            case 1: compararPujas(arrayMarket, arrayUsuarios, arrayJugadores);
                break;
            case 2: puntuarJugadores (arrayJugadores, arrayUsuarios, arrayMarket);
                break;
            case 3: puntuarUsuariosTrasJornada (arrayJugadores, arrayUsuarios, arrayMarket);
                break;
            case 4: Login.logIn();
                break;
            default: System.out.println("Seleccione una opcion entre 1 y 4 por favor");
                break;
        }
    }

    /** Este metodo compara las pujas hechas a cada jugador del mercado, y vende el jugador al usuario que mas haya ofrecido. Tambien se ocupa de resetear el mercado.
     *
     * @param arrayMarket ArrayList donde se encuentran aquellos jugadores que estan en el mercado
     * @param arrayUsuarios ArrayList donde se encuentran los datos de todos los usuarios
     * @param arrayJugadores ArrayList donde se encuentran todos los jugadores de la aplicacion, con sus respectivos datos
     */
    public static void compararPujas (ArrayList <Jugador> arrayMarket, ArrayList <UsuariosYadmins> arrayUsuarios, ArrayList <Jugador> arrayJugadores)
    {
        boolean reset=false;
        GestorBD gestor = new GestorBD("Comunio.db");
        for (int i=0; i<arrayMarket.size();i++)
        {
            Puja ganador = SelectData.selectPujaGanadora(arrayUsuarios,arrayMarket,arrayMarket.get(i).getNombre());
            if (!(ganador == null)) {
                System.out.println(ganador.getJugador().getNombre() + " puja: " + ganador.getPuja() + " pujador: " + ganador.getPujador().getUser());
                gestor.createLink();
                //asigna jugador al que mas ha pujado y pone en venta a 0 (pasa a no estar en venta)
                gestor.updateDataPuja(ganador.getJugador().getNombre(), 0, ganador.getPujador().getUser());
                int dineroRestante = (int) (ganador.getPujador().getDinero() - ganador.getPuja());
                float nuevoValor = ganador.getPujador().getValorEquipo() + ganador.getJugador().getValor();
                gestor.updateDataEnVenta(ganador.getPujador().getUser(), dineroRestante, nuevoValor,0);
                gestor.closeLink();
            }
            else
            {
                continue;
            }
        }
        gestor.createLink();
        gestor.resetearPujas();
        LecturaEscrituraFichero.escribirFichero(arrayMarket,1 );
        gestor.crearPujas();
        gestor.closeLink();

        System.out.println("Toca cualquier boton y el enter para volver al menu administrador: ");
        String x = Utilidades.leerTexto();
        menuAdministrador(arrayUsuarios, arrayJugadores, arrayMarket);
    }

    /** Este metodo define la puntuacion de cada jugador despues del partido de la jornada
     *
     * @param arrayJugadores ArrayList donde se encuentran todos los jugadores de la aplicacion, con sus respectivos datos
     * @param arrayUsuarios ArrayList donde se encuentran los datos de todos los usuarios
     * @param arrayMarket ArrayList donde se encuentran aquellos jugadores que estan en el mercado
     */
    public static void puntuarJugadores (ArrayList <Jugador> arrayJugadores, ArrayList <UsuariosYadmins> arrayUsuarios, ArrayList <Jugador> arrayMarket)
    {
        GestorBD gestor = new GestorBD("Comunio.db");
        gestor.createLink();
        System.out.println("Estos son todos los equipos para los que juegan los jugadores de los entrenadores: \n");
        ArrayList <String> arrayTeams = SelectData.selectDistinctTeams();

        for (String a: arrayTeams)
        {
            System.out.println("- " + a);
        }

        System.out.println("\nEscoge uno para puntuar los jugadores con dueño de ese equipo: ");
        String nombre = Utilidades.leerTexto();

        boolean aux = false;
        for (String a: arrayTeams)
        {
            if (a.equals(nombre))
            {
                aux = true;
                for (Jugador b: arrayJugadores)
                {
                    if (b.getTeam().equals(nombre))
                    {
                        if (b instanceof Portero)
                        {
                            System.out.println("Puntuaras al portero: " + b.getNombre() + "\n\t-Cuantos penaltis ha parado?: ");
                            int penaltisParados = Utilidades.leerEntero();
                            System.out.println("\t-Cuantas paradas ha hecho?: ");
                            int paradas = Utilidades.leerEntero();
                            ArrayList <Integer> arrayAnswers = preguntasRespuestasPuntuaciones();
                            boolean expulsado;
                            if (arrayAnswers.get(2) == 1)
                            {
                                expulsado = true;
                            }
                            else
                            {
                                expulsado = false;
                            }
                            b.setNumPenaltisParados(penaltisParados);
                            b.setNumParadas(paradas);
                            b.setNumGoles(arrayAnswers.get(0));
                            b.setMinutos(arrayAnswers.get(1));
                            b.setExpulsado(expulsado);
                            b.setNumAssist(arrayAnswers.get(3));
                            b.setNumGolesContra(arrayAnswers.get(4));
                            b.setValoracion(arrayAnswers.get(5));

                            int puntuacion = ((Portero) b).puntuacionTotal();
                            int puntosTotales = puntuacion + b.getPuntosTotales();
                            b.setPuntosTotales(puntosTotales);
                            b.setPoints(puntuacion);
                            gestor.updatePuntuacionesEstadisticasJugadores(b.getNombre(), puntuacion, arrayAnswers.get(1), arrayAnswers.get(0), arrayAnswers.get(3), arrayAnswers.get(2), paradas, penaltisParados, arrayAnswers.get(4), arrayAnswers.get(5), puntosTotales);
                        }
                        else if (b instanceof Defensa)
                        {
                            System.out.println("Puntuaras al defensa: " + b.getNombre());

                            ArrayList <Integer> arrayAnswers = preguntasRespuestasPuntuaciones();
                            boolean expulsado;
                            if (arrayAnswers.get(2) == 1)
                            {
                                expulsado = true;
                            }
                            else
                            {
                                expulsado = false;
                            }

                            b.setNumGoles(arrayAnswers.get(0));
                            b.setMinutos(arrayAnswers.get(1));
                            b.setExpulsado(expulsado);
                            b.setNumAssist(arrayAnswers.get(3));
                            b.setNumGolesContra(arrayAnswers.get(4));
                            b.setValoracion(arrayAnswers.get(5));

                            int puntuacion = ((Defensa) b).puntuacionTotal();
                            int puntosTotales = puntuacion + b.getPuntosTotales();
                            b.setPuntosTotales(puntosTotales);
                            b.setPoints(puntuacion);
                            gestor.updatePuntuacionesEstadisticasJugadores(b.getNombre(), puntuacion, arrayAnswers.get(1), arrayAnswers.get(0), arrayAnswers.get(3), arrayAnswers.get(2), 0, 0, arrayAnswers.get(4), arrayAnswers.get(5), puntosTotales);
                        }
                        else if (b instanceof Mediocentro)
                        {
                            System.out.println("Puntuaras al mediocentro: " + b.getNombre());
                            ArrayList <Integer> arrayAnswers = preguntasRespuestasPuntuaciones();
                            boolean expulsado;
                            if (arrayAnswers.get(2) == 1)
                            {
                                expulsado = true;
                            }
                            else
                            {
                                expulsado = false;
                            }
                            b.setNumGoles(arrayAnswers.get(0));
                            b.setMinutos(arrayAnswers.get(1));
                            b.setExpulsado(expulsado);
                            b.setNumAssist(arrayAnswers.get(3));
                            b.setNumGolesContra(arrayAnswers.get(4));
                            b.setValoracion(arrayAnswers.get(5));

                            int puntuacion = ((Mediocentro) b).puntuacionTotal();
                            int puntosTotales = puntuacion + b.getPuntosTotales();
                            b.setPuntosTotales(puntosTotales);
                            b.setPoints(puntuacion);

                            gestor.updatePuntuacionesEstadisticasJugadores(b.getNombre(), puntuacion, arrayAnswers.get(1), arrayAnswers.get(0), arrayAnswers.get(3), arrayAnswers.get(2), 0, 0, arrayAnswers.get(4), arrayAnswers.get(5), puntosTotales);
                        }
                        else
                        {
                            System.out.println("Puntuaras al delantero: " + b.getNombre());
                            ArrayList <Integer> arrayAnswers = preguntasRespuestasPuntuaciones();
                            boolean expulsado;
                            if (arrayAnswers.get(2) == 1)
                            {
                                expulsado = true;
                            }
                            else
                            {
                                expulsado = false;
                            }

                            b.setNumGoles(arrayAnswers.get(0));
                            b.setMinutos(arrayAnswers.get(1));
                            b.setExpulsado(expulsado);
                            b.setNumAssist(arrayAnswers.get(3));
                            b.setNumGolesContra(arrayAnswers.get(4));
                            b.setValoracion(arrayAnswers.get(5));


                            int puntuacion = ((Delantero) b).puntuacionTotal();
                            int puntosTotales = puntuacion + b.getPuntosTotales();
                            b.setPuntosTotales(puntosTotales);
                            b.setPoints(puntuacion);

                            gestor.updatePuntuacionesEstadisticasJugadores(b.getNombre(), puntuacion, arrayAnswers.get(1), arrayAnswers.get(0), arrayAnswers.get(3), arrayAnswers.get(2), 0, 0, arrayAnswers.get(4), arrayAnswers.get(5), puntosTotales);
                        }
                    }
                }
            }
        }
        //si introduce mal el nombre del equipo, se le da oportunidad de volver a meter y poder puntuar cuando introduzca bien.
        while (aux == false)
        {
            System.out.println("Escribe bien el nombre del equipo: ");
            nombre = Utilidades.leerTexto();

            for (String a: arrayTeams)
            {
                if (a.equals(nombre))
                {
                    aux = true;
                    for (Jugador b: arrayJugadores)
                    {
                        if (b.getTeam().equals(nombre))
                        {
                            if (b instanceof Portero)
                            {
                                System.out.println("Puntuaras al portero: " + b.getNombre() + "\n\t-Cuantos penaltis ha parado?: ");
                                int penaltisParados = Utilidades.leerEntero();
                                System.out.println("\t-Cuantas paradas ha hecho?: ");
                                int paradas = Utilidades.leerEntero();
                                ArrayList <Integer> arrayAnswers = preguntasRespuestasPuntuaciones();
                                boolean expulsado;
                                if (arrayAnswers.get(2) == 1)
                                {
                                    expulsado = true;
                                }
                                else
                                {
                                    expulsado = false;
                                }
                                b.setNumPenaltisParados(penaltisParados);
                                b.setNumParadas(paradas);
                                b.setNumGoles(arrayAnswers.get(0));
                                b.setMinutos(arrayAnswers.get(1));
                                b.setExpulsado(expulsado);
                                b.setNumAssist(arrayAnswers.get(3));
                                b.setNumGolesContra(arrayAnswers.get(4));
                                b.setValoracion(arrayAnswers.get(5));

                                int puntuacion = ((Portero) b).puntuacionTotal();
                                int puntosTotales = puntuacion + b.getPuntosTotales();
                                b.setPuntosTotales(puntosTotales);
                                b.setPoints(puntuacion);
                                gestor.updatePuntuacionesEstadisticasJugadores(b.getNombre(), puntuacion, arrayAnswers.get(1), arrayAnswers.get(0), arrayAnswers.get(3), arrayAnswers.get(2), paradas, penaltisParados, arrayAnswers.get(4), arrayAnswers.get(5), puntosTotales);
                            }
                            else if (b instanceof Defensa)
                            {
                                System.out.println("Puntuaras al defensa: " + b.getNombre());

                                ArrayList <Integer> arrayAnswers = preguntasRespuestasPuntuaciones();
                                boolean expulsado;
                                if (arrayAnswers.get(2) == 1)
                                {
                                    expulsado = true;
                                }
                                else
                                {
                                    expulsado = false;
                                }

                                b.setNumGoles(arrayAnswers.get(0));
                                b.setMinutos(arrayAnswers.get(1));
                                b.setExpulsado(expulsado);
                                b.setNumAssist(arrayAnswers.get(3));
                                b.setNumGolesContra(arrayAnswers.get(4));
                                b.setValoracion(arrayAnswers.get(5));

                                int puntuacion = ((Defensa) b).puntuacionTotal();
                                int puntosTotales = puntuacion + b.getPuntosTotales();
                                b.setPuntosTotales(puntosTotales);
                                b.setPoints(puntuacion);
                                gestor.updatePuntuacionesEstadisticasJugadores(b.getNombre(), puntuacion, arrayAnswers.get(1), arrayAnswers.get(0), arrayAnswers.get(3), arrayAnswers.get(2), 0, 0, arrayAnswers.get(4), arrayAnswers.get(5), puntosTotales);
                            }
                            else if (b instanceof Mediocentro)
                            {
                                System.out.println("Puntuaras al mediocentro: " + b.getNombre());
                                ArrayList <Integer> arrayAnswers = preguntasRespuestasPuntuaciones();
                                boolean expulsado;
                                if (arrayAnswers.get(2) == 1)
                                {
                                    expulsado = true;
                                }
                                else
                                {
                                    expulsado = false;
                                }
                                b.setNumGoles(arrayAnswers.get(0));
                                b.setMinutos(arrayAnswers.get(1));
                                b.setExpulsado(expulsado);
                                b.setNumAssist(arrayAnswers.get(3));
                                b.setNumGolesContra(arrayAnswers.get(4));
                                b.setValoracion(arrayAnswers.get(5));

                                int puntuacion = ((Mediocentro) b).puntuacionTotal();
                                int puntosTotales = puntuacion + b.getPuntosTotales();
                                b.setPuntosTotales(puntosTotales);
                                b.setPoints(puntuacion);

                                gestor.updatePuntuacionesEstadisticasJugadores(b.getNombre(), puntuacion, arrayAnswers.get(1), arrayAnswers.get(0), arrayAnswers.get(3), arrayAnswers.get(2), 0, 0, arrayAnswers.get(4), arrayAnswers.get(5), puntosTotales);
                            }
                            else
                            {
                                System.out.println("Puntuaras al delantero: " + b.getNombre());
                                ArrayList <Integer> arrayAnswers = preguntasRespuestasPuntuaciones();
                                boolean expulsado;
                                if (arrayAnswers.get(2) == 1)
                                {
                                    expulsado = true;
                                }
                                else
                                {
                                    expulsado = false;
                                }

                                b.setNumGoles(arrayAnswers.get(0));
                                b.setMinutos(arrayAnswers.get(1));
                                b.setExpulsado(expulsado);
                                b.setNumAssist(arrayAnswers.get(3));
                                b.setNumGolesContra(arrayAnswers.get(4));
                                b.setValoracion(arrayAnswers.get(5));


                                int puntuacion = ((Delantero) b).puntuacionTotal();
                                int puntosTotales = puntuacion + b.getPuntosTotales();
                                b.setPuntosTotales(puntosTotales);
                                b.setPoints(puntuacion);
                                

                                gestor.updatePuntuacionesEstadisticasJugadores(b.getNombre(), puntuacion, arrayAnswers.get(1), arrayAnswers.get(0), arrayAnswers.get(3), arrayAnswers.get(2), 0, 0, arrayAnswers.get(4), arrayAnswers.get(5), puntosTotales);
                            }
                        }
                    }
                }
            }
        }
        gestor.closeLink();

        System.out.println("Quieres continuar puntuando a otro equipo o aun no han terminado sus partidos? Para puntuar (1) para volver al menu administrador (0);");
        int x = Utilidades.leerEntero();
        if (x == 1)
        {
            puntuarJugadores(arrayJugadores, arrayUsuarios, arrayMarket);
        }
        else
        {
            menuAdministrador(arrayUsuarios, arrayJugadores, arrayMarket);
        }
    }

    /** Este metodo define las preguntas y las respuestas para la puntuacion de los jugadores
     *
     * @return Devuelve un ArrayList de enteros con las respuestas obtenidas
     */
    public static ArrayList <Integer>  preguntasRespuestasPuntuaciones ()
    {
        ArrayList <Integer> arrayRespuestas = new ArrayList<>();

        System.out.println("\t-Cuantos goles ha marcado?: ");
        int goles = Utilidades.leerEntero();
        arrayRespuestas.add(goles);
        System.out.println("\t-Cuantos minutos ha jugado?: ");
        int minutos = Utilidades.leerEntero();
        arrayRespuestas.add(minutos);
        System.out.println("\t-Ha sido expulsado? (1)Si, (0) No: ");
        int expuls = Utilidades.leerEntero();
        arrayRespuestas.add(expuls);
        System.out.println("\t-Cuantas asistencias ha hecho?: ");
        int asistencias = Utilidades.leerEntero();
        arrayRespuestas.add(asistencias);
        System.out.println("\t-Cuantos goles ha encajado?:");
        int golesEnContra = Utilidades.leerEntero();
        arrayRespuestas.add(golesEnContra);
        System.out.println("\t-Cuanto le valorarias del 0 al 3 en base a su partido?: ");
        int valoracion = Utilidades.leerEntero();
        arrayRespuestas.add(valoracion);

        return arrayRespuestas;
    }

    /**
     * Este metodo puntua a los entrenadores despues de que el administrador haya puntuado a todos los jugadores en el apartado 2
     * @param arrayJugadores ArrayList donde se encuentran todos los jugadores de la aplicacion, con sus respectivos datos
     * @param arrayUsuarios ArrayList donde se encuentran los datos de todos los usuarios
     * @param arrayMarket ArrayList donde se encuentran aquellos jugadores que estan en el mercado
     */
    public static void puntuarUsuariosTrasJornada (ArrayList <Jugador> arrayJugadores, ArrayList <UsuariosYadmins> arrayUsuarios, ArrayList <Jugador> arrayMarket)
    {
        System.out.println("Una vez puntuados todos los jugadores en el apartado 2, realizaremos la actualizaciones de los puntos de cada entrenador.\n");
        ArrayList<Alineacion> arrayAlineaciones = SelectData.selectAllAlineaciones(arrayUsuarios, arrayJugadores);

        GestorBD gestor = new GestorBD("Comunio.db");
        int puntosTotalesJornada = 0;
        int puntosJugador = 0;
        
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

            gestor.createLink();
            int puntosTotalesUsuario = arrayAlineaciones.get(i).getEntrenador().getPuntos() + puntosTotalesJornada;
            gestor.updatePuntuacionTotalUsers(puntosTotalesUsuario, arrayAlineaciones.get(i).getEntrenador().getUser());
            gestor.closeLink();
        }

        System.out.println("\nLos usuarios que habian puesto la alineacion para la jornada han sido puntuados y actualizados en la clasificacion, Gracias!" +
                " Queda asi� la clasificacion:\n");
        arrayUsuarios = SelectData.selectAllUsers();
        MenuUsuario.verClasificacion(arrayUsuarios, arrayJugadores, null);
        
        System.out.println("\nToca cualquier boton para volver al menu administrador: ");
        String a = Utilidades.leerTexto();
        menuAdministrador(arrayUsuarios, arrayJugadores, arrayMarket);
    }
}


