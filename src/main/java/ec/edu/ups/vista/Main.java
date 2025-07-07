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
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UsuarioDAO usuarioDAO = new UsuarioDAOMemoria();
                ProductoDAO productoDAO = new ProductoDAOMemoria();
                CarritoDAO carritoDAO = new CarritoDAOMemoria();
                MensajeInternacionalizacionHandler mensajeHandler = new MensajeInternacionalizacionHandler("es", "EC");
                LoginView loginView = new LoginView();
                AnadirUsuarioView anadirUsuarioView = new AnadirUsuarioView(mensajeHandler);
                ListarUsuarioView listarUsuarioView = new ListarUsuarioView(mensajeHandler);
                UsuarioActualizarView usuarioActualizarView = new UsuarioActualizarView(mensajeHandler);
                RegistrarseView registrarseView = new RegistrarseView();
                RegistrarPreguntaView registrarPreguntaView = new RegistrarPreguntaView();
                OlvideContrasenaView olvideContrasenaView = new OlvideContrasenaView();
                loginView.setVisible(true);

                UsuarioController usuarioController = new UsuarioController(
                        usuarioDAO, loginView, anadirUsuarioView, listarUsuarioView, usuarioActualizarView,registrarseView, registrarPreguntaView, olvideContrasenaView);

                loginView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        Usuario usuarioAutenticado = usuarioController.getUsuarioAutenticado();

                        if (usuarioAutenticado != null) {


                            Menu menu = new Menu(mensajeHandler);

                            ProductoAnadirView productoAnadirView = new ProductoAnadirView(mensajeHandler);
                            ProductoListaView productoListaView = new ProductoListaView(mensajeHandler);
                            CarritoAnadirView carritoAnadirView = new CarritoAnadirView(mensajeHandler);
                            ProductoDeleteView productoDeleteView = new ProductoDeleteView(mensajeHandler);
                            CarritoLista carritoLista = new CarritoLista(mensajeHandler);
                            Actualizar actualizar = new Actualizar(mensajeHandler);

                            ProductoController productoController = new ProductoController(
                                    productoDAO, productoAnadirView, productoListaView, productoDeleteView, actualizar, carritoAnadirView
                            );

                            CarritoController carritoController = new CarritoController(
                                    carritoDAO, productoDAO, carritoAnadirView, carritoLista, usuarioAutenticado
                            );

                            menu.mostrarMensaje("Bienvenido: " + usuarioAutenticado.getUsername());

                            if (usuarioAutenticado.getRol().equals(Rol.USUARIO)) {
                                menu.deshabilitarMenusAdministrador();
                            }

                            menu.getMenuItemCrearProducto().addActionListener(e1 -> {
                                if (!productoAnadirView.isVisible()) {
                                    productoAnadirView.setVisible(true);
                                    menu.getMiDesktopPane().add(productoAnadirView);
                                }
                            });

                            menu.getMenuItemBuscar().addActionListener(e1 -> {
                                if (!productoListaView.isVisible()) {
                                    productoListaView.setVisible(true);
                                    menu.getMiDesktopPane().add(productoListaView);
                                }
                            });

                            menu.getMenuItemEliminar().addActionListener(e1 -> {
                                if (!productoDeleteView.isVisible()) {
                                    productoDeleteView.setVisible(true);
                                    menu.getMiDesktopPane().add(productoDeleteView);
                                }
                            });

                            menu.getMenuItemActualizar().addActionListener(e1 -> {
                                if (!actualizar.isVisible()) {
                                    actualizar.setVisible(true);
                                    menu.getMiDesktopPane().add(actualizar);
                                }
                            });

                            menu.getMenuCarritoAnadir().addActionListener(e1 -> {
                                if (!carritoAnadirView.isVisible()) {
                                    carritoAnadirView.setVisible(true);
                                    menu.getMiDesktopPane().add(carritoAnadirView);
                                }
                            });

                            menu.getMenuCarritoListar().addActionListener(e1 -> {
                                if (!carritoLista.isVisible()) {
                                    carritoLista.setVisible(true);
                                    menu.getMiDesktopPane().add(carritoLista);
                                }
                            });

                            menu.getMenuItemUsuarioAdd().addActionListener(e1 -> {
                                if (!anadirUsuarioView.isVisible()) {
                                    anadirUsuarioView.setVisible(true);
                                    menu.getMiDesktopPane().add(anadirUsuarioView);
                                }
                            });

                            menu.getMenuItemUsuarioListar().addActionListener(e1 -> {
                                if (!listarUsuarioView.isVisible()) {
                                    listarUsuarioView.setVisible(true);
                                    menu.getMiDesktopPane().add(listarUsuarioView);
                                }
                            });

                            menu.getMenuItemUsuarioActualizar().addActionListener(e1 -> {
                                if (!usuarioActualizarView.isVisible()) {
                                    usuarioActualizarView.setVisible(true);
                                    menu.getMiDesktopPane().add(usuarioActualizarView);
                                }
                            });

                            menu.getMenuItemCerrarSesion().addActionListener(e1 -> {
                                menu.setVisible(false);
                                usuarioController.logout();
                            });

                            // Cambiar idioma dinámicamente
                            menu.getMenuItemEspanol().addActionListener(ev -> {
                                mensajeHandler.setLenguaje("es", "EC");
                                menu.cambiarIdioma();
                                productoAnadirView.cambiarIdioma();
                                productoDeleteView.cambiarIdioma();
                                usuarioActualizarView.cambiarIdioma();
                                productoListaView.cambiarIdioma();
                                listarUsuarioView.cambiarIdioma();
                                carritoLista.cambiarIdioma();
                                carritoAnadirView.cambiarIdioma();
                                anadirUsuarioView.cambiarIdioma();
                                actualizar.cambiarIdioma();
                            });

                            menu.getMenuItemIngles().addActionListener(ev -> {
                                mensajeHandler.setLenguaje("en", "US");
                                menu.cambiarIdioma();
                                productoAnadirView.cambiarIdioma();
                                productoDeleteView.cambiarIdioma();
                                usuarioActualizarView.cambiarIdioma();
                                productoListaView.cambiarIdioma();
                                listarUsuarioView.cambiarIdioma();
                                carritoLista.cambiarIdioma();
                                carritoAnadirView.cambiarIdioma();
                                anadirUsuarioView.cambiarIdioma();
                                actualizar.cambiarIdioma();
                            });

                            menu.getMenuItemFrances().addActionListener(ev -> {
                                mensajeHandler.setLenguaje("fr", "FR");
                                menu.cambiarIdioma();
                                productoAnadirView.cambiarIdioma();
                                productoDeleteView.cambiarIdioma();
                                usuarioActualizarView.cambiarIdioma();
                                productoListaView.cambiarIdioma();
                                listarUsuarioView.cambiarIdioma();
                                carritoLista.cambiarIdioma();
                                carritoAnadirView.cambiarIdioma();
                                anadirUsuarioView.cambiarIdioma();
                                actualizar.cambiarIdioma();
                            });
                            //menu
                            //
                            menu.getMenuItemItaliano().addActionListener(ev -> {
                                mensajeHandler.setLenguaje("it", "IT");
                                menu.cambiarIdioma();
                                productoAnadirView.cambiarIdioma();
                                productoDeleteView.cambiarIdioma();
                                usuarioActualizarView.cambiarIdioma();
                                productoListaView.cambiarIdioma();
                                listarUsuarioView.cambiarIdioma();
                                carritoLista.cambiarIdioma();
                                carritoAnadirView.cambiarIdioma();
                                anadirUsuarioView.cambiarIdioma();
                                actualizar.cambiarIdioma();
                            });

                            menu.setVisible(true);
                        }
                    }
                });
            }
        });
    }
}
