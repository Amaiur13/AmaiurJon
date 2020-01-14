package tests;

import basesDeDatos.GestorBD;
import basesDeDatos.SelectData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usuariosAdmins.UsuariosYadmins;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestInsertData
{
    private static GestorBD gestorBd;
    private static ArrayList <String> arrayInsert;
    private static ArrayList <UsuariosYadmins> arrayUsuarios;
    private static String userObtenido;
    private static String passObtenido;

    @BeforeEach
    void setUp()
    {
        gestorBd = new GestorBD("Comunio.db");
        gestorBd.createLink();
        gestorBd.crearTablas();

        arrayInsert = new ArrayList<>();
        arrayInsert.add("pablo;lopez;0;73;100000000;92450645");
    }

    @AfterEach
    void tearDown()
    {
        gestorBd.closeLink();
    }

    @Test
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
}