package Modelo;

/**
 * Linea de una venta.
 */
public class ItemVenta {

    private Producto producto;
    private int cantidad;
    private double precioUnitarioAplicado;

    /**
     * Crea un item y guarda el precio vigente.
     * @param producto producto vendido
     * @param cantidad unidades
     */
    public ItemVenta(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitarioAplicado = producto.calcularPrecioVenta();
    }

    /**
     * Calcula el subtotal del item.
     * @return precio por cantidad
     */
    public double calcularSubtotal() {
        return precioUnitarioAplicado * cantidad;
    }

    /** @return producto */
    public Producto getProducto() {
        return producto;
    }

    /** @return cantidad */
    public int getCantidad() {
        return cantidad;
    }
}
