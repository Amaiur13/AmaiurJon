package basesDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Contiene los metodos de reseteo de las tablas de la bd
 */
public class ResetearBD
{
    /**
     * Resetea 3 tablas que se le pasen por parametro
     * @param conn xonexion a la bd
     * @param tabla1 tabla a borrar
     * @param tabla2 tabla a borrar
     * @param tabla3 tabla a borrar
     */
    public static void resetear (Connection conn, String tabla1, String tabla2, String tabla3)
    {
        String tablaAux = "";
        for (int i=0; i<3; i++)
        {
            if (i == 0)
            {
                tablaAux = tabla1;
            }

            else if (i == 1)
            {
                tablaAux = tabla2;
            }

            else if (i == 2)
            {
                tablaAux = tabla3;
            }

           /* else
            {
                tablaAux = tabla4;
            }*/
            String sql = "drop table " +  tablaAux;


            try
                    (
                            Statement stmt = conn.createStatement()
                    )
            {
                // create a new table
                stmt.execute(sql);
            } catch (SQLException e)
            {
                System.out.println(e.getMessage() + "falla el reseteo");
            }
        }

    }

    /**
     * Borra la tabla pujas
     * @param tablaPujas string con tabla puja
     * @param conn conexion a la bd
     */
    public static void resetearPujas (String tablaPujas, Connection conn)
    {
        String sql = "drop table "+tablaPujas;

        try
                (
                        Statement stmt = conn.createStatement()
                )
        {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e)
        {
            System.out.println(e.getMessage() + "falla el reseteo");
        }

    }
}
