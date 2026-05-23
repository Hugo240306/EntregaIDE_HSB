package main;

import modelo.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("GESTIÓN DE PEDIDOS");

        Cliente cliente1 = new Cliente("Hugo Suárez", "hugo@email.com", "600111222");
        Cliente cliente2 = new Cliente("Lucas Mouris", "lucas@email.com", "600333444");

        Repartidor rep1 = new Repartidor("Xurxo Rivera", "xurxo@email.com", "600555666", "Centro");
        Repartidor rep2 = new Repartidor("Patricia Barreiro", "patricia@email.com", "600777888", "Norte");

        List<Repartidor> repartidores = new ArrayList<>();
        repartidores.add(rep1);
        repartidores.add(rep2);

        Producto puchero = new Producto("Puchero", 9.50, CategoriaProducto.COMIDA);
        Producto nestea  = new Producto("Nestea Maracuyá", 2.70, CategoriaProducto.BEBIDA);
        Producto tarta   = new Producto("Tarta de queso", 4.50, CategoriaProducto.POSTRE);
        Producto pulpo   = new Producto("Pulpo a la gallega", 8.00, CategoriaProducto.COMIDA);

        System.out.println("PEDIDO 1");
        Pedido pedido1 = cliente1.crearPedido();
        pedido1.agregarProducto(puchero);
        pedido1.agregarProducto(nestea);
        pedido1.agregarProducto(puchero);  // aqui cree el duplicado para que lo rechaze 
        pedido1.agregarProducto(tarta);
        System.out.println("Total pedido 1: " + String.format("%.2f", pedido1.calcularTotal()) + "€");
        System.out.println(pedido1);

        System.out.println("ASIGNACIÓN DE REPARTIDOR");
        Repartidor asignado1 = buscarRepartidorDisponible(repartidores);
        if (asignado1 != null) {
            pedido1.asignarRepartidor(asignado1);
        } else {
            System.out.println("No hay repartidores disponibles para el pedido 1");
        }
        System.out.println("¿Está Repartidor1 disponible? (true/false) " + rep1.isDisponible());

        System.out.println("PEDIDO 2");
        Pedido pedido2 = cliente2.crearPedido();
        pedido2.agregarProducto(pulpo);
        Repartidor asignado2 = buscarRepartidorDisponible(repartidores);
        if (asignado2 != null) {
            pedido2.asignarRepartidor(asignado2);
        } else {
            System.out.println("No hay repartidores disponibles para el pedido 2");
        }

        System.out.println("INTENTAR ENTREGAR PEDIDO SIN ESTAR EN REPARTO");
        Pedido pedido3 = cliente1.crearPedido();
        pedido3.entregar();  // aqui fallará ya que está pendiente

        System.out.println("INTENTAR PEDIDO SIN PRODUCTOS");
        Pedido pedido4 = cliente2.crearPedido();
        Repartidor asignado4 = buscarRepartidorDisponible(repartidores);
        if (asignado4 != null) {
            pedido4.asignarRepartidor(asignado4);  // aqui este pedido fallará porque no tiene productos
        } else {
            System.out.println("No hay repartidores disponibles para el pedido 4");
        }

        System.out.println("ENTREGAR PEDIDO 1");
        pedido1.entregar();
        System.out.println("¿Repartidor 1 disponible? " + rep1.isDisponible());

        System.out.println("ESTADO FINAL");
        System.out.println(pedido1);
        System.out.println(pedido2);
        System.out.println(cliente1);
        System.out.println(cliente2);
        System.out.println(rep1);
        System.out.println(rep2);
    }

    public static Repartidor buscarRepartidorDisponible(List<Repartidor> repartidores) {
        for (Repartidor r : repartidores) {
            if (r.isDisponible()) {
                return r;
            }
        }
        return null;
    }
}