package ec.edu.ups.vista;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.net.URL;
import java.util.List;

public class CarritoLista extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTextField textField1;
    private JTable table1;
    private JButton botonCBuscar;
    private JButton botonCListar;
    private JLabel codigoLabel;
    private DefaultTableModel modelo;

    private MensajeInternacionalizacionHandler mensajeHandler;

    public CarritoLista(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;

        setContentPane(panelPrincipal);
        setTitle(mensajeHandler.get("carrito.lista.titulo"));
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(800, 800);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setVisible(false);

        URL buscarURL = LoginView.class.getClassLoader().getResource("imagenes/buscar.png");
        if (buscarURL != null) {
            ImageIcon iconoBtnIniciarSesion = new ImageIcon(buscarURL);
            botonCBuscar.setIcon(iconoBtnIniciarSesion);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL listaURL = LoginView.class.getClassLoader().getResource("imagenes/buscar.png");
        if (listaURL != null) {
            ImageIcon iconoBtnIniciarSesion = new ImageIcon(listaURL);
            botonCListar.setIcon(iconoBtnIniciarSesion);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }


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

        codigoLabel.setText(mensajeHandler.get("carrito.codigo"));
        botonCBuscar.setText(mensajeHandler.get("botonC.buscar"));
        botonCListar.setText(mensajeHandler.get("botonC.listar"));
    }

    public void cambiarIdioma() {
        setTitle(mensajeHandler.get("carrito.lista.titulo"));
        codigoLabel.setText(mensajeHandler.get("carrito.codigo"));
        botonCBuscar.setText(mensajeHandler.get("botonC.buscar"));
        botonCListar.setText(mensajeHandler.get("botonC.listar"));

        modelo.setColumnIdentifiers(new Object[]{
                mensajeHandler.get("carrito.usuario"),
                mensajeHandler.get("carrito.codigo"),
                mensajeHandler.get("carrito.fecha"),
                mensajeHandler.get("carrito.items"),
                mensajeHandler.get("carrito.total")
        });
    }

    public void cargarDatos(List<Carrito> carritos) {
        if (carritos == null) {
            JOptionPane.showMessageDialog(this, mensajeHandler.get("carrito.no.encontrado"));
            return;
        }
        modelo.setNumRows(0);

        for (Carrito carrito : carritos) {
            Object[] fila = {
                    carrito.getUsuario().getUsername(),
                    carrito.getCodigo(),
                    carrito.getFechaFormateada(),
                    carrito.obtenerItems(),
                    carrito.calcularTotal()
            };
            modelo.addRow(fila);
        }
    }

    public void cargarBusqueda(Carrito carrito) {
        modelo.setNumRows(0);
        Object[] fila = {
                carrito.getCodigo(),
                carrito.getFechaFormateada(),
                carrito.obtenerItems(),
                carrito.calcularTotal()
        };
        modelo.addRow(fila);
    }
    public void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this,mensaje);
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

    public JTable getTable1() {
        return table1;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }

    public JButton getBuscarButton() {
        return botonCBuscar;
    }

    public void setBuscarButton(JButton buscarButton) {
        this.botonCBuscar = buscarButton;
    }

    public JButton getListarButton() {
        return botonCListar;
    }

    public void setListarButton(JButton listarButton) {
        this.botonCListar = listarButton;
    }
}
