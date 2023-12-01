/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control.logica;

/**
 *
 * @author amirz
 */
public class EstadoVentanaBusquedaEliminacion implements ObservadorControlador {

    private int listaColumna;
    private int listaBusqueda;
    
    public EstadoVentanaBusquedaEliminacion() {
        listaBusqueda = -1;
        listaColumna = -1;
    }
    
    @Override
    public void notificarCambio(String entidadCambiar, String valor) {
        if("Busqueda".equals(entidadCambiar))   { seleccionBusqueda(valor); }
        if("Columna".equals(entidadCambiar))    { seleccionColumna(valor); }
    }
    
    @Override
    public int obtenerEstado(String estadoActual) {
        return switch(estadoActual) {
            case "Busqueda" -> getListaBusqueda();
            case "Columna" -> getListaColumna();
            default -> -1;
        };
    }
    
    private void seleccionBusqueda(String valor) {
        switch(valor) {
            case "Secuencial" -> setListaBusqueda(1);
            case "Binaria" -> setListaBusqueda(2);
            case "Transformación por claves" -> setListaBusqueda(3);
            default -> setListaBusqueda(-1);
        }
    }
    
    private void seleccionColumna(String valor) {
        switch(valor) {
            case "ID" -> setListaColumna(1);
            case "Nombre" -> setListaColumna(2);
            case "Descripción" -> setListaColumna(3);
            case "Precio" -> setListaColumna(4);
            default -> setListaColumna(-1);
        }
    }

    public void setListaColumna(int listaColumna) {
        this.listaColumna = listaColumna;
    }

    public void setListaBusqueda(int listaBusqueda) {
        this.listaBusqueda = listaBusqueda;
    }

    public int getListaColumna() {
        return listaColumna;
    }

    public int getListaBusqueda() {
        return listaBusqueda;
    }
}
