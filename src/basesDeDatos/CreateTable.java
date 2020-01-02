package basesDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Contiene todos los metodos de creacion de tablas
 *
 */
public class CreateTable
{


    /**
     * Crea la tabla UsuariosYadmins
     *
     */
    public static void createNewTableUsuariosYadmins(Connection conn)
    {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS UsuariosYAdmins (\n"
                + "    user text PRIMARY KEY,\n"
                + "    password text NOT NULL,\n"
                + "    esAdmin integer NOT NULL check (esAdmin = 0 or esAdmin = 1),\n"
                + "    puntos integer NOT NULL, \n"
                + "    dinero integer NOT NULL, \n"
                + "    valor integer NOT NULL \n"
                + ");";

        try
                (
                        Statement stmt = conn.createStatement()
                )
        {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e)
        {
            System.out.println(e.getMessage() + "falla la tabla UsuariosYadmins");
        }
    }

    /**
     * Crea la tabla Jugadores
     * @param conn conexion a la bd
     */
    public static void createNewTableJugadores(Connection conn)
    {
        // SQLite connection string

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Jugadores (\n"
                + "    numID integer PRIMARY KEY NOT NULL, \n"
                + "    nombre text NOT NULL,\n"
                + "    demarcacion text NOT NULL,\n"
                + "    valor integer NOT NULL check (valor>=0),\n"
                + "    points integer NOT NULL, \n"
                + "    clausula integer NOT NULL check (clausula>=0), \n"
                + "    team text NOT NULL, \n"
                + "    minutos integer check (minutos>=0), \n"
                + "    numGoles integer check (numGoles>=0), \n"
                + "    numAssist integer check (numAssist>=0), \n"
                + "    expulsado integer check (expulsado = 0 or expulsado = 1), \n"
                + "    disponible integer check (disponible = 0 or disponible = 1), \n"
                + "    enVenta integer check (enVenta = 0 or enVenta = 1), \n"
                + "    dueno text NOT NULL, \n"
                + "    numParadas integer check (numParadas>=0), \n"
                + "    numPenaltisParados integer check (numPenaltisParados>=0), \n"
                + "    numGolesContra integer check (numGolesContra>=0), \n"
                + "    valoracion integer check (valoracion>=0 and valoracion<=3), \n"
                + "    puntosTotales integer default 0 \n"
                + ");";

        try
                (
                        Statement stmt = conn.createStatement()
                )
        {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e)
        {
            System.out.println(e.getMessage() + "falla la tabla Jugadores");
        }
    }

    /**
     * Crea la tabla Alineaciones
     * @param conn conexion a la bd
     */
    public static void createNewTableAlineaciones(Connection conn)
    {
        // SQLite connection string
        String name = "Comunio.db";
        String url = "jdbc:sqlite:" + name;

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Alineaciones (\n"
                + "    dibujo text NOT NULL, \n"
                + "    portero text NOT NULL,\n"
                + "    defensa1 text NOT NULL,\n"
                + "    defensa2 text NOT NULL, \n"
                + "    defensa3 text NOT NULL, \n"
                + "    defensa4 text, \n"
                + "    defensa5 text, \n"
                + "    mediocentro1 text NOT NULL,\n"
                + "    mediocentro2 text NOT NULL, \n"
                + "    mediocentro3 text NOT NULL, \n"
                + "    mediocentro4 text, \n"
                + "    mediocentro5 text, \n"
                + "    delantero1 text NOT NULL, \n"
                + "    delantero2 text, \n"
                + "    delantero3 text, \n"
                + "    entrenador text NOT NULL \n"
                + ");";

        try
                (
                        Statement stmt = conn.createStatement()
                )
        {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e)
        {
            System.out.println(e.getMessage() + "falla la tabla Alineaciones");
        }
    }

    /**
     * Crea la tabla Pujas
     * @param conn conexion a la bd
     */
    public static void createNewTablePujas(Connection conn)
    {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Pujas (\n"
                + "    jugador text NOT NULL,\n"
                + "    puja integer NOT NULL check (puja>=0),\n"
                + "    pujador text NOT NULL\n"
                + ");";

        try
                (
                        Statement stmt = conn.createStatement()
                )
        {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e)
        {
            System.out.println(e.getMessage() + "falla la tabla Pujas");
        }
    }
}
