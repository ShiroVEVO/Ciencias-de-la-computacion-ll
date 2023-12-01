/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package launcher;

import control.logica.Controlador;
import modelo.TiendaProductos;
import vista.Vista;

/**
 *
 * @author amirz
 */
public class Launcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TiendaProductos modelo = new TiendaProductos();
        Vista vista = new Vista();
        
        Controlador control = new Controlador(vista, modelo);
        control.iniciar();
    }
}
