package ec.edu.ups.vista;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.dao.impl.CarritoDAOMemoria;
import ec.edu.ups.dao.impl.ProductoDAOMemoria;
import ec.edu.ups.dao.impl.UsuarioDAOMemoria;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                /*ProductoAnadirView productoView = new ProductoAnadirView();
                ProductoListaView productoListaView = new ProductoListaView();
                ProductoDeleteView productoDeleteView = new ProductoDeleteView();
                Actualizar actualizar = new Actualizar();
                ProductoDAO productoDAO = new ProductoDAOMemoria();
                new ProductoController(productoDAO, productoView, productoListaView, productoDeleteView,actualizar);*/

                //Iniciar Sesion
                UsuarioDAO usuarioDAO = new UsuarioDAOMemoria();
                LoginView loginView = new LoginView();
                loginView.setVisible(true);


                UsuarioController usuarioController = new UsuarioController(usuarioDAO,loginView);

                loginView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        Usuario usuarioAutenticado = usuarioController.getUsuarioAutenticado();

                        if (usuarioAutenticado!= null){
                            //Instanciamos DAO (Singleton)
                            ProductoDAO productoDAO = new ProductoDAOMemoria();
                            CarritoDAO carritoDAO= new CarritoDAOMemoria();

                            //Instancias en vistas
                            Menu menu = new Menu();
                            ProductoAnadirView productoAnadirView = new ProductoAnadirView();
                            ProductoListaView productoListaView = new ProductoListaView();
                            CarritoAnadirView carritoAnadirView= new CarritoAnadirView();
                            ProductoDeleteView productoDeleteView = new ProductoDeleteView();
                            Actualizar actualizar = new Actualizar();


                            //Instanciamos Controladores
                            ProductoController productoController = new ProductoController(productoDAO,productoAnadirView,productoListaView,productoDeleteView,actualizar,carritoAnadirView);
                            CarritoController carritoController = new CarritoController(carritoDAO,productoDAO,carritoAnadirView);

                            menu.mostrarMensaje("Bienvenido: "+ usuarioAutenticado.getUsername());
                            /*if (usuarioAutenticado.getRol().equals(Rol.USUARIO)){
                                menu.deshabilitarMenusAdministrador();
                            }*/

                            menu.getMenuItemCrearProducto().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (! productoAnadirView.isVisible()){
                                        productoAnadirView.setVisible(true);
                                        menu.getjDesktopPane().add(productoAnadirView);
                                    }
                                }
                            });

                            menu.getMenuItemBuscar().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (! productoListaView.isVisible()){
                                        productoListaView.setVisible(true);
                                        menu.getjDesktopPane().add(productoListaView);
                                    }
                                }
                            });

                            menu.getMenuCarritoAnadir().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (! carritoAnadirView.isVisible()){
                                        carritoAnadirView.setVisible(true);
                                        menu.getjDesktopPane().add(carritoAnadirView);
                                    }
                                }
                            });
                            menu.getMenuItemEliminar().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (! productoDeleteView.isVisible()){
                                        productoDeleteView.setVisible(true);
                                        menu.getjDesktopPane().add(productoDeleteView);
                                    }
                                }
                            });

                            menu.getMenuItemActualizar().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (! actualizar.isVisible()){
                                        actualizar.setVisible(true);
                                        menu.getjDesktopPane().add(actualizar);
                                    }
                                }
                            });

                        }

                    }
                });

            }
        });
    }
}
