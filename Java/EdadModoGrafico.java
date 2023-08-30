import javax.swing.JOptionPane;

public class EdadModoGrafico {
    public static void main(String[] args) {
        String nombre = JOptionPane.showInputDialog("Ingrese su nombre:");
        int  añodenacimiento= Integer.parseInt(JOptionPane.showInputDialog("Ingrese el año de nacimiento:"));
        int añoactual = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el año actual:"));

        int edad = añoactual - añodenacimiento;

        String mensajeEdad;

        if (edad <= 12) {
            mensajeEdad = "Eres un niño";
        } else if (edad <= 18) {
            mensajeEdad = "Eres un adolescente";
        } else if (edad <= 30) {
            mensajeEdad = "Eres un joven adulto";
        } else if (edad <= 60) {
            mensajeEdad = "Eres un adulto";
        } else {
            mensajeEdad = "Eres un adulto mayor";
        }

        JOptionPane.showMessageDialog(null, "Hola " + nombre + ", tienes " + edad + " años. " + mensajeEdad);
    }
}