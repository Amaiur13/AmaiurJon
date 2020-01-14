package tests;

import ordenacion.MergeSort;
import org.junit.jupiter.api.Test;
import usuariosAdmins.Usuario;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestMergeSort {
    private static Usuario usuarioJon, usuarioAritz, usuarioPaul;
    private static ArrayList <Usuario> arrayUsuarios;

    @org.junit.jupiter.api.BeforeEach
    void setUp()
    {
        usuarioJon = new Usuario("jon", "zaba", false, 80, 73000000, 97000000);
        usuarioAritz = new Usuario("aritz", "eraun", false, 45,5550000,45000000);
        usuarioPaul = new Usuario("paul", "fer", false, 60, 70000000, 94000000);
        arrayUsuarios = new ArrayList<>();

        arrayUsuarios.add(usuarioJon);
        arrayUsuarios.add(usuarioAritz);
        arrayUsuarios.add(usuarioPaul);


    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }
    @org.junit.jupiter.api.Test
    void testMergeSortPuntos ()
    {
        MergeSort.mergesort(arrayUsuarios, 0, arrayUsuarios.size()-1, 0);
        for (int j = 0; j<arrayUsuarios.size()-1; j++)
        {
            assertTrue(arrayUsuarios.get(j).getPuntos()>arrayUsuarios.get(j+1).getPuntos());
        }

    }

    @Test
    void testMergeSortValor ()
    {
        MergeSort.mergesort(arrayUsuarios, 0, arrayUsuarios.size()-1, 1);
        for (int j = 0; j<arrayUsuarios.size()-1; j++)
        {
            assertTrue(arrayUsuarios.get(j).getValorEquipo()>arrayUsuarios.get(j+1).getValorEquipo());
        }
    }
}