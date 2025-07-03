package ec.edu.ups.modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

public class Carrito {
    private static int contador=0;
    private int codigo;
    private GregorianCalendar fechaCreacion;
    private List<ItemCarrito> items;
    private final double IVA = 0.12;
    private Usuario usuario;

    public Carrito(){
        this.codigo = contador++;
        this.items = new ArrayList<>();
        this.fechaCreacion = new GregorianCalendar();
        this.usuario = new Usuario();
    }

    public Carrito( GregorianCalendar fechadeCreacion, List<ItemCarrito> items, Usuario usuario) {
        this.codigo = contador++;
        this.fechaCreacion = fechadeCreacion;
        this.items = items;
        this.usuario = usuario;
    }
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public GregorianCalendar getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(GregorianCalendar fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void agregarProducto(Producto producto, int cantidad) {
        items.add(new ItemCarrito(producto, cantidad));
    }

    public void eliminarProducto(){
        items.clear();
    }
    public String getFechaFormateada() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(fechaCreacion.getTime());
    }
    public void agregarFecha(){
        this.fechaCreacion = new GregorianCalendar();
    }
    public void agregarUsuario(){
        this.usuario.getUsername();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void eliminarProducto(int codigoProducto) {
        Iterator<ItemCarrito> it = items.iterator();
        while (it.hasNext()) {
            if (it.next().getProducto().getCodigo() == codigoProducto) {
                it.remove();
                break;
            }
        }
    }

    public void vaciarCarrito() {
        items.clear();
    }

    public double calcularTotal() {
        return calcularSubtotal() + calcularIVA();
    }

    public List<ItemCarrito> obtenerItems() {
        return items;
    }
    public double calcularSubtotal() {
        double subtotal = 0;
        for (ItemCarrito item : items) {
            subtotal += item.getProducto().getPrecio() * item.getCantidad();
        }
        return subtotal;
    }

    public double calcularIVA() {
        double subtotal = calcularSubtotal();
        return subtotal * IVA;
    }



    public boolean estaVacio() {
        return items.isEmpty();
    }

    @Override
    public String toString() {

        java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada = formato.format(fechaCreacion.getTime());


        return "Carrito{" +
                "IVA=" + IVA +
                ", codigo=" + codigo +
                ", fechaCreacion=" + fechaFormateada +
                ", items=" + items +
                '}';
    }

}
