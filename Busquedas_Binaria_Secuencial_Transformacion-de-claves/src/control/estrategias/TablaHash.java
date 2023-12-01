/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control.estrategias;

import java.util.ArrayList;
import java.util.Arrays;
import modelo.Producto;

/**
 *
 * @author amirz
 */
public class TablaHash implements Ordenamiento {

    @Override
    public ArrayList<Producto> ordenar(ArrayList<Producto> listaProductos, int opcion) {
        Producto[] nuevoArray = new Producto[listaProductos.size()];
        Producto productoSeleccionado;
        int formula;
        
        for(int i = 0; i < listaProductos.size(); i++) {
            productoSeleccionado = listaProductos.get(i);
            formula = hashProducto(productoSeleccionado, opcion, listaProductos.size());
            
            while(nuevoArray[formula] != null) {
                if(formula == (listaProductos.size() - 1)) { formula = 0; }
                else                                       { formula = formula + 1; }
            }
            nuevoArray[formula] = productoSeleccionado;
        }
        
        return new ArrayList<>(Arrays.asList(nuevoArray));
    }
    
    private int hashProducto(Producto productoSeleccionado, int columnaHashear, int magnitud) {
        return switch (columnaHashear) {
            case 1 -> productoSeleccionado.getId() % magnitud;
            case 2 -> Math.abs(productoSeleccionado.getNombre().hashCode()) % magnitud;
            case 4 -> productoSeleccionado.getPrecio() % magnitud;
            default -> -1;
        };
    }
}
