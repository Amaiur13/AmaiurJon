package usuariosAdmins;

/** Esta clase define los atributos del usuario, que hereda de la clase UsuariosYAdmins
 *
 */
public final class Usuario extends UsuariosYadmins
{
    private int puntos;
    private float dinero;
    private float valorEquipo;

    /** Constructor de la clase usuario, con los atributos del constructor de la clase padre
     *
     * @param user Nombre del usuario
     * @param password Contraseña del usuario
     * @param esAdmin Boolean que define si es Usuario o Admistrador
     * @param puntos Numero de puntos del usuario
     * @param dinero Dinero actual del usuario
     */
    public Usuario(String user, String password, boolean esAdmin, int puntos, float dinero, float valorEquipo)
    {
        super(user, password, esAdmin);
        this.puntos = puntos;
        this.dinero = dinero;
        this.valorEquipo = valorEquipo;
    }

    /** Constructor vacio de la clase Usuario
     *
     */
    public Usuario()
    {
        super();

    }
    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public float getDinero() {
        return dinero;
    }

    public void setDinero(float dinero) {
        this.dinero = dinero;
    }

    public float getValorEquipo() {
        return valorEquipo;
    }

    public void setValorEquipo(float valorEquipo) {
        this.valorEquipo = valorEquipo;
    }

    public int puntuacionTrasJornada (int puntuacionJornada)
    {
        this.puntos = this.puntos + puntuacionJornada;
        return this.puntos;
    }

    public float nuevoValor (float nuevoValor)
    {
        this.valorEquipo= nuevoValor;
        return this.valorEquipo;
    }

}
