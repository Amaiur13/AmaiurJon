package interfaces;

/** Esta interfaz define el comportamiento general de los jugadores
 *
 */
public interface InterfazGeneral
{
    /** Este metodo se encarga de la puntuacion dependiendo de los goles marcados
     *
     * @return La puntuacion obtenida dependiendo de la cantidad de goles marcados
     */
    public abstract int marcarGol ();
    /** Este metodo se encarga de la puntuacion dependiendo si ha sido expulsado o no
     *
     * @return La puntuacion obtenida dependiendo de si ha sido expulsado
     */
    public abstract int expulsar ();

    /** Este metodo se encarga de la puntuacion dependiendo de las asistencias dadas
     *
     * @return La puntuacion obtenida dependiendo de la cantidad de asistencias dadas
     */
    public abstract int asistir ();

    /** Este metodo se encarga de la puntuacion dependiendo de la valoracion del administrador
     *
     * @return La puntuacion obtenida dependiendo de la valoracion del administrador
     */
    public abstract int valoracionAdmin ();

    /** Este metodo se encarga de la puntuacion total
     *
     * @return La puntuacion total obtenida
     */
    public abstract int puntuacionTotal ();

    /** Este metodo se encarga de la puntuacion dependiendo de los minutos jugados
     *
     * @return La puntuacion obtenida dependiendo de la cantidad de minutos jugados
     */
    public abstract int minutosJugados ();
}
