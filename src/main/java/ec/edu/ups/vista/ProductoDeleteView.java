package ec.edu.ups.vista;

import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ProductoDeleteView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JTextField textField1;
    private JButton eliminarButton;
    private JTable table1;
    private JButton buscarButton;
    private JLabel eliminarProductoLabel;
    private JLabel codigoLabel;
    private DefaultTableModel modelo;

    private MensajeInternacionalizacionHandler mensajeHandler;

    public ProductoDeleteView(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
        initComponents();
    }

    private void initComponents() {
        setContentPane(panelPrincipal);
        setTitle(mensajeHandler.get("productoDe.eliminar.titulo"));
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setVisible(false);

        eliminarProductoLabel.setText(mensajeHandler.get("productoDe.eliminar.titulo"));
        codigoLabel.setText(mensajeHandler.get("productoDe.codigo"));
        eliminarButton.setText(mensajeHandler.get("botonDe.eliminar"));
        buscarButton.setText(mensajeHandler.get("botonDe.buscar"));

        modelo = new DefaultTableModel();
        Object[] columnas = {
                mensajeHandler.get("productoDe.codigo"),
                mensajeHandler.get("productoDe.nombre"),
                mensajeHandler.get("productoDe.precio")
        };
        modelo.setColumnIdentifiers(columnas);
        table1.setModel(modelo);

        eliminarButton.addActionListener(e -> mostrarMensaje(mensajeHandler.get("productoDe.eliminado")));
    }

    public void cambiarIdioma() {
        setTitle(mensajeHandler.get("productoDe.eliminar.titulo"));
        eliminarProductoLabel.setText(mensajeHandler.get("productoDe.eliminar.titulo"));
        codigoLabel.setText(mensajeHandler.get("productoDe.codigo"));
        eliminarButton.setText(mensajeHandler.get("botonDe.eliminar"));
        buscarButton.setText(mensajeHandler.get("botonDe.buscar"));

        Object[] columnas = {
                mensajeHandler.get("productoDe.codigo"),
                mensajeHandler.get("productoDe.nombre"),
                mensajeHandler.get("productoDe.precio")
        };
        modelo.setColumnIdentifiers(columnas);
    }

    // Getters y setters

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public JButton getEliminarButton() {
        return eliminarButton;
    }

    public void setEliminarButton(JButton eliminarButton) {
        this.eliminarButton = eliminarButton;
    }

    public JTable getTable1() {
        return table1;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }

    public JButton getBuscarButton() {
        return buscarButton;
    }

    public void setBuscarButton(JButton buscarButton) {
        this.buscarButton = buscarButton;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void cargarDatos(Producto producto) {
        if (producto == null) {
            mostrarMensaje(mensajeHandler.get("productoDe.no.encontrado"));
            return;
        }

        modelo.setNumRows(0);

        Object[] fila = {
                producto.getCodigo(),
                producto.getNombre(),
                producto.getPrecio()
        };

        modelo.addRow(fila);
    }
}
