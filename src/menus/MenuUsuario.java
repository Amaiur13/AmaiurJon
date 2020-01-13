
package menus;

import ordenacion.MergeSort;
import LP.Utilidades;

import basesDeDatos.SelectData;
import jugadoresPujaAlineacion.*;
import mainSwing.Login;
import usuariosAdmins.Usuario;
import usuariosAdmins.UsuariosYadmins;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/** Esta clase contiene todo lo relacionado con el menu principal del usuario
 *
 */
public class MenuUsuario
{
    /** Este metodo muestra el menu principal del usuario
     *
     * @param arrayUsuarios ArrayList que guarda todos los datos de los usuarios
     * @param arrayJugadores ArrayList que guarda todos los jugadores con sus respectivos datos
     * @param usuario Usuario con el que hemos iniciado sesion
     */
    public  static void menuUsuario (ArrayList <UsuariosYadmins> arrayUsuarios, ArrayList <Jugador> arrayJugadores, UsuariosYadmins usuario)
    {
        System.out.println("Bienvenido a Comunio, ¿Qué desea?\n" +
                "1.- Mi Plantilla\n" +
                "2.- Clasificacion\n" +
                "3.- Mercado de fichajes\n" +
                "4.- Estadisticas\n" +    //en vez de registro de actividad
                "5.- Liga Santander: Plantillas\n" +
                "6.- Cerrar sesión");


        System.out.println("Seleccione la opcion deseada:");

        int a= Utilidades.leerEntero();

        switch (a)
        {
            case 1: MenuMiPlantilla.miPlantilla(arrayUsuarios, arrayJugadores, usuario);
                break;
            case 2: verClasificacion (arrayUsuarios, arrayJugadores, usuario);
                break;
            case 3: MenuMercado.menuMercado(arrayUsuarios, arrayJugadores, (Usuario) usuario);
                break;
            case 4: MenuEstadisticas.menuEstadisticas(arrayUsuarios, arrayJugadores, usuario);
                break;
            case 5: MenuPlantillaUsuario.mostrarPlantillaUsuarios(arrayJugadores, arrayUsuarios, usuario);
                break;
            case 6:
                Login.logIn();
                break;
            default: System.out.println("Seleccione una opcion entre 1 y 6 por favor");
                break;
        }


    }

    /** Este metodo muestra la clasificacion actual de todos los usuarios
     *
     * @param arrayUsuarios ArrayList que guarda todos los datos de los usuarios
     * @param arrayJugadores ArrayList que guarda todos los jugadores con sus respectivos datos
     * @param usuarioReal Usuario con el que hemos iniciado sesion
     */
    public static void verClasificacion (ArrayList <UsuariosYadmins> arrayUsuarios, ArrayList <Jugador> arrayJugadores, UsuariosYadmins usuarioReal)
    {
        ArrayList <UsuariosYadmins> arrayUsers = SelectData.selectAllUsers();
        

        for (int k=0; k<arrayUsuarios.size(); k++)
        {
            if (!(arrayUsuarios.get(k) instanceof Usuario) || arrayUsuarios.get(k).getUser().equals("comunio"))
            {
                arrayUsuarios.remove(k);
            }
        }
        
        //para pasar de usuariosyadmins a usuario
        Usuario b = new Usuario ();
        ArrayList <Usuario> arraySoloUsuarios = new ArrayList<>();
        for (UsuariosYadmins a: arrayUsuarios)
        {
        	b = (Usuario) a;
        	arraySoloUsuarios.add(b);
        }
       
        MergeSort.mergesort(arraySoloUsuarios, 0, arraySoloUsuarios.size()-1,1);
        
        /*
        for (int i=0; i<arrayUsuarios.size(); i++)
        {
            for (int j=0; j<arrayUsuarios.size()-1; j++)
            {
                if ((arrayUsuarios.get(j) instanceof Usuario) && (arrayUsuarios.get(j+1) instanceof Usuario))
                {
                    if (((Usuario) arrayUsuarios.get(j)).getPuntos() < ((Usuario) arrayUsuarios.get(j + 1)).getPuntos())
                    {
                        arrayUsuarios.add(arrayUsuarios.get(j));
                        arrayUsuarios.remove(j);
                    }
                }
            }
        }
        */

        Usuario usuario = new Usuario ();
        for (UsuariosYadmins a: arraySoloUsuarios)
        {
            usuario = (Usuario) a;
            System.out.println(usuario.getUser() + "    " + usuario.getPuntos() + "  puntos");
        }
        
        if (usuarioReal != null)
        {
        System.out.print("\n\nPulsa cualquier boton para volver al menú: ");
        String str = Utilidades.leerTexto();
        MenuMiPlantilla.miPlantilla(arrayUsers, arrayJugadores, usuarioReal);
        }
    }

    /** Este metodo muestra las plantillas de los usuarios deseados
     *
     * @param arrayJugadores ArrayList que guarda todos los jugadores con sus respectivos datos
     * @param arrayUsuarios ArrayList que guarda todos los datos de los usuarios
     * @param usuario Usuario con el que hemos iniciado sesion
     */

}

