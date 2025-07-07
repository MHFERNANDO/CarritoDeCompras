package ec.edu.ups.vista;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ListarMisCarritos extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTable table1;
    private JButton listarButton;
    private MensajeInternacionalizacionHandler mensajeHandler;
    private DefaultTableModel modelo;

    public ListarMisCarritos(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;

        setContentPane(panelPrincipal);
        setTitle(mensajeHandler.get("carrito.lista.titulo"));
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setVisible(false);

        // Configurar modelo y asignarlo a la tabla
        modelo = new DefaultTableModel();
        Object[] columnas = {
                mensajeHandler.get("carrito.usuario"),
                mensajeHandler.get("carrito.codigo"),
                mensajeHandler.get("carrito.fecha"),
                mensajeHandler.get("carrito.items"),
                mensajeHandler.get("carrito.total")
        };
        modelo.setColumnIdentifiers(columnas);
        table1.setModel(modelo);
    }

    public void cargarDatos(List<Carrito> carritos) {
        modelo.setRowCount(0);
        for (Carrito c : carritos) {
            modelo.addRow(new Object[]{
                    c.getUsuario() != null ? c.getUsuario().getNombre() : "",
                    c.getCodigo(),
                    c.getFechaFormateada(),
                    c.obtenerItems(),
                    c.calcularTotal()
            });
        }
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTable getTable1() {
        return table1;
    }

    public JButton getListarButton() {
        return listarButton;
    }
}
