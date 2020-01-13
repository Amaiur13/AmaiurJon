package jUnit;

import basesDeDatos.GestorBD;
import basesDeDatos.SelectData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usuariosAdmins.UsuariosYadmins;

import java.util.ArrayList;

public class TestSelectData
{
    private static GestorBD gestorBD;
    private static ArrayList <String> arrayPrueba;
    private static ArrayList <UsuariosYadmins> arrayUsuarios;
    private static String userEscogido, passEscogida;

    @BeforeEach

    public void setUp ()
    {
        gestorBD = new GestorBD("Comunio.db");
        gestorBD.createLink();
        gestorBD.crearTablas();

        arrayPrueba = new ArrayList<>();
        arrayPrueba.add("Mikel;mike;0;88;245000000;343450645");

    }

    @AfterEach
    public void TearDown ()
    {
        gestorBD.closeLink();
    }

    @Test
    public void TestSelectData()
    {
        gestorBD.insertData(arrayPrueba, "UsuariosYadmins");

        arrayUsuarios= SelectData.selectAllUsers();

        for (UsuariosYadmins a: arrayUsuarios)
        {
            if ((a.getUser().equals("Mikel")) && (a.getPassword().equals("mike")))
            {
                userEscogido= a.getUser();
                passEscogida = a.getPassword();
                break;
            }
        }

        Assert.assertTrue(userEscogido.equals("Mikel"));
        Assert.assertTrue(passEscogida.equals("mike"));
    }
}
