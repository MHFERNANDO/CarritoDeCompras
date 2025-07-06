package ec.edu.ups.vista;

import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ListarUsuarioView extends JInternalFrame {
    private JTextField textNombre;
    private JTable table1;
    private JButton buscarButton;
    private JButton listarButton;
    private JPanel panelPrincipal;
    private JLabel nombreLabel;
    private JLabel listaDeUsuariosLabel;
    private DefaultTableModel modelo;

    private MensajeInternacionalizacionHandler mensajeHandler;

    public ListarUsuarioView(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;

        setContentPane(panelPrincipal);
        setTitle(mensajeHandler.get("usuarioLi.titulo"));
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(800, 800);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setVisible(false);

        nombreLabel.setText(mensajeHandler.get("usuarioLi.nombre"));
        listaDeUsuariosLabel.setText(mensajeHandler.get("usuarioLi.listado"));
        buscarButton.setText(mensajeHandler.get("botonUBuscar"));
        listarButton.setText(mensajeHandler.get("botonULista"));

        modelo = new DefaultTableModel();
        Object[] columnas = {
                "Cedula",
                "Nombre",
                "Apellido",
                "Genero",
                "Fecha de Nacimiento",
                mensajeHandler.get("usuarioLi.columna.usuario"),
                mensajeHandler.get("usuarioLi.columna.contrasenia"),
                mensajeHandler.get("usuarioLi.columna.rol")
        };
        modelo.setColumnIdentifiers(columnas);
        table1.setModel(modelo);
    }

    public void cambiarIdioma() {
        setTitle(mensajeHandler.get("usuarioLi.titulo"));
        nombreLabel.setText(mensajeHandler.get("usuarioLi.nombre"));
        listaDeUsuariosLabel.setText(mensajeHandler.get("usuarioLi.listado"));
        buscarButton.setText(mensajeHandler.get("botonUBuscar"));
        listarButton.setText(mensajeHandler.get("botonULista"));

        modelo.setColumnIdentifiers(new Object[]{
                mensajeHandler.get("usuarioLi.columna.usuario"),
                mensajeHandler.get("usuarioLi.columna.contrasenia"),
                mensajeHandler.get("usuarioLi.columna.rol")
        });
    }

    public void mostrarMensajes(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void cargarDatos(List<Usuario> usuarios) {
        if (usuarios == null || usuarios.isEmpty()) {
            mostrarMensajes(mensajeHandler.get("usuarioLi.no_encontrado"));
            return;
        }

        modelo.setNumRows(0);

        for (Usuario usuario : usuarios) {
            Object[] fila = {
                    usuario.getCedula(),
                    usuario.getNombre(),
                    usuario.getApellido(),
                    usuario.getGenero(),
                    usuario.getFechaNac(),
                    usuario.getUsername(),
                    usuario.getContrasenia(),
                    usuario.getRol()
            };
            modelo.addRow(fila);
        }
    }

    public JTextField getTextNombre() {
        return textNombre;
    }

    public void setTextNombre(JTextField textNombre) {
        this.textNombre = textNombre;
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

    public JButton getListarButton() {
        return listarButton;
    }

    public void setListarButton(JButton listarButton) {
        this.listarButton = listarButton;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }
}
