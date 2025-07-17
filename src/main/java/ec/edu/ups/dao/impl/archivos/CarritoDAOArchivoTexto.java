package ec.edu.ups.dao.impl.archivos;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CarritoDAOArchivoTexto implements CarritoDAO {

    private final String archivo = "carritos.txt";

    @Override
    public void crear(Carrito carrito) {
        List<Carrito> carritos = listarTodos();
        carritos.add(carrito);
        guardarCarritos(carritos);
    }

    @Override
    public Carrito buscarPorCodigo(int codigo) {
        for (Carrito c : listarTodos()) {
            if (c.getCodigo() == codigo) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void actualizar(Carrito carrito) {
        List<Carrito> carritos = listarTodos();
        for (int i = 0; i < carritos.size(); i++) {
            if (carritos.get(i).getCodigo() == carrito.getCodigo()) {
                carritos.set(i, carrito);
                break;
            }
        }
        guardarCarritos(carritos);
    }

    @Override
    public void eliminar(int codigo) {
        List<Carrito> carritos = listarTodos();
        carritos.removeIf(c -> c.getCodigo() == codigo);
        guardarCarritos(carritos);
    }

    @Override
    public List<Carrito> listarTodos() {
        List<Carrito> carritos = new ArrayList<>();
        File file = new File(archivo);
        if (!file.exists()) {
            return carritos;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                int codigo = Integer.parseInt(partes[0]);
                String username = partes[1];
                Usuario usuario = new Usuario();
                usuario.setUsername(username);

                Carrito carrito = new Carrito();
                carrito.setCodigo(codigo);
                carrito.setUsuario(usuario);

                for (int i = 2; i < partes.length; i++) {
                    String[] itemPartes = partes[i].split(",");
                    int codProducto = Integer.parseInt(itemPartes[0]);
                    String nombre = itemPartes[1];
                    double precio = Double.parseDouble(itemPartes[2]);
                    int cantidad = Integer.parseInt(itemPartes[3]);

                    Producto producto = new Producto();
                    producto.setCodigo(codProducto);
                    producto.setNombre(nombre);
                    producto.setPrecio(precio);

                    ItemCarrito item = new ItemCarrito(producto, cantidad);
                    carrito.getItems().add(item);
                }
                carritos.add(carrito);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return carritos;
    }

    private void guardarCarritos(List<Carrito> carritos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (Carrito carrito : carritos) {
                StringBuilder sb = new StringBuilder();
                sb.append(carrito.getCodigo()).append(";")
                        .append(carrito.getUsuario() != null ? carrito.getUsuario().getUsername() : "");

                for (ItemCarrito item : carrito.getItems()) {
                    Producto p = item.getProducto();
                    sb.append(";")
                            .append(p.getCodigo()).append(",")
                            .append(p.getNombre()).append(",")
                            .append(p.getPrecio()).append(",")
                            .append(item.getCantidad());
                }
                writer.write(sb.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
