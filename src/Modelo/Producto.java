package Modelo;

/**
 * Superclase abstracta de los productos.
 */
public abstract class Producto {

    private String codigo;
    private String nombre;
    private double precioBase;
    private int existencias;
    private int stockMinimo;

    /**
     * Crea un producto.
     * @param cod codigo
     * @param nom nombre
     * @param precio precio base (no negativo)
     * @param exist existencias (no negativas)
     * @param min stock minimo (no negativo)
     */
    public Producto(String cod, String nom, double precio, int exist, int min) {
        this.codigo = cod;
        this.nombre = nom;
        setPrecioBase(precio);
        setExistencias(exist);
        if (min < 0) {
            throw new IllegalArgumentException("El stock minimo no puede ser negativo.");
        }
        this.stockMinimo = min;
    }

    /**
     * Calcula el precio final por unidad; cada tipo lo calcula distinto.
     * @return precio de venta
     */
    public abstract double calcularPrecioVenta();

    /**
     * Descuenta existencias si alcanzan.
     * @param cantidad unidades a descontar
     * @return true si se descontaron
     */
    public boolean descontarExistencias(int cantidad) {
        if (cantidad > 0 && cantidad <= existencias) {
            existencias -= cantidad;
            return true;
        }
        return false;
    }

    /**
     * Indica si las existencias estan por debajo del minimo.
     * @return true si hay stock bajo
     */
    public boolean tieneStockBajo() {
        return existencias < stockMinimo;
    }

    /**
     * Cambia el precio base.
     * @param precio nuevo precio, no negativo
     * @throws IllegalArgumentException si es negativo
     */
    public void setPrecioBase(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        this.precioBase = precio;
    }

    /**
     * Cambia las existencias.
     * @param exist nuevas existencias, no negativas
     * @throws IllegalArgumentException si es negativo
     */
    public void setExistencias(int exist) {
        if (exist < 0) {
            throw new IllegalArgumentException("Las existencias no pueden ser negativas.");
        }
        this.existencias = exist;
    }

    /** @return codigo */
    public String getCodigo() {
        return codigo;
    }

    /** @return nombre */
    public String getNombre() {
        return nombre;
    }

    /** @return existencias */
    public int getExistencias() {
        return existencias;
    }

    /** @return precio base */
    public double getPrecioBase() {
        return precioBase;
    }

    /**
     * Descripcion del producto.
     * @return texto en una linea
     */
    @Override
    public String toString() {
        return codigo + " | " + nombre + " | " + getClass().getSimpleName()
                + " | precio venta: " + String.format("%.2f", calcularPrecioVenta())
                + " | existencias: " + existencias;
    }
}
