package basesDeDatos;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Esta clase contiene todos los metodos de crear tablas
 *
 */
public class CreateDB
{
    /**
     * Connecta a la bd "filename"
     *
     * @param conn conexion de la bd
     */
    public static void createNewDatabase(Connection conn)
    {

        try
        {
            if (conn != null)
            {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
