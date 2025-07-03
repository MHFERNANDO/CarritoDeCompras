package ec.edu.ups.controlador;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.CarritoAnadirView;
import ec.edu.ups.vista.CarritoLista;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CarritoController{
    private CarritoDAO carritoDAO;
    private CarritoAnadirView carritoAnadirView;
    private CarritoLista carritoLista;
    private ProductoDAO productoDAO;
    private Carrito carrito;
    private Usuario usuarioAutenticado;

    public CarritoController(CarritoDAO carritoDAO,
                             ProductoDAO productoDAO,
                             CarritoAnadirView carritoAnadirView, CarritoLista carritoLista, Usuario usuarioEncontrado) {
        this.carritoDAO = carritoDAO;
        this.productoDAO = productoDAO;
        this.carritoAnadirView = carritoAnadirView;
        this.carritoLista = carritoLista;
        this.usuarioAutenticado = usuarioEncontrado;
        this.carrito = new Carrito();
        carritoAnadirView.getTextFecha().setText(carrito.getFechaFormateada());
        configurarEventosVistas();
    }

    private void configurarEventosVistas(){


        carritoAnadirView.getAgregarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anadirProducto();

            }
        });
        carritoAnadirView.getGuardarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCarrito();
            }
        });
        carritoAnadirView.getCancelarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarCarrito();
            }
        });
        carritoLista.getListarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarCarrito();
            }
        });
        carritoLista.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaCarritoBusqueda();
            }
        });

    }
    private void anadirProducto(){


        Producto producto = productoDAO.buscarPorCodigo(Integer.parseInt(carritoAnadirView.getTextCodigo().getText()));
        int cantidad = Integer.parseInt(carritoAnadirView.getComboCantidad().getSelectedItem().toString());

        boolean encontrado = false;

        for (ItemCarrito item: carrito.obtenerItems()){
            if(item.getProducto().getCodigo()== producto.getCodigo()){
                item.setCantidad(item.getCantidad()+cantidad);
                encontrado=true;
                break;
            }
        }

        if (encontrado !=true){
            carrito.agregarProducto(producto,cantidad);
        }

        cargarProductos();
        mostrarTotales();

    }
    private void cargarProductos(){
        List<ItemCarrito> items = carrito.obtenerItems();
        DefaultTableModel modelo= (DefaultTableModel) carritoAnadirView.getTable1().getModel();
        modelo.setNumRows(0);
        for(ItemCarrito item : items){
            modelo.addRow(new Object[]{item.getProducto().getCodigo(),
                                        item.getProducto().getNombre(),
                                        item.getProducto().getPrecio(),
                                        item.getCantidad(),
                                        item.getProducto().getPrecio()* item.getCantidad()});

        }
    }
    private void guardarCarrito(){

        if(carrito.estaVacio()){
            carritoAnadirView.mostrarMensaje("Debes agregar productos antes de guardar");
            return ;
        }


        carrito.agregarFecha();
        carrito.setUsuario(usuarioAutenticado);
        carritoDAO.crear(carrito);
        carritoAnadirView.mostrarMensaje("Carrito creado completamente");
        System.out.println(carritoDAO.listarTodos());

        carrito = new Carrito();
        carrito.setUsuario(usuarioAutenticado);
        carritoAnadirView.getTextFecha().setText(carrito.getFechaFormateada());
        cargarProductos();
        mostrarTotales();

    }
    private void mostrarTotales(){
        String subtotal = String.valueOf(carrito.calcularSubtotal());
        String iva = String.valueOf(carrito.calcularIVA());
        String total = String.valueOf(carrito.calcularTotal());

        carritoAnadirView.getTextSubtotal().setText(subtotal);
        carritoAnadirView.getTextIVA().setText(iva);
        carritoAnadirView.getTextTotal().setText(total);
    }

    private void cancelarCarrito(){
        carrito.vaciarCarrito();
        cargarProductos();
        mostrarTotales();
    }
    private void listarCarrito(){
        List<Carrito> carritos = carritoDAO.listarTodos();
        carritoLista.cargarDatos(carritos);
    }
    private void listaCarritoBusqueda(){
        int codigo = Integer.parseInt(carritoLista.getTextField1().getText());
        Carrito carritoEncontrado = carritoDAO.buscarPorCodigo(codigo);
        carritoLista.cargarBusqueda(carritoEncontrado);
    }

}
