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
import ec.edu.ups.dao.impl.archivos.CarritoDAOArchivoTexto;
import ec.edu.ups.dao.impl.archivos.ProductoDAOArchivoBin;
import ec.edu.ups.dao.impl.archivos.UsuarioDAOArchivoText;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            MensajeInternacionalizacionHandler mensajeHandler = new MensajeInternacionalizacionHandler("es", "EC");

            // Ventana para elegir almacenamiento
            ArchMemView selector = new ArchMemView();
            selector.setVisible(true);

            selector.getMemoriaButton().addActionListener(e -> {
                // DAOs en memoria
                UsuarioDAO usuarioDAO = new UsuarioDAOMemoria();
                ProductoDAO productoDAO = new ProductoDAOMemoria();
                CarritoDAO carritoDAO = new CarritoDAOMemoria();

                selector.dispose();

                iniciarAplicacion(usuarioDAO, productoDAO, carritoDAO, mensajeHandler);
            });

            selector.getArchivosButton().addActionListener(e -> {
                String ruta = selector.getTextField1().getText().trim();

                if (ruta.isEmpty()) {
                    JOptionPane.showMessageDialog(selector, "Debe ingresar una ruta para los archivos");
                    return;
                }

                // DAOs con archivos, pasando rutas
                UsuarioDAO usuarioDAO = new UsuarioDAOArchivoText(ruta + "/usuarios.txt");
                ProductoDAO productoDAO = new ProductoDAOArchivoBin(ruta + "/productos.dat");
                CarritoDAO carritoDAO = new CarritoDAOArchivoTexto(ruta + "/carritos.txt");

                selector.dispose();

                iniciarAplicacion(usuarioDAO, productoDAO, carritoDAO, mensajeHandler);
            });
        });
    }

    private static void iniciarAplicacion(UsuarioDAO usuarioDAO, ProductoDAO productoDAO, CarritoDAO carritoDAO, MensajeInternacionalizacionHandler mensajeHandler) {
        LoginView loginView = new LoginView(mensajeHandler);
        AnadirUsuarioView anadirUsuarioView = new AnadirUsuarioView(mensajeHandler);
        ListarUsuarioView listarUsuarioView = new ListarUsuarioView(mensajeHandler);
        UsuarioActualizarView usuarioActualizarView = new UsuarioActualizarView(mensajeHandler);
        RegistrarseView registrarseView = new RegistrarseView(mensajeHandler);
        RegistrarPreguntaView registrarPreguntaView = new RegistrarPreguntaView(mensajeHandler);
        OlvideContrasenaView olvideContrasenaView = new OlvideContrasenaView(mensajeHandler);
        loginView.setVisible(true);

        UsuarioController usuarioController = new UsuarioController(
                usuarioDAO, loginView, anadirUsuarioView, listarUsuarioView, usuarioActualizarView,
                registrarseView, registrarPreguntaView, olvideContrasenaView, mensajeHandler);

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
                    ListarMisCarritos listarMisCarritos = new ListarMisCarritos(mensajeHandler);

                    ProductoController productoController = new ProductoController(
                            productoDAO, productoAnadirView, productoListaView, productoDeleteView,
                            actualizar, carritoAnadirView, mensajeHandler
                    );

                    CarritoController carritoController = new CarritoController(
                            carritoDAO, productoDAO, carritoAnadirView, carritoLista, listarMisCarritos,
                            usuarioAutenticado, mensajeHandler
                    );

                    menu.mostrarMensaje("Bienvenido: " + usuarioAutenticado.getUsername());

                    if (usuarioAutenticado.getRol().equals(Rol.USUARIO)) {
                        menu.deshabilitarMenusAdministrador();
                    }

                    // Agregar listeners para menú (igual que antes)...

                    menu.getMenuItemCrearProducto().addActionListener(ev -> {
                        if (!productoAnadirView.isVisible()) {
                            productoAnadirView.setVisible(true);
                            menu.getMiDesktopPane().add(productoAnadirView);
                        }
                    });

                    menu.getMenuItemBuscar().addActionListener(ev -> {
                        if (!productoListaView.isVisible()) {
                            productoListaView.setVisible(true);
                            menu.getMiDesktopPane().add(productoListaView);
                        }
                    });

                    menu.getMenuItemListarMisCarritos().addActionListener(ev -> {
                        if (!listarMisCarritos.isVisible()) {
                            listarMisCarritos.setVisible(true);
                            menu.getMiDesktopPane().add(listarMisCarritos);
                        }
                    });

                    menu.getMenuItemEliminar().addActionListener(ev -> {
                        if (!productoDeleteView.isVisible()) {
                            productoDeleteView.setVisible(true);
                            menu.getMiDesktopPane().add(productoDeleteView);
                        }
                    });

                    menu.getMenuItemActualizar().addActionListener(ev -> {
                        if (!actualizar.isVisible()) {
                            actualizar.setVisible(true);
                            menu.getMiDesktopPane().add(actualizar);
                        }
                    });

                    menu.getMenuCarritoAnadir().addActionListener(ev -> {
                        if (!carritoAnadirView.isVisible()) {
                            carritoAnadirView.setVisible(true);
                            menu.getMiDesktopPane().add(carritoAnadirView);
                        }
                    });

                    menu.getMenuCarritoListar().addActionListener(ev -> {
                        if (!carritoLista.isVisible()) {
                            carritoLista.setVisible(true);
                            menu.getMiDesktopPane().add(carritoLista);
                        }
                    });

                    menu.getMenuItemUsuarioAdd().addActionListener(ev -> {
                        if (!anadirUsuarioView.isVisible()) {
                            anadirUsuarioView.setVisible(true);
                            menu.getMiDesktopPane().add(anadirUsuarioView);
                        }
                    });

                    menu.getMenuItemUsuarioListar().addActionListener(ev -> {
                        if (!listarUsuarioView.isVisible()) {
                            listarUsuarioView.setVisible(true);
                            menu.getMiDesktopPane().add(listarUsuarioView);
                        }
                    });

                    menu.getMenuItemUsuarioActualizar().addActionListener(ev -> {
                        if (!usuarioActualizarView.isVisible()) {
                            usuarioActualizarView.setVisible(true);
                            menu.getMiDesktopPane().add(usuarioActualizarView);
                        }
                    });

                    menu.getMenuItemCerrarSesion().addActionListener(ev -> {
                        menu.setVisible(false);
                        usuarioController.logout();
                    });

                    // Cambiar idioma dinámicamente
                    menu.getMenuItemEspanol().addActionListener(ev -> cambiarIdiomaGeneral(mensajeHandler, "es", "EC",
                            menu, productoAnadirView, productoDeleteView, usuarioActualizarView, productoListaView,
                            listarUsuarioView, carritoLista, carritoAnadirView, anadirUsuarioView, actualizar));

                    menu.getMenuItemIngles().addActionListener(ev -> cambiarIdiomaGeneral(mensajeHandler, "en", "US",
                            menu, productoAnadirView, productoDeleteView, usuarioActualizarView, productoListaView,
                            listarUsuarioView, carritoLista, carritoAnadirView, anadirUsuarioView, actualizar));

                    menu.getMenuItemFrances().addActionListener(ev -> cambiarIdiomaGeneral(mensajeHandler, "fr", "FR",
                            menu, productoAnadirView, productoDeleteView, usuarioActualizarView, productoListaView,
                            listarUsuarioView, carritoLista, carritoAnadirView, anadirUsuarioView, actualizar));

                    menu.getMenuItemItaliano().addActionListener(ev -> cambiarIdiomaGeneral(mensajeHandler, "it", "IT",
                            menu, productoAnadirView, productoDeleteView, usuarioActualizarView, productoListaView,
                            listarUsuarioView, carritoLista, carritoAnadirView, anadirUsuarioView, actualizar));

                    menu.setVisible(true);
                }
            }
        });
    }

    private static void cambiarIdiomaGeneral(MensajeInternacionalizacionHandler mensajeHandler, String lang, String country,
                                             Menu menu, ProductoAnadirView productoAnadirView, ProductoDeleteView productoDeleteView,
                                             UsuarioActualizarView usuarioActualizarView, ProductoListaView productoListaView,
                                             ListarUsuarioView listarUsuarioView, CarritoLista carritoLista,
                                             CarritoAnadirView carritoAnadirView, AnadirUsuarioView anadirUsuarioView,
                                             Actualizar actualizar) {
        mensajeHandler.setLenguaje(lang, country);
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
    }
}
