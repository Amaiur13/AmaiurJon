package jugadoresPujaAlineacion;

import usuariosAdmins.Usuario;

public abstract class Jugador
{
    private String nombre;
    private String demarcacion;
    private int valor;
    private int points;
    private int clausula;
    private String team;
    private int minutos;
    private int numID;
    private int numGoles;
    private int numAssist;
    private boolean expulsado;
    private boolean disponible;
    private boolean enVenta;
    private Usuario dueno;
    private int numParadas;
    private int numPenaltisParados;
    private int numGolesContra;
    private int valoracion;
    private int puntosTotales;

    public Jugador ()
    {}

    public Jugador (int numID, String nombre, String demarcacion, int valor, int points, int clausula, String team, int minutos, int numGoles, int numAssist, boolean expulsado, boolean disponible, boolean enVenta, Usuario dueno, int numParadas, int numPenaltisParados, int numGolesContra, int valoracion, int puntosTotales) {
        this.numID = numID;
        this.nombre=nombre;
        this.demarcacion = demarcacion;
        this.valor = valor;
        this.points = points;
        this.clausula = clausula;
        this.team = team;
        this.minutos = minutos;
        this.numGoles = numGoles;
        this.numAssist = numAssist;
        this.expulsado = expulsado;
        this.disponible = disponible;
        this.enVenta = enVenta;
        this.dueno = dueno;
        this.numParadas = numParadas;
        this.numPenaltisParados = numPenaltisParados;
        this.numGolesContra = numGolesContra;
        this.valoracion = valoracion;
        this.puntosTotales = puntosTotales;
    }

    public int getPuntosTotales() {
		return puntosTotales;
	}

	public void setPuntosTotales(int puntosTotales) {
		this.puntosTotales = puntosTotales;
	}

	public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public int getNumGolesContra() {
        return numGolesContra;
    }

    public void setNumGolesContra(int numGolesContra) {
        this.numGolesContra = numGolesContra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDemarcacion() {
        return demarcacion;
    }

    public void setDemarcacion(String demarcacion) {
        this.demarcacion = demarcacion;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getClausula() {
        return clausula;
    }

    public void setClausula(int clausula) {
        this.clausula = clausula;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public int getNumID() {
        return numID;
    }

    public void setNumID(int numID) {
        this.numID = numID;
    }

    public int getNumGoles() {
        return numGoles;
    }

    public void setNumGoles(int numGoles) {
        this.numGoles = numGoles;
    }

    public int getNumAssist() {
        return numAssist;
    }

    public void setNumAssist(int numAssist) {
        this.numAssist = numAssist;
    }

    public boolean isExpulsado() {
        return expulsado;
    }

    public void setExpulsado(boolean expulsado) {
        this.expulsado = expulsado;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public boolean isEnVenta() {
        return enVenta;
    }

    public void setEnVenta(boolean enVenta) {
        this.enVenta = enVenta;
    }

    public Usuario getDueno() {
        return dueno;
    }

    public void setDueno(Usuario dueno) {
        this.dueno = dueno;
    }

    public int getNumParadas() {
        return numParadas;
    }

    public void setNumParadas(int numParadas) {
        this.numParadas = numParadas;
    }

    public int getNumPenaltisParados() {
        return numPenaltisParados;
    }

    public void setNumPenaltisParados(int numPenaltisParados) {
        this.numPenaltisParados = numPenaltisParados;
    }
}
