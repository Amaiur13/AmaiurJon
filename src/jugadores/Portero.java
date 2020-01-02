package jugadores;

import interfaces.InterfazDefensiva;
import usuariosAdmins.Usuario;

public class Portero extends Jugador implements InterfazDefensiva
{
    public Portero(int numID, String nombre, String demarcacion, int valor, int points, int clausula, String team, int minutos, int numGoles, int numAssist, boolean expulsado, boolean disponible, boolean enVenta, Usuario dueno, int numParadas, int numPenaltisParados, int numGolesContra, int valoracion, int puntosTotales)
    {
        super(numID, nombre, demarcacion, valor, points, clausula, team, minutos, numGoles, numAssist, expulsado, disponible, enVenta, dueno, numParadas, numPenaltisParados, numGolesContra, valoracion, puntosTotales);
    }

    public Portero ()
    {
        super();
    }

    public int pararPenalti ()
    {
        return super.getNumPenaltisParados()*2;
    }

    public int paradas ()
    {
        return super.getNumParadas()*2;
    }

    /**
     * Metodo heredado de la interfaz, dependiendo de cuantos goles haya encajado el defensa se le dan puntos o se le quitan
     * @return devuelve los puntos a dar o quitar
     */
    @Override
    public int encajarGoles()
    {
        if (super.getNumGolesContra() == 0)
        {
            return 4;
        }

        else if (super.getNumGolesContra() == 1)
        {
            return 0;
        }
        else if (super.getNumGolesContra() == 2 || super.getNumGolesContra() == 3)
        {
            return -2;
        }

        else if (super.getNumGolesContra() == 4 || super.getNumGolesContra() == 5)
        {
            return -4;
        }

        else
        {
            return -5;
        }
    }

    /**
     * Metodo que multiplica por 6 los goles metidos para obtener los puntos respecto a goles
     * @return devuelve el numero de puntos
     */
    @Override
    public int marcarGol()
    {
        return super.getNumGoles()*6;
    }

    /**
     * Metodo que devuelve -2 si ha sido expulsado el jugador
     * @return devuelve -2
     */
    @Override
    public int expulsar() {
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
        int a = encajarGoles();
        int b = marcarGol();
        int c = asistir();
        int d = expulsar();
        int e = valoracionAdmin();
        int f = pararPenalti();
        int g = paradas();
        int h = minutosJugados();

        int puntuacion = a+b+c+d+e+f+g+h;
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
