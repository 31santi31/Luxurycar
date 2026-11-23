package Modelo;

/**
 * Medio de pago aceptado por el POS.
 */
public interface MetodoPago {

    /**
     * Procesa el pago.
     * @param monto valor a cobrar
     * @return true si el pago fue aprobado
     */
    boolean procesarPago(double monto);

    /**
     * Nombre del medio de pago.
     * @return nombre
     */
    String getNombreMetodo();

    /**
     * Detalle del pago para el comprobante.
     * @return texto con el detalle
     */
    String generarDetalleComprobante();
}
