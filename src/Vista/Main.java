package Vista;

import Modelo.*;
import Negocio.SistemaPOS;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        SistemaPOS pos = new SistemaPOS();
        cargarDatosIniciales(pos);
        int op;
        do {
            System.out.println("\n===== LUXURYCAR POS =====");
            System.out.println("1. Listar productos de un punto");
            System.out.println("2. Registrar producto");
            System.out.println("3. Actualizar precio y existencias");
            System.out.println("4. Productos con stock bajo");
            System.out.println("5. Listar clientes");
            System.out.println("6. Registrar cliente");
            System.out.println("7. Buscar cliente");
            System.out.println("8. Nueva venta");
            System.out.println("9. Reporte de ventas");
            System.out.println("10. Mis ganancias (trabajador)");
            System.out.println("11. Consolidado nacional (jefe)");
            System.out.println("0. Salir");
            op = leerInt("Opcion: ");
            switch (op) {
                case 1:
                    listarProductos(pos);
                    break;
                case 2:
                    registrarProducto(pos);
                    break;
                case 3:
                    actualizarProducto(pos);
                    break;
                case 4:
                    stockBajo(pos);
                    break;
                case 5:
                    listarClientes(pos);
                    break;
                case 6:
                    registrarCliente(pos);
                    break;
                case 7:
                    buscarCliente(pos);
                    break;
                case 8:
                    nuevaVenta(pos);
                    break;
                case 9:
                    reporteVentas(pos);
                    break;
                case 10:
                    misGanancias(pos);
                    break;
                case 11:
                    consolidado(pos);
                    break;
                case 0:
                    System.out.println("Hasta luego.");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (op != 0);
    }

    private static void cargarDatosIniciales(SistemaPOS pos) {
        Ciudad manizales = new Ciudad("C1", "Manizales");
        PuntoVenta centro = new PuntoVenta("P1", "Sede Centro", "Centro");
        centro.agregarProducto(new ProductoPunto("A001", "Cera para auto", 20000, 10, 3, 5));
        centro.agregarProducto(new ProductoPunto("A002", "Limpia vidrios", 8000, 15, 3, 0));
        centro.agregarProducto(new ProductoPunto("A003", "Esponja", 3000, 4, 5, -10));
        centro.agregarProducto(new ProductoPromocion("P001", "Shampoo automotriz", 15000, 8, 2, 20, "Promo mes"));
        centro.agregarProducto(new ProductoPromocion("P002", "Ambientador", 6000, 20, 3, 10, "Promo aroma"));
        manizales.agregarPuntoVenta(centro);
        pos.registrarCiudad(manizales);

        Jefe jefe = new Jefe("1", "Jefe Demo", "3000000000", "jefe@luxury.com", "1234");
        Administrador admin = new Administrador("2", "Admin Demo", "3000000001", "admin@luxury.com", "1234");
        admin.asignarPuntoVenta(centro);
        Trabajador trab = new Trabajador("3", "Trabajador Demo", "3000000002", "trab@luxury.com", "1234", manizales);
        trab.agregarPuntoAutorizado(centro);
        pos.registrarEmpleado(jefe);
        pos.registrarEmpleado(admin);
        pos.registrarEmpleado(trab);

        pos.registrarCliente(new Cliente("100", "Ana Gomez", "ana@mail.com", "Calle 1", "ABC123"));
        pos.registrarCliente(new Cliente("200", "Carlos Ruiz", "carlos@mail.com", "Calle 2", "XYZ789"));
    }

    private static PuntoVenta pedirPunto(SistemaPOS pos) {
        for (Ciudad c : pos.getCiudades()) {
            for (PuntoVenta p : c.getPuntosVenta()) {
                System.out.println("  " + p.getCodigoPunto() + " - " + p.getNombre() + " (" + c.getNombre() + ")");
            }
        }
        PuntoVenta punto = pos.buscarPuntoVenta(leer("Codigo del punto de venta: "));
        if (punto == null) {
            System.out.println("Punto de venta no encontrado.");
        }
        return punto;
    }

    private static void listarProductos(SistemaPOS pos) {
        PuntoVenta punto = pedirPunto(pos);
        if (punto == null) {
            return;
        }
        for (Producto p : punto.getInventarioLocal()) {
            System.out.println(p);
        }
    }

    private static void registrarProducto(SistemaPOS pos) {
        PuntoVenta punto = pedirPunto(pos);
        if (punto == null) {
            return;
        }
        System.out.println("Tipo: 1. Producto por punto  2. Producto en promocion");
        int tipo = leerInt("Opcion: ");
        if (tipo != 1 && tipo != 2) {
            System.out.println("Tipo no valido.");
            return;
        }
        String cod = leer("Codigo: ");
        String nom = leer("Nombre: ");
        try {
            double precio = leerDouble("Precio base: ");
            int exist = leerInt("Existencias: ");
            int min = leerInt("Stock minimo: ");
            Producto p;
            if (tipo == 1) {
                p = new ProductoPunto(cod, nom, precio, exist, min, leerDouble("Variacion % (puede ser negativa): "));
            } else {
                p = new ProductoPromocion(cod, nom, precio, exist, min,
                        leerDouble("Descuento % (0-100): "), leer("Nombre de la promocion: "));
            }
            System.out.println(pos.registrarProducto(punto, p) ? "Producto registrado." : "Ya existe ese codigo.");
        } catch (IllegalArgumentException e) {
            System.out.println("Dato invalido: " + e.getMessage());
        }
    }

    private static void actualizarProducto(SistemaPOS pos) {
        PuntoVenta punto = pedirPunto(pos);
        if (punto == null) {
            return;
        }
        Producto p = pos.buscarProductoPorCodigo(punto, leer("Codigo del producto: "));
        if (p == null) {
            System.out.println("Producto no encontrado.");
            return;
        }
        System.out.println(p);
        try {
            p.setPrecioBase(leerDouble("Nuevo precio base: "));
            p.setExistencias(leerInt("Nuevas existencias: "));
            System.out.println("Producto actualizado.");
        } catch (IllegalArgumentException e) {
            System.out.println("Dato invalido: " + e.getMessage());
        }
    }

    private static void stockBajo(SistemaPOS pos) {
        PuntoVenta punto = pedirPunto(pos);
        if (punto == null) {
            return;
        }
        if (pos.listarProductosStockBajo(punto).isEmpty()) {
            System.out.println("No hay productos con stock bajo.");
        }
        for (Producto p : pos.listarProductosStockBajo(punto)) {
            System.out.println(p);
        }
    }

    private static void listarClientes(SistemaPOS pos) {
        for (Cliente c : pos.getClientes()) {
            System.out.println(c);
        }
    }

    private static void registrarCliente(SistemaPOS pos) {
        String doc = leer("Documento: ");
        String nom = leer("Nombre: ");
        if (nom.isEmpty()) {
            System.out.println("El nombre no puede estar vacio.");
            return;
        }
        Cliente c = new Cliente(doc, nom, leer("Correo: "), leer("Direccion: "), leer("Placa: "));
        System.out.println(pos.registrarCliente(c) ? "Cliente registrado." : "Ya existe ese documento.");
    }

    private static void buscarCliente(SistemaPOS pos) {
        Cliente c = pos.buscarClientePorDocumento(leer("Documento: "));
        System.out.println(c == null ? "Cliente no encontrado." : c.toString());
    }

    private static void nuevaVenta(SistemaPOS pos) {
        Empleado emp = pos.buscarEmpleadoPorDocumento(leer("Documento del trabajador que atiende: "));
        if (!(emp instanceof Trabajador)) {
            System.out.println("Trabajador no encontrado.");
            return;
        }
        Trabajador trab = (Trabajador) emp;
        PuntoVenta punto = pedirPunto(pos);
        if (punto == null) {
            return;
        }
        if (!trab.puedeOperarEnPunto(punto.getCodigoPunto())) {
            System.out.println("El trabajador no esta autorizado en ese punto.");
            return;
        }
        String doc = leer("Documento del cliente (vacio = cliente general): ");
        Cliente cli = pos.getClienteGeneral();
        if (!doc.isEmpty()) {
            cli = pos.buscarClientePorDocumento(doc);
            if (cli == null) {
                System.out.println("Cliente no encontrado. Venta cancelada.");
                return;
            }
        }
        MetodoPago pago;
        System.out.println("Medio de pago: 1. Efectivo  2. Tarjeta");
        if (leerInt("Opcion: ") == 1) {
            pago = new PagoEfectivo(leerDouble("Monto recibido: "));
        } else {
            pago = new PagoTarjeta(leer("Franquicia: "), leer("Ultimos 4 digitos: "));
        }

        Venta venta = new Venta("PRESENCIAL", punto, trab, cli, pago);
        int agregados = 0;
        String codigo;
        while (!(codigo = leer("Codigo de producto (vacio para terminar): ")).isEmpty()) {
            Producto prod = pos.buscarProductoPorCodigo(punto, codigo);
            if (prod == null) {
                System.out.println("Producto no encontrado.");
                continue;
            }
            if (venta.agregarItem(prod, leerInt("Cantidad: "))) {
                agregados++;
            } else {
                System.out.println("Venta rechazada: cantidad invalida o mayor a las existencias ("
                        + prod.getExistencias() + " disponibles).");
            }
        }
        if (agregados == 0) {
            System.out.println("Venta cancelada: no hay productos.");
        } else if (pos.registrarVenta(venta)) {
            System.out.println("\n" + venta.generarComprobante());
        } else {
            System.out.println("El pago no fue aprobado. Venta cancelada.");
        }
    }

    private static void reporteVentas(SistemaPOS pos) {
        System.out.println("Numero de ventas: " + pos.getVentas().size());
        for (Venta v : pos.getVentas()) {
            System.out.println("Recibo " + v.getNumeroRecibo() + " | " + v.getCliente().getNombre()
                    + " | total: " + String.format("%.2f", v.calcularTotal()));
        }
        System.out.println("Total vendido: " + String.format("%.2f", pos.calcularTotalVendidoNacional()));
    }

    private static void misGanancias(SistemaPOS pos) {
        Empleado emp = pos.autenticarUsuario(leer("Correo: "), leer("Contrasena: "));
        if (emp == null) {
            System.out.println("Credenciales incorrectas.");
        } else if (emp instanceof Trabajador) {
            System.out.println("Ganancias acumuladas: "
                    + String.format("%.2f", ((Trabajador) emp).consultarMisGanancias("TOTAL")));
        } else {
            System.out.println("Esta opcion es solo para trabajadores.");
        }
    }

    private static void consolidado(SistemaPOS pos) {
        Empleado emp = pos.autenticarUsuario(leer("Correo: "), leer("Contrasena: "));
        if (emp == null) {
            System.out.println("Credenciales incorrectas.");
        } else if (emp instanceof Jefe) {
            System.out.println(((Jefe) emp).consultarConsolidadoNacional(pos.getVentas()));
        } else {
            System.out.println("Esta opcion es solo para el jefe.");
        }
    }

    private static String leer(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    private static int leerInt(String mensaje) {
        try {
            return Integer.parseInt(leer(mensaje));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static double leerDouble(String mensaje) {
        try {
            return Double.parseDouble(leer(mensaje));
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}