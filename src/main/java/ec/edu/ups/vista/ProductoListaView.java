package ec.edu.ups.vista;

import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProductoListaView extends JInternalFrame {

    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JTable tblProductos;
    private JPanel panelPrincipal;
    private JButton btnListar;
    private JLabel nombreLabel;
    private DefaultTableModel modelo;

    private MensajeInternacionalizacionHandler mensajeHandler;

    public ProductoListaView(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;

        setContentPane(panelPrincipal);
        setTitle(mensajeHandler.get("productoLi.titulo"));
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        setSize(500, 500);
        setVisible(false);

        nombreLabel.setText(mensajeHandler.get("productoLi.nombre"));
        btnBuscar.setText(mensajeHandler.get("botonPBuscar"));
        btnListar.setText(mensajeHandler.get("botonPLista"));

        modelo = new DefaultTableModel();
        Object[] columnas = {
                mensajeHandler.get("producto.codigo"),
                mensajeHandler.get("productoLi.nombre"),
                mensajeHandler.get("producto.precio")
        };
        modelo.setColumnIdentifiers(columnas);
        tblProductos.setModel(modelo);
    }

    public void cambiarIdioma() {
        setTitle(mensajeHandler.get("productoLi.titulo"));
        nombreLabel.setText(mensajeHandler.get("productoLi.nombre"));
        btnBuscar.setText(mensajeHandler.get("botonPBuscar"));
        btnListar.setText(mensajeHandler.get("botonPLista"));

        modelo.setColumnIdentifiers(new Object[] {
                mensajeHandler.get("producto.codigo"),
                mensajeHandler.get("productoLi.nombre"),
                mensajeHandler.get("producto.precio")
        });
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public void setTxtBuscar(JTextField txtBuscar) {
        this.txtBuscar = txtBuscar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JTable getTblProductos() {
        return tblProductos;
    }

    public void setTblProductos(JTable tblProductos) {
        this.tblProductos = tblProductos;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JButton getBtnListar() {
        return btnListar;
    }

    public void setBtnListar(JButton btnListar) {
        this.btnListar = btnListar;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public void cargarDatos(List<Producto> listaProductos) {
        if (listaProductos == null || listaProductos.isEmpty()) {
            JOptionPane.showMessageDialog(this, mensajeHandler.get("productoLi.no_encontrado"));
            return;
        }

        modelo.setNumRows(0);

        for (Producto producto : listaProductos) {
            Object[] fila = {
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio()
            };
            modelo.addRow(fila);
        }
    }

    public void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
