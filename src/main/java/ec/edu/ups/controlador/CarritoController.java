package ec.edu.ups.controlador;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.CarritoAnadirView;
import ec.edu.ups.vista.CarritoLista;
import ec.edu.ups.vista.ListarMisCarritos;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class CarritoController {
    private CarritoDAO carritoDAO;
    private CarritoAnadirView carritoAnadirView;
    private CarritoLista carritoLista;
    private ProductoDAO productoDAO;
    private Carrito carrito;
    private Usuario usuarioAutenticado;
    private MensajeInternacionalizacionHandler mensajeHandler;
    private ListarMisCarritos listarMisCarritosView;

    public CarritoController(CarritoDAO carritoDAO,
                             ProductoDAO productoDAO,
                             CarritoAnadirView carritoAnadirView,
                             CarritoLista carritoLista,
                             ListarMisCarritos listarMisCarritosView,
                             Usuario usuarioEncontrado,
                             MensajeInternacionalizacionHandler mensajeHandler) {
        this.carritoDAO = carritoDAO;
        this.productoDAO = productoDAO;
        this.carritoAnadirView = carritoAnadirView;
        this.carritoLista = carritoLista;
        this.listarMisCarritosView = listarMisCarritosView;
        this.usuarioAutenticado = usuarioEncontrado;
        this.mensajeHandler = mensajeHandler;
        this.carrito = new Carrito();
        carritoAnadirView.getTextFecha().setText(carrito.getFechaFormateada());
        configurarEventosVistas();
    }

    private void configurarEventosVistas() {
        carritoAnadirView.getAgregarButton().addActionListener(e -> anadirProducto());
        listarMisCarritosView.getListarButton().addActionListener(e -> listarCarritosUsuario());
        carritoAnadirView.getGuardarButton().addActionListener(e -> guardarCarrito());
        carritoAnadirView.getCancelarButton().addActionListener(e -> cancelarCarrito());
        carritoLista.getListarButton().addActionListener(e -> listarCarrito());
        carritoLista.getBuscarButton().addActionListener(e -> listaCarritoBusqueda());
    }

    private void listarCarritosUsuario() {
        List<Carrito> carritosUsuario = carritoDAO.listarTodos()
                .stream()
                .filter(c -> c.getUsuario() != null
                        && c.getUsuario().getUsername().equals(usuarioAutenticado.getUsername()))
                .collect(Collectors.toList());

        if (carritosUsuario.isEmpty()) {
            listarMisCarritosView.mostrarMensaje(mensajeHandler.get("carrito.noEncontrado"));
        } else {
            listarMisCarritosView.cargarDatos(carritosUsuario);
        }
    }

    private void anadirProducto() {
        try {
            int codigoProducto = Integer.parseInt(carritoAnadirView.getTextCodigo().getText());
            Producto producto = productoDAO.buscarPorCodigo(codigoProducto);
            if (producto == null) {
                carritoAnadirView.mostrarMensaje(mensajeHandler.get("carrito.productoNoEncontrado"));
                return;
            }
            int cantidad = Integer.parseInt(carritoAnadirView.getComboCantidad().getSelectedItem().toString());

            boolean encontrado = false;
            for (ItemCarrito item : carrito.obtenerItems()) {
                if (item.getProducto().getCodigo() == producto.getCodigo()) {
                    item.setCantidad(item.getCantidad() + cantidad);
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                carrito.agregarProducto(producto, cantidad);
            }
            cargarProductos();
            mostrarTotales();
        } catch (NumberFormatException ex) {
            carritoAnadirView.mostrarMensaje(mensajeHandler.get("carrito.ingreseCodigoValido"));
        }
    }

    private void cargarProductos() {
        List<ItemCarrito> items = carrito.obtenerItems();
        DefaultTableModel modelo = (DefaultTableModel) carritoAnadirView.getTable1().getModel();
        modelo.setNumRows(0);
        for (ItemCarrito item : items) {
            modelo.addRow(new Object[]{
                    item.getProducto().getCodigo(),
                    item.getProducto().getNombre(),
                    formatearPrecio(item.getProducto().getPrecio()),
                    item.getCantidad(),
                    formatearPrecio(item.getProducto().getPrecio() * item.getCantidad())
            });
        }
    }

    private String formatearPrecio(double precio) {
        String lang = mensajeHandler.getLocale().getLanguage();
        String simboloMoneda = "$"; // default español
        if ("fr".equals(lang) || "it".equals(lang)) {
            simboloMoneda = "€";
        }
        return simboloMoneda + String.format("%.2f", precio);
    }

    private void guardarCarrito() {
        if (carrito.estaVacio()) {
            carritoAnadirView.mostrarMensaje(mensajeHandler.get("carrito.agregarAntesDeGuardar"));
            return;
        }
        carrito.agregarFecha();
        carrito.setUsuario(usuarioAutenticado);
        carritoDAO.crear(carrito);
        carritoAnadirView.mostrarMensaje(mensajeHandler.get("carrito.creadoCorrectamente"));

        carrito = new Carrito();
        carrito.setUsuario(usuarioAutenticado);
        carritoAnadirView.getTextFecha().setText(carrito.getFechaFormateada());
        cargarProductos();
        mostrarTotales();
    }

    private void mostrarTotales() {
        carritoAnadirView.getTextSubtotal().setText(formatearPrecio(carrito.calcularSubtotal()));
        carritoAnadirView.getTextIVA().setText(formatearPrecio(carrito.calcularIVA()));
        carritoAnadirView.getTextTotal().setText(formatearPrecio(carrito.calcularTotal()));
    }

    private void cancelarCarrito() {
        carrito.vaciarCarrito();
        cargarProductos();
        mostrarTotales();
    }

    private void listarCarrito() {
        List<Carrito> carritos = carritoDAO.listarTodos();
        carritoLista.cargarDatos(carritos);
    }

    private void listaCarritoBusqueda() {
        try {
            int codigo = Integer.parseInt(carritoLista.getTextField1().getText());
            Carrito carritoEncontrado = carritoDAO.buscarPorCodigo(codigo);
            if (carritoEncontrado == null) {
                carritoLista.mostrarMensaje(mensajeHandler.get("carrito.noEncontrado"));
            } else {
                carritoLista.cargarBusqueda(carritoEncontrado);
            }
        } catch (NumberFormatException ex) {
            carritoLista.mostrarMensaje(mensajeHandler.get("carrito.ingreseCodigoValido"));
        }
    }
}
