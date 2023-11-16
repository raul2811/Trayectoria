import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfCodigo extends JFrame {

    public InterfCodigo() {
        initComponents();
    }

    private void initComponents() {
        // Configurar los parámetros de la ventana
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear los componentes
        JButton botonconexion = new JButton("Conectar");
        JButton botonCerrar = new JButton("Cerrar la aplicación");

        // Crear un contenedor
        JPanel panelDeContenido = new JPanel();

        // Asociar los componentes al contenedor
        panelDeContenido.add(botonconexion);
        panelDeContenido.add(botonCerrar);

        // Asociar el contenedor a la ventana
        setContentPane(panelDeContenido);

        // Hacer visible la ventana
        setVisible(true);

        // Agregar un ActionListener al botón "Conectar"
        botonconexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llamar al método "conexion" de la clase "test_db"
                test_db testDB = new test_db();
                testDB.conexion();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new InterfCodigo();
            }
        });
    }
}
