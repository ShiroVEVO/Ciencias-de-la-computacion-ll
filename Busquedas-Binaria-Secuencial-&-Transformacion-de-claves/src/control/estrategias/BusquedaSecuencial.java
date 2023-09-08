/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control.estrategias;

import control.estrategias.Busqueda;
import modelo.Producto;
import java.util.ArrayList;

/**
 *
 * @author amirz
 */
public class BusquedaSecuencial implements Busqueda {

    @Override
    public ArrayList<Producto> buscar(ArrayList<Producto> listaProductos, int opcion, String atributo) {
        ArrayList<Producto> productosEncontrados = new ArrayList<>();
        Producto productoSeleccionado;
        
        for(int i = 0; i < listaProductos.size(); i++) {
            productoSeleccionado = listaProductos.get(i);
            
            if(comparar(productoSeleccionado, opcion, atributo)) {
                productosEncontrados.add(productoSeleccionado);
            }
        }
        return productosEncontrados;
    }
    
    private boolean comparar(Producto productoSeleccionado, int opcion, String atributo) {
        return switch (opcion) {
            case 1 -> productoSeleccionado.getId() == Integer.parseInt(atributo);
            case 2 -> productoSeleccionado.getNombre().equals((String)atributo);
            case 3 -> productoSeleccionado.getDescripcion().contains((String) atributo);
            case 4 -> productoSeleccionado.getPrecio() == Integer.parseInt(atributo);
            default -> false;
        };
    }
}
