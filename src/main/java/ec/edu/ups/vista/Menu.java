package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

public class Menu extends JFrame {

    private MensajeInternacionalizacionHandler mensajeHandler;

    private JMenuBar menuBar;
    private JMenu menuProducto;
    private JMenu menuCarrito;
    private JMenu menuUsuario;
    private JMenu menuIdioma;

    private JMenuItem menuItemCrearProducto;
    private JMenuItem menuItemActualizar;
    private JMenuItem menuItemEliminar;
    private JMenuItem menuItemBuscar;

    private JMenuItem menuCarritoAnadir;
    private JMenuItem menuCarritoListar;
    private JMenuItem menuItemListarMisCarritos;

    private JMenuItem menuItemUsuarioListar;
    private JMenuItem menuItemUsuarioAdd;
    private JMenuItem menuItemUsuarioActualizar;

    private JMenuItem menuItemCerrarSesion;

    private JMenuItem menuItemEspanol;
    private JMenuItem menuItemIngles;
    private JMenuItem menuItemFrances;
    private JMenuItem menuItemItaliano;

    private JDesktopPane jDesktopPane;
    private MiDesktopPane miDesktopPane;

    public Menu(MensajeInternacionalizacionHandler mensajeHandler){
        this.mensajeHandler = mensajeHandler;
        initComponents();
    }

    private void initComponents() {
        menuBar = new JMenuBar();
        menuProducto = new JMenu(mensajeHandler.get("menu.producto"));
        menuCarrito= new JMenu(mensajeHandler.get("menu.carrito"));
        menuUsuario = new JMenu(mensajeHandler.get("menu.usuario"));
        menuIdioma = new JMenu(mensajeHandler.get("menu.idiomas"));

        menuItemEspanol = new JMenuItem(mensajeHandler.get("menu.idioma.es"));
        menuItemIngles = new JMenuItem(mensajeHandler.get("menu.idioma.en"));
        menuItemFrances = new JMenuItem(mensajeHandler.get("menu.idioma.fr"));
        menuItemItaliano = new JMenuItem(mensajeHandler.get("menu.idioma.it"));

        menuItemCrearProducto = new JMenuItem(mensajeHandler.get("menu.producto.crear"));
        menuItemEliminar = new JMenuItem(mensajeHandler.get("menu.producto.eliminar"));
        menuItemActualizar = new JMenuItem(mensajeHandler.get("menu.producto.actualizar"));
        menuItemBuscar = new JMenuItem(mensajeHandler.get("menu.producto.buscar"));

        menuCarritoAnadir = new JMenuItem(mensajeHandler.get("menu.carrito.anadir"));
        menuCarritoListar = new JMenuItem(mensajeHandler.get("menu.carrito.listar"));
        menuItemListarMisCarritos = new JMenuItem(mensajeHandler.get("menu.carrito.listarMisCarritos"));

        menuItemUsuarioAdd = new JMenuItem(mensajeHandler.get("menu.usuario.add"));
        menuItemUsuarioListar = new JMenuItem(mensajeHandler.get("menu.usuario.listar"));
        menuItemUsuarioActualizar = new JMenuItem(mensajeHandler.get("menu.usuario.actualizar"));

        menuItemCerrarSesion = new JMenuItem(mensajeHandler.get("menu.cerrarSesion"));

        jDesktopPane = new JDesktopPane();

        menuProducto.add(menuItemCrearProducto);
        menuProducto.add(menuItemEliminar);
        menuProducto.add(menuItemBuscar);
        menuProducto.add(menuItemActualizar);

        menuCarrito.add(menuCarritoAnadir);
        menuCarrito.add(menuCarritoListar);
        menuCarrito.add(menuItemListarMisCarritos);

        menuUsuario.add(menuItemUsuarioAdd);
        menuUsuario.add(menuItemUsuarioListar);
        menuUsuario.add(menuItemUsuarioActualizar);

        menuIdioma.add(menuItemEspanol);
        menuIdioma.add(menuItemIngles);
        menuIdioma.add(menuItemFrances);
        menuIdioma.add(menuItemItaliano);

        menuBar.add(menuProducto);
        menuBar.add(menuCarrito);
        menuBar.add(menuUsuario);
        menuBar.add(menuIdioma);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(menuItemCerrarSesion);

        miDesktopPane = new MiDesktopPane();

        setJMenuBar(menuBar);
        setContentPane(miDesktopPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(mensajeHandler.get("app.titulo"));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void cambiarIdioma() {
        menuProducto.setText(mensajeHandler.get("menu.producto"));
        menuCarrito.setText(mensajeHandler.get("menu.carrito"));
        menuUsuario.setText(mensajeHandler.get("menu.usuario"));
        menuIdioma.setText(mensajeHandler.get("menu.idiomas"));

        menuItemEspanol.setText(mensajeHandler.get("menu.idioma.es"));
        menuItemIngles.setText(mensajeHandler.get("menu.idioma.en"));
        menuItemFrances.setText(mensajeHandler.get("menu.idioma.fr"));
        menuItemItaliano.setText(mensajeHandler.get("menu.idioma.it"));

        menuItemCrearProducto.setText(mensajeHandler.get("menu.producto.crear"));
        menuItemEliminar.setText(mensajeHandler.get("menu.producto.eliminar"));
        menuItemActualizar.setText(mensajeHandler.get("menu.producto.actualizar"));
        menuItemBuscar.setText(mensajeHandler.get("menu.producto.buscar"));

        menuCarritoAnadir.setText(mensajeHandler.get("menu.carrito.anadir"));
        menuCarritoListar.setText(mensajeHandler.get("menu.carrito.listar"));
        menuItemListarMisCarritos.setText(mensajeHandler.get("menu.carrito.listarMisCarritos"));

        menuItemUsuarioAdd.setText(mensajeHandler.get("menu.usuario.add"));
        menuItemUsuarioListar.setText(mensajeHandler.get("menu.usuario.listar"));
        menuItemUsuarioActualizar.setText(mensajeHandler.get("menu.usuario.actualizar"));

        menuItemCerrarSesion.setText(mensajeHandler.get("menu.cerrarSesion"));

        setTitle(mensajeHandler.get("app.titulo"));
    }

    public MiDesktopPane getMiDesktopPane() {
        return miDesktopPane;
    }

    public void setMiDesktopPane(MiDesktopPane miDesktopPane) {
        this.miDesktopPane = miDesktopPane;
    }

    public JMenuItem getMenuItemCrearProducto() { return menuItemCrearProducto; }
    public JMenuItem getMenuItemActualizar() { return menuItemActualizar; }
    public JMenuItem getMenuItemEliminar() { return menuItemEliminar; }
    public JMenuItem getMenuItemBuscar() { return menuItemBuscar; }
    public JMenuItem getMenuCarritoAnadir() { return menuCarritoAnadir; }
    public JMenuItem getMenuCarritoListar() { return menuCarritoListar; }
    public JMenuItem getMenuItemListarMisCarritos() { return menuItemListarMisCarritos; }
    public JMenuItem getMenuItemUsuarioAdd() { return menuItemUsuarioAdd; }
    public JMenuItem getMenuItemUsuarioListar() { return menuItemUsuarioListar; }
    public JMenuItem getMenuItemUsuarioActualizar() { return menuItemUsuarioActualizar; }
    public JMenuItem getMenuItemCerrarSesion() { return menuItemCerrarSesion; }
    public JMenuItem getMenuItemEspanol() { return menuItemEspanol; }
    public JMenuItem getMenuItemIngles() { return menuItemIngles; }
    public JMenuItem getMenuItemFrances() { return menuItemFrances; }
    public JMenuItem getMenuItemItaliano() { return menuItemItaliano; }
    public JDesktopPane getjDesktopPane() { return jDesktopPane; }

    public MensajeInternacionalizacionHandler getMensajeHandler() {
        return mensajeHandler;
    }

    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
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

    public JMenu getMenuCarrito() {
        return menuCarrito;
    }

    public void setMenuCarrito(JMenu menuCarrito) {
        this.menuCarrito = menuCarrito;
    }

    public JMenu getMenuUsuario() {
        return menuUsuario;
    }

    public void setMenuUsuario(JMenu menuUsuario) {
        this.menuUsuario = menuUsuario;
    }

    public JMenu getMenuIdioma() {
        return menuIdioma;
    }

    public void setMenuIdioma(JMenu menuIdioma) {
        this.menuIdioma = menuIdioma;
    }

    public void setMenuItemCrearProducto(JMenuItem menuItemCrearProducto) {
        this.menuItemCrearProducto = menuItemCrearProducto;
    }

    public void setMenuItemActualizar(JMenuItem menuItemActualizar) {
        this.menuItemActualizar = menuItemActualizar;
    }

    public void setMenuItemEliminar(JMenuItem menuItemEliminar) {
        this.menuItemEliminar = menuItemEliminar;
    }

    public void setMenuItemBuscar(JMenuItem menuItemBuscar) {
        this.menuItemBuscar = menuItemBuscar;
    }

    public void setMenuCarritoAnadir(JMenuItem menuCarritoAnadir) {
        this.menuCarritoAnadir = menuCarritoAnadir;
    }

    public void setMenuCarritoListar(JMenuItem menuCarritoListar) {
        this.menuCarritoListar = menuCarritoListar;
    }

    public void setMenuItemListarMisCarritos(JMenuItem menuItemListarMisCarritos) {
        this.menuItemListarMisCarritos = menuItemListarMisCarritos;
    }

    public void setMenuItemUsuarioListar(JMenuItem menuItemUsuarioListar) {
        this.menuItemUsuarioListar = menuItemUsuarioListar;
    }

    public void setMenuItemUsuarioAdd(JMenuItem menuItemUsuarioAdd) {
        this.menuItemUsuarioAdd = menuItemUsuarioAdd;
    }

    public void setMenuItemUsuarioActualizar(JMenuItem menuItemUsuarioActualizar) {
        this.menuItemUsuarioActualizar = menuItemUsuarioActualizar;
    }

    public void setMenuItemCerrarSesion(JMenuItem menuItemCerrarSesion) {
        this.menuItemCerrarSesion = menuItemCerrarSesion;
    }

    public void setMenuItemEspanol(JMenuItem menuItemEspanol) {
        this.menuItemEspanol = menuItemEspanol;
    }

    public void setMenuItemIngles(JMenuItem menuItemIngles) {
        this.menuItemIngles = menuItemIngles;
    }

    public void setMenuItemFrances(JMenuItem menuItemFrances) {
        this.menuItemFrances = menuItemFrances;
    }

    public void setMenuItemItaliano(JMenuItem menuItemItaliano) {
        this.menuItemItaliano = menuItemItaliano;
    }

    public void setjDesktopPane(JDesktopPane jDesktopPane) {
        this.jDesktopPane = jDesktopPane;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void deshabilitarMenusAdministrador() {
        getMenuItemCrearProducto().setEnabled(false);
        getMenuItemCrearProducto().setVisible(false);
        getMenuItemActualizar().setEnabled(false);
        getMenuItemActualizar().setVisible(false);
        getMenuItemEliminar().setVisible(false);
        getMenuItemEliminar().setEnabled(false);
        getMenuUsuario().setEnabled(false);
        getMenuUsuario().setVisible(false);
        getMenuCarritoListar().setVisible(false);
        getMenuCarritoListar().setEnabled(false);
    }
}
