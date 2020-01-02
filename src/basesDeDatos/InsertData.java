package basesDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Clase que agrupa todos los metodos de insercion de bd
 *
 */
public class InsertData
{
    /**
     * Inserta nuevo usuario a la tabla UsuariosYadmins
     *
     * @param user nombre usuario
     * @param password contraseña
     * @param esAdmin si es administrador o usuario
     * @param puntos puntos del usuario
     */
    public static void insertIntoUsuariosYadmins(String user, String password, String esAdmin, String puntos, String dinero, String valor, Connection conn)
    {
        String sql = "INSERT INTO UsuariosYAdmins VALUES( ?,?,?,?,?,?)";

        try
                (
                        PreparedStatement pstmt = conn.prepareStatement(sql)
                )
        {
            pstmt.setString(1, user );
            pstmt.setString(2, password);
            pstmt.setInt(3, Integer.parseInt(esAdmin));
            pstmt.setInt(4, Integer.parseInt(puntos));
            pstmt.setInt(5, Integer.parseInt(dinero));
            pstmt.setFloat(6, Integer.parseInt(valor));
            pstmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage() + "falla UsuariosYadmins");
        }
    }


    /**
     * Inserta nuevo jugador a la tabla Jugadores
     * @param numID id
     * @param nombre nombre
     * @param demarcacion puesto
     * @param valor precio
     * @param points puntos
     * @param clausula clausula de rescision
     * @param team equipo
     * @param minutos minutos jugados
     * @param numGoles goles metidos
     * @param numAssist asistencias
     * @param expulsado si esta expulsado o no
     * @param disponible leisonado o disponible
     * @param enVenta enventa o no
     * @param dueno entrenador
     * @param numParadas paradas
     * @param numParadasPenalti penaltis
     * @param numGolesContra goles encajados
     * @param valoracion valoracion
     * @param conn conexion a la bd
     */
    public static void insertIntoJugadores(String numID, String nombre, String demarcacion, String valor, String points, String clausula, String team, String minutos, String numGoles, String numAssist, String expulsado, String disponible, String enVenta, String dueno, String numParadas, String numParadasPenalti, String numGolesContra, String valoracion, Connection conn)
    {
    	final int puntosTotales = 0;
    	
        String sql = "INSERT INTO Jugadores VALUES( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try
                (
                        PreparedStatement pstmt = conn.prepareStatement(sql)
                )
        {
            pstmt.setInt(1, Integer.parseInt(numID));
            pstmt.setString(2, nombre);
            pstmt.setString(3, demarcacion);
            pstmt.setInt(4, Integer.parseInt(valor));
            pstmt.setInt(5, Integer.parseInt(points));
            pstmt.setInt(6, Integer.parseInt(clausula));
            pstmt.setString(7, team);
            pstmt.setInt(8, Integer.parseInt(minutos));
            pstmt.setInt(9, Integer.parseInt(numGoles));
            pstmt.setInt(10, Integer.parseInt(numAssist));
            pstmt.setInt(11, Integer.parseInt(expulsado));
            pstmt.setInt(12, Integer.parseInt(disponible));
            pstmt.setInt(13, Integer.parseInt(enVenta));
            pstmt.setString(14, dueno);
            pstmt.setInt(15, Integer.parseInt(numParadas));
            pstmt.setInt(16, Integer.parseInt(numParadasPenalti));
            pstmt.setInt(17, Integer.parseInt(numGolesContra));
            pstmt.setInt(18, Integer.parseInt(valoracion));
            pstmt.setInt(19, puntosTotales);
            pstmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage() + "fallo insert jugadores");
        }
    }

    /**
     * Inserta una alineacion a la bd
     * @param dibujo esquema con el que jugar (4-3-3, 4-4-2...)
     * @param portero
     * @param defensa1
     * @param defensa2
     * @param defensa3
     * @param defensa4
     * @param defensa5
     * @param mediocentro1
     * @param mediocentro2
     * @param mediocentro3
     * @param mediocentro4
     * @param mediocentro5
     * @param delantero1
     * @param delantero2
     * @param delantero3
     * @param entrenador
     * @param conn
     */
    public static void insertIntoAlineaciones (String dibujo, String portero, String defensa1, String defensa2, String defensa3, String defensa4, String defensa5, String mediocentro1, String mediocentro2, String mediocentro3, String mediocentro4, String mediocentro5, String delantero1, String delantero2, String delantero3, String entrenador, Connection conn)
    {
        String sql = "INSERT INTO Alineaciones VALUES( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try
                (
                        PreparedStatement pstmt = conn.prepareStatement(sql)
                )
        {
            pstmt.setString(1, dibujo );
            pstmt.setString(2, portero);
            pstmt.setString(3, defensa1);
            pstmt.setString(4, defensa2);
            pstmt.setString(5, defensa3);
            pstmt.setString(6, defensa4);
            pstmt.setString(7, defensa5);
            pstmt.setString(8, mediocentro1);
            pstmt.setString(9, mediocentro2);
            pstmt.setString(10, mediocentro3);
            pstmt.setString(11, mediocentro4);
            pstmt.setString(12, mediocentro5);
            pstmt.setString(13, delantero1);
            pstmt.setString(14, delantero2);
            pstmt.setString(15, delantero3);
            pstmt.setString(16, entrenador);

            pstmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Inserta una puja a la tabla pujas
     * @param jugador jugador pujado
     * @param puja cantidad pujada
     * @param pujador quien hace la puja
     * @param conn conexion a la bd
     */
    public static void insertIntoPujas(String jugador, String puja, String pujador, Connection conn)
    {
        String sql = "INSERT INTO Pujas VALUES( ?,?,?)";

        try
                (
                        PreparedStatement pstmt = conn.prepareStatement(sql)
                )
        {
            pstmt.setString(1, jugador );
            pstmt.setInt(2, Integer.parseInt(puja));
            pstmt.setString(3, pujador);
            pstmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage() + "falla pujas");
        }
    }
}

