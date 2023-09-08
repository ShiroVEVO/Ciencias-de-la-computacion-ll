/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control.logica;

/**
 *
 * @author amirz
 */
public class EstadoVentanaPrincipal implements ObservadorControlador {

    private int listaColumna;
    private int ordenamiento;
    
    public EstadoVentanaPrincipal() {
        listaColumna = -1;
        ordenamiento = -1;
    }
    
    @Override
    public void notificarCambio(String accion, String valor) {
        if("Columna".equals(accion))        { seleccionColumna(valor); }
        if("Ordenamiento".equals(accion))   { seleccionOrdenamiento(valor); }
    }

    @Override
    public int obtenerEstado(String accion) {
        return switch(accion) {
            case "Columna" -> getListaColumna();
            case "Ordenamiento" -> getOrdenamiento();
            default -> -1;
        };
    }
    
    private void seleccionColumna(String valor) {
        switch(valor) {
            case "ID" -> setListaColumna(1);
            case "Nombre" -> setListaColumna(2);
            case "Descripción" -> setListaColumna(3);
            case "Precio" -> setListaColumna(4);
            default -> setListaColumna(0);
        }
    }
    
    private void seleccionOrdenamiento(String valor) {
        switch(valor) {
            case "BurstSort" -> setOrdenamiento(1);
            case "Tabla Hash" -> setOrdenamiento(2);
            default -> setOrdenamiento(0);
        }
    }

    private int getListaColumna() {
        return listaColumna;
    }

    private void setListaColumna(int listaColumna) {
        this.listaColumna = listaColumna;
    }

    public int getOrdenamiento() {
        return ordenamiento;
    }

    public void setOrdenamiento(int ordenamiento) {
        this.ordenamiento = ordenamiento;
    }
}
