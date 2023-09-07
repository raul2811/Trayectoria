/*
Escribir un programa sin menu con arreglos en java , los arreglos  almacenaran 1  el codigo de una tarifa de servicioes de internet numerico entero 
codigo aleatorio de cuatro digitos "un vector" para n cantidad de producto y en otro la tarifa , los vectores se manejaran desde metodos desde el main el primer
metodo cargara todos los datos a los vectores , imprimir por pantalla  , un tecer metodo recorrera el vector precio para contar cuantas tarifas menores 
de 20 dolares existen.
*/

import javax.swing.JOptionPane;

public class clase5 {
    public static void main(String[] args) {
        String[] producto = new String[10];
        int[] codigo = new int[10];
        float[] precio = new float[10];
        Solicitar_Datos(codigo, precio, producto);
    }

    private static void Solicitar_Datos(int[] codigo, float[] precio, String[] producto) {
        for (int i = 0; i < codigo.length; i++) {
            producto[i] = JOptionPane.showInputDialog(null, "Ingrese el nombre del Producto #" + (i + 1));
            codigo[i] = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el código del Producto #" + (i + 1)));
            precio[i] = Float.parseFloat(JOptionPane.showInputDialog(null, "Ingrese el precio del Producto #" + (i + 1)));
        }
    }
}