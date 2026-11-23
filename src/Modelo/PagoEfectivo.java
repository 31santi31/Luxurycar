package Modelo;

/**
 * Pago en efectivo con calculo de cambio.
 */
public class PagoEfectivo implements MetodoPago {

    private double montoRecibido;
    private double cambioDevuelto;

    /**
     * Crea un pago en efectivo.
     * @param montoRecibido dinero entregado por el cliente
     */
    public PagoEfectivo(double montoRecibido) {
        this.montoRecibido = montoRecibido;
        this.cambioDevuelto = 0;
    }

    /**
     * Aprueba si el dinero alcanza y calcula el cambio.
     * @param monto valor a cobrar
     * @return true si el monto recibido cubre el valor
     */
    @Override
    public boolean procesarPago(double monto) {
        if (montoRecibido < monto) {
            return false;
        }
        cambioDevuelto = montoRecibido - monto;
        return true;
    }

    /** @return "Efectivo" */
    @Override
    public String getNombreMetodo() {
        return "Efectivo";
    }

    /** @return monto recibido y cambio */
    @Override
    public String generarDetalleComprobante() {
        return "Efectivo | Recibido: " + String.format("%.2f", montoRecibido)
                + " | Cambio: " + String.format("%.2f", cambioDevuelto);
    }
}
