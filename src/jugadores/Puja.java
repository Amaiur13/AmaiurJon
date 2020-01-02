package jugadores;

import jugadores.Jugador;
import usuariosAdmins.Usuario;

/** Esta clase define la estructura de las pujas a los jugadores del mercado
 *
 */
public class Puja
{
    Jugador jugador;
    int puja;
    Usuario pujador;

    /** Constructor de la clase Puja
     *
     * @param jugador Jugador por el que se ha pujado
     * @param puja Cantidad numerica de la puja
     * @param pujador Usuario que ha hecho la puja
     */
    public Puja(Jugador jugador, int puja, Usuario pujador)
    {
        this.jugador = jugador;
        this.puja = puja;
        this.pujador = pujador;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public int getPuja() {
        return puja;
    }

    public void setPuja(int puja) {
        this.puja = puja;
    }

    public Usuario getPujador() {
        return pujador;
    }

    public void setPujador(Usuario pujador) {
        this.pujador = pujador;
    }
}
