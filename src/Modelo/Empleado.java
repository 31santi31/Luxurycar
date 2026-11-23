package Modelo;

/**
 * Superclase abstracta de los empleados del negocio.
 */
public abstract class Empleado {

    private String documento;
    private String nombre;
    private String telefono;
    private String correoCorporativo;
    private String password;
    private String rol;

    /**
     * Crea un empleado.
     * @param doc documento
     * @param nom nombre
     * @param tel telefono
     * @param correo correo corporativo
     * @param pass contrasena
     * @param rol rol del empleado
     */
    public Empleado(String doc, String nom, String tel, String correo, String pass, String rol) {
        this.documento = doc;
        this.nombre = nom;
        this.telefono = tel;
        this.correoCorporativo = correo;
        this.password = pass;
        this.rol = rol;
    }

    /**
     * Verifica las credenciales.
     * @param correo correo ingresado
     * @param pass contrasena ingresada
     * @return true si coinciden
     */
    public boolean iniciarSesion(String correo, String pass) {
        return this.correoCorporativo.equals(correo) && this.password.equals(pass);
    }

    /**
     * Cambia el correo corporativo.
     * @param correo nuevo correo, no puede estar vacio
     * @throws IllegalArgumentException si esta vacio
     */
    public void setCorreoCorporativo(String correo) {
        if (correo == null || correo.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo no puede estar vacio.");
        }
        this.correoCorporativo = correo.trim();
    }

    /**
     * Cambia la contrasena.
     * @param pass nueva contrasena, no puede estar vacia
     * @throws IllegalArgumentException si esta vacia
     */
    public void setPassword(String pass) {
        if (pass == null || pass.isEmpty()) {
            throw new IllegalArgumentException("La contrasena no puede estar vacia.");
        }
        this.password = pass;
    }

    /** @return documento del empleado */
    public String getDocumento() {
        return documento;
    }

    /** @return nombre del empleado */
    public String getNombre() {
        return nombre;
    }

    /** @return correo corporativo */
    public String getCorreoCorporativo() {
        return correoCorporativo;
    }

    /** @return rol del empleado */
    public String getRol() {
        return rol;
    }
}
