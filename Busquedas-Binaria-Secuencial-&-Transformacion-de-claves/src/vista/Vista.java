/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import javax.swing.JOptionPane;

/**
 *
 * @author amirz
 */
public class Vista {
    public VentanaPrincipal ventanaInicio;
    public VentanaInsertar ventanaInsertar;
    public VentanaBusquedaEliminacion ventanaBusquedaEliminacion;
    
    public Vista() {
        this.ventanaInicio = new VentanaPrincipal();
        this.ventanaInsertar = new VentanaInsertar();
        this.ventanaBusquedaEliminacion = new VentanaBusquedaEliminacion();
    }    
    
    public void mensajeError(String textoPantalla) {
        JOptionPane.showMessageDialog(null, textoPantalla, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void mensajeInformacion(String textoPantalla) {
        JOptionPane.showMessageDialog(null, textoPantalla, "Error", JOptionPane.INFORMATION_MESSAGE);
    }
}
