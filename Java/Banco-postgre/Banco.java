import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Banco {
    private base_de_datos db;

    public Banco() {
        db = new base_de_datos();
        createUI();
    }

    private void createUI() {
        JFrame frame = new JFrame("Interfaz Gráfica");
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton conectarButton = new JButton("Conectar");
        conectarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.conexion();
            }
        });

        JButton buscarButton = new JButton("Buscar Cliente");
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreCliente = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
                if (nombreCliente != null && !nombreCliente.isEmpty()) {
                    db.buscarCliente(nombreCliente);
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese un nombre de cliente válido");
                }
            }
        });
        

        JButton agregarButton = new JButton("Agregar Cliente");
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
                String apellido = JOptionPane.showInputDialog("Ingrese el apellido del cliente:");
                String edadStr = JOptionPane.showInputDialog("Ingrese la edad del cliente:");
                String dineroStr = JOptionPane.showInputDialog("Ingrese el dinero del cliente:");

                if (nombre != null && apellido != null && edadStr != null && dineroStr != null &&
                    !nombre.isEmpty() && !apellido.isEmpty() && !edadStr.isEmpty() && !dineroStr.isEmpty()) {
                    try {
                        int edad = Integer.parseInt(edadStr);
                        float dinero = Float.parseFloat(dineroStr);
                        db.agregarCliente(nombre, apellido, edad, dinero);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "La edad y el dinero deben ser números válidos");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos");
                }
            }
        });

        
        JButton cerrarButton = new JButton("Cerrar Conexión");
        cerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.cerrarConexion();
            }
        });

        frame.add(conectarButton);
        frame.add(buscarButton);
        frame.add(cerrarButton);
        frame.add(agregarButton);
        frame.setSize(300, 150);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Banco interfaz = new Banco();
    }
}
