package basesDeDatos;

import jugadoresPujaAlineacion.*;
import usuariosAdmins.Administrador;
import usuariosAdmins.Usuario;
import usuariosAdmins.UsuariosYadmins;

import java.sql.*;
import java.util.ArrayList;

/**
 * Clase que contiene todos los metodos select de la bd
 */
public class SelectData
{
    /**
     * Metodo que obtiene conexion a la bd
     * @return la conexion
     */
    private static Connection connect()
    {
        // SQLite connection string
        String name = "Comunio.db";
        String url = "jdbc:sqlite:" + name;
        Connection conn = null;

        try
        {
            conn = DriverManager.getConnection(url);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    /**
     * Selecciona todos los usuarios
     * @return los devuelve en un arraylist de tipo UsuariosYadmins
     */
    public static ArrayList<UsuariosYadmins> selectAllUsers ()
    {
        ArrayList<UsuariosYadmins> arrayUsuarios = new ArrayList<>();
        String sql = "SELECT user, password, esAdmin, puntos, dinero, valor FROM UsuariosYadmins";

        try
                (
                        Connection conn = connect();
                        Statement stmt  = conn.createStatement();
                        ResultSet rs    = stmt.executeQuery(sql)
                )
        {

            // loop through the result set
            while (rs.next())
            {

                String user = rs.getString("user");
                String password = rs.getString("password");
                int esAdmin = rs.getInt("esAdmin");
                int puntos = rs.getInt("puntos");
                float dinero = (float) rs.getInt("dinero");
                float valor = (float) rs.getInt("valor");

                if (esAdmin == 1)
                {
                    Administrador admin = new Administrador(user, password, true);
                    arrayUsuarios.add(admin);
                }

                else
                {
                    Usuario usuario = new Usuario(user, password, false, puntos, dinero,valor);
                    arrayUsuarios.add(usuario);
                }
            }
        }

        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return arrayUsuarios;
    }

    /**
     * Slecciona todos los jugadores
     * @param arrayUsuarios arraylist con todos los usuarios
     * @return devuelve todos los jugadores en un arraylist de tipo Jugador
     */
    public static ArrayList<Jugador> selectAllPlayers (ArrayList <UsuariosYadmins> arrayUsuarios)
    {
        ArrayList<Jugador> arrayJugadores = new ArrayList<>();
        String sql = "SELECT numID, nombre, demarcacion, valor, points, clausula, team, minutos, numGoles, " +
                        "numAssist, expulsado, disponible, enVenta, dueno, numParadas, numPenaltisParados, numGolesContra, valoracion, puntosTotales FROM Jugadores";

        try
                (
                        Connection conn = connect();
                        Statement stmt  = conn.createStatement();
                        ResultSet rs    = stmt.executeQuery(sql)
                )
        {

            // loop through the result set
            while (rs.next())
            {

                int numID = rs.getInt("numID");
                String nombre = rs.getString("nombre");
                String demarcacion = rs.getString("demarcacion");
                int valor = rs.getInt("valor");
                int points = rs.getInt("points");
                int clausula = rs.getInt("clausula");
                String team = rs.getString("team");
                int minutos = rs.getInt("minutos");
                int numGoles = rs.getInt("numGoles");
                int numAssist = rs.getInt("numAssist");
                int expulsado = rs.getInt("expulsado");
                int disponible = rs.getInt("disponible");
                int enVenta = rs.getInt("enVenta");
                String dueno = rs.getString("dueno");
                int numParadas = rs.getInt("numParadas");
                int numPenaltisParados = rs.getInt("numPenaltisParados");
                int numGolesContra = rs.getInt("numGolesContra");
                int valoracion = rs.getInt("valoracion");
                int puntosTotales = rs.getInt("puntosTotales");

                UsuariosYadmins user = null;

                for (int i=0; i<arrayUsuarios.size(); i++)
                {
                    if (arrayUsuarios.get(i) instanceof Usuario)
                    {
                        if (arrayUsuarios.get(i).getUser().equals(dueno))
                        {
                            user = (Usuario) arrayUsuarios.get(i);
                            break;
                        }
                    }
                }

                boolean expulsado1;
                boolean disponible1;
                boolean enVenta1;

                if (expulsado == 1)
                {
                    expulsado1 = true;
                }

                else
                {
                    expulsado1 = false;
                }

                if (disponible == 1)
                {
                    disponible1 = true;
                }

                else
                {
                    disponible1 = false;
                }

                if (enVenta == 1)
                {
                    enVenta1 = true;
                }

                else
                {
                    enVenta1 = false;
                }

                if (demarcacion.equals("Delantero"))
                {
                    Delantero delantero = new Delantero(numID, nombre, demarcacion, valor, points, clausula, team, minutos, numGoles, numAssist, expulsado1, disponible1, enVenta1, (Usuario) user, numParadas, numPenaltisParados, numGolesContra, valoracion, puntosTotales);
                    arrayJugadores.add(delantero);
                }

                else if (demarcacion.equals("Mediocentro"))
                {
                    Mediocentro mediocentro = new Mediocentro(numID, nombre, demarcacion, valor, points, clausula, team, minutos, numGoles, numAssist, expulsado1, disponible1, enVenta1, (Usuario) user, numParadas, numPenaltisParados, numGolesContra, valoracion, puntosTotales);
                    arrayJugadores.add(mediocentro);
                }

                else if (demarcacion.equals("Defensa"))
                {
                    Defensa defensa = new Defensa(numID, nombre, demarcacion, valor, points, clausula, team, minutos, numGoles, numAssist, expulsado1, disponible1, enVenta1, (Usuario) user, numParadas, numPenaltisParados, numGolesContra, valoracion, puntosTotales);
                    arrayJugadores.add(defensa);
                }

                else
                {
                    Portero portero = new Portero(numID, nombre, demarcacion, valor, points, clausula, team, minutos, numGoles, numAssist, expulsado1, disponible1, enVenta1, (Usuario) user, numParadas, numPenaltisParados, numGolesContra, valoracion, puntosTotales);
                    arrayJugadores.add(portero);
                }
            }
        }

        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return arrayJugadores;
    }

    /**
     * Selecciona todas las alineaciones de la bd
     * @param arrayUsuarios todos los usuarios en arraylist tipo UsuariosYadmins
     * @param arrayJugadores todos los jugadores en arraylist tipo Jugador
     * @return
     */
    public static ArrayList<Alineacion> selectAllAlineaciones (ArrayList <UsuariosYadmins> arrayUsuarios, ArrayList<Jugador> arrayJugadores)
    {
        ArrayList<Alineacion> arrayAlineaciones = new ArrayList<>();
        String sql = "SELECT dibujo, portero, defensa1, defensa2, defensa3, defensa4, defensa5, mediocentro1, mediocentro2, mediocentro3, mediocentro4, mediocentro5, delantero1, delantero2, delantero3, entrenador FROM Alineaciones";


        ArrayList <ArrayList<Jugador>> copiaDeAlineacionOrdenada = new ArrayList <>();
        ArrayList <ArrayList<Jugador>> copiaDeAuxAlineacion;
        ArrayList <Usuario> arrayDuenoAlin;
        ArrayList <String> arrayDibujos;

        try
                (
                        Connection conn = connect();
                        Statement stmt  = conn.createStatement();
                        ResultSet rs    = stmt.executeQuery(sql)
                )
        {
            int numDef = 0;
            int numMed = 0;
            copiaDeAuxAlineacion = new ArrayList<>();
            arrayDibujos = new ArrayList<>();
            arrayDuenoAlin = new ArrayList<>();
            Usuario user;
            ArrayList<Jugador> auxAlineacion = new ArrayList<>();

            // loop through the result set
            while (rs.next())
            {
                String dibujo = rs.getString("dibujo");
                String portero = rs.getString("portero");
                String defensa1 = rs.getString("defensa1");
                String defensa2 = rs.getString("defensa2");
                String defensa3 = rs.getString("defensa3");
                String defensa4 = rs.getString("defensa4");
                String defensa5 = rs.getString("defensa5");
                String mediocentro1 = rs.getString("mediocentro1");
                String mediocentro2 = rs.getString("mediocentro2");
                String mediocentro3 = rs.getString("mediocentro3");
                String mediocentro4 = rs.getString("mediocentro4");
                String mediocentro5 = rs.getString("mediocentro5");
                String delantero1 = rs.getString("delantero1");
                String delantero2 = rs.getString("delantero2");
                String delantero3 = rs.getString("delantero3");
                String entrenador = rs.getString("entrenador");//yasta

                String[] divDib = dibujo.split("-");
                numDef = Integer.parseInt(divDib[0]);
                numMed = Integer.parseInt(divDib[1]);


                //recorremos el array de todos los usuarios para ver cual coincide con string usuario del fichero y conseguir dicho objeto usuario
                for (int i=0; i<arrayUsuarios.size(); i++)
                {
                    if (arrayUsuarios.get(i) instanceof Usuario)
                    {
                        if (arrayUsuarios.get(i).getUser().equals(entrenador))
                        {
                            user = (Usuario) arrayUsuarios.get(i);
                            arrayDuenoAlin.add(user);
                            break;
                        }
                    }
                }

                //añade los dibujos de todas las alineaciones
                arrayDibujos.add(dibujo);

                //este for recorre los jugadores para compararlos alos string del fichero y rellenar auxAlineacion con los jugadores de mi alineacion pero desordenados.
                for (int i = 0; i < arrayJugadores.size(); i++)
                {
                    if (numDef == 5 && numMed == 4)
                    {
                        if (arrayJugadores.get(i).getNombre().equals(portero)) {
                            Portero auxPortero = (Portero) arrayJugadores.get(i);
                            auxAlineacion.add(auxPortero);
                        } else if ((arrayJugadores.get(i).getNombre().equals(defensa1)) || (arrayJugadores.get(i).getNombre().equals(defensa2)) || (arrayJugadores.get(i).getNombre().equals(defensa3)) || (arrayJugadores.get(i).getNombre().equals(defensa4)) || (arrayJugadores.get(i).getNombre().equals(defensa5))) {
                            Defensa auxDefensa = (Defensa) arrayJugadores.get(i);
                            auxAlineacion.add(auxDefensa);
                        } else if ((arrayJugadores.get(i).getNombre().equals(mediocentro1)) || (arrayJugadores.get(i).getNombre().equals(mediocentro2)) || (arrayJugadores.get(i).getNombre().equals(mediocentro3)) || (arrayJugadores.get(i).getNombre().equals(mediocentro4))) {
                            Mediocentro auxMediocentro = (Mediocentro) arrayJugadores.get(i);
                            auxAlineacion.add(auxMediocentro);
                        } else if ((arrayJugadores.get(i).getNombre().equals(delantero1))) {
                            Delantero auxDelantero = (Delantero) arrayJugadores.get(i);
                            auxAlineacion.add(auxDelantero);
                        }
                    }

                    else if (numDef == 5 && numMed == 3)
                    {
                        if (arrayJugadores.get(i).getNombre().equals(portero)) {
                            Portero auxPortero = (Portero) arrayJugadores.get(i);
                            auxAlineacion.add(auxPortero);
                        } else if ((arrayJugadores.get(i).getNombre().equals(defensa1)) || (arrayJugadores.get(i).getNombre().equals(defensa2)) || (arrayJugadores.get(i).getNombre().equals(defensa3)) || (arrayJugadores.get(i).getNombre().equals(defensa4)) || (arrayJugadores.get(i).getNombre().equals(defensa5))) {
                            Defensa auxDefensa = (Defensa) arrayJugadores.get(i);
                            auxAlineacion.add(auxDefensa);
                        } else if ((arrayJugadores.get(i).getNombre().equals(mediocentro1)) || (arrayJugadores.get(i).getNombre().equals(mediocentro2)) || (arrayJugadores.get(i).getNombre().equals(mediocentro3))) {
                            Mediocentro auxMediocentro = (Mediocentro) arrayJugadores.get(i);
                            auxAlineacion.add(auxMediocentro);
                        } else if ((arrayJugadores.get(i).getNombre().equals(delantero1)) || (arrayJugadores.get(i).getNombre().equals(delantero2))) {
                            Delantero auxDelantero = (Delantero) arrayJugadores.get(i);
                            auxAlineacion.add(auxDelantero);
                        }
                    }

                    else if (numDef == 4 && numMed == 5) {
                        if (arrayJugadores.get(i).getNombre().equals(portero)) {
                            Portero auxPortero = (Portero) arrayJugadores.get(i);
                            auxAlineacion.add(auxPortero);
                        } else if ((arrayJugadores.get(i).getNombre().equals(defensa1)) || (arrayJugadores.get(i).getNombre().equals(defensa2)) || (arrayJugadores.get(i).getNombre().equals(defensa3)) || (arrayJugadores.get(i).getNombre().equals(defensa4))) {
                            Defensa auxDefensa = (Defensa) arrayJugadores.get(i);
                            auxAlineacion.add(auxDefensa);
                        } else if ((arrayJugadores.get(i).getNombre().equals(mediocentro1)) || (arrayJugadores.get(i).getNombre().equals(mediocentro2)) || (arrayJugadores.get(i).getNombre().equals(mediocentro3)) || (arrayJugadores.get(i).getNombre().equals(mediocentro4)) || (arrayJugadores.get(i).getNombre().equals(mediocentro5))) {
                            Mediocentro auxMediocentro = (Mediocentro) arrayJugadores.get(i);
                            auxAlineacion.add(auxMediocentro);
                        } else if ((arrayJugadores.get(i).getNombre().equals(delantero1))) {
                            Delantero auxDelantero = (Delantero) arrayJugadores.get(i);
                            auxAlineacion.add(auxDelantero);
                        }
                    }

                    else if (numDef == 4 && numMed == 4) {
                        if (arrayJugadores.get(i).getNombre().equals(portero)) {
                            Portero auxPortero = (Portero) arrayJugadores.get(i);
                            auxAlineacion.add(auxPortero);
                        } else if ((arrayJugadores.get(i).getNombre().equals(defensa1)) || (arrayJugadores.get(i).getNombre().equals(defensa2)) || (arrayJugadores.get(i).getNombre().equals(defensa3)) || (arrayJugadores.get(i).getNombre().equals(defensa4))) {
                            Defensa auxDefensa = (Defensa) arrayJugadores.get(i);
                            auxAlineacion.add(auxDefensa);
                        } else if ((arrayJugadores.get(i).getNombre().equals(mediocentro1)) || (arrayJugadores.get(i).getNombre().equals(mediocentro2)) || (arrayJugadores.get(i).getNombre().equals(mediocentro3)) || (arrayJugadores.get(i).getNombre().equals(mediocentro4))) {
                            Mediocentro auxMediocentro = (Mediocentro) arrayJugadores.get(i);
                            auxAlineacion.add(auxMediocentro);
                        } else if ((arrayJugadores.get(i).getNombre().equals(delantero1)) || (arrayJugadores.get(i).getNombre().equals(delantero2))) {
                            Delantero auxDelantero = (Delantero) arrayJugadores.get(i);
                            auxAlineacion.add(auxDelantero);
                        }
                    }

                    else if (numDef == 4 && numMed == 3) {
                        if (arrayJugadores.get(i).getNombre().equals(portero)) {
                            Portero auxPortero = (Portero) arrayJugadores.get(i);
                            auxAlineacion.add(auxPortero);
                        } else if ((arrayJugadores.get(i).getNombre().equals(defensa1)) || (arrayJugadores.get(i).getNombre().equals(defensa2)) || (arrayJugadores.get(i).getNombre().equals(defensa3)) || (arrayJugadores.get(i).getNombre().equals(defensa4))) {
                            Defensa auxDefensa = (Defensa) arrayJugadores.get(i);
                            auxAlineacion.add(auxDefensa);
                        } else if ((arrayJugadores.get(i).getNombre().equals(mediocentro1)) || (arrayJugadores.get(i).getNombre().equals(mediocentro2)) || (arrayJugadores.get(i).getNombre().equals(mediocentro3))) {
                            Mediocentro auxMediocentro = (Mediocentro) arrayJugadores.get(i);
                            auxAlineacion.add(auxMediocentro);
                        } else if ((arrayJugadores.get(i).getNombre().equals(delantero1)) || (arrayJugadores.get(i).getNombre().equals(delantero2)) || (arrayJugadores.get(i).getNombre().equals(delantero3))) {
                            Delantero auxDelantero = (Delantero) arrayJugadores.get(i);
                            auxAlineacion.add(auxDelantero);
                        }
                    }

                    else if (numDef == 3 && numMed == 5) {
                        if (arrayJugadores.get(i).getNombre().equals(portero)) {
                            Portero auxPortero = (Portero) arrayJugadores.get(i);
                            auxAlineacion.add(auxPortero);
                        } else if ((arrayJugadores.get(i).getNombre().equals(defensa1)) || (arrayJugadores.get(i).getNombre().equals(defensa2)) || (arrayJugadores.get(i).getNombre().equals(defensa3))) {
                            Defensa auxDefensa = (Defensa) arrayJugadores.get(i);
                            auxAlineacion.add(auxDefensa);
                        } else if ((arrayJugadores.get(i).getNombre().equals(mediocentro1)) || (arrayJugadores.get(i).getNombre().equals(mediocentro2)) || (arrayJugadores.get(i).getNombre().equals(mediocentro3)) || (arrayJugadores.get(i).getNombre().equals(mediocentro4)) || (arrayJugadores.get(i).getNombre().equals(mediocentro5))) {
                            Mediocentro auxMediocentro = (Mediocentro) arrayJugadores.get(i);
                            auxAlineacion.add(auxMediocentro);
                        } else if ((arrayJugadores.get(i).getNombre().equals(delantero1)) || (arrayJugadores.get(i).getNombre().equals(delantero2))) {
                            Delantero auxDelantero = (Delantero) arrayJugadores.get(i);
                            auxAlineacion.add(auxDelantero);
                        }
                    }

                    else if (numDef == 3 && numMed == 4) {
                        if (arrayJugadores.get(i).getNombre().equals(portero)) {
                            Portero auxPortero = (Portero) arrayJugadores.get(i);
                            auxAlineacion.add(auxPortero);
                        } else if ((arrayJugadores.get(i).getNombre().equals(defensa1)) || (arrayJugadores.get(i).getNombre().equals(defensa2)) || (arrayJugadores.get(i).getNombre().equals(defensa3))) {
                            Defensa auxDefensa = (Defensa) arrayJugadores.get(i);
                            auxAlineacion.add(auxDefensa);
                        } else if ((arrayJugadores.get(i).getNombre().equals(defensa1)) || (arrayJugadores.get(i).getNombre().equals(defensa2)) || (arrayJugadores.get(i).getNombre().equals(defensa3)) || (arrayJugadores.get(i).getNombre().equals(defensa4))) {
                            Mediocentro auxMediocentro = (Mediocentro) arrayJugadores.get(i);
                            auxAlineacion.add(auxMediocentro);
                        } else if ((arrayJugadores.get(i).getNombre().equals(delantero1)) || (arrayJugadores.get(i).getNombre().equals(delantero2)) || (arrayJugadores.get(i).getNombre().equals(delantero3))) {
                            Delantero auxDelantero = (Delantero) arrayJugadores.get(i);
                            auxAlineacion.add(auxDelantero);
                        }
                    }
                }//acaba for
                copiaDeAuxAlineacion.add(auxAlineacion);//tengo en este arraylist todos los arraylists de tipo jugador que he rellenado con las variables necesarias para una alineacion desordenadas
                auxAlineacion = new ArrayList<>();
            }//acaba while

            //añadir portero al array auxAlineacion
            for (int j=0; j<copiaDeAuxAlineacion.size(); j++)
            {
                ArrayList<Jugador> alineacionOrdenada = new ArrayList<>();

                for (int i=0; i < copiaDeAuxAlineacion.get(j).size(); i++)
                {
                    if (copiaDeAuxAlineacion.get(j).get(i) instanceof Portero)
                    {
                        alineacionOrdenada.add(copiaDeAuxAlineacion.get(j).get(i));
                    }
                }

                //añadir defensas al array auxAlineacion
                for (int i=0; i < copiaDeAuxAlineacion.get(j).size(); i++)
                {
                    if (copiaDeAuxAlineacion.get(j).get(i) instanceof Defensa)
                    {
                        alineacionOrdenada.add(copiaDeAuxAlineacion.get(j).get(i));
                    }
                }

                //añadir medios al array auxAlineacion
                for (int i=0; i < copiaDeAuxAlineacion.get(j).size(); i++)
                {
                    if (copiaDeAuxAlineacion.get(j).get(i) instanceof Mediocentro)
                    {
                        alineacionOrdenada.add(copiaDeAuxAlineacion.get(j).get(i));
                    }
                }

                //añadir delanteros al array auxAlineacion
                for (int i=0; i < copiaDeAuxAlineacion.get(j).size(); i++)
                {
                    if (copiaDeAuxAlineacion.get(j).get(i) instanceof Delantero)
                    {
                        alineacionOrdenada.add(copiaDeAuxAlineacion.get(j).get(i));
                    }
                }
                copiaDeAlineacionOrdenada.add(alineacionOrdenada); //tengo todos los arraylist de tipo jugador con alineacion ordenada
            }

            Alineacion alin = null;


            for (int i=0; i<copiaDeAlineacionOrdenada.size(); i++)
            {
                String [] dib = arrayDibujos.get(i).split("-");
                numDef = Integer.parseInt(dib[0]);
                numMed = Integer.parseInt(dib[1]);

                if (numDef == 5 && numMed == 4) {
                    alin = new Alineacion(arrayDibujos.get(i), (Portero) copiaDeAlineacionOrdenada.get(i).get(0),
                            (Defensa) copiaDeAlineacionOrdenada.get(i).get(1), (Defensa) copiaDeAlineacionOrdenada.get(i).get(2), (Defensa) copiaDeAlineacionOrdenada.get(i).get(3), (Defensa) copiaDeAlineacionOrdenada.get(i).get(4), (Defensa) copiaDeAlineacionOrdenada.get(i).get(5),
                            (Mediocentro) copiaDeAlineacionOrdenada.get(i).get(6), (Mediocentro) copiaDeAlineacionOrdenada.get(i).get(7), (Mediocentro) copiaDeAlineacionOrdenada.get(i).get(8), (Mediocentro) copiaDeAlineacionOrdenada.get(i).get(9), null,
                            (Delantero) copiaDeAlineacionOrdenada.get(i).get(10), null, null, arrayDuenoAlin.get(i));
                    arrayAlineaciones.add(alin);
                } else if (numDef == 5 && numMed == 3) {
                    alin = new Alineacion(arrayDibujos.get(i), (Portero) copiaDeAlineacionOrdenada.get(i).get(0),
                            (Defensa) copiaDeAlineacionOrdenada.get(i).get(1), (Defensa) copiaDeAlineacionOrdenada.get(i).get(2), (Defensa) copiaDeAlineacionOrdenada.get(i).get(3), (Defensa) copiaDeAlineacionOrdenada.get(i).get(4), (Defensa) copiaDeAlineacionOrdenada.get(i).get(5),
                            (Mediocentro) copiaDeAlineacionOrdenada.get(i).get(6), (Mediocentro) copiaDeAlineacionOrdenada.get(i).get(7), (Mediocentro) copiaDeAlineacionOrdenada.get(i).get(8), null, null,
                            (Delantero) copiaDeAlineacionOrdenada.get(i).get(9), (Delantero) copiaDeAlineacionOrdenada.get(i).get(10), null, arrayDuenoAlin.get(i));
                    arrayAlineaciones.add(alin);
                } else if (numDef == 4 && numMed == 5) {
                    alin = new Alineacion(arrayDibujos.get(i), (Portero) copiaDeAlineacionOrdenada.get(i).get(0),
                            (Defensa) copiaDeAlineacionOrdenada.get(i).get(1), (Defensa) copiaDeAlineacionOrdenada.get(i).get(2), (Defensa) copiaDeAlineacionOrdenada.get(i).get(3), (Defensa) copiaDeAlineacionOrdenada.get(i).get(4), null,
                            (Mediocentro) copiaDeAlineacionOrdenada.get(i).get(5), (Mediocentro) copiaDeAlineacionOrdenada.get(i).get(6), (Mediocentro) copiaDeAlineacionOrdenada.get(i).get(7), (Mediocentro) copiaDeAlineacionOrdenada.get(i).get(8), (Mediocentro) copiaDeAlineacionOrdenada.get(i).get(9),
                            (Delantero) copiaDeAlineacionOrdenada.get(i).get(10), null, null, arrayDuenoAlin.get(i));
                    arrayAlineaciones.add(alin);
                } else if (numDef == 4 && numMed == 4) {
                    alin = new Alineacion(arrayDibujos.get(i), (Portero) copiaDeAlineacionOrdenada.get(i).get(0),
                            (Defensa) copiaDeAlineacionOrdenada.get(i).get(1), (Defensa) copiaDeAlineacionOrdenada.get(i).get(2), (Defensa) copiaDeAlineacionOrdenada.get(i).get(3), (Defensa) copiaDeAlineacionOrdenada.get(i).get(4), null,
                            (Mediocentro) copiaDeAlineacionOrdenada.get(i).get(5), (Mediocentro) copiaDeAlineacionOrdenada.get(i).get(6), (Mediocentro) copiaDeAlineacionOrdenada.get(i).get(7), (Mediocentro) copiaDeAlineacionOrdenada.get(i).get(8), null,
                            (Delantero) copiaDeAlineacionOrdenada.get(i).get(9), (Delantero) copiaDeAlineacionOrdenada.get(i).get(10), null, arrayDuenoAlin.get(i));
                    arrayAlineaciones.add(alin);
                } else if (numDef == 4 && numMed == 3) {
                    alin = new Alineacion(arrayDibujos.get(i), (Portero) copiaDeAlineacionOrdenada.get(i).get(0),
                            (Defensa) copiaDeAlineacionOrdenada.get(i).get(1), (Defensa) copiaDeAlineacionOrdenada.get(i).get(2), (Defensa) copiaDeAlineacionOrdenada.get(i).get(3), (Defensa) copiaDeAlineacionOrdenada.get(i).get(4), null,
                            (Mediocentro) copiaDeAlineacionOrdenada.get(i).get(5), (Mediocentro) copiaDeAlineacionOrdenada.get(i).get(6), (Mediocentro) copiaDeAlineacionOrdenada.get(i).get(7), null,null,
                            (Delantero) copiaDeAlineacionOrdenada.get(i).get(8), (Delantero) copiaDeAlineacionOrdenada.get(i).get(9), (Delantero) copiaDeAlineacionOrdenada.get(i).get(10), arrayDuenoAlin.get(i));
                    arrayAlineaciones.add(alin);
                } else if (numDef == 3 && numMed == 5) {
                    alin = new Alineacion(arrayDibujos.get(i), (Portero) copiaDeAlineacionOrdenada.get(i).get(0),
                            (Defensa) copiaDeAlineacionOrdenada.get(i).get(1), (Defensa) copiaDeAlineacionOrdenada.get(i).get(2), (Defensa) copiaDeAlineacionOrdenada.get(i).get(3), null, null,
                            (Mediocentro) copiaDeAlineacionOrdenada.get(i).get(4), (Mediocentro) copiaDeAlineacionOrdenada.get(i).get(5), (Mediocentro) copiaDeAlineacionOrdenada.get(i).get(6), (Mediocentro) copiaDeAlineacionOrdenada.get(i).get(7), (Mediocentro) copiaDeAlineacionOrdenada.get(i).get(8),
                            (Delantero) copiaDeAlineacionOrdenada.get(i).get(9), (Delantero) copiaDeAlineacionOrdenada.get(i).get(10), null, arrayDuenoAlin.get(i));
                    arrayAlineaciones.add(alin);
                } else if (numDef == 3 && numMed == 4) {
                    alin = new Alineacion(arrayDibujos.get(i), (Portero) copiaDeAlineacionOrdenada.get(i).get(0),
                            (Defensa) copiaDeAlineacionOrdenada.get(i).get(1), (Defensa) copiaDeAlineacionOrdenada.get(i).get(2), (Defensa) copiaDeAlineacionOrdenada.get(i).get(3),  null, null,
                            (Mediocentro) copiaDeAlineacionOrdenada.get(i).get(4), (Mediocentro) copiaDeAlineacionOrdenada.get(i).get(5), (Mediocentro) copiaDeAlineacionOrdenada.get(i).get(6), (Mediocentro) copiaDeAlineacionOrdenada.get(i).get(7), null,
                            (Delantero) copiaDeAlineacionOrdenada.get(i).get(8), (Delantero) copiaDeAlineacionOrdenada.get(i).get(9), (Delantero) copiaDeAlineacionOrdenada.get(i).get(10), arrayDuenoAlin.get(i));
                    arrayAlineaciones.add(alin);

                }
                alin = null;

            }//acaba for
        }//acaba try

        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return arrayAlineaciones;
    }

    /**
     * Selecciona para cada jugador la puja ganadora
     * @param arrayUsuarios todos los usuarios en arraylist de tipo UsuariosYadmins
     * @param arrayMarket arraylist de tipo Jugador con todos los jugadores del mercado
     * @param nombreJugador el jugador cuyas pujas se analizan
     * @return devuelve objeto de tipo puja, la que mas cantidad haya puesto
     */

    public static Puja selectPujaGanadora (ArrayList <UsuariosYadmins> arrayUsuarios, ArrayList <Jugador> arrayMarket, String nombreJugador)
    {
        //String sql = "SELECT jugador, MAX(puja) as pujaMax, pujador FROM Pujas WHERE jugador = ?";

        String sql = "SELECT jugador, MAX(puja) as pujaMax, pujador from Pujas Where jugador = '" + nombreJugador + "'";

        Puja pujaGanadora = null;
        try
                (
                        Connection conn = connect();
                        Statement stmt  = conn.createStatement();
                        ResultSet rs    = stmt.executeQuery(sql);
                        //PreparedStatement pstmt = conn.prepareStatement(sql)
                ) {

            //pstmt.setString(1,nombreJugador);
            // loop through the result set
            while (rs.next())
            {
                String jugador = rs.getString("jugador");
                int puja= rs.getInt("pujaMax");
                String pujador = rs.getString("pujador");

                for (int i=0; i<arrayMarket.size();i++)
                {
                    if(arrayMarket.get(i).getNombre().equals(nombreJugador))
                    {
                        for(int j=0; j<arrayUsuarios.size();j++)
                        {
                            if(arrayUsuarios.get(j).getUser().equals(pujador))
                            {
                                pujaGanadora = new Puja(arrayMarket.get(i), puja, (Usuario) arrayUsuarios.get(j) );
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pujaGanadora;
    }


    /**
     * Cuenta cuantas pujas hay por un jugador en concreto
     * @param nomJugador nombre el jugador
     * @return devuelva entero con numero de pujas
     */
    public static int selectAllPujasAboutPlayer (String nomJugador)
    {
        String sql = "SELECT count(jugador) AS numPujas from Pujas Where jugador = '" + nomJugador + "'";
        int numPujas = 0;
        try
                (
                        Connection conn = connect();
                        Statement stmt  = conn.createStatement();
                        ResultSet rs    = stmt.executeQuery(sql);
                        //PreparedStatement pstmt = conn.prepareStatement(sql)
                ) {

            //pstmt.setString(1,nombreJugador);
            // loop through the result set
            while (rs.next())
            {
                numPujas = rs.getInt("numPujas");
            }
            } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return numPujas;
    }



    /**
     * Selecciona todos los equipos de los jugadores, sin repeticiones
     * @return devuelve arraylist tipo string con los equipos diferentes
     */
    public static ArrayList<String> selectDistinctTeams ()
    {
        ArrayList<String> arrayTeams = new ArrayList<>();
        String sql = "SELECT distinct team FROM Jugadores";

        try
                (
                        Connection conn = connect();
                        Statement stmt  = conn.createStatement();
                        ResultSet rs    = stmt.executeQuery(sql)
                )
        {

            // loop through the result set
            while (rs.next())
            {

                String team = rs.getString("team");
                arrayTeams.add(team);
            }
        }

        catch (SQLException e)
        {
            System.out.println(e.getMessage() + "falla el select distinct");
        }

        return arrayTeams;
    }

    /**
     * Selecciona los puntos del jugador pasado por parametro
     * @param jugador pueden ser (portero, defensa1, defensa2, ...)
     * @return devuelve la puntuacion del jugador indicado
     */
    public static int jugadorYsusPuntosTotales  (String jugador, String entrenador)//sin terminar
    {

        String sql = "SELECT " + jugador + ", b.points FROM Alineaciones A JOIN Jugadores B ON " + jugador + " = b.nombre " + "WHERE a.entrenador = '" + entrenador + "';";
        int puntos = 0;
        try
                (
                        Connection conn = connect();
                        Statement stmt  = conn.createStatement();
                        ResultSet rs    = stmt.executeQuery(sql)
                )
        {

            // loop through the result set
            while (rs.next())
            {
                puntos = rs.getInt("points");
            }
        }

        catch (SQLException e)
        {
            System.out.println(e.getMessage() + "falla el select puntosJugador");
        }

        return puntos;
    }
}
