package jUnit;

import basesDeDatos.SelectData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usuariosAdmins.Administrador;
import usuariosAdmins.Usuario;
import usuariosAdmins.UsuariosYadmins;
import static main.Login.comprobarLogIn;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestLogin
{
    private static String userCorrecto;
    private static String contrasenaCorrecta;
    private static UsuariosYadmins user;
    private static ArrayList<UsuariosYadmins> arrayusuarios;
    private static String userCorrectoAdmin;
    private static String contrasenaCorrectaAdmin;
    private static UsuariosYadmins admin;

    @BeforeEach
    void setUp()
    {
        userCorrecto = "jon";
        contrasenaCorrecta = "zaba";
        user = new Usuario("jon", "zaba", false, 80, 73000000,97000000);
        userCorrectoAdmin = "amaiur";
        contrasenaCorrectaAdmin = "13";
        admin = new Administrador("amaiur", "13", true);
        arrayusuarios = SelectData.selectAllUsers();
    }

    @AfterEach
    void tearDown()
    {
    }

    @Test
    void testComprobarLogIn()
    {
        UsuariosYadmins userExpected = comprobarLogIn(userCorrecto, contrasenaCorrecta, arrayusuarios);
        UsuariosYadmins adminExpected = comprobarLogIn(userCorrectoAdmin, contrasenaCorrectaAdmin, arrayusuarios);
        assertTrue(user.getUser().equals(userExpected.getUser()));
        assertTrue(user.getPassword().equals(userExpected.getPassword()));
        assertEquals(null, comprobarLogIn("iñigo", "lopez", arrayusuarios));
        assertEquals(admin.getUser(), adminExpected.getUser());
    }
}