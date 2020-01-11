package menus;

import LP.Utilidades;
import basesDeDatos.GestorBD;
import basesDeDatos.SelectData;
import ficheros.LecturaEscrituraFichero;
import jugadoresPujaAlineacion.Jugador;
import jugadoresPujaAlineacion.Puja;
import threads.VentanaCronometro;
import usuariosAdmins.Usuario;
import usuariosAdmins.UsuariosYadmins;

import java.util.ArrayList;
import java.util.Random;

/** Esta clase define todo lo relacionado con el mercado de la aplicacion
 *
 */
public class MenuMercado
{
    /** Este metodo muestra el menu de la opcion mercado, con sus respectivas opciones
     *
     * @param arrayUsuarios ArrayList donde se encuentran todos los datos de los usuarios
     * @param arrayJugadores ArrayList donde se encuentran todos los jugadores, con sus respectivos datos
     * @param usuario Usuario con el que hemos iniciado sesion
     */
    public static void menuMercado (ArrayList<UsuariosYadmins> arrayUsuarios, ArrayList<Jugador> arrayJugadores, Usuario usuario)
    {
        arrayJugadores = SelectData.selectAllPlayers(arrayUsuarios);
        System.out.println("Has seleccionado la opcion 'Mercado'. ¿Que desea hacer?\n" +
                "1.- Poner jugador en venta\n" +
                "2.- Comprar jugador\n" +
                "3.- Volver al menu principal\n");

        System.out.println("Seleccione la opcion deseada:");
        int b = Utilidades.leerEntero();
        switch (b)
        {
            case 1: venderJugador(arrayUsuarios, arrayJugadores, usuario);
                break;
            case 2: comprarJugador (arrayUsuarios, arrayJugadores, usuario);
                break;
            case 3: MenuUsuario.menuUsuario(arrayUsuarios, arrayJugadores, usuario);
                break;
            default: System.out.println("Seleccione una opcion entre 1 y 3 por favor");
        }

    }

    /** Este metodo se encarga de poner en venta jugadores de los usuarios
     *
     * @param arrayUsuarios ArrayList donde se encuentran todos los datos de los usuarios
     * @param arrayJugadores ArrayList donde se encuentran todos los jugadores, con sus respectivos datos
     * @param usuario Usuario con el que hemos iniciado sesion
     */
    public static void venderJugador (ArrayList <UsuariosYadmins> arrayUsuarios, ArrayList<Jugador> arrayJugadores, Usuario usuario)
    {
        System.out.println("Estos son tus jugadores que aun no estan en venta: ");

        for (Jugador a: arrayJugadores)
        {
            if (a.getDueno().getUser().equals(usuario.getUser())) {
                if (a.isEnVenta() == false) {
                    System.out.println("Nombre: " + a.getNombre() + "   Demarcacion: " + a.getDemarcacion() + "   Valor: " + a.getValor() + "   Clausula: " + a.getClausula());
                }
            }
        }

        System.out.println("El nombre del que quieras vender: ");
        String nombre = Utilidades.leerTexto();
        boolean aux = false;
        Jugador auxx = null;
        for (Jugador a: arrayJugadores)
        {
            if (a.getNombre().equals(nombre))
            {
                if (a.getDueno().getUser().equals(usuario.getUser()))
                {
                    aux = true;
                    auxx = a;
                    break;
                }
            }
        }

        if (aux)
        {
            System.out.println("Tu jugador se pondra a la venta por el precio de su valor: "+ auxx.getValor());
        }

        else
        {
            System.out.println("Ese jugador no es tuyo!  ");
            System.out.println("Pulsa un boton para volver a ver tus jugadores: ");
            String hola = Utilidades.leerTexto();
            venderJugador(arrayUsuarios, arrayJugadores, usuario);
        }

        GestorBD dbMaan = new GestorBD("Comunio.db");
        dbMaan.createLink();
        dbMaan.updateDataEnVenta(nombre, 1, 0 ,1);
        dbMaan.closeLink();


        System.out.println("Pulsa un boton para volver al menu mercado: ");
        String a = Utilidades.leerTexto();
        menuMercado(arrayUsuarios, arrayJugadores, usuario);


    }

    /** Este metodo se encarga de la compra de jugadores por parte de los usuarios
     *
     * @param arrayUsuarios ArrayList donde se encuentran todos los datos de los usuarios
     * @param arrayJugadores ArrayList donde se encuentran todos los jugadores, con sus respectivos datos
     * @param usuario Usuario con el que hemos iniciado sesion
     */
    public static void comprarJugador (ArrayList <UsuariosYadmins> arrayUsuarios, ArrayList<Jugador> arrayJugadores, Usuario usuario)
    {
        ArrayList<Jugador> arrayVenta = new ArrayList<Jugador>();//todos los jugadores q estan en venta del comunio
        //todos los del mercado tnto comunio como usuarios
        ArrayList <String> arrayPujas = new ArrayList<>();

        for(int i=0; i<arrayJugadores.size();i++)
        {
            if((arrayJugadores.get(i).isEnVenta() == true) && (arrayJugadores.get(i).getDueno().getUser().equals("comunio")))
            {
                arrayVenta.add(arrayJugadores.get(i));
            }
        }

        ArrayList<Jugador> arrayMarket = LecturaEscrituraFichero.leerFicheroMercado(arrayJugadores);
        boolean reset = LecturaEscrituraFichero.reset();



        int tamano = arrayMarket.size();
        ArrayList<Jugador>borrado = new ArrayList<Jugador>();
        if (reset == true)
        {
            for (int i=0; i<arrayMarket.size(); i++)
            {
                borrado.add(arrayMarket.get(i));
            }

            arrayMarket.removeAll(borrado);


            int duplicados=0;
            Random generateRandomNumber = new Random(System.currentTimeMillis());
            for(int k=0; k<10;k++)
            {
                if (k==0)
                {
                    arrayMarket.add(arrayVenta.get(generateRandomNumber.nextInt(arrayVenta.size())));
                    duplicados++;
                }
                else
                {
                    arrayMarket.add(arrayVenta.get(generateRandomNumber.nextInt(arrayVenta.size())));

                    for (int z = 0; z < duplicados; z++)
                    {
                        if ((arrayMarket.get(z).getNombre().equals(arrayMarket.get(k).getNombre())))
                        {
                            arrayMarket.remove(k);
                            k--;
                            duplicados--;
                            break;

                        }


                    }
                    duplicados++;
                }
            }

            for(int i=0; i<arrayJugadores.size();i++)
            {
                for (int j=0; j<arrayMarket.size();j++) {
                    if (arrayJugadores.get(i).isEnVenta() == true && !(arrayJugadores.get(i).getDueno().getUser().equals("comunio")) && !(arrayJugadores.get(i).getNombre().equals(arrayMarket.get(j).getNombre()))) {
                        arrayMarket.add(arrayJugadores.get(i));//añade los jugadores en venta con dueño
                        break;
                    }
                }
            }
        }

        else
        {
            Jugador aux = null;
            boolean auxDupl = false;
            for(int i=0; i<arrayJugadores.size();i++)
            {
                if (arrayJugadores.get(i).isEnVenta() == true && !(arrayJugadores.get(i).getDueno().getUser().equals("comunio")))
                {
                    aux = arrayJugadores.get(i);
                }

                else
                {
                    continue;
                }
                for (int j=0; j<arrayMarket.size();j++)
                {
                    if (aux != null)
                    {
                        if (arrayMarket.get(j).getNombre().equals(aux.getNombre()))
                        {
                            auxDupl = true;
                        }
                    }
                }

                if (auxDupl == false)
                {
                    arrayMarket.add(aux);//añade los jugadores en venta con dueño
                    auxDupl = false;
                    continue;
                }
            }
        }




        LecturaEscrituraFichero.escribirFichero(arrayMarket,0);

        System.out.println("Jugadores en venta: ");
        for (int j=0; j<arrayMarket.size();j++)
        {
            System.out.println("Nombre: "+arrayMarket.get(j).getNombre()+"\t Demarcacion: "+arrayMarket.get(j).getDemarcacion()+"\t Valor: "+arrayMarket.get(j).getValor()+"\t Puntos: "+arrayMarket.get(j).getPoints()+ "\t Dueno: "+arrayMarket.get(j).getDueno().getUser());
        }


        System.out.println("\n Introduzca el nombre completo del jugador que quieres comprar o introduce 'volver' para volver al menu del mercado: ");
        String nombre = Utilidades.leerTexto();

        if (nombre.equals("volver"))
        {
            menuMercado(arrayUsuarios, arrayJugadores, usuario);
        }
        int contadorMercado = 0;
        Jugador jugadorElegido = null;

        for (int i=0; i<arrayMarket.size();i++)
        {
            if (((arrayMarket.get(i).getNombre().equals(nombre)) && !(arrayMarket.get(i).getDueno().getUser().equals(usuario.getUser()))))
            {
                contadorMercado ++;
                jugadorElegido = arrayMarket.get(i);
                break;
            }
        }

        while (contadorMercado==0)
        {
            System.out.println("Introduzca un nombre que aparezca disponible en el mercado o que no sea tuyo: ");
            nombre= Utilidades.leerTexto();

            for (int i=0; i<arrayMarket.size();i++)
            {
                if (arrayMarket.get(i).getNombre().equals(nombre))
                {
                    jugadorElegido = arrayMarket.get(i);
                    contadorMercado ++;
                    break;
                }
            }
        }


        System.out.println("Cuanto quieres pujar por "+jugadorElegido.getNombre()+"?");
        System.out.println("Puja minima: "+jugadorElegido.getValor());
        System.out.println("Puja maxima: "+ usuario.getDinero()+(usuario.getDinero()/4));

        int puja = Utilidades.leerEntero();

        Puja oferta = new Puja(jugadorElegido, puja, usuario);

        String strPuja = String.valueOf(puja);

        arrayPujas.add(jugadorElegido.getNombre() + ";" +  strPuja + ";" + usuario.getUser());

        GestorBD dbManag = new GestorBD("Comunio.db");
        dbManag.createLink();
        dbManag.insertData(arrayPujas, "Pujas");
        dbManag.closeLink();

        int numPujas = SelectData.selectAllPujasAboutPlayer(oferta.getJugador().getNombre());

        System.out.println("Tu oferta ha sido guardada, toca cualquier boton para volver al menu del mercado.");
        if (numPujas == 1)
        {
            VentanaCronometro ventanaCron = new VentanaCronometro(oferta);
            ventanaCron.setVisible(true);
        }

        String x = Utilidades.leerTexto();
        MenuUsuario.menuUsuario(arrayUsuarios, arrayJugadores, usuario);
    }

}
