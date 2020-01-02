package interfaces;

/** Esta interfaz define el comportamiento de los jugadores con posiciones defensivas, y hereda de la interfaz general
 *
 */
public interface InterfazDefensiva extends InterfazGeneral
{
    /** Este metodo se encarga de la puntuacion por goles encajados
     *
     * @return Devuelve la puntuacion obtenida en el apartado goles encajados
     */
    public abstract int encajarGoles();
}
