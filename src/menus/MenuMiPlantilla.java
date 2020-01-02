package menus;

import LP.Utilidades;
import basesDeDatos.GestorBD;
import basesDeDatos.SelectData;
import jugadores.*;
import usuariosAdmins.Usuario;
import usuariosAdmins.UsuariosYadmins;

import java.util.ArrayList;
import java.util.stream.Stream;

/** Esta clase se encarga de todo lo relacionado con la plantilla de cada usuario
 *
 */
public class MenuMiPlantilla
{
    /** Este metodo muestra el menu de la opcion Plantilla, con sus respectivas opciones
     *
     * @param arrayUsuarios ArrayList que guarda todos los datos de los usuarios
     * @param arrayJugadores ArrayList que guarda todos los jugadores con sus respectivos datos
     * @param usuario Usuario con el que hemos iniciado sesion
     */
    public static void miPlantilla (ArrayList<UsuariosYadmins> arrayUsuarios, ArrayList <Jugador> arrayJugadores, UsuariosYadmins usuario)
    {
        System.out.println("Has seleccionado la opcion 'Mi Plantilla'. ¿Que desea hacer?\n" +
                "1.- Ver plantilla\n" +
                "2.- Ver alineacion\n" +
                "3.- Poner alineacion\n" +
                "4.- Volver al menu principal\n");

        System.out.println("Seleccione la opcion deseada:");
        int b = Utilidades.leerEntero();
        boolean volverAlMenuu = true;
        switch (b)
        {
            case 1: verPlantilla(arrayUsuarios, arrayJugadores, usuario, volverAlMenuu);
                break;
            case 2: verAlineacion (arrayUsuarios, arrayJugadores, usuario);
                break;
            case 3: ponerAlineacion (arrayJugadores, arrayUsuarios, usuario);
                break;
            case 4: MenuUsuario.menuUsuario(arrayUsuarios, arrayJugadores, usuario);
                break;
            default: System.out.println("Seleccione una opcion entre 1 y 4 por favor");

        }
    }

    /** Este metodo muestra la plantilla del usuario con el que hemos iniciado sesion
     *
     * @param arrayUsuarios ArrayList que guarda todos los datos de los usuarios
     * @param arrayJugadores ArrayList que guarda todos los jugadores con sus respectivos datos
     * @param usuario Usuario con el que hemos iniciado sesion
     * @param volverAlMenu Boolean que se encarga de volver al menu
     */
    public static void verPlantilla (ArrayList <UsuariosYadmins> arrayUsuarios, ArrayList <Jugador> arrayJugadores, UsuariosYadmins usuario, boolean volverAlMenu)
    {
    	Stream <String> arrayNombres = arrayJugadores.stream()
    												 .filter(u -> u.getDueno().getUser().equals(usuario.getUser()))
    												 .map(Jugador::getNombre);
        long numero = arrayJugadores.stream()
				                    .filter(u -> u.getDueno().getUser().equals(usuario.getUser()))
				                    .count();
        
        System.out.println("Tienes " + numero + " jugadores: \n");
        arrayNombres.forEach(System.out::println);
        
        
    	/*for (int i=0; i<arrayJugadores.size();i++)
        {
            if (arrayJugadores.get(i).getDueno().equals(usuario))
            {
                System.out.println("Nombre: "+arrayJugadores.get(i).getNombre());
            }
        }*/

        if (volverAlMenu == true)
        {
            System.out.print("\n\nPulsa cualquier boton para volver al menú: ");
            String str = Utilidades.leerTexto();
            miPlantilla(arrayUsuarios, arrayJugadores, usuario);
        }


    }

    /** Este metodo muestra la alineacion escogida por el usuario
     *
     * @param arrayUsuarios ArrayList que guarda todos los datos de los usuarios
     * @param arrayJugadores ArrayList que guarda todos los jugadores con sus respectivos datos
     * @param usuario Usuario con el que hemos iniciado sesion
     */
    public static void verAlineacion (ArrayList <UsuariosYadmins> arrayUsuarios, ArrayList <Jugador> arrayJugadores, UsuariosYadmins usuario)
    {
        //ArrayList <Alineacion> arrayAlineaciones = LecturaEscrituraFichero.leerFicheroAlineacion(arrayUsuarios, arrayJugadores);
        ArrayList <Alineacion> arrayAlineaciones = SelectData.selectAllAlineaciones(arrayUsuarios, arrayJugadores);
        Alineacion aux = null;

        for (int i=0; i<arrayAlineaciones.size(); i++)
        {
            if (arrayAlineaciones.get(i).getEntrenador().equals(usuario))
            {
                aux = arrayAlineaciones.get(i);
                System.out.println(aux);
            }
        }

        if (aux == null)
        {
            System.out.println("Aun no has puesto ninguna alineacion " + usuario.getUser() + "!");
        }

        System.out.print("\n\nPulsa cualquier boton para volver al menú: ");
        String str = Utilidades.leerTexto();
        miPlantilla(arrayUsuarios, arrayJugadores, usuario);
    }

    /** Este metodo se encarga de poner la alinaecion deseada para la jirnada
     *
     * @param arrayJugadores ArrayList que guarda todos los jugadores con sus respectivos datos
     * @param arrayUsuarios ArrayList que guarda todos los datos de los usuarios
     * @param usuario Usuario con el que hemos iniciado sesion
     */
    public static void ponerAlineacion (ArrayList <Jugador> arrayJugadores, ArrayList <UsuariosYadmins> arrayUsuarios, UsuariosYadmins usuario)
    {
        boolean volverAlMenu = false;
        System.out.println("Esta es tu plantilla: ");
        verPlantilla(arrayUsuarios, arrayJugadores, usuario, volverAlMenu);
        System.out.println("Escoge tu dibujo para esta jornada {**FORMATO--> X-X-X siendo X+X+X=10 Y ((X >= 3) && (X <=5))}: ");
        String dibujo = Utilidades.leerTexto();

        //escoger portero
        System.out.println("Estos son tus porteros: ");

        for (int i=0; i<arrayJugadores.size(); i++)
        {
            if (arrayJugadores.get(i).getDueno().equals(usuario))
            {
                if (arrayJugadores.get(i) instanceof Portero)
                {
                    System.out.println("Nombre: " + arrayJugadores.get(i).getNombre() + " Posicion: " + arrayJugadores.get(i).getDemarcacion());
                }
            }
        }

        System.out.println("Elige uno por su nombre: ");

        Portero atezaina = new Portero ();
        boolean existe = false;

        while (existe == false)
        {
            String portero = Utilidades.leerTexto();

            for (int i = 0; i < arrayJugadores.size(); i++)
            {
                if (arrayJugadores.get(i).getDueno().equals(usuario))
                {
                    if (arrayJugadores.get(i) instanceof Portero)
                    {
                        if (arrayJugadores.get(i).getNombre().equals(portero))
                        {
                            atezaina = (Portero) arrayJugadores.get(i);
                            existe = true;
                        }
                    }
                }
            }

            if (!existe)
            {
                System.out.println("No tienes ese portero, vuelvelo a introducir: ");
            }
        }

        //escoger defensas
        System.out.println("Estos son tus defensas: ");

        for (int i=0; i<arrayJugadores.size(); i++)
        {
            if (arrayJugadores.get(i).getDueno().equals(usuario))
            {
                if (arrayJugadores.get(i) instanceof Defensa)
                {
                    System.out.println("Nombre: " + arrayJugadores.get(i).getNombre() + " Posicion: " + arrayJugadores.get(i).getDemarcacion());
                }
            }
        }
        String [] dividirDibujo = dibujo.split("-");

        System.out.println("Introduciras " + dividirDibujo[0] + " defensas por su nombre: ");
        ArrayList <Defensa> defensas = new ArrayList<Defensa>();
        Defensa defensa1;


        for (int i=0; i<Integer.parseInt(dividirDibujo[0]); i++)
        {
            existe = false;
            System.out.println("Introduce el defensa " + (i+1) +": ");
            while (existe == false)
            {
                String defensaTeclado = Utilidades.leerTexto();

                for (int j= 0; j < arrayJugadores.size(); j++)
                {
                    if (arrayJugadores.get(j).getDueno().equals(usuario))
                    {
                        if (arrayJugadores.get(j) instanceof Defensa)
                        {
                            if (arrayJugadores.get(j).getNombre().equals(defensaTeclado))
                            {
                                defensa1 = (Defensa) arrayJugadores.get(j);
                                defensas.add(defensa1);
                                existe = true;
                            }
                        }
                    }
                }

                if (!existe) {
                    System.out.println("No tienes ese defensa, vuelvelo a introducir: ");
                }
            }
        }


        //escoger mediocentros
        System.out.println("Estos son tus mediocentros: ");

        for (int i=0; i<arrayJugadores.size(); i++)
        {
            if (arrayJugadores.get(i).getDueno().equals(usuario))
            {
                if (arrayJugadores.get(i) instanceof Mediocentro)
                {
                    System.out.println("Nombre: " + arrayJugadores.get(i).getNombre() + " Posicion: " + arrayJugadores.get(i).getDemarcacion());
                }
            }
        }

        System.out.println("Introduciras " + dividirDibujo[1] + " mediocentros por su nombre: ");
        ArrayList <Mediocentro> mediocentros = new ArrayList<Mediocentro>();
        Mediocentro mediocentro1;


        for (int i=0; i<Integer.parseInt(dividirDibujo[1]); i++)
        {
            existe = false;
            System.out.println("Introduce el mediocentro " + (i+1) +": ");
            while (existe == false)
            {
                String mediocentroTeclado = Utilidades.leerTexto();

                for (int j= 0; j < arrayJugadores.size(); j++)
                {
                    if (arrayJugadores.get(j).getDueno().equals(usuario))
                    {
                        if (arrayJugadores.get(j) instanceof Mediocentro)
                        {
                            if (arrayJugadores.get(j).getNombre().equals(mediocentroTeclado))
                            {
                                mediocentro1 = (Mediocentro) arrayJugadores.get(j);
                                mediocentros.add(mediocentro1);
                                existe = true;
                            }
                        }
                    }
                }

                if (!existe)
                {
                    System.out.println("No tienes ese mediocentro, vuelvelo a introducir: ");
                }
            }
        }

        //escoger delanteros
        System.out.println("Estos son tus delanteros: ");

        for (int i=0; i<arrayJugadores.size(); i++)
        {
            if (arrayJugadores.get(i).getDueno().equals(usuario))
            {
                if (arrayJugadores.get(i) instanceof Delantero)
                {
                    System.out.println("Nombre: " + arrayJugadores.get(i).getNombre() + " Posicion: " + arrayJugadores.get(i).getDemarcacion());
                }
            }
        }

        System.out.println("Introduciras " + dividirDibujo[2] + " delanteros por su nombre: ");
        ArrayList <Delantero> delanteros = new ArrayList<Delantero>();
        Delantero delantero1;


        for (int i=0; i<Integer.parseInt(dividirDibujo[2]); i++)
        {
            existe = false;
            System.out.println("Introduce el delantero " + (i+1) +": ");
            while (existe == false)
            {
                String delanteroTeclado = Utilidades.leerTexto();

                for (int j= 0; j < arrayJugadores.size(); j++)
                {
                    if (arrayJugadores.get(j).getDueno().equals(usuario))
                    {
                        if (arrayJugadores.get(j) instanceof Delantero)
                        {
                            if (arrayJugadores.get(j).getNombre().equals(delanteroTeclado))
                            {
                                delantero1 = (Delantero) arrayJugadores.get(j);
                                delanteros.add(delantero1);
                                existe = true;
                            }
                        }
                    }
                }

                if (!existe)
                {
                    System.out.println("No tienes ese delantero, vuelvelo a introducir: ");
                }
            }
        }

        Alineacion titulares = null;
        ArrayList<Alineacion> arrayAlin = SelectData.selectAllAlineaciones(arrayUsuarios, arrayJugadores);
        ArrayList <Alineacion> arrayAlineacionActualizada = eliminarAlineacionVieja(arrayAlin, usuario);

        if ((Integer.parseInt(dividirDibujo[0]) == 5) && (Integer.parseInt(dividirDibujo[1]) == 4))
        {
            titulares = new Alineacion(dibujo, atezaina, defensas.get(0), defensas.get(1), defensas.get(2), defensas.get(3), defensas.get(4),
                    mediocentros.get(0), mediocentros.get(1), mediocentros.get(2), mediocentros.get(3), null,
                    delanteros.get(0), null, null, (Usuario) usuario);
            arrayAlineacionActualizada.add(titulares);

            actualizarTablaAlineacion(arrayAlineacionActualizada);
        }

        else if ((Integer.parseInt(dividirDibujo[0]) == 5) && (Integer.parseInt(dividirDibujo[1]) == 3))
        {
            titulares = new Alineacion(dibujo, atezaina, defensas.get(0), defensas.get(1), defensas.get(2), defensas.get(3), defensas.get(4),
                    mediocentros.get(0), mediocentros.get(1), mediocentros.get(2), null, null,
                    delanteros.get(0), delanteros.get(1), null, (Usuario) usuario);
            arrayAlineacionActualizada.add(titulares);
            actualizarTablaAlineacion(arrayAlineacionActualizada);
        }

        else if ((Integer.parseInt(dividirDibujo[0]) == 4) && (Integer.parseInt(dividirDibujo[1]) == 5))
        {
            titulares = new Alineacion(dibujo, atezaina, defensas.get(0), defensas.get(1), defensas.get(2), defensas.get(3), null,
                    mediocentros.get(0), mediocentros.get(1), mediocentros.get(2), mediocentros.get(3), mediocentros.get(4),
                    delanteros.get(0), null, null, (Usuario) usuario);
            arrayAlineacionActualizada.add(titulares);
            actualizarTablaAlineacion(arrayAlineacionActualizada);
        }

        else if ((Integer.parseInt(dividirDibujo[0]) == 4) && (Integer.parseInt(dividirDibujo[1]) == 4))
        {
            titulares = new Alineacion(dibujo, atezaina, defensas.get(0), defensas.get(1), defensas.get(2), defensas.get(3), null,
                    mediocentros.get(0), mediocentros.get(1), mediocentros.get(2), mediocentros.get(3), null,
                    delanteros.get(0), delanteros.get(1), null, (Usuario) usuario);
            arrayAlineacionActualizada.add(titulares);
            actualizarTablaAlineacion(arrayAlineacionActualizada);
        }

        else if ((Integer.parseInt(dividirDibujo[0]) == 4) && (Integer.parseInt(dividirDibujo[1]) == 3))
        {
            titulares = new Alineacion(dibujo, atezaina, defensas.get(0), defensas.get(1), defensas.get(2), defensas.get(3), null,
                    mediocentros.get(0), mediocentros.get(1), mediocentros.get(2), null, null,
                    delanteros.get(0), delanteros.get(1), delanteros.get(2), (Usuario) usuario);
            arrayAlineacionActualizada.add(titulares);
            actualizarTablaAlineacion(arrayAlineacionActualizada);
        }

        else if ((Integer.parseInt(dividirDibujo[0]) == 3) && (Integer.parseInt(dividirDibujo[1]) == 5))
        {
            titulares = new Alineacion(dibujo, atezaina, defensas.get(0), defensas.get(1), defensas.get(2), null, null,
                    mediocentros.get(0), mediocentros.get(1), mediocentros.get(2), mediocentros.get(3), mediocentros.get(4),
                    delanteros.get(0), delanteros.get(1), null, (Usuario) usuario);
            arrayAlineacionActualizada.add(titulares);
            actualizarTablaAlineacion(arrayAlineacionActualizada);
        }

        else if ((Integer.parseInt(dividirDibujo[0]) == 3) && (Integer.parseInt(dividirDibujo[1]) == 4))
        {
            titulares = new Alineacion(dibujo, atezaina, defensas.get(0), defensas.get(1), defensas.get(2), null, null,
                    mediocentros.get(0), mediocentros.get(1), mediocentros.get(2), mediocentros.get(3), null,
                    delanteros.get(0), delanteros.get(1), delanteros.get(2), (Usuario) usuario);
            arrayAlineacionActualizada.add(titulares);
            actualizarTablaAlineacion(arrayAlineacionActualizada);
        }



        //ArrayList <Alineacion> arrayAlin = LecturaEscrituraFichero.leerFicheroAlineacion(arrayUsuarios, arrayJugadores);

        //LecturaEscrituraFichero.escribirFicheroAlineacion(arrayAlin, "./src/ficherosYbd/Alineacion.txt");


        System.out.println("Alineación introducida " + usuario.getUser() + "! Pulse cualquier boton y el enter para volver atrás: ");
        String str = Utilidades.leerTexto();
        miPlantilla(arrayUsuarios, arrayJugadores, usuario);
    }

    /** Este metodo se encarga de eliminar la alineacion vieja de la base de datos ademas de vaciar el arrayAlin para que no sobreescriba alineaciones viejas despues
     *
     * @param arrayAlin ArrayList que contiene la alineacion actual del usuario
     * @param usuario Usuario con el que hemos iniciado sesion
     * @return Devuelve el ArrayList que contiene la alineacion, pero, en este caso, sin jugadores
     */
    public static ArrayList <Alineacion> eliminarAlineacionVieja (ArrayList <Alineacion> arrayAlin, UsuariosYadmins usuario)
    {
        GestorBD bdGestor = new GestorBD("Comunio.db");
        bdGestor.createLink();
        Alineacion alin = null;
        for (Alineacion a : arrayAlin)
        {//si hay una alineacion del mismo entrenador en la bd la borra
            if (a.getEntrenador().getUser().equals(usuario.getUser()))
            {
                alin = a;
                arrayAlin.remove(alin);
                break;
            }
        }
        if (alin != null)
        {
            bdGestor.deleteData("Alineaciones", "entrenador", usuario.getUser());
        }
        bdGestor.closeLink();

        ArrayList <Alineacion> arrayBorrar = new ArrayList<>();
        for (Alineacion b: arrayAlin)
        {//deja vacio arrayAlin para k proximamente escriba la nueva alineacion y lo suba a la bd solo esa.
            arrayBorrar.add(b);
        }
        arrayAlin.removeAll(arrayBorrar);

        return arrayAlin;
        //LecturaEscrituraFichero.escribirFicheroAlineacion(arrayAlin, "./src/ficherosYbd/Alineacion.txt");
    }

    /** Este metodo se encarga de actualizar las alineaciones de los usuarios
     *
     * @param arrayAlin ArrayList que contiene la alineacion actual del usuario
     */
    public static void actualizarTablaAlineacion (ArrayList <Alineacion> arrayAlin)
    {
        ArrayList <String> alineacionStr = new ArrayList<>();
        GestorBD bdMan = new GestorBD("Comunio.db");
        bdMan.createLink();


        for (Alineacion a: arrayAlin)
        {
            String alin = a.getDibujo() + ";" + a.getPortero() + ";" +
                    a.getDefensa1() + ";" + a.getDefensa2() + ";" + a.getDefensa3() + ";" + a.getDefensa4() + ";" + a.getDefensa5() + ";" +
                    a.getMediocentro1() + ";" + a.getMediocentro2() + ";" + a.getMediocentro3() + ";" + a.getMediocentro4() + ";" + a.getMediocentro5() + ";" +
                    a.getDelantero1() + ";" + a.getDelantero2() + ";" + a.getDelantero3() + ";" +
                    a.getEntrenador().getUser();
            alineacionStr.add(alin);
        }

        bdMan.insertData(alineacionStr, "Alineaciones");
        bdMan.closeLink();
    }
}
