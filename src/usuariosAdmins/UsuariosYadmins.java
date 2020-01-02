package usuariosAdmins;

/** Clase abstracta que se encarga de los atributos en comun de usuarios y administradores
 *
 */
public abstract class UsuariosYadmins
{
    private String user;
    private String password;
    private boolean esAdmin;

    /** Constructor de la clase UsuariosYAdmins con sus respectivos atributos
     *
     * @param user Nombre del usuario
     * @param password Contraseña del usuario
     * @param esAdmin Boolean que define si es usuario o adminsitrador
     */
    public UsuariosYadmins(String user, String password, boolean esAdmin)
    {
        this.user = user;
        this.password = password;
        this.esAdmin = esAdmin;
    }

    /** Constructor vacio de la clase UsuariosYAdmins
     *
     */
    public UsuariosYadmins()
    {}


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }


}
