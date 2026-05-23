package modelo;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {

    private List<Pedido> pedidos;
    private static int contadorId = 1;

    public Cliente(String nombre, String email, String telefono) {
        super(nombre, email, telefono);
        this.pedidos = new ArrayList<>();
    }


    
    public Pedido crearPedido() {
        Pedido pedido = new Pedido(contadorId++, this);
        pedidos.add(pedido);
        System.out.println("Pedido #" + pedido.getId() + " creado para " + getNombre());
        return pedido;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    @Override
    public String toString() {
        return super.toString() + " | Pedidos realizados: " + pedidos.size();
    }
}
