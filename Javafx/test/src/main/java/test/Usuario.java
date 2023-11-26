package test;

public class Usuario {
    // Declaración de atributos privados de la clase Usuario
    private String nombre;      // Almacena el nombre del usuario
    private String apellido;    // Almacena el apellido del usuario
    private int id;             // Almacena el identificador único del usuario
    private String username;    // Almacena el nombre de usuario
    private String tipoCuenta;
    private double montoCuenta;
    
    // Constructor de la clase Usuario que recibe parámetros para inicializar los atributos
    public Usuario(String nombre, String apellido, int id, String username) {
        this.nombre = nombre;       // Asigna el valor del parámetro 'nombre' al atributo 'nombre' de la clase
        this.apellido = apellido;   // Asigna el valor del parámetro 'apellido' al atributo 'apellido' de la clase
        this.id = id;               // Asigna el valor del parámetro 'id' al atributo 'id' de la clase
        this.username = username;   // Asigna el valor del parámetro 'username' al atributo 'username' de la clase
    }

    // Métodos para manejar la información de la cuenta
    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public void setMontoCuenta(double montoCuenta) {
        this.montoCuenta = montoCuenta;
    }

    
    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public double getMontoCuenta() {
        return montoCuenta;
    }
    
    // Getters para otros atributos
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return this.username;
    }
}