package basesDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Es la clase que gestiona la BD con sus metodos principales
 */
public class GestorBD
{
    private Connection conn;
    private String BDname;
    private final String URL = "jdbc:sqlite:";


    // Constructor
    public GestorBD(String BDname)
    {
        this.BDname = this.URL + BDname;
    }

    /**
     * Obtiene conexion a la base de datos
     */
    public void createLink()
    {
        try
        {
            this.conn = DriverManager.getConnection(this.BDname);
        }
        catch (SQLException e)
        {
            System.out.println("Error creating connection. " + e.getMessage());
        }
    }

    /**
     * Crea la DB
     */
    public void createDataBase ()
    {
        CreateDB.createNewDatabase(this.conn);
    }

    /**
     * Crea la tabla pujas
     */
    public void crearPujas()
    {
        CreateTable.createNewTablePujas(this.conn);
    }

    /**
     * Crea todas las tablas necesarias al inicio de la aplicacion
     */
    public void crearTablas()
    {
        CreateTable.createNewTableAlineaciones(this.conn);
        CreateTable.createNewTableUsuariosYadmins(this.conn);
        CreateTable.createNewTableJugadores(this.conn);
        CreateTable.createNewTablePujas(this.conn);
    }

    /**
     * Inserta datos en las tablas
     * @param data arraylist de tipo string donde estan los datos a insertar de cada tupla
     * @param tabla la tabla en la que insertar
     */
    public void insertData(List<String> data, String tabla)
    {
        if (tabla.equals("UsuariosYadmins"))
        {
            for (String a : data)
            {
                String [] splitUser = a.split(";");
                InsertData.insertIntoUsuariosYadmins(splitUser[0], splitUser[1], splitUser[2], splitUser[3],splitUser[4],splitUser[5], this.conn);
            }
        }

        else if (tabla.equals("Jugadores"))
        {
            for (String a : data)
            {
                String [] splitUser = a.split(";");
                InsertData.insertIntoJugadores(splitUser[0], splitUser[1], splitUser[2], splitUser[3], splitUser[4], splitUser[5], splitUser[6], splitUser[7], splitUser[8], splitUser[9], splitUser[10], splitUser[11], splitUser[12], splitUser[13], splitUser[14], splitUser[15], splitUser[16], splitUser[17], this.conn);
            }
        }

        else if (tabla.equals("Alineaciones"))
        {
            for (String a : data)
            {
                String [] splitUser = a.split(";");
                InsertData.insertIntoAlineaciones(splitUser[0], splitUser[1], splitUser[2], splitUser[3], splitUser[4], splitUser[5], splitUser[6], splitUser[7], splitUser[8], splitUser[9], splitUser[10], splitUser[11], splitUser[12], splitUser[13], splitUser[14], splitUser[15], this.conn);
            }
        }

        else if (tabla.equals("Pujas"))
        {
            for (String a : data)
            {
                String [] splitUser = a.split(";");
                InsertData.insertIntoPujas(splitUser[0], splitUser[1], splitUser[2], this.conn);
            }
        }

    }

    /**
     * Actualiza el valor del atributo enVenta de la tabla Jugadores o actualiza atributo dinero de la tabla UsuariosYadmins tras comprar jugadores
     * @param nombre nombre del jugador o nombre usuario
     * @param enVenta 1 --> esta en venta, 0--> no esta en venta o dinero del usuario
     * @param aux decide si cual de los 2 metodos descritos se ejecuta
     */
    public void updateDataEnVenta(String nombre, int enVenta, float valorEquipo, int aux)
    {
        if (aux == 1)
        {
            UpdateData.updateEnVenta(nombre, enVenta, this.conn);
        }

        else
        {//en venta en este caso es el dinero, es por reutilizar codigo en vez de hacer mismo metodo 2 veces.
            UpdateData.updateDineroUsuario(nombre, enVenta, valorEquipo, this.conn);
        }
    }

    /**
     * Tras resetear mercado y comparar pujas, asigna jugador al que mas ha pujado y pone en venta a 0
     * @param nombre nombre jugador
     * @param enVenta pone enVenta a 0 para indicar que ha sido adquirido y no está en venta
     * @param dueno usuario comprador
     */
    public void updateDataPuja(String nombre, int enVenta, String dueno)
    {
        UpdateData.updateNoEnVenta(nombre,enVenta,this.conn,dueno);
    }

    /**
     * Actualiza las estadisticas de los jugadores cuando administrador puntua a los jugadores
     * @param nombre nombre jugador
     * @param points puntos totales
     * @param minutos minutos jugados
     * @param numGoles goles metidos
     * @param numAssist asistencis realizadas
     * @param expulsado expulsado o no
     * @param numParadas numero paradas
     * @param numPenaltisParados numero penaltis parados
     * @param numGolesContra goles encajados
     * @param valoracion valoracion del administrador
     */
    public void updatePuntuacionesEstadisticasJugadores (String nombre, int points, int minutos, int numGoles, int numAssist, int expulsado, int numParadas, int numPenaltisParados, int numGolesContra, int valoracion, int puntosTotales)
    {
        UpdateData.updatePuntuaciones(nombre, points, minutos, numGoles, numAssist, expulsado, numParadas, numPenaltisParados, numGolesContra, valoracion, puntosTotales, this.conn);
    }

    /**
     * Actualiza la puntuacionTotal de los usuarios
     * @param puntos puntos totales del usuario
     * @param usuario usuario
     */
    public void updatePuntuacionTotalUsers (int puntos, String usuario)
    {
        UpdateData.updatePuntuacionTotalUsuario(puntos, usuario, this.conn);
    }

    /**
     * Actualiza estadisticas de los jugadores tras finalizar jornada
     * @param nombreJugador jugador a reiniciar estadisticas
     */
    public void updateEstadisticasa0trasJornada (String nombreJugador)
    {
        UpdateData.updatePuntuacionesA0trasJornada(nombreJugador, this.conn);
    }

    /**
     * Borra la tabla pujas
     */
    public void resetearPujas()
    {
        ResetearBD.resetearPujas("Pujas", this.conn);
    }

    /**
     * Resetea las tablas UsuariosYadmins, Jugadores y Alineaciones
     */
    public void resetearBD()
    {
        ResetearBD.resetear(this.conn, "UsuariosYadmins", "Jugadores", "Alineaciones");
    }

    /**
     * Metodo generico para borrar
     * @param tabla en que tabla quieres borrar tuplas
     * @param nomAtr el nombre del atributo en que te fijas para borrar
     * @param valorAtr ealor del atributo
     */
    public void deleteData (String tabla, String nomAtr, String valorAtr)
    {
        DeleteData.delete(tabla, nomAtr, valorAtr, this.conn);

    }

    /**
     * Cierra la conexion a la bd
     */
    public void closeLink()
    {
        try
        {
            if (this.conn != null)
            {
                this.conn.close();
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Error closing connection" + ex.getMessage());
        }
    }


}
