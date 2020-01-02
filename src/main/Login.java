package main;

import LP.Utilidades;
import basesDeDatos.SelectData;
import ficherosYbd.LecturaEscrituraFichero;
import jugadores.Jugador;
import menus.*;
import usuariosAdmins.Administrador;
import usuariosAdmins.Usuario;
import usuariosAdmins.UsuariosYadmins;

import java.util.ArrayList;
/** Se encarga de la identificación del usuario. Distingue usuarios y administradores, y los lleva a diferentes menus.
 *
 */
public class Login
{
    /** Se encarga de la identificación del usuario. Distingue usuarios y administradores, y los lleva a diferentes menus.
     *
     */
    public static void logIn ()
    {

        System.out.println("Introduce tu usuario: ");
        String usuario = Utilidades.leerTexto();
        System.out.println("Introduce tu contraseña: ");
        String contra = Utilidades.leerTexto();

        ArrayList <UsuariosYadmins> arrayUsuarios = SelectData.selectAllUsers();
        //ArrayList<UsuariosYadmins> arrayUsuarios = LecturaEscrituraFichero.leerFichero();
        //ArrayList<Jugador> arrayJugadores = LecturaEscrituraFichero.leerFicheroJugador(arrayUsuarios);
        ArrayList<Jugador> arrayJugadores = SelectData.selectAllPlayers(arrayUsuarios);
        ArrayList<Jugador> arrayMarket = LecturaEscrituraFichero.leerFicheroMercado(arrayJugadores);
        UsuariosYadmins userOadmin = comprobarLogIn(usuario, contra, arrayUsuarios);


        if (userOadmin instanceof Administrador)
        {
            System.out.println("Bienvenido, " + usuario + "\n");
            MenuAdministrador.menuAdministrador(arrayUsuarios, arrayJugadores, arrayMarket);
        }

        else if (userOadmin instanceof Usuario)
        {
            System.out.println("Bienvenido, " + usuario + "\n");
            MenuUsuario.menuUsuario(arrayUsuarios, arrayJugadores, userOadmin);
        }

        else
        {
            System.out.println("Usuario o contraseña incorrectos");
        }
    }

    /** Comprueba los datos introducidos en el login y diferencia administadores y usuarios
     *
     * @param usuario Nombre de usuario
     * @param contra Contraseña del usuario
     * @param arrayUsuarios ArrayList tipo UsuariosYAdmins donde se guardan los usuarios y los administradores
     * @return Devuelve un usuario, un administrador o, en caso de identificacion incorrecta, nada.
     */
    public static UsuariosYadmins  comprobarLogIn (String usuario, String contra, ArrayList <UsuariosYadmins> arrayUsuarios)
    {
        int aux = 0;
        UsuariosYadmins admin = new Administrador();
        UsuariosYadmins user = new Usuario();

        for (int i = 0; i < arrayUsuarios.size(); i++)
        {
            if (arrayUsuarios.get(i) instanceof Administrador)
            {
                admin = arrayUsuarios.get(i);

                if (usuario.equals(arrayUsuarios.get(i).getUser()))
                {
                    if (contra.equals(arrayUsuarios.get(i).getPassword()))
                    {
                        aux = 2;
                        break;
                    }
                }
            }

            else if (arrayUsuarios.get(i) instanceof Usuario)
            {
                user = arrayUsuarios.get(i);

                if (usuario.equals(arrayUsuarios.get(i).getUser()))
                {
                    if (contra.equals(arrayUsuarios.get(i).getPassword()))
                    {
                        aux = 3;
                        break;
                    }
                }
            }
        }
        if (aux == 2)
        {
            return admin;
        }

        else if (aux == 3)
        {
            return user;
        }

        else
        {
            return null;
        }
    }
}
