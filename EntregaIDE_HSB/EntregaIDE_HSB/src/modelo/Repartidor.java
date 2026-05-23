package modelo;

import java.util.ArrayList;
import java.util.List;

public class Repartidor extends Usuario {

    private String zonaReparto;
    private boolean disponible;
    private List<Pedido> pedidos;

    public Repartidor(String nombre, String email, String telefono, String zonaReparto) {
        super(nombre, email, telefono);
        this.zonaReparto = zonaReparto;
        this.disponible = true;
        this.pedidos = new ArrayList<>();
    }

    public String getZonaReparto() {
        return zonaReparto;
    }

    public void setZonaReparto(String zonaReparto) {
        this.zonaReparto = zonaReparto;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    @Override
    public String toString() {
        return super.toString() + " | Zona: " + zonaReparto + " | Disponible: " + disponible;
    }
}
