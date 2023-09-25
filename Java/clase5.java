/*
Escribir un programa sin menu con arreglos en java , los arreglos  almacenaran 1  el codigo de una tarifa de servicioes de internet numerico entero 
codigo aleatorio de cuatro digitos "un vector" para n cantidad de producto y en otro la tarifa , los vectores se manejaran desde metodos desde el main el primer
metodo cargara todos los datos a los vectores , imprimir por pantalla  , un tecer metodo recorrera el vector precio para contar cuantas tarifas menores 
de 20 dolares existen.
*/

import javax.swing.JOptionPane;

public class clase5 {
    public static void main(String[] args) {
        String[] producto = new String[2];
        float[] codigo = new float[2];
        float[] precio = new float[2];
        Solicitar_Datos(codigo, precio, producto);
        Impresion(codigo, precio, producto);
    }

    private static void Solicitar_Datos(float[] codigo, float[] precio, String[] producto) {
        for (int i = 0; i < codigo.length; i++) {
            producto[i] = JOptionPane.showInputDialog(null, "Ingrese el nombre del Producto #" + (i + 1));
            codigo[i] = Float.parseFloat
            (JOptionPane.showInputDialog(null, "Ingrese el código del Producto #" + (i + 1)));
            precio[i] = Float.parseFloat(JOptionPane.showInputDialog(null, "Ingrese el precio del Producto #" + (i + 1)));
        }
    }

    private static void Impresion(float[] codigo, float[] precio, String[] producto) {
        for (int i = 0; i < producto.length; i++) {
               JOptionPane.showMessageDialog(null,producto[i]+"\n"+codigo[i]+"\n"+precio[i]);       
        }
    }
}