package Modelo;

import java.util.ArrayList;

/**
 * Trabajador que atiende ventas en uno o varios puntos.
 */
public class Trabajador extends Empleado {

    private Ciudad ciudadAsignada;
    private ArrayList<PuntoVenta> puntosAutorizados;
    private boolean rotacionAutorizadaPorJefe;
    private String diaDescanso;
    private String jornadaAcordada;
    private boolean enDescansoHoy;
    private boolean puedeVerGananciasDia;
    private boolean puedeVerGananciasSemana;
    private boolean puedeVerPromocionesVendidas;
    private double gananciasAcumuladas;

    /**
     * Crea un trabajador.
     * @param doc documento
     * @param nom nombre
     * @param tel telefono
     * @param correo correo corporativo
     * @param pass contrasena
     * @param ciudad ciudad asignada
     */
    public Trabajador(String doc, String nom, String tel, String correo, String pass, Ciudad ciudad) {
        super(doc, nom, tel, correo, pass, "TRABAJADOR");
        this.ciudadAsignada = ciudad;
        this.puntosAutorizados = new ArrayList<>();
        this.rotacionAutorizadaPorJefe = false;
        this.enDescansoHoy = false;
        this.puedeVerGananciasDia = false;
        this.puedeVerGananciasSemana = false;
        this.puedeVerPromocionesVendidas = false;
        this.gananciasAcumuladas = 0;
    }

    /**
     * Autoriza un punto de venta.
     * @param punto punto a autorizar
     * @return true si se agrego, false si es null o ya estaba
     */
    public boolean agregarPuntoAutorizado(PuntoVenta punto) {
        if (punto == null || puntosAutorizados.contains(punto)) {
            return false;
        }
        puntosAutorizados.add(punto);
        return true;
    }

    /**
     * Verifica si puede operar en un punto.
     * @param codigoPunto codigo del punto
     * @return true si esta autorizado
     */
    public boolean puedeOperarEnPunto(String codigoPunto) {
        for (PuntoVenta p : puntosAutorizados) {
            if (p.getCodigoPunto().equals(codigoPunto)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Configura los permisos de visualizacion.
     * @param dia ver ganancias del dia
     * @param semana ver ganancias de la semana
     * @param promos ver promociones vendidas
     */
    public void configurarPermisosVista(boolean dia, boolean semana, boolean promos) {
        this.puedeVerGananciasDia = dia;
        this.puedeVerGananciasSemana = semana;
        this.puedeVerPromocionesVendidas = promos;
    }

    /**
     * Suma una comision a las ganancias del trabajador.
     * @param valor comision de una venta (no negativa)
     */
    public void acumularGanancia(double valor) {
        if (valor > 0) {
            gananciasAcumuladas += valor;
        }
    }

    /**
     * Consulta las ganancias acumuladas.
     * @param periodo periodo consultado (informativo)
     * @return ganancias acumuladas
     */
    public double consultarMisGanancias(String periodo) {
        return gananciasAcumuladas;
    }

    /** @return ciudad asignada */
    public Ciudad getCiudadAsignada() {
        return ciudadAsignada;
    }

    /** @return puntos autorizados */
    public ArrayList<PuntoVenta> getPuntosAutorizados() {
        return puntosAutorizados;
    }

    /** @return true si el jefe autorizo la rotacion */
    public boolean isRotacionAutorizadaPorJefe() {
        return rotacionAutorizadaPorJefe;
    }

    /**
     * Marca la rotacion autorizada.
     * @param autorizada valor nuevo
     */
    public void setRotacionAutorizadaPorJefe(boolean autorizada) {
        this.rotacionAutorizadaPorJefe = autorizada;
    }

    /** @return dia de descanso */
    public String getDiaDescanso() {
        return diaDescanso;
    }

    /**
     * Define el dia de descanso.
     * @param diaDescanso dia
     */
    public void setDiaDescanso(String diaDescanso) {
        this.diaDescanso = diaDescanso;
    }

    /** @return jornada acordada */
    public String getJornadaAcordada() {
        return jornadaAcordada;
    }

    /**
     * Define la jornada.
     * @param jornadaAcordada jornada
     */
    public void setJornadaAcordada(String jornadaAcordada) {
        this.jornadaAcordada = jornadaAcordada;
    }

    /** @return true si hoy descansa */
    public boolean isEnDescansoHoy() {
        return enDescansoHoy;
    }

    /**
     * Marca si descansa hoy.
     * @param enDescansoHoy valor nuevo
     */
    public void setEnDescansoHoy(boolean enDescansoHoy) {
        this.enDescansoHoy = enDescansoHoy;
    }

    /** @return true si puede ver ganancias del dia */
    public boolean isPuedeVerGananciasDia() {
        return puedeVerGananciasDia;
    }

    /** @return true si puede ver ganancias de la semana */
    public boolean isPuedeVerGananciasSemana() {
        return puedeVerGananciasSemana;
    }

    /** @return true si puede ver promociones vendidas */
    public boolean isPuedeVerPromocionesVendidas() {
        return puedeVerPromocionesVendidas;
    }
}
