package Modelo;

/**
 * Producto cuyo precio varia segun el punto de venta.
 */
public class ProductoPunto extends Producto {

    private double porcentajeVariacionPunto;

    /**
     * Crea un producto con variacion por punto.
     * @param cod codigo
     * @param nom nombre
     * @param precio precio base
     * @param exist existencias
     * @param min stock minimo
     * @param variacion porcentaje de variacion (puede ser negativo)
     */
    public ProductoPunto(String cod, String nom, double precio, int exist, int min, double variacion) {
        super(cod, nom, precio, exist, min);
        this.porcentajeVariacionPunto = variacion;
    }

    /**
     * Precio base mas la variacion.
     * @return precio de venta por unidad
     */
    @Override
    public double calcularPrecioVenta() {
        return getPrecioBase() * (1 + porcentajeVariacionPunto / 100);
    }

    /** @return porcentaje de variacion */
    public double getPorcentajeVariacionPunto() {
        return porcentajeVariacionPunto;
    }
}
