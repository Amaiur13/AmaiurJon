package basesDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Contiene el metodo de borrar tuplas
 *
 */
public class DeleteData
{
    /**
     * Metodo generico para borrar
     * @param tabla en que tabla quieres borrar tuplas
     * @param nombreAtributo el nombre del atributo en que te fijas para borrar
     * @param valorAtributo valor del atributo
     * @param conn
     */
    public static void delete(String tabla, String nombreAtributo, String valorAtributo, Connection conn)
    {
        String sql = "DELETE FROM " + tabla + " WHERE " + nombreAtributo + " = ?";

        try
                (
                        PreparedStatement pstmt = conn.prepareStatement(sql)
                )
        {

            // set the corresponding param
            pstmt.setString(1, valorAtributo);

            // execute the delete statement
            pstmt.executeUpdate();

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage() + "falla delete");
        }
    }
}
