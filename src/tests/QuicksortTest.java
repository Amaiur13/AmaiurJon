package tests;

import jugadoresPujaAlineacion.Defensa;
import jugadoresPujaAlineacion.Jugador;
import jugadoresPujaAlineacion.Portero;
import ordenacion.Quicksort;
import usuariosAdmins.Usuario;
import usuariosAdmins.UsuariosYadmins;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class QuicksortTest
{
    private static UsuariosYadmins usuarioJon;
    private static Portero porteroJon1;
    private static Defensa defensaJon1;
    private static ArrayList<Jugador> arrayJugadores;


    @org.junit.jupiter.api.BeforeEach
    void setUp()
    {
        usuarioJon = new Usuario("jon", "zaba", false, 80, 73000000, 97000000);
        porteroJon1 = new Portero(17,"Moya","Portero",2000000,0,4000000,"Real Sociedad",0,0,0,false,false,false, (Usuario) usuarioJon,0,0,0,0, 20);
        defensaJon1 = new Defensa(15,"Monreal","Defensa",2000000,0,4000000,"Real Sociedad",0,0,0,false,false,false, (Usuario) usuarioJon,0,0,0,0, 21);
        arrayJugadores = new ArrayList<>();
        arrayJugadores.add(porteroJon1);
        arrayJugadores.add(defensaJon1);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void testQuicksort ()
    {
        Quicksort.quicksort(arrayJugadores, 0, arrayJugadores.size() -1);
        for (int i=0; i<arrayJugadores.size()-1; i++)
        {
            assertTrue(arrayJugadores.get(i).getPuntosTotales() > arrayJugadores.get(i+1).getPuntosTotales());
        }
    }
}