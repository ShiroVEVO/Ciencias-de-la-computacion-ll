/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control.estrategias;

import modelo.Producto;
import java.util.ArrayList;

/**
 *
 * @author amirz
 */
public class BusquedaTransformacionClaves implements Busqueda {

    @Override
    public ArrayList<Producto> buscar(ArrayList<Producto> listaProductos, int opcion, String atributo) {
        ArrayList<Producto> productosEncontrados = new ArrayList<>();
        Producto productoSeleccionado;
        boolean productoExistente = true;
        
        int indice = hashAtributo(atributo, opcion, listaProductos.size());
        int inicio = indice;
        
        do {
            productoSeleccionado = listaProductos.get(indice);
            if(indice == (listaProductos.size() - 1)) { indice = 0; }
            else                                      { indice = indice + 1; }
            if(inicio == indice)                      { productoExistente = false; break; }
        } while(!comparar(productoSeleccionado, opcion, atributo));
       
        if(productoExistente) { productosEncontrados.add(productoSeleccionado); }
        
        return productosEncontrados;
    }
    
    private int hashAtributo(String atributo, int opcion, int magnitud) {
        return switch (opcion) {
            case 1, 4 -> Integer.parseInt(atributo) % magnitud;
            case 2 -> Math.abs(atributo.hashCode()) % magnitud;
            default -> -1;
        };
    }
    
    private boolean comparar(Producto productoSeleccionado, int opcion, String atributo) {
        return switch (opcion) {
            case 1 -> productoSeleccionado.getId() == Integer.parseInt(atributo);
            case 2 -> productoSeleccionado.getNombre().equals((String)atributo);
            case 4 -> productoSeleccionado.getPrecio() == Integer.parseInt(atributo);
            default -> false;
        };
    }
}
