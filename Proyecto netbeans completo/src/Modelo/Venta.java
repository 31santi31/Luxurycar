package Modelo;

import java.util.ArrayList;

/**
 * Venta compuesta por items.
 */
public class Venta {

    private static int contadorRecibos = 0;
    private static final double IVA = 0.19;
    private static final double PORCENTAJE_COMISION = 0.50;

    private int numeroRecibo;
    private String canalVenta;
    private PuntoVenta puntoVenta;
    private Trabajador trabajador;
    private Cliente cliente;
    private ArrayList<ItemVenta> items;
    private MetodoPago metodoPago;

    /**
     * Crea una venta y le asigna el siguiente numero de recibo.
     * @param canal canal de venta
     * @param punto punto de venta
     * @param trab trabajador que atiende
     * @param cli cliente (registrado o general)
     * @param pago medio de pago
     */
    public Venta(String canal, PuntoVenta punto, Trabajador trab, Cliente cli, MetodoPago pago) {
        contadorRecibos++;
        this.numeroRecibo = contadorRecibos;
        this.canalVenta = canal;
        this.puntoVenta = punto;
        this.trabajador = trab;
        this.cliente = cli;
        this.metodoPago = pago;
        this.items = new ArrayList<>();
    }

    /**
     * Valor del IVA.
     * @return IVA como decimal
     */
    public static double getIva() {
        return IVA;
    }

    /**
     * Agrega un item si hay existencias suficientes (las existencias se descuentan al registrar la venta).
     * @param producto producto
     * @param cantidad unidades
     * @return true si se agrego, false si la cantidad es invalida o supera las existencias
     */
    public boolean agregarItem(Producto producto, int cantidad) {
        if (producto == null || cantidad <= 0) {
            return false;
        }
        int yaEnVenta = 0;
        for (ItemVenta item : items) {
            if (item.getProducto() == producto) {
                yaEnVenta += item.getCantidad();
            }
        }
        if (yaEnVenta + cantidad > producto.getExistencias()) {
            return false;
        }
        items.add(new ItemVenta(producto, cantidad));
        return true;
    }

    /**
     * Suma los subtotales.
     * @return subtotal sin IVA
     */
    public double calcularSubtotal() {
        double subtotal = 0;
        for (ItemVenta item : items) {
            subtotal += item.calcularSubtotal();
        }
        return subtotal;
    }

    /**
     * Calcula el IVA.
     * @return valor del IVA
     */
    public double calcularIVA() {
        return calcularSubtotal() * IVA;
    }

    /**
     * Calcula el total.
     * @return subtotal mas IVA
     */
    public double calcularTotal() {
        return calcularSubtotal() + calcularIVA();
    }

    /**
     * Comision del trabajador sobre el subtotal.
     * @return pago al trabajador
     */
    public double calcularPagoTrabajador() {
        return calcularSubtotal() * PORCENTAJE_COMISION;
    }

    /**
     * Ganancia de la empresa.
     * @return subtotal menos comision
     */
    public double calcularGananciaEmpresa() {
        return calcularSubtotal() - calcularPagoTrabajador();
    }

    /**
     * Arma el comprobante.
     * @return texto del recibo
     */
    public String generarComprobante() {
        StringBuilder sb = new StringBuilder();
        sb.append("========== RECIBO No. ").append(numeroRecibo).append(" ==========\n");
        sb.append("Canal: ").append(canalVenta).append("\n");
        sb.append("Punto de venta: ").append(puntoVenta.getNombre()).append("\n");
        sb.append("Atendido por: ").append(trabajador.getNombre()).append("\n");
        sb.append("Cliente: ").append(cliente.getNombre()).append("\n");
        sb.append("------------------------------------\n");
        for (ItemVenta item : items) {
            sb.append(item.getProducto().getNombre()).append(" x").append(item.getCantidad())
              .append(" = ").append(String.format("%.2f", item.calcularSubtotal())).append("\n");
        }
        sb.append("------------------------------------\n");
        sb.append("Subtotal: ").append(String.format("%.2f", calcularSubtotal())).append("\n");
        sb.append("IVA (19%): ").append(String.format("%.2f", calcularIVA())).append("\n");
        sb.append("TOTAL: ").append(String.format("%.2f", calcularTotal())).append("\n");
        sb.append("Pago: ").append(metodoPago.generarDetalleComprobante()).append("\n");
        return sb.toString();
    }

    /** @return medio de pago */
    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    /** @return trabajador que atendio */
    public Trabajador getTrabajador() {
        return trabajador;
    }

    /** @return cliente */
    public Cliente getCliente() {
        return cliente;
    }

    /** @return numero de recibo */
    public int getNumeroRecibo() {
        return numeroRecibo;
    }

    /** @return items de la venta */
    public ArrayList<ItemVenta> getItems() {
        return items;
    }
}
