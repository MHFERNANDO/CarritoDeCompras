package ec.edu.ups.controlador;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoController {

    private ProductoAnadirView productoAnadirView;
    private ProductoListaView productoListaView;
    private ProductoDeleteView productoDeleteView;
    private Actualizar actualizar;
    private CarritoAnadirView carritoAnadirView;

    private ProductoDAO productoDAO;
    private MensajeInternacionalizacionHandler mensajeHandler;

    public ProductoController(ProductoDAO productoDAO,
                              ProductoAnadirView productoAnadirView,
                              ProductoListaView productoListaView, ProductoDeleteView productoDeleteView,
                              Actualizar actualizar, CarritoAnadirView carritoAnadirView,
                              MensajeInternacionalizacionHandler mensajeHandler) {
        this.productoDAO = productoDAO;
        this.productoAnadirView = productoAnadirView;
        this.productoListaView = productoListaView;
        this.productoDeleteView = productoDeleteView;
        this.actualizar = actualizar;
        this.carritoAnadirView = carritoAnadirView;
        this.mensajeHandler = mensajeHandler;
        configurarEventos();
    }

    public ProductoController(ProductoAnadirView productoAnadirView, CarritoAnadirView carritoAnadirView,
                              MensajeInternacionalizacionHandler mensajeHandler) {
        this.productoAnadirView = productoAnadirView;
        this.carritoAnadirView = carritoAnadirView;
        this.mensajeHandler = mensajeHandler;
        configurarEventosAniadir();
    }

    public ProductoController(Actualizar actualizar, CarritoAnadirView carritoAnadirView,
                              MensajeInternacionalizacionHandler mensajeHandler) {
        this.actualizar = actualizar;
        this.carritoAnadirView = carritoAnadirView;
        this.mensajeHandler = mensajeHandler;
        configurarEventosActualizar();
    }

    public ProductoController(ProductoListaView productoListaView, CarritoAnadirView carritoAnadirView,
                              MensajeInternacionalizacionHandler mensajeHandler) {
        this.productoListaView = productoListaView;
        this.carritoAnadirView = carritoAnadirView;
        this.mensajeHandler = mensajeHandler;
        configurarListas();
    }

    public ProductoController(ProductoDeleteView productoDeleteView, CarritoAnadirView carritoAnadirView,
                              MensajeInternacionalizacionHandler mensajeHandler) {
        this.productoDeleteView = productoDeleteView;
        this.carritoAnadirView = carritoAnadirView;
        this.mensajeHandler = mensajeHandler;
    }

    private void configurarEventosAniadir(){
        productoAnadirView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
            }
        });
    }

    private void configurarEventosActualizar(){
        actualizar.getModificarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizar();
            }
        });
        actualizar.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPorCodigoAc();
            }
        });
    }

    private void configurarListas(){
        productoListaView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProducto();
            }
        });
        productoListaView.getBtnListar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarProductos();
            }
        });
    }

    private void configurarEventos() {
        actualizar.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPorCodigoAc();
            }
        });
        productoAnadirView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
            }
        });

        productoDeleteView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProductoDelete();
            }
        });

        productoListaView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProducto();
            }
        });

        productoListaView.getBtnListar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarProductos();
            }
        });
        productoDeleteView.getEliminarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });
        actualizar.getModificarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizar();
            }
        });
        carritoAnadirView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPorCodigo();
            }
        });
        /*carritoAnadirView.getAgregarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anadirProductoCarro();
            }
        });*/
    }

    private void guardarProducto() {
        String code = productoAnadirView.getTxtCodigo().getText();
        String nombre = productoAnadirView.getTxtNombre().getText();
        String prec = productoAnadirView.getTxtPrecio().getText();

        if (code.isEmpty() || nombre.isEmpty() || prec.isEmpty()){
            productoAnadirView.mostrarMensaje(mensajeHandler.get("producto.ingresarTodosCampos"));
            return ;
        }
        int codigo = Integer.parseInt(code);
        double precio = Double.parseDouble(prec);
        productoDAO.crear(new Producto(codigo, nombre, precio));
        productoAnadirView.mostrarMensaje(mensajeHandler.get("producto.guardadoCorrectamente"));
        productoAnadirView.limpiarCampos();
        productoAnadirView.mostrarProductos(productoDAO.listarTodos());
    }

    private void buscarProducto() {
        String nombre = productoListaView.getTxtBuscar().getText();

        if (productoListaView.getTxtBuscar().getText().isEmpty()){
            productoListaView.mostrarMensaje(mensajeHandler.get("producto.llenarTodosCampos"));
            return;
        }

        List<Producto> productosEncontrados = productoDAO.buscarPorNombre(nombre);
        productoListaView.cargarDatos(productosEncontrados);
    }

    private void buscarProductoDelete() {

        if (productoDeleteView.getTextField1().getText().isEmpty()){
            productoDeleteView.mostrarMensaje(mensajeHandler.get("producto.llenarTodosCampos"));
            return ;
        }

        int codigo = Integer.parseInt(productoDeleteView.getTextField1().getText());
        Producto productosEncontrados = productoDAO.buscarPorCodigo(codigo);
        productoDeleteView.cargarDatos(productosEncontrados);
    }

    private void buscarPorCodigo(){
        if (carritoAnadirView.getTextField1().getText().isEmpty()){
            carritoAnadirView.mostrarMensaje(mensajeHandler.get("producto.llenarTodosCampos"));
            return ;
        }

        int codigo = Integer.parseInt(carritoAnadirView.getTextField1().getText());
        Producto producto= productoDAO.buscarPorCodigo(codigo);
        if (producto == null){
            carritoAnadirView.getTextField2().setText(mensajeHandler.get("producto.noEncontrado"));
            carritoAnadirView.getTextField3().setText("");
        }
        else {
            carritoAnadirView.getTextField2().setText(producto.getNombre());
            carritoAnadirView.getTextField3().setText(formatearPrecio(producto.getPrecio()));
        }
    }

    private void buscarPorCodigoAc(){
        if (actualizar.getTextField1().getText().isEmpty()){
            actualizar.mostrarMensaje(mensajeHandler.get("producto.llenarTodosCampos"));
            return ;
        }

        int codigo = Integer.parseInt(actualizar.getTextField1().getText());
        Producto producto= productoDAO.buscarPorCodigo(codigo);

        if (producto == null){
            actualizar.mostrarMensaje(mensajeHandler.get("producto.noEncontrado"));
        }
        else {
            actualizar.getTextNombreShow().setText(producto.getNombre());
            actualizar.getTextPrecioShow().setText(formatearPrecio(producto.getPrecio()));
        }
    }

    private void listarProductos() {
        List<Producto> productos = productoDAO.listarTodos();
        productoListaView.cargarDatos(productos);
    }

    private void eliminarProducto(){
        if (productoDeleteView.getTextField1().getText().isEmpty()){
            productoDeleteView.mostrarMensaje(mensajeHandler.get("producto.ingresarCodigoEliminar"));
            return ;
        }

        int code = Integer.parseInt(productoDeleteView.getTextField1().getText());
        Producto productoDelete = productoDAO.buscarPorCodigo(code);

        if (productoDelete == null) {
            productoDeleteView.mostrarMensaje(mensajeHandler.get("producto.noEncontrado"));
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(null,
                mensajeHandler.get("producto.confirmarEliminar"),
                mensajeHandler.get("producto.tituloConfirmacion"),
                JOptionPane.YES_NO_OPTION);

        if(confirmacion == JOptionPane.YES_OPTION){
            productoDAO.eliminar(productoDelete.getCodigo());
            productoDeleteView.mostrarMensaje(mensajeHandler.get("producto.eliminadoCorrectamente"));
        }else{
            productoDeleteView.mostrarMensaje(mensajeHandler.get("producto.eliminacionCancelada"));
        }
    }

    private void actualizar(){
        if (actualizar.getTextField1().getText().isEmpty() ||
                actualizar.getTextField2().getText().isEmpty() ||
                actualizar.getTextField3().getText().isEmpty()) {
            actualizar.mostrarMensaje(mensajeHandler.get("producto.llenarTodosCampos"));
            return;
        }

        int code = Integer.parseInt(actualizar.getTextField1().getText());
        Producto productoAc = productoDAO.buscarPorCodigo(code);

        if (productoAc != null) {
            int confActualizar = JOptionPane.showConfirmDialog(null,
                    mensajeHandler.get("producto.confirmarActualizar"),
                    mensajeHandler.get("producto.tituloConfirmacion"),
                    JOptionPane.YES_NO_OPTION);

            if(confActualizar == JOptionPane.YES_OPTION){
                String nombre = actualizar.getTextField2().getText();
                double precio = Double.parseDouble(actualizar.getTextField3().getText());
                Producto productoAct = new Producto(code, nombre, precio);
                productoDAO.actualizar(productoAct);
                actualizar.mostrarMensaje(mensajeHandler.get("producto.actualizadoCorrectamente"));
            } else {
                actualizar.mostrarMensaje(mensajeHandler.get("producto.actualizacionCancelada"));
            }
        } else {
            actualizar.mostrarMensaje(mensajeHandler.get("producto.noEncontrado"));
        }
    }

    private String formatearPrecio(double precio) {
        String lang = mensajeHandler.getLocale().getLanguage();
        String simboloMoneda = "$"; // por defecto español
        if ("fr".equals(lang) || "it".equals(lang)) {
            simboloMoneda = "€";
        }
        return simboloMoneda + String.format("%.2f", precio);
    }
}
