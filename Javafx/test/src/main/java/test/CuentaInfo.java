package test;

public class CuentaInfo {
    private int id;
    private String tipo;
    private double monto;

    public CuentaInfo(int id, String tipo, double monto) {
        this.id = id;
        this.tipo = tipo;
        this.monto = monto;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }
<<<<<<< Updated upstream
=======

    public String traducirTipo(String tipoDB) {
        switch (tipoDB) {
            case "1":
                return "Cuenta De Ahorros";
            case "2":
                return "Cuenta Corriente";
            case "3":
                return "Prestamo";
            default:
                return tipoDB; // Devuelve el valor de la base de datos si no hay coincidencia
        }
    }

    @Override
    public String toString() {
        return traducirTipo(tipo); // Devuelve el nombre traducido del tipo de cuenta
    }
>>>>>>> Stashed changes
}