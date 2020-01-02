package jugadores;

import jugadores.*;
import usuariosAdmins.Usuario;

/** Define la alineacion de cada usuario
 *
 */
public class Alineacion
{
    private String dibujo;

    private Portero portero;
    private Defensa defensa1;
    private Defensa defensa2;
    private Defensa defensa3;
    private Defensa defensa4;
    private Defensa defensa5;
    private Mediocentro mediocentro1;
    private Mediocentro mediocentro2;
    private Mediocentro mediocentro3;
    private Mediocentro mediocentro4;
    private Mediocentro mediocentro5;
    private Delantero delantero1;
    private Delantero delantero2;
    private Delantero delantero3;

    private Usuario entrenador;

    /** Define la alineacion del usuario
     *
     * @param dibujo String que define la formacion del equipo
     * @param portero Portero del equipo, del tipo Portero
     * @param defensa1 Primer defensa del equipo, del tipo Defensa
     * @param defensa2 Segundo defensa del equipo, del tipo Defensa
     * @param defensa3 Tercer defensa del equipo, del tipo Defensa
     * @param defensa4 Cuarto defensa del equipo, del tipo Defensa
     * @param defensa5 Quinto defensa del equipo, del tipo Defensa
     * @param mediocentro1 Primer mediocentro del equipo, del tipo Mediocentro
     * @param mediocentro2 Segundo mediocentro del equipo, del tipo Mediocentro
     * @param mediocentro3 Tercer mediocentro del equipo, del tipo Mediocentro
     * @param mediocentro4 Cuarto mediocentro del equipo, del tipo Mediocentro
     * @param mediocentro5 Quinto mediocentro del equipo, del tipo Mediocentro
     * @param delantero1 Primer delantero del equipo, del tipo Delantero
     * @param delantero2 Segundo delantero del equipo, del tipo Delantero
     * @param delantero3 Tercer delantero del equipo, del tipo Delantero
     * @param entrenador Entrenador del equipo, del tipo Usuario
     */
    public Alineacion(String dibujo, Portero portero, Defensa defensa1, Defensa defensa2, Defensa defensa3, Defensa defensa4, Defensa defensa5, Mediocentro mediocentro1, Mediocentro mediocentro2, Mediocentro mediocentro3, Mediocentro mediocentro4, Mediocentro mediocentro5, Delantero delantero1, Delantero delantero2, Delantero delantero3, Usuario entrenador)
    {
        this.dibujo = dibujo;
        this.portero = portero;
        this.defensa1 = defensa1;
        this.defensa2 = defensa2;
        this.defensa3 = defensa3;
        this.defensa4 = defensa4;
        this.defensa5 = defensa5;
        this.mediocentro1 = mediocentro1;
        this.mediocentro2 = mediocentro2;
        this.mediocentro3 = mediocentro3;
        this.mediocentro4 = mediocentro4;
        this.mediocentro5 = mediocentro5;
        this.delantero1 = delantero1;
        this.delantero2 = delantero2;
        this.delantero3 = delantero3;
        this.entrenador = entrenador;
    }

    /** Constructor vacio de alineacion
     *
     */
    public Alineacion()
    { }

    public String getDibujo() {
        return dibujo;
    }

    public void setDibujo(String dibujo) {
        this.dibujo = dibujo;
    }

    public Portero getPortero() {
        return portero;
    }

    public void setPortero(Portero portero) {
        this.portero = portero;
    }

    public Defensa getDefensa1() {
        return defensa1;
    }

    public void setDefensa1(Defensa defensa1) {
        this.defensa1 = defensa1;
    }

    public Defensa getDefensa2() {
        return defensa2;
    }

    public void setDefensa2(Defensa defensa2) {
        this.defensa2 = defensa2;
    }

    public Defensa getDefensa3() {
        return defensa3;
    }

    public void setDefensa3(Defensa defensa3) {
        this.defensa3 = defensa3;
    }

    public Defensa getDefensa4() {
        return defensa4;
    }

    public void setDefensa4(Defensa defensa4) {
        this.defensa4 = defensa4;
    }

    public Defensa getDefensa5() {
        return defensa5;
    }

    public void setDefensa5(Defensa defensa5) {
        this.defensa5 = defensa5;
    }

    public Mediocentro getMediocentro1() {
        return mediocentro1;
    }

    public void setMediocentro1(Mediocentro mediocentro1) {
        this.mediocentro1 = mediocentro1;
    }

    public Mediocentro getMediocentro2() {
        return mediocentro2;
    }

    public void setMediocentro2(Mediocentro mediocentro2) {
        this.mediocentro2 = mediocentro2;
    }

    public Mediocentro getMediocentro3() {
        return mediocentro3;
    }

    public void setMediocentro3(Mediocentro mediocentro3) {
        this.mediocentro3 = mediocentro3;
    }

    public Mediocentro getMediocentro4() {
        return mediocentro4;
    }

    public void setMediocentro4(Mediocentro mediocentro4) {
        this.mediocentro4 = mediocentro4;
    }

    public Mediocentro getMediocentro5() {
        return mediocentro5;
    }

    public void setMediocentro5(Mediocentro mediocentro5) {
        this.mediocentro5 = mediocentro5;
    }

    public Delantero getDelantero1() {
        return delantero1;
    }

    public void setDelantero1(Delantero delantero1) {
        this.delantero1 = delantero1;
    }

    public Delantero getDelantero2() {
        return delantero2;
    }

    public void setDelantero2(Delantero delantero2) {
        this.delantero2 = delantero2;
    }

    public Delantero getDelantero3() {
        return delantero3;
    }

    public void setDelantero3(Delantero delantero3) {
        this.delantero3 = delantero3;
    }

    public Usuario getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Usuario entrenador) {
        this.entrenador = entrenador;
    }

    @Override
    public String toString()
    {
        return "Este es tu 11 titular:\n" +
                "- Dibujo = " + dibujo +
                "\n\n\tportero = " + portero +
                "\n\n\tdefensa1 = " + defensa1 +
                "\n\tdefensa2 = " + defensa2 +
                "\n\tdefensa3 = " + defensa3 +
                "\n\tdefensa4 = " + defensa4 +
                "\n\tdefensa5 = " + defensa5 +
                "\n\n\tmediocentro1 = " + mediocentro1 +
                "\n\tmediocentro2 = " + mediocentro2 +
                "\n\tmediocentro3 = " + mediocentro3 +
                "\n\tmediocentro4 = " + mediocentro4 +
                "\n\tmediocentro5 = " + mediocentro5 +
                "\n\n\tdelantero1 = " + delantero1 +
                "\n\tdelantero2 = " + delantero2 +
                "\n\tdelantero3 = " + delantero3 +
                "\n\n- Entrenador = " + entrenador.getUser();
    }
}
