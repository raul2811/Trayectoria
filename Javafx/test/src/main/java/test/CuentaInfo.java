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
}