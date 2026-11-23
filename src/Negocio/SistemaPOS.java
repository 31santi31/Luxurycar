package Negocio;

import Modelo.*;
import java.util.ArrayList;

/**
 * Logica del negocio: administra ciudades, empleados, clientes y ventas.
 */
public class SistemaPOS {

    private ArrayList<Ciudad> ciudades;
    private ArrayList<Empleado> empleados;
    private ArrayList<Cliente> clientes;
    private ArrayList<Venta> ventas;
    private Cliente clienteGeneral;

    /**
     * Crea el sistema con listas vacias y el cliente general.
     */
    public SistemaPOS() {
        this.ciudades = new ArrayList<>();
        this.empleados = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.ventas = new ArrayList<>();
        this.clienteGeneral = new Cliente("0", "Cliente General", "-", "-", "-");
    }

    /**
     * Autentica un empleado.
     * @param correo correo
     * @param pass contrasena
     * @return el empleado, o null si las credenciales no coinciden
     */
    public Empleado autenticarUsuario(String correo, String pass) {
        for (Empleado e : empleados) {
            if (e.iniciarSesion(correo, pass)) {
                return e;
            }
        }
        return null;
    }

    /**
     * Registra una ciudad.
     * @param c ciudad
     */
    public void registrarCiudad(Ciudad c) {
        if (c != null && !ciudades.contains(c)) {
            ciudades.add(c);
        }
    }

    /**
     * Registra un empleado si su documento no existe.
     * @param e empleado
     */
    public void registrarEmpleado(Empleado e) {
        if (e != null && buscarEmpleadoPorDocumento(e.getDocumento()) == null) {
            empleados.add(e);
        }
    }

    /**
     * Busca un empleado por documento.
     * @param documento documento
     * @return el empleado, o null si no existe
     */
    public Empleado buscarEmpleadoPorDocumento(String documento) {
        for (Empleado e : empleados) {
            if (e.getDocumento().equals(documento)) {
                return e;
            }
        }
        return null;
    }

    /**
     * Busca un punto de venta en todas las ciudades.
     * @param codigo codigo del punto
     * @return el punto, o null si no existe
     */
    public PuntoVenta buscarPuntoVenta(String codigo) {
        for (Ciudad c : ciudades) {
            for (PuntoVenta p : c.getPuntosVenta()) {
                if (p.getCodigoPunto().equals(codigo)) {
                    return p;
                }
            }
        }
        return null;
    }

    /**
     * Registra un producto en un punto si el codigo no existe alli.
     * @param punto punto de venta
     * @param producto producto a registrar
     * @return true si se registro
     */
    public boolean registrarProducto(PuntoVenta punto, Producto producto) {
        if (punto == null || producto == null
                || buscarProductoPorCodigo(punto, producto.getCodigo()) != null) {
            return false;
        }
        punto.agregarProducto(producto);
        return true;
    }

    /**
     * Busca un producto en el inventario de un punto.
     * @param punto punto de venta
     * @param codigo codigo del producto
     * @return el producto, o null si no existe
     */
    public Producto buscarProductoPorCodigo(PuntoVenta punto, String codigo) {
        if (punto == null) {
            return null;
        }
        for (Producto p : punto.getInventarioLocal()) {
            if (p.getCodigo().equals(codigo)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Registra un cliente si su documento no existe.
     * @param c cliente
     * @return true si se registro
     */
    public boolean registrarCliente(Cliente c) {
        if (c == null || buscarClientePorDocumento(c.getDocumento()) != null) {
            return false;
        }
        clientes.add(c);
        return true;
    }

    /**
     * Busca un cliente por documento.
     * @param documento documento
     * @return el cliente, o null si no existe
     */
    public Cliente buscarClientePorDocumento(String documento) {
        for (Cliente c : clientes) {
            if (c.getDocumento().equals(documento)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Cobra la venta; si se aprueba, descuenta las existencias y la guarda.
     * @param venta venta a registrar
     * @return true si el pago fue aprobado y la venta quedo registrada
     */
    public boolean registrarVenta(Venta venta) {
        if (venta == null || venta.getItems().isEmpty()) {
            return false;
        }
        if (!venta.getMetodoPago().procesarPago(venta.calcularTotal())) {
            return false;
        }
        for (ItemVenta item : venta.getItems()) {
            item.getProducto().descontarExistencias(item.getCantidad());
        }
        venta.getTrabajador().acumularGanancia(venta.calcularPagoTrabajador());
        ventas.add(venta);
        return true;
    }

    /**
     * Productos de un punto con stock bajo.
     * @param punto punto de venta
     * @return lista de productos con stock bajo
     */
    public ArrayList<Producto> listarProductosStockBajo(PuntoVenta punto) {
        ArrayList<Producto> bajos = new ArrayList<>();
        for (Producto p : punto.getInventarioLocal()) {
            if (p.tieneStockBajo()) {
                bajos.add(p);
            }
        }
        return bajos;
    }

    /**
     * Total vendido (con IVA) en todas las ventas.
     * @return total vendido
     */
    public double calcularTotalVendidoNacional() {
        double total = 0;
        for (Venta v : ventas) {
            total += v.calcularTotal();
        }
        return total;
    }

    /**
     * Suma las comisiones de un trabajador.
     * @param trabajador trabajador
     * @return total de comisiones
     */
    public double calcularPagoTrabajador(Trabajador trabajador) {
        double total = 0;
        for (Venta v : ventas) {
            if (v.getTrabajador().getDocumento().equals(trabajador.getDocumento())) {
                total += v.calcularPagoTrabajador();
            }
        }
        return total;
    }

    /** @return ciudades */
    public ArrayList<Ciudad> getCiudades() {
        return ciudades;
    }

    /** @return clientes registrados */
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    /** @return ventas realizadas */
    public ArrayList<Venta> getVentas() {
        return ventas;
    }

    /** @return cliente para ventas sin cliente registrado */
    public Cliente getClienteGeneral() {
        return clienteGeneral;
    }
}
