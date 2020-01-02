package jUnit;

import ordenacion.MergeSort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usuariosAdmins.Usuario;
import usuariosAdmins.UsuariosYadmins;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestMergeSort {
    private static Usuario user1;
    private static Usuario user2;
    private static Usuario user3;
    private static ArrayList <Usuario> arrayUsuarios;

    @BeforeEach
    void setUp() {
        user1 = new Usuario("jon", "zaba", false, 50, 100000, 30000);
        user2 = new Usuario("amaiur", "2", false, 100, 200000, 200000);
        arrayUsuarios = new ArrayList<>();
        arrayUsuarios.add(user1);
        arrayUsuarios.add(user2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void mergesort() {
        MergeSort.mergesort(arrayUsuarios, 0, arrayUsuarios.size()-1, 1);

        for (int i=0; i<arrayUsuarios.size()-1; i++)
        {
            assertTrue(arrayUsuarios.get(i).getPuntos() > arrayUsuarios.get(i+1).getPuntos());
        }
    }
}