package modelo;

public class Producto {

    private String nombre;
    private double precio;
    private CategoriaProducto categoria;

    public Producto(String nombre, double precio, CategoriaProducto categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public CategoriaProducto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProducto categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return nombre + " (" + categoria + ") - " + String.format("%.2f", precio) + "€";
    }
}
