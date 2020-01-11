package excepciones;

import basesDeDatos.GestorBD;
import basesDeDatos.SelectData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import usuariosAdmins.UsuariosYadmins;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class TestInsertData
{
    private static GestorBD gestorBd;
    private static ArrayList <String> arrayInsert;
    private static ArrayList <UsuariosYadmins> arrayUsuarios;
    private static String userObtenido;
    private static String passObtenido;


    @Before
    void setUp()
    {
        gestorBd = new GestorBD("Comunio.db");
        gestorBd.createLink();
        //gestorBd.resetearBD();
        //gestorBd.crearTablas();

        arrayInsert = new ArrayList<>();
        arrayInsert.add("pablo;lopez;0;73;100000000;92450645");
    }

    @After
    void tearDown()
    {
        gestorBd.closeLink();
    }

    @Test(expected = SQLException.class)
    void insertIntoUsuariosYadmins()
    {
        gestorBd.insertData(arrayInsert, "UsuariosYadmins");
        arrayUsuarios = SelectData.selectAllUsers();

        for (UsuariosYadmins a: arrayUsuarios)
        {
            if ((a.getUser().equals("pablo")) && (a.getPassword().equals("lopez")))
            {
                userObtenido = a.getUser();
                passObtenido = a.getPassword();
                break;
            }
        }
        assertTrue(userObtenido.equals("pablo"));
        assertTrue(passObtenido.equals("lopez"));
    }

    @Test()
    void testException2 ()
    {
        gestorBd.insertData(arrayInsert, "UsuariosYadmins");

    }
}