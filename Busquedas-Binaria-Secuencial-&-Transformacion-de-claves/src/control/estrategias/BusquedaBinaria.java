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
public class BusquedaBinaria implements Busqueda {

    @Override
    public ArrayList<Producto> buscar(ArrayList<Producto> listaProductos, int opcion, String atributo) {
        ArrayList<Producto> productosEncontrados = new ArrayList<>();
        Producto productoSeleccionado = null;
        
        int izquierda = 0;
        int derecha = listaProductos.size()-1;
        int mitad;
        
        while(izquierda <= derecha) {
            mitad = ((izquierda + derecha) / 2);
            productoSeleccionado = listaProductos.get(mitad);
            
            if(compararMenor(productoSeleccionado, opcion, atributo))       { izquierda = mitad + 1; }
            else if(compararMayor(productoSeleccionado, opcion, atributo))  { derecha = mitad - 1; }
            
            else { 
                productosEncontrados.add(productoSeleccionado);
                return productosEncontrados;
            }
        }
        
        return productosEncontrados;
    }
    
    private boolean compararMenor(Producto productoSeleccionado, int opcion, String atributo) {
        return switch (opcion) {
            case 1 -> productoSeleccionado.getId() < Integer.parseInt(atributo);
            case 2 -> (productoSeleccionado.getNombre().compareTo((String)atributo)) < 0;
            case 4 -> productoSeleccionado.getPrecio() < Integer.parseInt(atributo);
            default -> false;
        };
    }
    
    private boolean compararMayor(Producto productoSeleccionado, int opcion, String atributo) {
        return switch (opcion) {
            case 1 -> productoSeleccionado.getId() > Integer.parseInt(atributo);
            case 2 -> (productoSeleccionado.getNombre().compareTo((String)atributo)) > 0;
            case 4 -> productoSeleccionado.getPrecio() > Integer.parseInt(atributo);
            default -> false;
        };
    }
}
