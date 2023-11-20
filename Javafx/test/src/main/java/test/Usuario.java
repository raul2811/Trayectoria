package test;

public class Usuario {
    private String nombre;
    private String apellido;
    private double dinero;
    private int id;

    // Constructor
    public Usuario(String nombre, String apellido, double dinero, int id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dinero = dinero;
        this.id = id;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public double getDinero() {
        return dinero;
    }

    public int getId() {
        return id;
    }
}