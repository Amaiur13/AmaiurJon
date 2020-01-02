package usuariosAdmins;

import usuariosAdmins.UsuariosYadmins;

/** Define los atributos de la clase administrador, que hereda de UsuariosYAdmins
 *
 */
public final class Administrador extends UsuariosYadmins
{
    /** Constructor de administrador
     *
     * @param user Nombre del administrador
     * @param password Contraseña del administrador
     * @param esAdmin Boolean que define si es usuario o administrador
     */
    public Administrador(String user, String password, boolean esAdmin)
    {
        super(user, password, esAdmin);
    }

    /** Constructor vacio de administrador
     *
     */
    public Administrador()
    {
        super();
    }
}
