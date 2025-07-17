package ec.edu.ups.dao.impl.archivos;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOArchivoBin implements ProductoDAO {

    private final String ruta;

    private static final int NOMBRE_SIZE_CHARS = 25;
    private static final int RECORD_SIZE = 4 + (NOMBRE_SIZE_CHARS * 2) + 8 + 1;
    // int codigo(4) + nombre(25*2=50) + double precio(8) + boolean disponible(1) = 63 bytes

    public ProductoDAOArchivoBin(String ruta) {
        this.ruta = ruta;
        try {
            new RandomAccessFile(ruta, "rw").close(); // crea archivo si no existe
        } catch (IOException e) {
            System.err.println("Error al inicializar archivo productos: " + e.getMessage());
        }
    }

    @Override
    public void crear(Producto producto) {
        try (RandomAccessFile file = new RandomAccessFile(ruta, "rw")) {
            file.seek(file.length()); // ir al final para añadir
            file.writeInt(producto.getCodigo());
            writeFixedString(file, producto.getNombre(), NOMBRE_SIZE_CHARS);
            file.writeDouble(producto.getPrecio());
            file.writeBoolean(true); // disponible
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Producto buscarPorCodigo(int codigo) {
        try (RandomAccessFile file = new RandomAccessFile(ruta, "r")) {
            long numRecords = file.length() / RECORD_SIZE;
            for (int i = 0; i < numRecords; i++) {
                file.seek(i * RECORD_SIZE);
                int codigoActual = file.readInt();
                String nombre = readFixedString(file, NOMBRE_SIZE_CHARS);
                double precio = file.readDouble();
                boolean disponible = file.readBoolean();

                if (disponible && codigoActual == codigo) {
                    return new Producto(codigoActual, nombre, precio);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> encontrados = new ArrayList<>();
        try (RandomAccessFile file = new RandomAccessFile(ruta, "r")) {
            long numRecords = file.length() / RECORD_SIZE;
            for (int i = 0; i < numRecords; i++) {
                file.seek(i * RECORD_SIZE);
                int codigo = file.readInt();
                String nombreActual = readFixedString(file, NOMBRE_SIZE_CHARS);
                double precio = file.readDouble();
                boolean disponible = file.readBoolean();

                if (disponible && nombreActual.toLowerCase().startsWith(nombre.toLowerCase())) {
                    encontrados.add(new Producto(codigo, nombreActual, precio));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encontrados;
    }

    @Override
    public void actualizar(Producto producto) {
        try (RandomAccessFile file = new RandomAccessFile(ruta, "rw")) {
            long numRecords = file.length() / RECORD_SIZE;
            for (int i = 0; i < numRecords; i++) {
                file.seek(i * RECORD_SIZE);
                int codigoActual = file.readInt();
                if (codigoActual == producto.getCodigo()) {
                    file.seek(i * RECORD_SIZE);
                    file.writeInt(producto.getCodigo());
                    writeFixedString(file, producto.getNombre(), NOMBRE_SIZE_CHARS);
                    file.writeDouble(producto.getPrecio());
                    file.writeBoolean(true);
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int codigo) {
        try (RandomAccessFile file = new RandomAccessFile(ruta, "rw")) {
            long numRecords = file.length() / RECORD_SIZE;
            for (int i = 0; i < numRecords; i++) {
                file.seek(i * RECORD_SIZE);
                int codigoActual = file.readInt();
                if (codigoActual == codigo) {
                    // posición del boolean disponible
                    long posDisponible = i * RECORD_SIZE + RECORD_SIZE - 1;
                    file.seek(posDisponible);
                    file.writeBoolean(false); // marca como no disponible
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Producto> listarTodos() {
        List<Producto> productos = new ArrayList<>();
        try (RandomAccessFile file = new RandomAccessFile(ruta, "r")) {
            long numRecords = file.length() / RECORD_SIZE;
            for (int i = 0; i < numRecords; i++) {
                file.seek(i * RECORD_SIZE);
                int codigo = file.readInt();
                String nombre = readFixedString(file, NOMBRE_SIZE_CHARS);
                double precio = file.readDouble();
                boolean disponible = file.readBoolean();

                if (disponible) {
                    productos.add(new Producto(codigo, nombre, precio));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productos;
    }

    // Métodos auxiliares para strings de tamaño fijo
    private void writeFixedString(RandomAccessFile file, String s, int size) throws IOException {
        StringBuilder sb = new StringBuilder(s != null ? s : "");
        if (sb.length() > size) {
            sb.setLength(size);
        } else {
            while (sb.length() < size) {
                sb.append('\0');
            }
        }
        file.writeChars(sb.toString());
    }

    private String readFixedString(RandomAccessFile file, int size) throws IOException {
        char[] buffer = new char[size];
        for (int i = 0; i < size; i++) {
            buffer[i] = file.readChar();
        }
        int length = 0;
        while (length < size && buffer[length] != '\0') {
            length++;
        }
        return new String(buffer, 0, length);
    }
}
