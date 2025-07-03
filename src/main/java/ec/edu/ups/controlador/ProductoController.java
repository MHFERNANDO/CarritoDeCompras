package ec.edu.ups.controlador;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;
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

    public ProductoController(ProductoDAO productoDAO,
                              ProductoAnadirView productoAnadirView,
                              ProductoListaView productoListaView, ProductoDeleteView productoDeleteView, Actualizar actualizar, CarritoAnadirView carritoAnadirView) {
        this.productoDAO = productoDAO;
        this.productoAnadirView = productoAnadirView;
        this.productoListaView = productoListaView;
        this.productoDeleteView = productoDeleteView;
        this.actualizar = actualizar;
        this.carritoAnadirView = carritoAnadirView;
        configurarEventos();
    }

    public ProductoController(ProductoAnadirView productoAnadirView, CarritoAnadirView carritoAnadirView) {
        this.productoAnadirView = productoAnadirView;
        this.carritoAnadirView = carritoAnadirView;
        configurarEventosAniadir();
    }

    public ProductoController(Actualizar actualizar, CarritoAnadirView carritoAnadirView) {
        this.actualizar = actualizar;
        this.carritoAnadirView = carritoAnadirView;
        configurarEventosActualizar();
    }

    public ProductoController(ProductoListaView productoListaView, CarritoAnadirView carritoAnadirView) {
        this.productoListaView = productoListaView;
        this.carritoAnadirView = carritoAnadirView;
        configurarListas();
    }

    public ProductoController(ProductoDeleteView productoDeleteView, CarritoAnadirView carritoAnadirView) {
        this.productoDeleteView = productoDeleteView;
        this.carritoAnadirView = carritoAnadirView;
    }

    private void configurarEventosAniadir(){
        productoAnadirView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
            }
        });
    }

    private void  configurarEventosActualizar(){
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
        String code = (productoAnadirView.getTxtCodigo().getText());
        String nombre = productoAnadirView.getTxtNombre().getText();
        String prec = (productoAnadirView.getTxtPrecio().getText());

        if (code.isEmpty()||nombre.isEmpty()||prec.isEmpty()){
            productoAnadirView.mostrarMensaje("Ingresar todos los campos");
            return ;
        }
        int codigo = Integer.parseInt(code);
        double precio = Double.parseDouble(prec);
        productoDAO.crear(new Producto(codigo, nombre, precio));
        productoAnadirView.mostrarMensaje("Producto guardado correctamente");
        productoAnadirView.limpiarCampos();
        productoAnadirView.mostrarProductos(productoDAO.listarTodos());
    }
    /*private void guardarProductoT(){
        int codigo = Integer.parseInt(carritoAnadirView.getTextField1().getText());
        String nombre = carritoAnadirView.getTextField2().getText();
        double precio = Double.parseDouble(carritoAnadirView.getTextField3().getText());
        int cantidad = carritoAnadirView.getComboCantidad().getSelectedIndex();

    }*/

    private void buscarProducto() {
        String nombre = productoListaView.getTxtBuscar().getText();

        if (productoListaView.getTxtBuscar().getText().isEmpty()){
            productoListaView.mostrarMensaje("Llenar todos los campos");
        }

        List<Producto> productosEncontrados = productoDAO.buscarPorNombre(nombre);
        productoListaView.cargarDatos(productosEncontrados);
    }

    private void buscarProductoDelete() {

        int codigo = Integer.parseInt(productoDeleteView.getTextField1().getText());
        if (productoDeleteView.getTextField1().getText().isEmpty()){
            productoDeleteView.mostrarMensaje("Llena todos los campos");
            return ;
        }
        Producto productosEncontrados = productoDAO.buscarPorCodigo(codigo);
        productoDeleteView.cargarDatos(productosEncontrados);
    }

    private void buscarPorCodigo(){
        int codigo = Integer.parseInt(carritoAnadirView.getTextField1().getText());

        if (carritoAnadirView.getTextField1().getText().isEmpty()){
            carritoAnadirView.mostrarMensaje("Llena todos los campos");
            return ;
        }

        Producto producto= productoDAO.buscarPorCodigo(codigo);
        if (producto== null){
            carritoAnadirView.getTextField2().setText("Producto no encontrado");
        }
        else {
            carritoAnadirView.getTextField2().setText(producto.getNombre());
            carritoAnadirView.getTextField3().setText(producto.getPrecio()+"");
        }
    }

    private void buscarPorCodigoAc(){
        int codigo = Integer.parseInt(actualizar.getTextField1().getText());
        Producto producto= productoDAO.buscarPorCodigo(codigo);

        if(actualizar.getTextField1().getText().isEmpty()){
            actualizar.mostrarMensaje("Llena todos los campos");
            return ;
        }

        if (producto== null){
            actualizar.mostrarMensaje("Producto no Encontrado");
        }
        else {
            actualizar.getTextNombreShow().setText(producto.getNombre());
            actualizar.getTextPrecioShow().setText(producto.getPrecio()+"");
        }
    }

    /*private void anadirProductoCarro(){
        carritoAnadirView.cargarDatos1();
    }*/

    private void listarProductos() {
        List<Producto> productos = productoDAO.listarTodos();
        productoListaView.cargarDatos(productos);
    }
    private void eliminarProducto(){
        String codigo = productoDeleteView.getTextField1().getText();
        if (codigo.isEmpty()){
            productoDeleteView.mostrarMensaje("Ingresa un codigo para eliminar");
            return ;
        }

        int code = Integer.parseInt(codigo);
        Producto productoDelete = productoDAO.buscarPorCodigo(code);

        if (productoDelete == null) {
            productoDeleteView.mostrarMensaje("Producto no encontrado");
        }
            //productoDAO.eliminar(productoDelete.getCodigo());
            //System.out.println("Producto eliminado.");
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estas seguro de que deseas eliminar el producto ","Confirmar eliminacion",JOptionPane.YES_NO_OPTION);
        if(confirmacion==JOptionPane.YES_OPTION){
            productoDAO.eliminar(productoDelete.getCodigo());
            productoDeleteView.mostrarMensaje("Producto eliminado correctamente");
        }else{
            productoDeleteView.mostrarMensaje("Eliminacion cancelada correctamente");
        }
    }
    private void actualizar(){
        String codigo = actualizar.getTextField1().getText();
        int code = Integer.parseInt(codigo);
        Producto productoAc = productoDAO.buscarPorCodigo(code);
        if (codigo.isEmpty()){
            actualizar.mostrarMensaje("Llenar todos los capos");
        }

        if (productoAc != null) {
            int confActualizar = JOptionPane.showConfirmDialog(null,"¿Seguro deseas actualizar prodcuto?","Actualizar producto",JOptionPane.YES_NO_OPTION);
            if(confActualizar==JOptionPane.YES_OPTION){
                String nombre = actualizar.getTextField2().getText();
                double precio = Double.parseDouble(actualizar.getTextField3().getText());
                Producto productoAct = new Producto(code,nombre,precio);
                productoDAO.actualizar(productoAct);
                actualizar.mostrarMensaje("Producto actualizado correctamente");
            }else {
                actualizar.mostrarMensaje("Actualizacion cancelada correctamente");
            }
        } else {
            actualizar.mostrarMensaje("Producto no encontrado.");
        }
    }

}