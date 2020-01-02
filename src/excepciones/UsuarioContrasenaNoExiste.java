package excepciones;

/**
 * Clase con excepciones para ventanas
 */
public class UsuarioContrasenaNoExiste extends Exception
{
    //Aun no hacemos uso de ella
    public UsuarioContrasenaNoExiste (String mensaje)
    {
        super(mensaje);
    }
}
