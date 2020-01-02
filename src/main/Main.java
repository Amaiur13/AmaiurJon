package main;

import java.util.ArrayList;

import basesDeDatos.CreateTable;
import basesDeDatos.GestorBD;

/** Clase main que ejecuta el programa
 *
 */
public class Main
{
    /** Se encarga de ejecutar el programa.
     *
     * @param args
     */
    public static void main (String [] args)
    {
    	
       /* GestorBD dbManager = new GestorBD("Comunio.db");

        dbManager.createLink();
        dbManager.resetearPujas();
        dbManager.crearPujas();
        dbManager.closeLink();

        dbManager.createLink();
        dbManager.resetearBD();
        dbManager.crearTablas();

        ArrayList <String> arrayUsers = new ArrayList<>();
        arrayUsers.add("amaiur;13;1;0;0;0");
        arrayUsers.add("jon;zaba;0;80;100000000;97000000");
        arrayUsers.add("aritz;eraun;0;60;100000000;89000000");
        arrayUsers.add("paul;fer;0;70;100000000;92535555");
        arrayUsers.add("comunio;comunio;0;0;0;0");
        dbManager.insertData(arrayUsers, "UsuariosYadmins");

        ArrayList <String> arrayJugadores = new ArrayList<>();
        arrayJugadores.add("1;Oyarzabal;Mediocentro;40000000;0;70000000;Real Sociedad;0;0;0;0;0;0;jon;0;0;0;0");
        arrayJugadores.add("2;Merino;Mediocentro;10000000;0;20000000;Real Sociedad;0;0;0;0;0;0;aritz;0;0;0;0");
        arrayJugadores.add("3;Odegaard;Mediocentro;50000000;0;80000000;Real Sociedad;0;0;0;0;0;0;paul;0;0;0;0");
        arrayJugadores.add("4;Pique;Defensa;30000000;0;60000000;Barcelona;0;0;0;0;0;0;jon;0;0;0;0");
        
        arrayJugadores.add("5;Umtiti;Defensa;30000000;0;60000000;Barcelona;0;0;0;0;0;0;aritz;0;0;0;0");
        arrayJugadores.add("6;Varane;Defensa;0;0;0;Real Madrid;0;0;0;0;0;0;paul;0;0;0;0");
        arrayJugadores.add("7;Benzema;Delantero;40000000;0;70000000;Real Madrid;0;0;0;0;0;0;jon;0;0;0;0");
        arrayJugadores.add("8;Luis Suarez;Delantero;50000000;0;90000000;Barcelona;0;0;0;0;0;0;aritz;0;0;0;0");
        arrayJugadores.add("9;Aduriz;Delantero;1000000;0;2000000;Athletic Club;0;0;0;0;0;0;paul;0;0;0;0");
        arrayJugadores.add("10;Modric;Mediocentro;30000000;0;50000000;Real Madrid;0;0;0;0;0;0;jon;0;0;0;0");
        arrayJugadores.add("11;Busquets;Mediocentro;15000000;0;25000000;Barcelona;0;0;0;0;0;0;aritz;0;0;0;0");
        arrayJugadores.add("12;Parejo;Mediocentro;40000000;0;50000000;Valencia;0;0;0;0;0;0;paul;0;0;0;0");
        arrayJugadores.add("13;Garay;Defensa;10000000;0;15000000;Valencia;0;0;0;0;0;0;jon;0;0;0;0");
        arrayJugadores.add("14;Monreal;Defensa;3000000;0;6000000;Real Sociedad;0;0;0;0;0;0;aritz;0;0;0;0");
        arrayJugadores.add("15;Elustondo;Defensa;1000000;0;2000000;Real Sociedad;0;0;0;0;0;0;paul;0;0;0;0");
        arrayJugadores.add("16;Remiro;Portero;2000000;0;4000000;Real Sociedad;0;0;0;0;0;0;jon;0;0;0;0");
        arrayJugadores.add("17;Moya;Portero;2000000;0;4000000;Real Sociedad;0;0;0;0;0;0;aritz;0;0;0;0");
        arrayJugadores.add("18;Zubiaurre;Portero;200000;0;400000;Real Sociedad;0;0;0;0;0;0;paul;0;0;0;0");
        arrayJugadores.add("19;Willian Jose;Delantero;20000000;0;40000000;Real Sociedad;0;0;0;0;0;0;jon;0;0;0;0");
        arrayJugadores.add("20;Leo Messi;Delantero;200000000;0;400000000;Barcelona;0;0;0;0;0;0;aritz;0;0;0;0");
        arrayJugadores.add("21;Gameiro;Delantero;20000000;0;40000000;Valencia;0;0;0;0;0;0;paul;0;0;0;0");
        arrayJugadores.add("22;Yuri;Defensa;150000000;0;25000000;Athletic Club;0;0;0;0;0;0;jon;0;0;0;0");
        arrayJugadores.add("23;Yerai;Defensa;150000000;0;25000000;Athletic Club;0;0;0;0;0;0;aritz;0;0;0;0");
        arrayJugadores.add("24;Iñigo Martinez;Defensa;150000000;0;25000000;Athletic Club;0;0;0;0;0;0;paul;0;0;0;0");
        arrayJugadores.add("26;Koke;Mediocentro;150000000;0;25000000;Atletico Madrid;0;0;0;0;0;0;jon;0;0;0;0");
        arrayJugadores.add("27;Saul;Mediocentro;150000000;0;25000000;Atletico Madrid;0;0;0;0;0;0;aritz;0;0;0;0");
        arrayJugadores.add("28;Thomas;Mediocentro;150000000;0;25000000;Atletico Madrid;0;0;0;0;0;0;paul;0;0;0;0");
        arrayJugadores.add("29;Hermoso;Defensa;150000000;0;25000000;Atletico Madrid;0;0;0;0;0;0;jon;0;0;0;0");
        arrayJugadores.add("30;Felipe;Defensa;150000000;0;25000000;Atletico Madrid;0;0;0;0;0;0;aritz;0;0;0;0");
        arrayJugadores.add("31;Gimenez;Defensa;150000000;0;25000000;Atletico Madrid;0;0;0;0;0;0;paul;0;0;0;0");
        arrayJugadores.add("32;Kondogbia;Mediocentro;150000000;0;25000000;Valencia;0;0;0;0;0;0;jon;0;0;0;0");
        arrayJugadores.add("33;Soler;Mediocentro;150000000;0;25000000;Valencia;0;0;0;0;0;0;aritz;0;0;0;0");
        arrayJugadores.add("34;Coquelin;Mediocentro;150000000;0;25000000;Valencia;0;0;0;0;0;0;paul;0;0;0;0");
        arrayJugadores.add("35;Griezmann;Delantero;550000000;0;75000000;Barcelona;0;0;0;0;0;1;jon;0;0;0;0");
        arrayJugadores.add("36;Dembele;Delantero;550000000;0;75000000;Barcelona;0;0;0;0;0;1;aritz;0;0;0;0");
        arrayJugadores.add("37;Ansu fati;Delantero;5500000;0;750000;Barcelona;0;0;0;0;0;1;paul;0;0;0;0");
        arrayJugadores.add("38;Vinicius;Delantero;2500000;0;450000;Real Madrid;0;0;0;0;0;1;comunio;0;0;0;0");
        arrayJugadores.add("39;Jovic;Delantero;2500000;0;450000;Real Madrid;0;0;0;0;0;1;comunio;0;0;0;0");
        arrayJugadores.add("40;Brahim;Delantero;2500000;0;450000;Real Madrid;0;0;0;0;0;1;comunio;0;0;0;0");
        arrayJugadores.add("41;Carles Perez;Delantero;500000;0;150000;Barcelona;0;0;0;0;0;1;comunio;0;0;0;0");
        arrayJugadores.add("42;Alena;Mediocentro;100000;0;120000;Barcelona;0;0;0;0;0;1;comunio;0;0;0;0");
        arrayJugadores.add("43;Riqui Puig;Mediocentro;100000;0;120000;Barcelona;0;0;0;0;0;1;comunio;0;0;0;0");
        arrayJugadores.add("44;Nacho;Defensa;100000;0;120000;Real Madrid;0;0;0;0;0;1;comunio;0;0;0;0");
        arrayJugadores.add("45;Areola;Portero;100000;0;120000;Real Madrid;0;0;0;0;0;1;comunio;0;0;0;0");
        arrayJugadores.add("46;Mendy;Defensa;100000;0;120000;Real Madrid;0;0;0;0;0;1;comunio;0;0;0;0");
        arrayJugadores.add("47;Militao;Defensa;100000;0;120000;Real Madrid;0;0;0;0;0;1;comunio;0;0;0;0");
        arrayJugadores.add("48;Fede Valverde;Mediocentro;100000;0;120000;Real Madrid;0;0;0;0;0;1;comunio;0;0;0;0");
        arrayJugadores.add("49;Isco;Mediocentro;100000;0;120000;Real Madrid;0;0;0;0;0;1;comunio;0;0;0;0");
        arrayJugadores.add("50;James;Mediocentro;100000;0;120000;Real Madrid;0;0;0;0;0;1;comunio;0;0;0;0");
        arrayJugadores.add("51;Bono;Portero;100000;0;120000;Sevilla;0;0;0;0;0;1;comunio;0;0;0;0");
        arrayJugadores.add("52;Diego Carlos;Defensa;100000;0;120000;Sevilla;0;0;0;0;0;1;comunio;0;0;0;0");
        arrayJugadores.add("53;Carrico;Defensa;100000;0;120000;Sevilla;0;0;0;0;0;1;comunio;0;0;0;0");
        arrayJugadores.add("54;Navas;Defensa;100000;0;120000;Sevilla;0;0;0;0;0;1;comunio;0;0;0;0");
        arrayJugadores.add("55;Escudero;Defensa;100000;0;120000;Sevilla;0;0;0;0;0;1;comunio;0;0;0;0");
        arrayJugadores.add("56;Fernando;Mediocentro;100000;0;120000;Sevilla;0;0;0;0;0;1;comunio;0;0;0;0");
        arrayJugadores.add("57;Ocampos;Mediocentro;100000;0;120000;Sevilla;0;0;0;0;0;1;comunio;0;0;0;0");
        arrayJugadores.add("58;Oliver Torres;Mediocentro;100000;0;120000;Sevilla;0;0;0;0;0;1;comunio;0;0;0;0");
        arrayJugadores.add("59;Nolito;Delantero;100000;0;120000;Sevilla;0;0;0;0;0;1;comunio;0;0;0;0");
        arrayJugadores.add("60;Munir;Delantero;100000;0;120000;Sevilla;0;0;0;0;0;1;comunio;0;0;0;0");
        arrayJugadores.add("61;Chicharito;Delantero;100000;0;120000;Sevilla;0;0;0;0;0;1;comunio;0;0;0;0");
        arrayJugadores.add("62;Soldado;Delantero;100000;0;120000;Granada;0;0;0;0;0;1;comunio;0;0;0;0");
        arrayJugadores.add("63;Montoro;Medioncetro;100000;0;120000;Granada;0;0;0;0;0;1;comunio;0;0;0;0");
        arrayJugadores.add("64;Darwin Machis;Delantero;100000;0;120000;Granada;0;0;0;0;0;1;comunio;0;0;0;0");
        arrayJugadores.add("65;Antonio Puertas;Delantero;100000;0;120000;Granada;0;0;0;0;0;1;comunio;0;0;0;0");
        arrayJugadores.add("66;Yangel;Mediocentro;100000;0;120000;Granada;0;0;0;0;0;1;comunio;0;0;0;0");
        arrayJugadores.add("67;Neva;Defensa;100000;0;120000;Granada;0;0;0;0;0;1;comunio;0;0;0;0");
        arrayJugadores.add("68;Victor Diaz;Defensa;100000;0;120000;Granada;0;0;0;0;0;1;comunio;0;0;0;0");
        arrayJugadores.add("69;Domingos Duarte;Defensa;100000;0;120000;Granada;0;0;0;0;0;1;comunio;0;0;0;0");
        arrayJugadores.add("70;German;Defensa;100000;0;120000;Granada;0;0;0;0;0;1;comunio;0;0;0;0");



        dbManager.insertData(arrayJugadores, "Jugadores");

        dbManager.closeLink();*/


        System.out.println("Bienvenido, inicie sesion!");
        Login.logIn();
    }

}
