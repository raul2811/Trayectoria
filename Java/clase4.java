
/*Escribir un programa que mediante que mediante el uso de varios métodos los cuales van a ser llamados el 
Mail que realice un método captura solicita el nombre la cantidad y el precio unitario de un 
producto método dos factura solicita al usuario nombre de un producto si se encuentra debe solicitar 
la cantidad a facturar si la cantidad es menor o igual a la existente realizar 
la factura y agregar el 7% método tres imprimir el nombre del producto la cantidad restante y el total a pagar por el pedido */


import javax.swing.JOptionPane;

public class clase4 {

     static String namep;
     static int quantity;
     static float precio;

    public static void main(String[] args) {
        
    
    Registrar_Producto();
    Comprar();
        
    }
    
    private static void Registrar_Producto() {
        namep= JOptionPane.showInputDialog(null, "Ingrese el nombre del producto");
        quantity= Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad del producto"));
        precio= Float.parseFloat(JOptionPane.showInputDialog(null, "Ingrese el precio del producto"));
    }

    private static void Comprar() {
        String name= JOptionPane.showInputDialog(null, "Ingrese el nombre del producto");
        if (name.equals(namep)) {
            Facturar(name);
        }
        else{
            JOptionPane.showMessageDialog(null, "No se encontro un producto con ese nombre");
        }
        
    }

    private static void Facturar(String name) {
        int cantidad;
        cantidad=Integer.parseInt(JOptionPane.showInputDialog(null, "Que cantidad desea comprar"));

        if (cantidad <= quantity) {
           Calculo(name,cantidad);
        } else {
        JOptionPane.showMessageDialog(null, "La cantidad ingresada es mayor a la cantidad en nuestra bodega");
    
     }
    }
    private static void Calculo(String name,int cantidad) {
        double costo_bruto;
        double total;
        double impuesto=0.07;
        costo_bruto = (cantidad * precio);
        impuesto = (impuesto * costo_bruto);
        total = (impuesto + costo_bruto);
        Imprimir (costo_bruto,impuesto,total);
        
         
    }

    private static void Imprimir(double costo_bruto, double impuesto, double total) {
        JOptionPane.showMessageDialog(null, "Detalle de la factura:\n" +
                "Costo bruto: $" + costo_bruto + "\n" +
                "Impuesto (7%): $" + impuesto + "\n" +
                "Total a pagar: $" + total);
    }

}




    


