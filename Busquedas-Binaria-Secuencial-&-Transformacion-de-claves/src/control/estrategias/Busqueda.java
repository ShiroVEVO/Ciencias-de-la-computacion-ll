/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package control.estrategias;

import java.util.ArrayList;
import modelo.Producto;
/**
 *
 * @author amirz
 */
public interface Busqueda {
    public ArrayList<Producto> buscar(ArrayList<Producto> listaProductos, int opcion, String atributo);
}
