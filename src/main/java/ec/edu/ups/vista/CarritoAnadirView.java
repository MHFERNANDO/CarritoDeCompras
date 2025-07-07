package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.net.URL;

public class CarritoAnadirView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTable table1;
    private JButton guardarButton;
    private JButton cancelarButton;
    private JTextField textCodigo;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textCantidad;
    private JTextField textSubtotal;
    private JTextField textIVA;
    private JTextField textTotal;
    private JButton buscarButton;
    private JButton agregarButton;
    private JComboBox comboCantidad;
    private JTextField textFecha;
    private JLabel fechaLabel;
    private JLabel codigoLabel;
    private JLabel nombreLabel;
    private JLabel pecioLabel;
    private JLabel cantidadLabel;
    private JLabel subtotalLabel;
    private JLabel ivaLabel;
    private JLabel totalLabel;
    private JLabel AgegarCarritoLabel;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mensajeHandler;

    public CarritoAnadirView(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
        setContentPane(panelPrincipal);
        setTitle(mensajeHandler.get("carritoA.titulo"));
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(800, 800);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setVisible(false);
        cargarDatos();

        URL buscarURL = LoginView.class.getClassLoader().getResource("imagenes/buscar.png");
        if (buscarURL != null) {
            ImageIcon iconoBtnIniciarSesion = new ImageIcon(buscarURL);
            buscarButton.setIcon(iconoBtnIniciarSesion);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL contraURL = LoginView.class.getClassLoader().getResource("imagenes/add1.png");
        if (contraURL != null) {
            ImageIcon iconoBtnIniciarSesion = new ImageIcon(contraURL);
            agregarButton.setIcon(iconoBtnIniciarSesion);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL saveURL = LoginView.class.getClassLoader().getResource("imagenes/guardar.png");
        if (saveURL != null) {
            ImageIcon iconoBtnIniciarSesion = new ImageIcon(saveURL);
            guardarButton.setIcon(iconoBtnIniciarSesion);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL limpiarURL = LoginView.class.getClassLoader().getResource("imagenes/x.png");
        if (limpiarURL != null) {
            ImageIcon iconoBtnIniciarSesion = new ImageIcon(limpiarURL);
            cancelarButton.setIcon(iconoBtnIniciarSesion);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }

        modelo = new DefaultTableModel();
        Object[] columnas = {
                mensajeHandler.get("carritoA.columna.codigo"),
                mensajeHandler.get("carritoA.columna.nombre"),
                mensajeHandler.get("carritoA.columna.precio"),
                mensajeHandler.get("carritoA.columna.cantidad"),
                mensajeHandler.get("carritoA.columna.subtotal")
        };
        modelo.setColumnIdentifiers(columnas);
        table1.setModel(modelo);

        AgegarCarritoLabel.setText(mensajeHandler.get("carritoA.titulo"));
        fechaLabel.setText(mensajeHandler.get("carritoA.fecha"));
        codigoLabel.setText(mensajeHandler.get("carritoA.codigo"));
        nombreLabel.setText(mensajeHandler.get("carritoA.nombre"));
        pecioLabel.setText(mensajeHandler.get("carritoA.precio"));
        cantidadLabel.setText(mensajeHandler.get("carritoA.cantidad"));
        subtotalLabel.setText(mensajeHandler.get("carritoA.subtotal"));
        ivaLabel.setText(mensajeHandler.get("carritoA.iva"));
        totalLabel.setText(mensajeHandler.get("carritoA.total"));

        guardarButton.setText(mensajeHandler.get("botonCGuardar"));
        cancelarButton.setText(mensajeHandler.get("botonCCancelar"));
        buscarButton.setText(mensajeHandler.get("botonCBuscar"));
        agregarButton.setText(mensajeHandler.get("botonCAgregar"));
    }

    public void cambiarIdioma() {
        setTitle(mensajeHandler.get("carritoA.titulo"));
        AgegarCarritoLabel.setText(mensajeHandler.get("carritoA.titulo"));
        fechaLabel.setText(mensajeHandler.get("carritoA.fecha"));
        codigoLabel.setText(mensajeHandler.get("carritoA.codigo"));
        nombreLabel.setText(mensajeHandler.get("carritoA.nombre"));
        pecioLabel.setText(mensajeHandler.get("carritoA.precio"));
        cantidadLabel.setText(mensajeHandler.get("carritoA.cantidad"));
        subtotalLabel.setText(mensajeHandler.get("carritoA.subtotal"));
        ivaLabel.setText(mensajeHandler.get("carritoA.iva"));
        totalLabel.setText(mensajeHandler.get("carritoA.total"));
        guardarButton.setText(mensajeHandler.get("botonCGuardar"));
        cancelarButton.setText(mensajeHandler.get("botonCCancelar"));
        buscarButton.setText(mensajeHandler.get("botonCBuscar"));
        agregarButton.setText(mensajeHandler.get("botonCAgregar"));

        modelo.setColumnIdentifiers(new Object[] {
                mensajeHandler.get("carritoA.columna.codigo"),
                mensajeHandler.get("carritoA.columna.nombre"),
                mensajeHandler.get("carritoA.columna.precio"),
                mensajeHandler.get("carritoA.columna.cantidad"),
                mensajeHandler.get("carritoA.columna.subtotal")
        });
    }

    private void cargarDatos() {
        comboCantidad.removeAllItems();
        for (int i = 0; i < 20; i++) {
            comboCantidad.addItem(String.valueOf(i + 1));
        }
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public DefaultTableModel getModelo() { return modelo; }
    public void setModelo(DefaultTableModel modelo) { this.modelo = modelo; }
    public JPanel getPanelPrincipal() { return panelPrincipal; }
    public void setPanelPrincipal(JPanel panelPrincipal) { this.panelPrincipal = panelPrincipal; }
    public JTable getTable1() { return table1; }
    public void setTable1(JTable table1) { this.table1 = table1; }
    public JButton getGuardarButton() { return guardarButton; }
    public void setGuardarButton(JButton guardarButton) { this.guardarButton = guardarButton; }
    public JButton getCancelarButton() { return cancelarButton; }
    public void setCancelarButton(JButton cancelarButton) { this.cancelarButton = cancelarButton; }
    public JTextField getTextField1() { return textCodigo; }
    public void setTextField1(JTextField textField1) { this.textCodigo = textField1; }
    public JTextField getTextField2() { return textField2; }
    public void setTextField2(JTextField textField2) { this.textField2 = textField2; }
    public JTextField getTextField3() { return textField3; }
    public void setTextField3(JTextField textField3) { this.textField3 = textField3; }
    public JTextField getTextField4() { return textCantidad; }
    public void setTextField4(JTextField textField4) { this.textCantidad = textField4; }
    public JTextField getTextField5() { return textSubtotal; }
    public void setTextField5(JTextField textField5) { this.textSubtotal = textField5; }
    public JTextField getTextField6() { return textIVA; }
    public void setTextField6(JTextField textField6) { this.textIVA = textField6; }
    public JTextField getTextField7() { return textTotal; }
    public void setTextField7(JTextField textField7) { this.textTotal = textField7; }
    public JButton getBuscarButton() { return buscarButton; }
    public void setBuscarButton(JButton buscarButton) { this.buscarButton = buscarButton; }
    public JButton getAgregarButton() { return agregarButton; }
    public void setAgregarButton(JButton agregarButton) { this.agregarButton = agregarButton; }
    public JTextField getTextFecha() { return textFecha; }
    public void setTextFecha(JTextField textFecha) { this.textFecha = textFecha; }
    public JTextField getTextCodigo() { return textCodigo; }
    public void setTextCodigo(JTextField textCodigo) { this.textCodigo = textCodigo; }
    public JTextField getTextCantidad() { return textCantidad; }
    public void setTextCantidad(JTextField textCantidad) { this.textCantidad = textCantidad; }
    public JTextField getTextSubtotal() { return textSubtotal; }
    public void setTextSubtotal(JTextField textSubtotal) { this.textSubtotal = textSubtotal; }
    public JTextField getTextIVA() { return textIVA; }
    public void setTextIVA(JTextField textIVA) { this.textIVA = textIVA; }
    public JTextField getTextTotal() { return textTotal; }
    public void setTextTotal(JTextField textTotal) { this.textTotal = textTotal; }
    public JComboBox getComboCantidad() { return comboCantidad; }
    public void setComboCantidad(JComboBox comboCantidad) { this.comboCantidad = comboCantidad; }
}
