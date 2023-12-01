/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import control.estrategias.Busqueda;
import java.util.ArrayList;
import control.estrategias.Ordenamiento;

/**
 *
 * @author amirz
 */
public class TiendaProductos {
    private ArrayList<Producto> productosAplicacion;
    private Busqueda estrategiaBusqueda;
    private Ordenamiento estrategiaOrdenamiento;
    
    public TiendaProductos() {
        this.productosAplicacion = new ArrayList<>();
        this.estrategiaBusqueda = null;
        this.estrategiaOrdenamiento = null;
    }
    
    public void ordenarProductos(int opcion) {
        setProductosAplicacion(estrategiaOrdenamiento.ordenar(productosAplicacion, opcion));
    }

    public ArrayList<Producto> buscarProductos(int opcion, String atributo) {
        return estrategiaBusqueda.buscar(
                productosAplicacion,
                opcion,
                atributo
        );
    }
    
    public void setProductosAplicacion(ArrayList<Producto> productosAplicacion) {
        this.productosAplicacion = productosAplicacion;
    }

    public void setEstrategiaBusqueda(Busqueda estrategiaBusqueda) {
        this.estrategiaBusqueda = estrategiaBusqueda;
    }

    public void setEstrategiaOrdenamiento(Ordenamiento estrategiaOrdenamiento) {
        this.estrategiaOrdenamiento = estrategiaOrdenamiento;
    }

    public ArrayList<Producto> getProductosAplicacion() {
        return productosAplicacion;
    }
    
    public void insertarProducto(Producto producto) {
        productosAplicacion.add(producto);
    }
    
    public void eliminarProducto(String clave) {
        productosAplicacion.removeAll(estrategiaBusqueda.buscar(
                productosAplicacion,
                1,
                clave
        ));
    }
    
    public void eliminarProductos(int opcion, String atributo) {
        productosAplicacion.removeAll(estrategiaBusqueda.buscar(
                productosAplicacion,
                opcion,
                atributo
        ));
    }
}
