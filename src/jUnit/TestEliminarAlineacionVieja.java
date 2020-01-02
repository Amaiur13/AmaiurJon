package jUnit;

import basesDeDatos.SelectData;
import jugadores.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usuariosAdmins.Usuario;
import usuariosAdmins.UsuariosYadmins;

import java.util.ArrayList;

import static menus.MenuMiPlantilla.eliminarAlineacionVieja;
import static org.junit.jupiter.api.Assertions.*;

class TestEliminarAlineacionVieja {
    UsuariosYadmins usuarioJon;
    private static ArrayList<Alineacion> arrayAlineaciones;

    private static Alineacion alineacionJon1;
    Portero porteroJon1;
    Defensa defensaJon1;
    Defensa defensaJon2;
    Defensa defensaJon3;
    Mediocentro mediocentroJon1;
    Mediocentro mediocentroJon2;
    Mediocentro mediocentroJon3;
    Mediocentro mediocentroJon4;
    Delantero delanteroJon1;
    Delantero delanteroJon2;
    Delantero delanteroJon3;

    private static Alineacion alineacionJon2;
    Portero porteroJonn1;
    Defensa defensaJonn1;
    Defensa defensaJonn2;
    Defensa defensaJonn3;
    Defensa defensaJonn4;
    Mediocentro mediocentroJonn1;
    Mediocentro mediocentroJonn2;
    Mediocentro mediocentroJonn3;
    Delantero delanteroJonn1;
    Delantero delanteroJonn2;
    Delantero delanteroJonn3;

    @BeforeEach
    void setUp() {
        usuarioJon = new Usuario("jon", "zaba", false, 80, 73000000, 97000000);
        porteroJon1 = new Portero(17,"Moya","Portero",2000000,0,4000000,"Real Sociedad",0,0,0,false,false,false, (Usuario) usuarioJon,0,0,0,0, 0);
        defensaJon1 = new Defensa(15,"Monreal","Defensa",2000000,0,4000000,"Real Sociedad",0,0,0,false,false,false, (Usuario) usuarioJon,0,0,0,0, 0);
        defensaJon2 = new Defensa(19,"Llorente","Defensa",2000000,0,4000000,"Real Sociedad",0,0,0,false,false,false, (Usuario) usuarioJon,0,0,0,0, 0);
        defensaJon3 = new Defensa(18,"Elustondo","Defensa",2000000,0,4000000,"Real Sociedad",0,0,0,false,false,false, (Usuario) usuarioJon,0,0,0,0, 0);
        mediocentroJon1 = new Mediocentro (120,"Zurutuza","Mediocentro",2000000,0,4000000,"Real Sociedad",0,0,0,false,false,false, (Usuario) usuarioJon,0,0,0,0,0);
        mediocentroJon2 = new Mediocentro (121,"Illarra","Mediocentro",2000000,0,4000000,"Real Sociedad",0,0,0,false,false,false, (Usuario) usuarioJon,0,0,0,0,0);
        mediocentroJon3 = new Mediocentro (122,"Guevara","Mediocentro",2000000,0,4000000,"Real Sociedad",0,0,0,false,false,false, (Usuario) usuarioJon,0,0,0,0,0);
        mediocentroJon4 = new Mediocentro (123,"Zubeldia","Mediocentro",2000000,0,4000000,"Real Sociedad",0,0,0,false,false,false, (Usuario) usuarioJon,0,0,0,0,0);
        delanteroJon1 = new Delantero (124,"Willian Jose","Delantero",2000000,0,4000000,"Real Sociedad",0,0,0,false,false,false, (Usuario) usuarioJon,0,0,0,0,0);
        delanteroJon2 = new Delantero (125,"Portu","Delantero",2000000,0,4000000,"Real Sociedad",0,0,0,false,false,false, (Usuario) usuarioJon,0,0,0,0,0);
        delanteroJon3 = new Delantero (126,"Isak","Delantero",2000000,0,4000000,"Real Sociedad",0,0,0,false,false,false, (Usuario) usuarioJon,0,0,0,0,0);
        alineacionJon1 = new Alineacion("3-4-3", porteroJon1, defensaJon1, defensaJon2, defensaJon3, null, null, mediocentroJon1,mediocentroJon2,mediocentroJon3,mediocentroJon4,null, delanteroJon1, delanteroJon2, delanteroJon3, (Usuario) usuarioJon);

        arrayAlineaciones = new ArrayList<>();
        arrayAlineaciones.add(alineacionJon1);
        porteroJonn1 = porteroJon1;
        defensaJonn1 = defensaJon1;
        defensaJonn2 = defensaJon2;
        defensaJonn3 = defensaJon3;
        defensaJonn4 = new Defensa(11,"Zaldua","Defensa",2000000,0,4000000,"Real Sociedad",0,0,0,false,false,false, (Usuario) usuarioJon,0,0,0,0,0);

        mediocentroJonn1 = mediocentroJon1;
        mediocentroJonn2 = mediocentroJon2;
        mediocentroJonn3 = mediocentroJon3;

        delanteroJonn1 = delanteroJon1;
        delanteroJonn2 = delanteroJon2;
        delanteroJonn3 = delanteroJon3;
        alineacionJon2 = new Alineacion("4-3-3", porteroJonn1, defensaJonn1, defensaJonn2, defensaJonn3, defensaJonn4, null, mediocentroJon1,mediocentroJon2,mediocentroJon3,null, null, delanteroJon1, delanteroJon2, delanteroJon3, (Usuario) usuarioJon);
        arrayAlineaciones.add(alineacionJon2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testEliminarAlineacionVieja() {
        ArrayList <Alineacion> arrayAlinVacia = eliminarAlineacionVieja(arrayAlineaciones, usuarioJon);
        //comprueba que vacia el arrayAlinVacia
        assertTrue(arrayAlinVacia.size() == 0);

        //comprobaremos que haya borrado de la bd la alineacion vieja de 'jon'
        ArrayList<UsuariosYadmins> arrayUsers = SelectData.selectAllUsers();
        ArrayList <Jugador> arrayPlayers = SelectData.selectAllPlayers(arrayUsers);
        ArrayList <Alineacion> arrayAlinBd = SelectData.selectAllAlineaciones(arrayUsers, arrayPlayers);

        boolean aux = false;
        for (int i=0; i<arrayAlinBd.size(); i++)
        {
            if (arrayAlinBd.get(i).getEntrenador().getUser().equals(usuarioJon.getUser()))
            {
                aux = true;
                break;
            }
        }
        assertTrue(aux == false);
    }
}