package jugadores;

import interfaces.InterfazGeneral;
import usuariosAdmins.Usuario;

public class Delantero extends Jugador implements InterfazGeneral
{
    public Delantero(int numID, String nombre, String demarcacion, int valor, int points, int clausula, String team, int minutos, int numGoles, int numAssist, boolean expulsado, boolean disponible, boolean enVenta, Usuario dueno, int numParadas, int numPenaltisParados, int numGolesContra, int valoracion, int puntosTotales)
    {
        super(numID,nombre, demarcacion, valor, points, clausula, team, minutos, numGoles, numAssist, expulsado, disponible, enVenta, dueno, numParadas, numPenaltisParados, numGolesContra, valoracion, puntosTotales);
    }


    /**
     * Metodo que multiplica por 4 los goles metidos para obtener los puntos respecto a goles
     * @return devuelve el numero de puntos
     */
    @Override
    public int marcarGol()
    {
        return super.getNumGoles()*4;
    }
    /**
     * Metodo que devuelve -2 si ha sido expulsado el jugador
     * @return devuelve -2
     */
    @Override
    public int expulsar()
    {
        if (super.isExpulsado() == true)
        {
            return -2;
        }

        else
        {
            return 0;
        }
    }

    /**
     * Metodo que multiplica *3 las asistencias
     * @return devuelve los puntos de asistencias
     */
    @Override
    public int asistir()
    {
        return super.getNumAssist()*3;
    }

    /**
     * Metodo que devuelve la valoracion del administrador
     * @return devuelve los puntos de la valoracion
     */
    @Override
    public int valoracionAdmin()
    {
        return super.getValoracion();
    }

    /**
     * Metodo que da 2 puntos si el jugador juega mas de 60 minutos, 1 si juega algo o 0 si no juega
     * @return devuelve puntos
     */
    @Override
    public int minutosJugados()
    {
        if ((super.getMinutos() > 0) && (super.getMinutos() < 60))
        {
            return 1;
        }

        else if (super.getMinutos() > 60)
        {
            return 2;
        }

        else
        {
            return 0;
        }
    }

    /**
     * Metodo que calcula la puntuacion total del jugador
     * @return devuelve la puntuacion total
     */
    @Override
    public int puntuacionTotal()
    {
        int a = marcarGol();
        int b = asistir();
        int c = expulsar();
        int d = valoracionAdmin();
        int e = minutosJugados();

        int puntuacion = a+b+c+d+e;
        return puntuacion;
    }

    /**
     * Metodo que devuelve el nombre del jugador en string cuando se invoca al objeto de este tipo
     * @return devuelve el nombre en string
     */
    @Override
    public String toString()
    {
        return this.getNombre() + "  " + this.getDemarcacion() + "   Puntos jornada anterior: " + this.getPoints() + " puntos   " + "   Puntos totales: " + super.getPuntosTotales() + "   " +  this.getTeam() + "  Valor: " + this.getValor();
    }
}
