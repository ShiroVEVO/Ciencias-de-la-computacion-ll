/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package control.logica;

/**
 *
 * @author amirz
 */
public interface ObservadorControlador {
    public void notificarCambio(String accion, String valor);
    public int obtenerEstado(String accion);
}
