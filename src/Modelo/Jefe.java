package Modelo;

import java.util.ArrayList;

/**
 * Jefe del negocio, con acceso total.
 */
public class Jefe extends Empleado {

    private String nivelAcceso = "TOTAL";

    /**
     * Crea un jefe.
     * @param doc documento
     * @param nom nombre
     * @param tel telefono
     * @param correo correo corporativo
     * @param pass contrasena
     */
    public Jefe(String doc, String nom, String tel, String correo, String pass) {
        super(doc, nom, tel, correo, pass, "JEFE");
    }

    /**
     * Cambia correo y contrasena de un empleado.
     * @param emp empleado a modificar
     * @param correo nuevo correo
     * @param pass nueva contrasena
     */
    public void crearCuentaEmpleado(Empleado emp, String correo, String pass) {
        emp.setCorreoCorporativo(correo);
        emp.setPassword(pass);
    }

    /**
     * Autoriza la rotacion de un trabajador y le asigna puntos.
     * @param trab trabajador
     * @param puntosCiudad puntos de venta autorizados
     */
    public void autorizarRotacionTrabajador(Trabajador trab, ArrayList<PuntoVenta> puntosCiudad) {
        trab.setRotacionAutorizadaPorJefe(true);
        for (PuntoVenta p : puntosCiudad) {
            trab.agregarPuntoAutorizado(p);
        }
    }

    /**
     * Resume el numero de ventas y el total vendido.
     * @param ventas ventas realizadas
     * @return texto con el consolidado
     */
    public String consultarConsolidadoNacional(ArrayList<Venta> ventas) {
        double total = 0;
        for (Venta v : ventas) {
            total += v.calcularTotal();
        }
        return "Ventas: " + ventas.size() + " | Total vendido: " + String.format("%.2f", total);
    }

    /** @return nivel de acceso del jefe */
    public String getNivelAcceso() {
        return nivelAcceso;
    }
}
