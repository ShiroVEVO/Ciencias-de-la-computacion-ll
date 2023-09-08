/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package control.estrategias;

import modelo.Producto;
import java.util.ArrayList;

/**
 *
 * @author amirz
 */
public interface Ordenamiento {
    public ArrayList<Producto> ordenar(ArrayList<Producto> listaProductos, int opcion);
}
