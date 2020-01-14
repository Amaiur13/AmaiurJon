package menus;

import LP.Utilidades;
import basesDeDatos.SelectData;
import jugadoresPujaAlineacion.Jugador;
import ordenacion.MergeSort;
import usuariosAdmins.Usuario;
import usuariosAdmins.UsuariosYadmins;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MenuPlantillasLigaSantander
{
    /** Este metodo muestra las plantillas de los usuarios deseados
     *
     * @param arrayJugadores ArrayList que guarda todos los jugadores con sus respectivos datos
     * @param arrayUsuarios ArrayList que guarda todos los datos de los usuarios
     * @param usuario Usuario con el que hemos iniciado sesion
     */
    public static void mostrarPlantillaUsuarios(ArrayList<Jugador> arrayJugadores, ArrayList <UsuariosYadmins> arrayUsuarios, UsuariosYadmins usuario) {
       /* System.out.println("Estos son los entrenadores contrincantes de tu liga: ");
        for (UsuariosYadmins a : arrayUsuarios) {
            if (!(a.getUser().equals(usuario.getUser())) && (a instanceof Usuario) && (!a.getUser().equals("comunio"))) {
                System.out.println("\t-" + a.getUser());
            }
        }
        */
        System.out.println("Que tipo de ordenacion desea realizar?\n" +
                "1- Ordenacion por nombre\n" +
                "2- Ordenacion por puntos\n" +
                "3- Ordenacion por valor de equipo\n");

        System.out.println("Seleccione la opcion deseada:");

        int b = Utilidades.leerEntero();

        switch (b) {
            case 1: ordenacionNombre(arrayJugadores, arrayUsuarios, usuario);
                break;
            case 2: ordenacionPuntos(arrayJugadores, arrayUsuarios, usuario);
                break;
            case 3: ordenacionValor(arrayJugadores, arrayUsuarios, usuario);
                break;
            default:
                System.out.println("Seleccione una opcion entre 1 y 3 por favor");
                break;
        }
    }

    /** Este metodo se encarga de ordenar a los usuarios en orden alfabetico, de la A a la Z
     *
     * @param arrayJugadores ArrayList que guarda todos los jugadores con sus respectivos datos
     * @param arrayUsuarios ArrayList que guarda todos los datos de los usuarios
     * @param usuario Usuario con el que hemos iniciado sesion
     */
    public static void ordenacionNombre (ArrayList <Jugador> arrayJugadores, ArrayList <UsuariosYadmins> arrayUsuarios, UsuariosYadmins usuario)
    {
        List<UsuariosYadmins> arrayUsers = SelectData.selectAllUsers();

        Stream<String> ordenado = arrayUsers.stream().filter(x->x.isEsAdmin()==false && !(x.getUser().equals("comunio"))).map(UsuariosYadmins::getUser).sorted();
        ordenado.forEach(System.out::println);

        verPlantillaUsuario(arrayJugadores, arrayUsuarios, usuario);

    }

    /** Este metodo se encarga de ordenar a los usuarios dependediendo del valor de su equipo, de mayor a menor
     *
     * @param arrayJugadores ArrayList que guarda todos los jugadores con sus respectivos datos
     * @param arrayUsuarios ArrayList que guarda todos los datos de los usuarios
     * @param usuario Usuario con el que hemos iniciado sesion
     */
    public static void ordenacionValor (ArrayList <Jugador> arrayJugadores, ArrayList <UsuariosYadmins> arrayUsuarios, UsuariosYadmins usuario){
        ArrayList <UsuariosYadmins> arrayUsers = SelectData.selectAllUsers();


        for (int k=0; k<arrayUsuarios.size(); k++)
        {
            if (!(arrayUsuarios.get(k) instanceof Usuario) || arrayUsuarios.get(k).getUser().equals("comunio"))
            {
                arrayUsuarios.remove(k);
            }
        }

        Usuario b = new Usuario ();
        ArrayList <Usuario> arraySoloUsuarios = new ArrayList<>();
        for (UsuariosYadmins a: arrayUsuarios)
        {
            b = (Usuario) a;
            arraySoloUsuarios.add(b);
        }

        MergeSort.mergesort(arraySoloUsuarios, 0, arraySoloUsuarios.size()-1,0);

        Usuario user = new Usuario ();
        for (UsuariosYadmins a: arraySoloUsuarios)
        {
            user = (Usuario) a;
            System.out.println(user.getUser() + "    " + user.getValorEquipo() + " euros");
        }
        verPlantillaUsuario(arrayJugadores, arrayUsuarios, usuario);
    }

    /** Este metodo se encarga de ordenar a los usuarios dependiendo del cumputo global de puntos, de mayor a menor
     *
     * @param arrayJugadores ArrayList que guarda todos los jugadores con sus respectivos datos
     * @param arrayUsuarios ArrayList que guarda todos los datos de los usuarios
     *  @param usuario Usuario con el que hemos iniciado sesion
     */
    public static void ordenacionPuntos (ArrayList <Jugador> arrayJugadores, ArrayList <UsuariosYadmins> arrayUsuarios, UsuariosYadmins usuario)
    {
        ArrayList <UsuariosYadmins> arrayUsers = SelectData.selectAllUsers();


        for (int k=0; k<arrayUsuarios.size(); k++)
        {
            if (!(arrayUsuarios.get(k) instanceof Usuario) || arrayUsuarios.get(k).getUser().equals("comunio"))
            {
                arrayUsuarios.remove(k);
            }
        }

        Usuario b = new Usuario ();
        ArrayList <Usuario> arraySoloUsuarios = new ArrayList<>();
        for (UsuariosYadmins a: arrayUsuarios)
        {
            b = (Usuario) a;
            arraySoloUsuarios.add(b);
        }

        MergeSort.mergesort(arraySoloUsuarios, 0, arraySoloUsuarios.size()-1,1);

        Usuario user = new Usuario ();
        for (UsuariosYadmins a: arraySoloUsuarios)
        {
            user = (Usuario) a;
            System.out.println(user.getUser() + "    " + user.getPuntos() + "  puntos");
        }
        verPlantillaUsuario(arrayJugadores, arrayUsuarios, usuario);
    }

    /** Este metodo se encarga de mostrar la plantilla del usuario deseado
     *
     * @param arrayJugadores ArrayList que guarda todos los jugadores con sus respectivos datos
     * @param arrayUsuarios ArrayList que guarda todos los datos de los usuarios
     * @param usuario Usuario con el que hemos iniciado sesion
     */
    public static void verPlantillaUsuario (ArrayList <Jugador> arrayJugadores, ArrayList <UsuariosYadmins> arrayUsuarios, UsuariosYadmins usuario) {
        System.out.println("Nombre del entrenador cuya plantilla quieras ver: ");
        String nombre = Utilidades.leerTexto();
        UsuariosYadmins usuarioPlantilla = null;

        boolean aux = false;
        for (UsuariosYadmins a : arrayUsuarios) {
            if (nombre.equals(a.getUser())) {
                usuarioPlantilla = a;
                break;
            }
        }

        while (usuarioPlantilla == null) {
            System.out.println(" Ese entrenador no existe, introduce otro que exista: ");
            nombre = Utilidades.leerTexto();
            usuarioPlantilla = null;

            aux = false;
            for (UsuariosYadmins a : arrayUsuarios) {
                if (nombre.equals(a.getUser())) {
                    usuarioPlantilla = a;
                    break;
                }
            }
        }

        MenuMiPlantilla.verPlantilla(arrayUsuarios, arrayJugadores, usuarioPlantilla, false);

        System.out.println("\nToca cualquier boton para volver al menu del mercado.");
        String x = Utilidades.leerTexto();
        MenuUsuario.menuUsuario(arrayUsuarios, arrayJugadores, usuario);
    }
}
