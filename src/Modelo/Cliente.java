package Modelo;

/**
 * Cliente del negocio.
 */
public class Cliente {

    private String documento;
    private String nombre;
    private String correo;
    private String direccionEnvio;
    private String placaVehiculo;

    /**
     * Crea un cliente.
     * @param doc documento
     * @param nom nombre
     * @param correo correo
     * @param dir direccion de envio
     * @param placa placa del vehiculo
     */
    public Cliente(String doc, String nom, String correo, String dir, String placa) {
        this.documento = doc;
        this.nombre = nom;
        this.correo = correo;
        this.direccionEnvio = dir;
        this.placaVehiculo = placa;
    }

    /** @return documento */
    public String getDocumento() {
        return documento;
    }

    /** @return nombre */
    public String getNombre() {
        return nombre;
    }

    /** @return correo */
    public String getCorreo() {
        return correo;
    }

    /** @return placa del vehiculo */
    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    /**
     * Descripcion del cliente.
     * @return texto en una linea
     */
    @Override
    public String toString() {
        return documento + " | " + nombre + " | " + correo + " | placa: " + placaVehiculo;
    }
}
