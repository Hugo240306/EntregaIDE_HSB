package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int id;
    private LocalDate fechaPedido;
    private EstadoPedido estado;
    private Cliente cliente;
    private Repartidor repartidor;
    private List<Producto> productos;

    public Pedido(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.fechaPedido = LocalDate.now();
        this.estado = EstadoPedido.PENDIENTE;
        this.repartidor = null;
        this.productos = new ArrayList<>();
    }

    public boolean agregarProducto(Producto producto) {
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(producto.getNombre())) {
                System.out.println("El producto '" + producto.getNombre() + "' ya está en el pedido.");
                return false;
            }
        }
        productos.add(producto);
        System.out.println("Producto añadido: " + producto.getNombre());
        return true;
    }

    public double calcularTotal() {
        double total = 0;
        for (Producto p : productos) {
            total += p.getPrecio();
        }
        return total;
    }

    public boolean asignarRepartidor(Repartidor repartidor) {
        if (productos.isEmpty()) {
            System.out.println("No se puede asignar repartidor: el pedido no tiene productos");
            return false;
        }
        if (!repartidor.isDisponible()) {
            System.out.println("El repartidor " + repartidor.getNombre() + " no está disponible");
            return false;
        }
        this.repartidor = repartidor;
        this.estado = EstadoPedido.EN_REPARTO;
        repartidor.setDisponible(false);
        repartidor.agregarPedido(this);
        System.out.println("Pedido " + id + " asignado a " + repartidor.getNombre() + " Estado: EN REPARTO");
        return true;
    }

    // Entregar pedido
    public boolean entregar() {
        if (estado != EstadoPedido.EN_REPARTO) {
            System.out.println("No se puede entregar: el pedido no está en reparto (estado actual: " + estado + ")");
            return false;
        }
        this.estado = EstadoPedido.ENTREGADO;
        if (repartidor != null) {
            repartidor.setDisponible(true);
            System.out.println("Repartidor " + repartidor.getNombre() + " vuelve a estar disponible");
        }
        System.out.println("Pedido " + id + " entregado correctamente");
        return true;
    }

    // Cancelar pedido
    public void cancelar() {
        this.estado = EstadoPedido.CANCELADO;
        if (repartidor != null && estado == EstadoPedido.EN_REPARTO) {
            repartidor.setDisponible(true);
        }
        System.out.println("Pedido " + id + " cancelado");
    }

    public int getId() { return id; }
    public LocalDate getFechaPedido() { return fechaPedido; }
    public EstadoPedido getEstado() { return estado; }
    public Cliente getCliente() { return cliente; }
    public Repartidor getRepartidor() { return repartidor; }
    public List<Producto> getProductos() { return productos; }

    @Override
    public String toString() {
        return "Pedido #" + id +
               " | Fecha: " + fechaPedido +
               " | Estado: " + estado +
               " | Cliente: " + cliente.getNombre() +
               " | Repartidor: " + (repartidor != null ? repartidor.getNombre() : "Sin asignar") +
               " | Total: " + String.format("%.2f", calcularTotal()) + "€";
    }
}
