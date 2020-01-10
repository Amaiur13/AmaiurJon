package basesDeDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Contiene todos los metodos de actualizacion de tablas de bd
 *
 */
public class UpdateData
{
    /**
     * actualiza si un jugador esta en venta o no
     * @param nombre nombre jugador
     * @param enVenta variable enventa
     * @param conn conexion a la bd
     */
    public static void updateEnVenta(String nombre, int enVenta, Connection conn)
    {
        String sql = "UPDATE Jugadores SET enVenta = ? WHERE nombre = ?";

        try
                (
                        PreparedStatement pstmt = conn.prepareStatement(sql)
                )
        {
            // set the corresponding param
            pstmt.setInt(1, enVenta);
            pstmt.setString(2, nombre);


            // update
            pstmt.executeUpdate();
            }
        catch (SQLException e)
        {
            System.out.println(e.getMessage() + "falla update jugadores");
        }
    }

    /**
     * Actualiza variable en venta y dueño tras comparar y resetear pujas
     * @param nombre nombre jugador
     * @param enVenta si esta en venta o no
     * @param conn conexion a la bd
     * @param dueno entrenador
     */
    public static void updateNoEnVenta(String nombre, int enVenta, Connection conn, String dueno)
    {
        String sql = "UPDATE Jugadores SET enVenta = ? , dueno = ? WHERE nombre = ?";

        try
                (
                        PreparedStatement pstmt = conn.prepareStatement(sql)
                )
        {
            // set the corresponding param
            pstmt.setInt(1, enVenta);
            pstmt.setString(2,dueno);
            pstmt.setString(3, nombre);

            // update
            pstmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage() + "falla update jugadores");
        }
    }

    /**
     * Actualiza el dinero de los usuarios tras comprar jugadores
     * @param user usuario
     * @param dinero dinero restante
     * @param conn conexion a la bd
     */
    public static void updateDineroUsuario(String user, int dinero, float valor, Connection conn)
    {
        String sql = "UPDATE UsuariosYadmins SET dinero = ?, valor = ? WHERE user = ?";

        try
                (
                        PreparedStatement pstmt = conn.prepareStatement(sql)
                )
        {
            // set the corresponding param
            pstmt.setInt(1, dinero);
            pstmt.setFloat(2, valor);
            pstmt.setString(3, user);


            // update
            pstmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage() + "falla update dinero usuarios");
        }
    }

    /**
     * Actualiza las puntuaciones o estadisticas de los jugadores cuando administrador les puntua
     * @param nombre
     * @param points
     * @param minutos
     * @param numGoles
     * @param numAssist
     * @param expulsado
     * @param numParadas
     * @param numPenaltisParados
     * @param numGolesContra
     * @param valoracion
     * @param conn
     */
    public static void updatePuntuaciones(String nombre, int points, int minutos, int numGoles, int numAssist, int expulsado, int numParadas, int numPenaltisParados, int numGolesContra, int valoracion, int puntosTotales, Connection conn)
    {
        String sql = "UPDATE Jugadores SET points = ? , minutos = ? ,  numGoles = ? , numAssist = ? , expulsado = ? , numParadas = ? , numPenaltisParados = ? , numGolesContra = ? , valoracion = ?, puntosTotales = ? WHERE nombre = ?";

        try
                (
                        PreparedStatement pstmt = conn.prepareStatement(sql)
                )
        {
            // set the corresponding param
            pstmt.setInt(1, points);
            pstmt.setInt(2, minutos);
            pstmt.setInt(3, numGoles);
            pstmt.setInt(4, numAssist);
            pstmt.setInt(5, expulsado);
            pstmt.setInt(6, numParadas);
            pstmt.setInt(7, numPenaltisParados);
            pstmt.setInt(8, numGolesContra);
            pstmt.setInt(9, valoracion);
            pstmt.setInt(10, puntosTotales);
            pstmt.setString(11, nombre);

            // update
            pstmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage() + " falla update puntuaciones");
        }
    }

    /**
     * Actualiza los puntos de los entrenadores o usuarios despuies de que el administrador haya puntuado a todos los jugadores
     * @param puntuacionTotal puntos totales
     * @param usuario usuario o entrenador
     * @param conn conexion a la bd
     */
    public static void updatePuntuacionTotalUsuario(int puntuacionTotal, String usuario, Connection conn)
    {
        String sql = "UPDATE UsuariosYadmins SET puntos = ? WHERE user = ?";

        try
                (
                        PreparedStatement pstmt = conn.prepareStatement(sql)
                )
        {
            // set the corresponding param
            pstmt.setInt(1, puntuacionTotal);
            pstmt.setString(2, usuario);

            // update
            pstmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage() + "falla update puntuacion total usuarios");
        }
    }

    public static void updatePuntuacionesA0trasJornada (String nombreJugador, Connection conn)
    {
        if (nombreJugador == null)
        {
            System.out.println("hola");
            return;
        }

        else {
            String mySql = "UPDATE Jugadores set points = 0, minutos = 0, numGoles = 0, numAssist = 0, expulsado = 0, numParadas = 0, numPenaltisParados = 0, numGolesContra = 0, valoracion = 0 WHERE nombre = ?";


            try (PreparedStatement pstmt = conn.prepareStatement(mySql)) {
                pstmt.setString(1, nombreJugador);

                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
