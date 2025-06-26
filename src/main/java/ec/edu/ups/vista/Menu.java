package ec.edu.ups.vista;

import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.impl.ProductoDAOMemoria;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private JMenuBar menuBar;
    private JMenu menuProducto;
    private JMenu menuCarrito;
    private JMenuItem menuItemCrearProducto;
    private JMenuItem menuItemActualizar;
    private JMenuItem menuItemEliminar;
    private JMenuItem menuItemBuscar;
    private JMenuItem menuCarritoAnadir;
    private JDesktopPane jDesktopPane;

    public Menu(){
        menuBar = new JMenuBar();
        menuProducto = new JMenu("Producto");
        menuCarrito= new JMenu("Carrito");

        menuItemCrearProducto = new JMenuItem("Crear Producto");
        menuItemEliminar = new JMenuItem("Eliminar Producto");
        menuItemActualizar= new JMenuItem("Actualizar Prodcuto");
        menuItemBuscar = new JMenuItem("Buscar Producto");
        menuCarritoAnadir= new JMenuItem("Agregar Carrito");

        jDesktopPane= new JDesktopPane();
        menuBar.add(menuProducto);
        menuBar.add(menuCarrito);
        menuProducto.add(menuItemCrearProducto);
        menuProducto.add(menuItemEliminar);
        menuProducto.add(menuItemBuscar);
        menuProducto.add(menuItemActualizar);
        menuCarrito.add(menuCarritoAnadir);

        setExtendedState(JFrame.MAXIMIZED_BOTH);

       /* menuItemCrearProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductoAnadirView productoAnadirView = new ProductoAnadirView();
                JInternalFrame jInternalFrame= new JInternalFrame();
                jInternalFrame.setSize(500,500);
                jInternalFrame.setMaximizable(true);
                jInternalFrame.setClosable(true);
                jInternalFrame.setTitle("Crear Producto");
                jInternalFrame.setIconifiable(true);
                jInternalFrame.setResizable(true);
                jInternalFrame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
                jInternalFrame.setVisible(true);
                jDesktopPane.add(productoAnadirView.getPanelPrincipal());

                //ProductoAnadirView anadirView = new ProductoAnadirView();
                //ProductoDAO productoDAO = new ProductoDAOMemoria();
                //new ProductoController(productoDAO,anadirView,null,null,null);
            }
        });*/


        setJMenuBar(menuBar);
        setContentPane(jDesktopPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sistema de Carritos de Compra");
        //setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);



    }


    public void setMenuBar(JMenuBar menuBar) {
        this.menuBar = menuBar;
    }

    public JMenu getMenuProducto() {
        return menuProducto;
    }

    public void setMenuProducto(JMenu menuProducto) {
        this.menuProducto = menuProducto;
    }

    public JMenuItem getMenuCarritoAnadir() {
        return menuCarritoAnadir;
    }

    public void setMenuCarritoAnadir(JMenuItem menuCarritoAnadir) {
        this.menuCarritoAnadir = menuCarritoAnadir;
    }

    public JMenuItem getMenuItemCrearProducto() {
        return menuItemCrearProducto;
    }

    public void setMenuItemCrearProducto(JMenuItem menuItemCrearProducto) {
        this.menuItemCrearProducto = menuItemCrearProducto;
    }

    public void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /*public void deshabilitarMenusAdministrador() {
        getMenuItemCrearProducto().setEnabled(false);
        getMenuItemBuscar().setEnabled(false);
        getMenuItemActualizar().setEnabled(false);
        getMenuItemEliminar().setEnabled(false);
    }*/

    public JMenuItem getMenuItemActualizar() {
        return menuItemActualizar;
    }

    public void setMenuItemActualizar(JMenuItem menuItemActualizar) {
        this.menuItemActualizar = menuItemActualizar;
    }

    public JMenuItem getMenuItemEliminar() {
        return menuItemEliminar;
    }

    public void setMenuItemEliminar(JMenuItem menuItemEliminar) {
        this.menuItemEliminar = menuItemEliminar;
    }

    public JMenuItem getMenuItemBuscar() {
        return menuItemBuscar;
    }

    public void setMenuItemBuscar(JMenuItem menuItemBuscar) {
        this.menuItemBuscar = menuItemBuscar;
    }

    public JDesktopPane getjDesktopPane() {
        return jDesktopPane;
    }

    public void setjDesktopPane(JDesktopPane jDesktopPane) {
        this.jDesktopPane = jDesktopPane;
    }

    public static void main(String[] args) {
        new Menu();
    }

    public JMenu getMenuCarrito() {
        return menuCarrito;
    }

    public void setMenuCarrito(JMenu menuCarrito) {
        this.menuCarrito = menuCarrito;
    }
}
