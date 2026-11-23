package Modelo;

import java.util.ArrayList;

/**
 * Administrador de uno o varios puntos de venta.
 */
public class Administrador extends Empleado {

    private ArrayList<PuntoVenta> puntosAsignados;

    /**
     * Crea un administrador.
     * @param doc documento
     * @param nom nombre
     * @param tel telefono
     * @param correo correo corporativo
     * @param pass contrasena
     */
    public Administrador(String doc, String nom, String tel, String correo, String pass) {
        super(doc, nom, tel, correo, pass, "ADMINISTRADOR");
        this.puntosAsignados = new ArrayList<>();
    }

    /**
     * Asigna un punto de venta.
     * @param punto punto a asignar
     */
    public void asignarPuntoVenta(PuntoVenta punto) {
        if (punto != null && !puntosAsignados.contains(punto)) {
            puntosAsignados.add(punto);
        }
    }

    /**
     * Verifica si administra un punto.
     * @param codigoPunto codigo del punto
     * @return true si tiene acceso
     */
    public boolean tieneAccesoAPunto(String codigoPunto) {
        for (PuntoVenta p : puntosAsignados) {
            if (p.getCodigoPunto().equals(codigoPunto)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Configura los permisos de vista de un trabajador.
     * @param trab trabajador
     * @param dia ver ganancias del dia
     * @param sem ver ganancias de la semana
     * @param prom ver promociones vendidas
     */
    public void gestionarPermisosTrabajador(Trabajador trab, boolean dia, boolean sem, boolean prom) {
        trab.configurarPermisosVista(dia, sem, prom);
    }
}
