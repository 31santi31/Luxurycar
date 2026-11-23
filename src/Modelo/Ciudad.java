package Modelo;

import java.util.ArrayList;

/**
 * Ciudad que agrupa puntos de venta.
 */
public class Ciudad {

    private String idCiudad;
    private String nombre;
    private ArrayList<PuntoVenta> puntosVenta;

    /**
     * Crea una ciudad.
     * @param idCiudad identificador
     * @param nombre nombre de la ciudad
     */
    public Ciudad(String idCiudad, String nombre) {
        this.idCiudad = idCiudad;
        this.nombre = nombre;
        this.puntosVenta = new ArrayList<>();
    }

    /**
     * Agrega un punto de venta.
     * @param punto punto a agregar
     */
    public void agregarPuntoVenta(PuntoVenta punto) {
        if (punto != null && !puntosVenta.contains(punto)) {
            puntosVenta.add(punto);
        }
    }

    /** @return nombre de la ciudad */
    public String getNombre() {
        return nombre;
    }

    /** @return puntos de venta de la ciudad */
    public ArrayList<PuntoVenta> getPuntosVenta() {
        return puntosVenta;
    }
}
