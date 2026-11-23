package Modelo;

/**
 * Producto con descuento porcentual.
 */
public class ProductoPromocion extends Producto {

    private double porcentajeDescuento;
    private String nombrePromocion;

    /**
     * Crea un producto en promocion.
     * @param cod codigo
     * @param nom nombre
     * @param precio precio base
     * @param exist existencias
     * @param min stock minimo
     * @param desc descuento entre 0 y 100
     * @param promo nombre de la promocion
     */
    public ProductoPromocion(String cod, String nom, double precio, int exist, int min,
                             double desc, String promo) {
        super(cod, nom, precio, exist, min);
        if (desc < 0 || desc > 100) {
            throw new IllegalArgumentException("El descuento debe estar entre 0 y 100.");
        }
        this.porcentajeDescuento = desc;
        this.nombrePromocion = promo;
    }

    /**
     * Precio base menos el descuento.
     * @return precio de venta por unidad
     */
    @Override
    public double calcularPrecioVenta() {
        return getPrecioBase() * (1 - porcentajeDescuento / 100);
    }

    /** @return nombre de la promocion */
    public String getNombrePromocion() {
        return nombrePromocion;
    }
}
