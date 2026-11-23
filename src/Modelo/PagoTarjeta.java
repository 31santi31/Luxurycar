package Modelo;

/**
 * Pago con tarjeta.
 */
public class PagoTarjeta implements MetodoPago {

    private String franquicia;
    private String ultimosCuatroDigitos;

    /**
     * Crea un pago con tarjeta.
     * @param franquicia franquicia de la tarjeta
     * @param ultimosCuatroDigitos ultimos 4 digitos
     */
    public PagoTarjeta(String franquicia, String ultimosCuatroDigitos) {
        this.franquicia = franquicia;
        this.ultimosCuatroDigitos = ultimosCuatroDigitos;
    }

    /**
     * Aprueba si el monto es positivo y los ultimos digitos son 4 numeros.
     * @param monto valor a cobrar
     * @return true si es aprobado
     */
    @Override
    public boolean procesarPago(double monto) {
        return monto > 0 && ultimosCuatroDigitos != null && ultimosCuatroDigitos.matches("\\d{4}");
    }

    /** @return "Tarjeta" */
    @Override
    public String getNombreMetodo() {
        return "Tarjeta";
    }

    /** @return franquicia y ultimos digitos */
    @Override
    public String generarDetalleComprobante() {
        return "Tarjeta | " + franquicia + " terminada en " + ultimosCuatroDigitos;
    }
}
