package Modelo;

import java.util.ArrayList;

/**
 * Punto de venta con su inventario local.
 */
public class PuntoVenta {

    private String codigoPunto;
    private String nombre;
    private String tipoUbicacion;
    private ArrayList<Producto> inventarioLocal;

    /**
     * Crea un punto de venta.
     * @param codigo codigo del punto
     * @param nombre nombre
     * @param tipo tipo de ubicacion
     */
    public PuntoVenta(String codigo, String nombre, String tipo) {
        this.codigoPunto = codigo;
        this.nombre = nombre;
        this.tipoUbicacion = tipo;
        this.inventarioLocal = new ArrayList<>();
    }

    /**
     * Agrega un producto al inventario.
     * @param p producto a agregar
     */
    public void agregarProducto(Producto p) {
        if (p != null && !inventarioLocal.contains(p)) {
            inventarioLocal.add(p);
        }
    }

    /** @return productos del punto */
    public ArrayList<Producto> getInventarioLocal() {
        return inventarioLocal;
    }

    /** @return codigo del punto */
    public String getCodigoPunto() {
        return codigoPunto;
    }

    /** @return nombre del punto */
    public String getNombre() {
        return nombre;
    }

    /** @return tipo de ubicacion */
    public String getTipoUbicacion() {
        return tipoUbicacion;
    }
}
