package test; // Este código está dentro del paquete 'test'

// Definición de la clase Usuario
public class Usuario {
    // Declaración de atributos privados de la clase Usuario
    private String nombre;      // Almacena el nombre del usuario
    private String apellido;    // Almacena el apellido del usuario
    private double dinero;      // Almacena la cantidad de dinero del usuario
    private int id;             // Almacena el identificador único del usuario
    private String username1;   // Almacena el nombre de usuario

    // Constructor de la clase Usuario que recibe parámetros para inicializar los atributos
    public Usuario(String nombre, String apellido, double dinero, int id, String username1) {
        this.nombre = nombre;       // Asigna el valor del parámetro 'nombre' al atributo 'nombre' de la clase
        this.apellido = apellido;   // Asigna el valor del parámetro 'apellido' al atributo 'apellido' de la clase
        this.dinero = dinero;       // Asigna el valor del parámetro 'dinero' al atributo 'dinero' de la clase
        this.id = id;               // Asigna el valor del parámetro 'id' al atributo 'id' de la clase
        this.username1 = username1; // Asigna el valor del parámetro 'username1' al atributo 'username1' de la clase
    }

    // Métodos Getter que permiten acceder a los valores de los atributos desde fuera de la clase

    // Devuelve el nombre del usuario
    public String getNombre() {
        return nombre;
    }

    // Devuelve el apellido del usuario
    public String getApellido() {
        return apellido;
    }

    // Devuelve la cantidad de dinero del usuario
    public double getDinero() {
        return dinero;
    }

    // Devuelve el identificador único del usuario
    public int getId() {
        return id;
    }

    // Devuelve el nombre de usuario
    public String getUsername() {
        return this.username1;
    }
}